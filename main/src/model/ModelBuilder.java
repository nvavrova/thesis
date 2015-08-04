package model;

import ast.DefaultVisitor;
import ast.expression.primary.atom.Identifier;
import ast.expression.primary.atom.trailed.AttributeRef;
import ast.expression.primary.atom.trailed.Call;
import ast.expression.primary.atom.trailed.DirectCall;
import ast.param.Params;
import ast.param.SimpleParam;
import ast.path.Path;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Global;
import ast.statement.simple.ImportFrom;
import ast.statement.simple.SimpleImport;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by Nik on 17-05-2015
 */
public class ModelBuilder {

	private static final String SELF_KEYWORD = "self";

	private final BuildingVisitor buildingVisitor;
	private final LinkingVisitor linkingVisitor;

	public ModelBuilder(List<ast.Module> trees, Project project) {
		project.initVersion();
		this.buildingVisitor = new BuildingVisitor(trees, project);
		this.linkingVisitor = new LinkingVisitor(trees, project);
		project.cleanupVersion();
	}

	private class LinkingVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Linker linker;

		public LinkingVisitor(List<ast.Module> trees, Project project) {
			super();
			this.linker = new Linker(project);
			for (ast.Module m : trees) {
				this.currentFilePath = m.getFilePath();
				m.accept(this);
			}
			this.linker.link();
		}

		@Override
		public Void visit(SimpleImport n) {
			for (Path p : n.getPaths()) {
				String alias = p.hasAlias() ? p.getAlias().getValue() : p.getPath();
				this.linker.addImport(this.currentFilePath, p.getPath(), alias);
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			for (Path p : n.getPaths()) {
				String alias = p.hasAlias() ? p.getAlias().getValue() : p.getPath();
				this.linker.addImport(this.currentFilePath, n.getModule().getPath(), p.getPath(), alias);
			}
			this.visitChildren(n);
			return null;
		}
	}



	private class BuildingVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Project project;
		private final Stack<model.Class> classes;

		public BuildingVisitor(List<ast.Module> trees, Project project) {
			super();
			this.project = project;
			this.classes = new Stack<>();
			for (ast.Module m : trees) {
				this.currentFilePath = m.getFilePath();
				m.accept(this);
			}
		}

		@Override
		public void visitChildren(ast.Module n) {
			String filePath = n.getFilePath();
			model.Module module = new model.Module(filePath, n.getName());
			this.project.registerModule(module);
			super.visitChildren(n);
		}

		@Override
		public Void visit(Global n) {
			if (this.inClass()) {
				this.getCurrentClass().registerGlobalUse();
			}
			return null;
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
			currentModule.registerClass(c);

			this.classes.push(c);
			this.visitChildren(n);
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

				List<String> paramNames = this.getParamNames(n.getParams()).stream()
						.filter(p -> !p.equals(SELF_KEYWORD))
						.collect(Collectors.toList());

				if (n.isAccessor()) {
					this.getCurrentClass().addAccessor(n.getNameString(), n.getLocInfo(), paramNames);
				}
				else {
					this.getCurrentClass().addMethod(n.getNameString(), n.getLocInfo(), paramNames);
				}
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
//			n.getName().accept(this);
			n.getParams().accept(this);
			n.getDecorators().forEach(d -> d.accept(this));
		}

		@Override
		public Void visit(Identifier n) {
			this.addVar(n.getValue());
			return null;
		}

		private List<String> getParamNames(Params params) {
			List<String> paramNames = this.getParamNames(params.getPositionalArgs());
			paramNames.addAll(this.getParamNames(params.getArgs()));
			paramNames.addAll(this.getParamNames(params.getKwargs()));
			return paramNames;
		}

		private List<String> getParamNames(List<SimpleParam> params) {
			return params.stream()
					.map(p -> p.getId().getValue())
					.collect(Collectors.toList());
		}

		@Override
		public Void visit(AttributeRef n) {
			this.addVar(n.toString());
			if (n.getBase() instanceof Identifier) {
				Identifier id = (Identifier) n.getBase();
				this.addVar(id.getValue());
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Call n) {
			this.addRef(n.getBase().toString());
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(DirectCall n) {
			this.addRef(n.getBase().toString() + "." + n.getCall().getName());
			this.visitChildren(n);
			return null;
		}

		@Override
		public void visitChildren(DirectCall n) {
			n.getBase().accept(this);
			n.getCall().accept(this);
		}

		private void addVar(String varName) {
			if (this.inMethod() && (this.isClassVar(varName))) {
				this.getCurrentClass().addVariable(varName);
			}
			else if (this.inClass() && !this.inMethod()) {
				this.getCurrentClass().addVariable(SELF_KEYWORD + "." + varName);
//				this.getCurrentClass().addVariable(this.getCurrentClass().getName() + "." + varName);
			}
		}

		private void addRef(String name) {
			if (this.inClass()) {
				this.getCurrentClass().addReference(name);
			}
		}

		private boolean isClassVar(String varName) {
			return varName.startsWith(this.getCurrentClass().getName() + ".") || varName.startsWith(SELF_KEYWORD + ".");
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
			return this.getCurrentClass().getLastAddedMethod();
		}

		private boolean inClass() {
			return !this.classes.isEmpty();
		}

		private boolean inMethod() {
			return this.inClass() && !this.getCurrentClass().hasNoMethods();
		}
	}
}
