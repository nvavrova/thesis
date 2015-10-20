package model;

import ast.DefaultVisitor;
import ast.expression.ExprList;
import ast.expression.nocond.atom.Identifier;
import ast.expression.nocond.atom.trailed.AttributeRef;
import ast.expression.nocond.atom.trailed.Call;
import ast.expression.nocond.atom.trailed.DirectCall;
import ast.path.Path;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Assign;
import ast.statement.simple.Global;
import ast.statement.simple.ImportFrom;
import ast.statement.simple.ImportPaths;
import util.StringHelper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 17-05-2015
 */
public class ModelBuilder {

	private static final String SELF_KEYWORD = "self";

	private final Project project;

	public ModelBuilder(Project oldProject, Map<String, ast.Module> oldTrees, Map<String, ast.Module> trees) {
		this.project = new Project(oldProject.getFolder());

		//build new modules
		BuildingVisitor buildingVisitor = new BuildingVisitor(this.project);
		buildingVisitor.build(trees.values());

		//add relevant modules from previous version
		Set<Module> oldModules = oldProject.getModules().stream()
				.filter(m -> oldTrees.containsKey(m.getFilePath()) && !this.project.hasModule(m.getFilePath()))
				.map(m -> oldProject.getModule(m.getFilePath()))
				.collect(Collectors.toSet());
		oldModules.forEach(this.project::addModule);

		//link
		LinkingVisitor linkingVisitor = new LinkingVisitor(this.project);
		linkingVisitor.link(trees.values());
	}

	public ModelBuilder(File projectFolder, Collection<ast.Module> trees) {
		this.project = new Project(projectFolder);
		BuildingVisitor buildingVisitor = new BuildingVisitor(this.project);
		buildingVisitor.build(trees);
		LinkingVisitor linkingVisitor = new LinkingVisitor(this.project);
		linkingVisitor.link(trees);
	}

	public Project getProject() {
		return this.project;
	}

	private class LinkingVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Project project;
		private Linker linker;

		public LinkingVisitor(Project project) {
			super();
			this.project = project;
		}

		public void link(Collection<ast.Module> trees) {
			this.linker = new Linker(this.project);
			for (ast.Module m : trees) {
				this.currentFilePath = m.getFilePath();
				m.accept(this);
			}
			this.linker.link();
		}

		@Override
		public Void visit(ImportPaths n) {
			for (Path p : n.getPaths()) {
				this.linker.addImport(this.currentFilePath, p.getPath(), this.getAlias(p));
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			for (Path p : n.getPaths()) {
				this.linker.addImport(this.currentFilePath, n.getModule().getPath(), p.getPath(), this.getAlias(p));
			}
			this.visitChildren(n);
			return null;
		}

		private String getAlias(Path p) {
			return p.hasAlias() ? p.getAlias().getValue() : p.getPath();
		}
	}


	private class BuildingVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Project project;
		private final Stack<ContentContainer> contentContainers;

		private final Stack<model.Class> classes;
		private final Stack<Method> methods;
		private boolean inAssign;

		private boolean inAssignLeft;
		private boolean inAssignRight;
		private String leftAssign;
		private String rightAssign;

		public BuildingVisitor(Project project) {
			super();
			this.project = project;
			this.contentContainers = new Stack<>();

			this.classes = new Stack<>();
			this.methods = new Stack<>();
			this.inAssign = false;
			this.inAssignLeft = false;
			this.inAssignRight = false;
		}

		public void build(Collection<ast.Module> trees) {
			for (ast.Module m : trees) {
				this.currentFilePath = m.getFilePath();
				m.accept(this);
			}
		}

		@Override
		public void visitChildren(ast.Module n) {
			String filePath = n.getFilePath();
			model.Module module = new model.Module(filePath, n.getName(), StringHelper.implode(n.getErrors(), "\n"));

			this.project.addModule(module);

			this.contentContainers.push(module);
			super.visitChildren(n);
			this.contentContainers.pop();
		}

		@Override
		public Void visit(Global n) {
			n.getIdentifiers().forEach(i -> this.getCurrentContainer().addGlobalDefinition(i.getValue()));
			return null;
		}

		@Override
		public void visitChildren(Assign n) {
			this.inAssign = true;
			super.visitChildren(n);
			for (int i = 0; i < (n.getExprElements().size() - 1); i++) {
				this.inAssignLeft = true;
				ExprList left = n.getExprElements().get(i);
				left.accept(this);
				this.inAssignLeft = false;

				this.inAssignRight = true;
				ExprList right = n.getExprElements().get(i);
				right.accept(this);
				this.inAssignRight = false;

				//TODO: add the assign values into vars at some point (probably at Identifier visit?)
				if (this.leftAssign != null && this.rightAssign != null) {
					this.getCurrentContainer().addAssign(leftAssign, rightAssign);
				}

				this.leftAssign = null;
				this.rightAssign = null;
			}
			this.inAssign = false;
		}

