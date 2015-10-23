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
				this.linker.addModuleImport(this.currentFilePath, p.getPath(), this.getAlias(p));
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			for (Path p : n.getPaths()) {
				this.linker.addImportFrom(this.currentFilePath, n.getModule().getPath(), p.getPath(), this.getAlias(p));
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
		//		private final Stack<Method> methods;
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
//			this.methods = new Stack<>();
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
				ExprList right = n.getExprElements().get(i + 1);
				right.accept(this);
				this.inAssignRight = false;

				if (this.leftAssign != null && this.rightAssign != null) {
					this.getCurrentContainer().addAssign(this.leftAssign, this.rightAssign);
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
//			String name = this.getPrevClassName() + n.getName().getValue();

			List<String> parents = n.getInheritance().stream()
					.map(p -> p.getValue().toString())
					.collect(Collectors.toList());

			Class c = new Class(n.getName().getValue(), this.getCurrentModule(), locInfo, parents);
			this.getCurrentContainer().addClassDefinition(c);

			this.classes.push(c);
			this.contentContainers.push(c);
			this.visitChildren(n);
			this.contentContainers.pop();
			this.classes.pop();

			return null;
		}

//		private String getPrevClassName() {
//			if (this.classes.size() == 0) {
//				return "";
//			}
//			return this.classes.lastElement().getName() + ".";
//		}

		@Override
		public Void visit(Function n) {
//			if (this.inClass()) {
			List<String> paramNames = n.getParams().getParamNames().stream()
					.filter(p -> !p.equals(SELF_KEYWORD))
					.collect(Collectors.toList());

			Method method = new Method(n.getNameString(), this.getCurrentModule(), n.getLocInfo(), paramNames, n.isAccessor());
			this.getCurrentContainer().addMethodDefinition(method);

//			this.methods.push(method);
			this.contentContainers.push(method);
			this.visitChildren(n);
			this.contentContainers.pop();
//			this.methods.pop();

			return null;
//			}
//			this.visitChildren(n);
//			return null;
		}

		@Override
		public void visitChildren(ClassDef n) {
			//prevents registering class names as variables
			n.getBody().accept(this);
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public void visitChildren(Function n) {
			//prevents registering function names as variables
			n.getBody().accept(this);
			if (n.hasReturnType()) {
				n.getReturnType().accept(this);
			}
			n.getParams().accept(this);
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public Void visit(Identifier n) {
			this.addVarDef(n.getValue());
			this.addVarRef(n.getValue());
			this.setAssignVar(n.getValue());
			return null;
		}

		@Override
		public Void visit(AttributeRef n) {
			this.addVarDef(n.toString());
			this.addVarRef(n.toString());
			this.setAssignVar(n.toString());
			this.visitChildren(n);
			return null;
		}


		private void addVarRef(String fullName) {
			this.getCurrentContainer().addVariableReference(fullName);
		}

		private void addVarDef(String fullName) {
			List<String> varParts = StringHelper.explode(fullName, ".");
			if (varParts.size() == 1) {
				//simple identifier
				this.getCurrentContainer().addVariableDefinition(fullName);
			}
			else if (varParts.size() > 1) {
				//attribute reference - add if it references a class or instance variable of the current class
				if (this.isClassOrInstanceVariable(varParts.get(0))) {
					this.getCurrentClass().addVariableDefinition(varParts.get(0) + "." + varParts.get(1));
				}
			}
		}

		private boolean isClassOrInstanceVariable(String prefix) {
			return prefix.equals("self") || (this.inClass() && prefix.equals(this.getCurrentClass().getName()));
		}

		@Override
		public Void visit(Call n) {
			this.addMethodRef(n.getBase().toString());
			this.setAssignVar(n.getBase().toString()); //TODO: fix this
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DirectCall n) {
			this.addMethodRef(n.getBase().toString() + "." + n.getCall().getName());
			this.setAssignVar(n.getBase().toString() + "." + n.getCall().getName()); //TODO: fix this
			this.visitChildren(n);
			return null;
		}

		private void setAssignCall(String value) {
			if (this.inAssign && this.inAssignLeft && this.leftAssign == null) {
				this.leftAssign = value;
			}
			if (this.inAssign && this.inAssignRight && this.rightAssign == null) {
				this.rightAssign = value;
			}
		}

		private void setAssignVar(String value) {
			if (this.inAssign && this.inAssignLeft && this.leftAssign == null) {
				this.leftAssign = value;
			}
			if (this.inAssign && this.inAssignRight && this.rightAssign == null) {
				this.rightAssign = value;
			}
		}

		@Override
		public void visitChildren(Call n) {
			//prevents registering function names as variables
			n.getArgs().accept(this);
		}

		@Override
		public void visitChildren(DirectCall n) {
			//prevents registering function names as variables
			n.getCall().accept(this);
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

		//
//		private Method getCurrentMethod() {
//			return this.methods.peek();
//		}
//
		private boolean inClass() {
			return !this.classes.isEmpty();
		}
//
//		private boolean inClassMethod() {
//			return this.inClass() && !this.methods.isEmpty();
//		}

		private ContentContainer getCurrentContainer() {
			return this.contentContainers.peek();
		}
	}
}
