package model;

import ast.DefaultVisitor;
import ast.expression.ExprList;
import ast.expression.nocond.atom.Identifier;
import ast.expression.nocond.atom.trailed.AttributeRef;
import ast.expression.nocond.atom.trailed.Call;
import ast.expression.nocond.atom.trailed.ObjectMethodCall;
import ast.path.Path;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Assign;
import ast.statement.simple.Global;
import ast.statement.simple.ImportFrom;
import ast.statement.simple.ImportPaths;
import util.LexicalHelper;
import util.StringHelper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 17-05-2015
 */
public class ModelBuilder {

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
		private final Stack<Subroutine> subroutines;
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
			this.subroutines = new Stack<>();
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
			model.Module module = new model.Module(n.getName(), n.getLocInfo(), filePath, StringHelper.implode(n.getErrors(), "\n"));

			this.project.addModule(module);

			this.contentContainers.push(module);
			super.visitChildren(n);
			this.contentContainers.pop();
		}

		@Override
		public Void visit(Global n) {
			for (Identifier i : n.getIdentifiers()) {
				this.addVarDef(i.getValue(), VarType.GLOBAL);
			}
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

				this.leftAssign = null;
				this.rightAssign = null;
			}
			this.inAssign = false;
		}

		@Override
		public Void visit(ClassDef n) {
			Integer locInfo = n.getLocInfo();

			ClassArgumentCollector argCollector = new ClassArgumentCollector();
			List<String> parents = n.getInheritance().stream()
					.map(p -> p.accept(argCollector))
					.collect(Collectors.toList());

			Class c = new Class(n.getName().getValue(), locInfo, this.getCurrentContainer(), parents);
			this.getCurrentContainer().addClassDefinition(c);

			this.classes.push(c);
			this.contentContainers.push(c);
			this.visitChildren(n);
			this.contentContainers.pop();
			this.classes.pop();

			return null;
		}

		@Override
		public Void visit(Function n) {
			List<String> paramNames = n.getParams().getParamNames().stream()
					.filter(p -> !p.equals(LexicalHelper.SELF_KEYWORD))
					.collect(Collectors.toList());
			SubroutineType type = !this.inClass() ? SubroutineType.FUNCTION :
					(n.isStatic() ? SubroutineType.STATIC_METHOD : SubroutineType.INSTANCE_METHOD);
			Subroutine subroutine = new Subroutine(n.getNameString(), n.getLocInfo(), this.getCurrentContainer(), type, paramNames, n.isAccessor());
			this.getCurrentContainer().addSubroutineDefinition(subroutine);

			this.subroutines.push(subroutine);
			this.contentContainers.push(subroutine);
			this.visitChildren(n);
			this.contentContainers.pop();
			this.subroutines.pop();

			return null;
		}

		@Override
		public void visitChildren(ClassDef n) {
			//prevents registering class names as variables
			n.getBody().accept(this);
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public void visitChildren(Function n) {
			//prevents registering function names and param names as variables
			n.getBody().accept(this);
			if (n.hasReturnType()) {
				n.getReturnType().accept(this);
			}
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public Void visit(Identifier n) {
			this.addVarDef(n.getValue());
			this.addVarRef(n.getValue());
			this.setAssignVar((String) n.getValue());
			return null;
		}

		@Override
		public Void visit(AttributeRef n) {
			this.addVarDef(n.toString());
			this.addVarRef(n.toString());
			this.setAssignVar((String) n.toString());
//			this.visitChildren(n);
			return null;
		}

		private void addVarRef(String fullName) {
			this.getCurrentContainer().addVariableReference(fullName);
		}

		private boolean hasClassVarName(String varName) {
			return varName.startsWith(this.getCurrentClass().getName() + ".");
		}

		private boolean hasInstanceVarName(String varName) {
			return varName.startsWith(LexicalHelper.SELF_KEYWORD + ".");
		}

		private void addVarDef(String fullName) {
			List<String> nameParts = StringHelper.explode(fullName, ".");
			if (this.inClass()) {
				if (this.hasClassVarName(fullName)) {
					this.addVarDef(nameParts.get(1), VarType.CLASS);
				}
				else if (this.hasInstanceVarName(fullName)) {
					this.addVarDef(nameParts.get(1), VarType.INSTANCE);
				}
				//if its name has more than 1 part, then it was just an attribute reference
				else if (!this.inSubroutine() && nameParts.size() == 1) {
					//variable defined inside of a class but outside of a method -> both class and instance variable
					this.addVarDef(nameParts.get(0), VarType.CLASS);
					this.addVarDef(nameParts.get(0), VarType.INSTANCE);
				}
			}
			else {
				this.addVarDef(nameParts.get(0), VarType.LOCAL);
			}
		}

		private void addVarDef(String name, VarType varType) {
			ContentContainer parent = varType == VarType.CLASS || varType == VarType.INSTANCE ? this.getCurrentClass() : this.getCurrentContainer();
			Variable var = new Variable(name, parent, varType);
			parent.addVariableDefinition(var);
		}

		@Override
		public Void visit(Call n) {
			this.addSubroutineRef(n.getBase().toString());
			this.setAssignCall(n.getBase().toString());
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ObjectMethodCall n) {
			this.addSubroutineRef(n.getBase().toString() + "." + n.getCall().getName());
			this.setAssignCall((n.getBase().toString() + "." + n.getCall().getName()));
			this.visitChildren(n);
			return null;
		}

		private void setAssignCall(String value) {
			this.setAssignVar(value, AssignType.CALL);
		}

		private void setAssignVar(String value) {
			this.setAssignVar(value, AssignType.VARIABLE);
		}

		private void setAssignVar(String value, AssignType type) {
			if (this.inAssign && this.inAssignLeft && this.leftAssign == null) {
				this.leftAssign = value;
			}
			if (this.inAssign && this.inAssignRight && this.rightAssign == null) {
				this.rightAssign = value;
				this.addAssign(type);
			}
		}

		private void addAssign(AssignType type) {
			if (this.leftAssign != null && this.rightAssign != null) {
				model.Assign assign = new model.Assign(this.leftAssign, this.rightAssign, type);
				this.getCurrentContainer().addAssign(assign);
			}
		}

		@Override
		public void visitChildren(Call n) {
			//prevents registering function names as variables
			n.getArgs().accept(this);
		}

		@Override
		public void visitChildren(ObjectMethodCall n) {
			//prevents registering function names as variables
			n.getCall().accept(this);
		}

		private void addSubroutineRef(String name) {
			this.getCurrentContainer().addSubroutineCall(name);
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
//			return this.subroutines.peek();
//		}
//
		private boolean inClass() {
			return !this.classes.isEmpty();
		}

		private boolean inSubroutine() {
			return !this.subroutines.isEmpty();
		}
//
//		private boolean inClassMethod() {
//			return this.inClass() && !this.subroutines.isEmpty();
//		}

		private ContentContainer getCurrentContainer() {
			return this.contentContainers.peek();
		}
	}
}