		@Override
		public Void visit(ClassDef n) {
			Integer locInfo = n.getLocInfo();

			//accounts for the classes within classes
			String prevClsNames = this.getPrevClassName();
			String name = prevClsNames + n.getName().getValue();

			List<String> parents = n.getInheritance().stream()
					.map(p -> p.getValue().toString())
					.collect(Collectors.toList());

			model.Module currentModule = this.getCurrentModule();
			Class c = new Class(name, currentModule, locInfo, parents);
			currentModule.addClass(c);

			this.classes.push(c);
			this.contentContainers.push(c);
			this.visitChildren(n);
			this.contentContainers.pop();
			this.classes.pop();

			return null;
		}

		private String getPrevClassName() {
			if (this.classes.size() == 0) {
				return "";
			}
			return this.classes.lastElement().getName() + ".";
		}

		@Override
		public Void visit(Function n) {
			if (this.inClass()) {
				List<String> paramNames = n.getParams().getParamNames().stream()
						.filter(p -> !p.equals(SELF_KEYWORD))
						.collect(Collectors.toList());

				Class cls = this.getCurrentClass();
				Method method = new Method(n.getNameString(), cls, n.getLocInfo(), paramNames, n.isAccessor());
				cls.addMethodDefinition(method);

				this.methods.push(method);
				this.contentContainers.push(method);
				this.visitChildren(n);
				this.contentContainers.pop();
				this.methods.pop();

				return null;
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public void visitChildren(Function n) {
			n.getBody().accept(this);
			if (n.hasReturnType()) {
				n.getReturnType().accept(this);
			}
			//DON'T visit the name, otherwise it gets added as a variable
			n.getParams().accept(this);
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public Void visit(Identifier n) {
			this.addVar(n.getValue());
			return null;
		}

		@Override
		public Void visit(AttributeRef n) {
			this.addVar(n.toString());
			this.visitChildren(n);
			return null;
		}

		private void addVar(String varFullName) {
			String varBaseName = this.getVarBaseName(varFullName);
			if (varBaseName != null) {
				if (this.inClassMethod()) {
					this.getCurrentClass().addVariableDefinition(varBaseName);
					this.getCurrentMethod().addInstanceVarUse(varBaseName);
				}
				else if (this.inAssign) {
					this.getCurrentClass().addVariableDefinition(SELF_KEYWORD + "." + varBaseName);
				}
			}
			if (!this.inClass()) {
				this.getCurrentModule().addVariableDefinition(varFullName);
			}
			this.addVarRef(varFullName);

		}

		private void addVarRef(String name) {
			if (this.inClass()) {
				List<String> refParts = StringHelper.explode(name, ".");
				for (int i = 1; i <= refParts.size(); i++) {
					this.addVarRef(refParts.subList(0, i));
				}
			}
		}

		private void addVarRef(List<String> refParts) {
			String varName = StringHelper.implode(refParts, ".");
			this.getCurrentContainer().addVariableReference(varName);
			if (this.inClassMethod()) {
				this.getCurrentMethod().addPotentialNonInstanceVarUse(varName);
			}
		}

		private String getVarBaseName(String varFullName) {
			List<String> nameParts = StringHelper.explode(varFullName, ".");
			if (this.inClassMethod() && nameParts.size() > 1 && this.isClassVar(varFullName)) {
				return nameParts.get(0) + "." + nameParts.get(1);
			}
			if (this.inClass() && !this.inClassMethod()) {
				return nameParts.get(0);
			}
			return null;
		}

		private boolean isClassVar(String varName) {
			return varName.startsWith(this.getCurrentClass().getName() + ".") || varName.startsWith(SELF_KEYWORD + ".");
		}

		@Override
		public Void visit(Call n) {
			this.addMethodRef(n.getBase().toString());
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DirectCall n) {
			this.addMethodRef(n.getBase().toString() + "." + n.getCall().getName());
			this.visitChildren(n);
			return null;
		}

		private void addMethodRef(String name) {
			this.getCurrentContainer().addMethodCall(name);
		}

		private model.Module getCurrentModule() {
			if (this.currentFilePath == null || !this.project.hasModule(this.currentFilePath)) {
				return null;
			}
			return this.project.getModule(this.currentFilePath);
		}

		private Class getCurrentClass() {
			return this.classes.peek();
		}

		private Method getCurrentMethod() {
			return this.methods.peek();
		}

		private boolean inClass() {
			return !this.classes.isEmpty();
		}

		private boolean inClassMethod() {
			return this.inClass() && !this.methods.isEmpty();
		}

		private ContentContainer getCurrentContainer() {
			return this.contentContainers.peek();
		}
	}
}
