package model;

import ast.Module;
import ast.path.Path;
import ast.expression.primary.atom.Identifier;
import ast.expression.primary.atom.trailed.AttributeRef;
import ast.expression.primary.atom.trailed.Call;
import ast.expression.primary.atom.trailed.DirectCall;
import ast.param.Params;
import ast.param.SimpleParam;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Global;
import ast.statement.simple.ImportFrom;
import ast.statement.simple.SimpleImport;
import ast.DefaultVisitor;
import thesis.StringHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by Nik on 17-05-2015
 */
public class ModelBuilder {

	private final BuildingVisitor buildingVisitor;
	private final LinkingVisitor linkingVisitor;

	public ModelBuilder(String projectFolder, List<Module> trees) {
		this.buildingVisitor = new BuildingVisitor(trees);
		Map<String, model.Module> modules = this.buildingVisitor.getModules();
		this.linkingVisitor = new LinkingVisitor(projectFolder, trees, modules);
	}

	public Map<String, model.Module> getModules() {
		return this.buildingVisitor.getModules();
	}

	private class LinkingVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Linker linker;

		public LinkingVisitor(String projectFolder, List<Module> trees, Map<String, model.Module> modules) {
			super();
			this.linker = new Linker(projectFolder, modules);
			for (Module m : trees) {
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
		private final Map<String, model.Module> modules;
		private final Stack<model.Class> classes;

		public BuildingVisitor(List<Module> trees) {
			super();
			this.modules = new HashMap<>();
			this.classes = new Stack<>();
			for (Module m : trees) {
				this.currentFilePath = m.getFilePath();
				m.accept(this);
			}
		}

		public Map<String, model.Module> getModules() {
			return this.modules;
		}

		@Override
		public void visitChildren(Module n) {
			model.Module module = new model.Module(n.getFilePath(), n.getName());
			this.modules.put(n.getFilePath(), module);
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
			List<String> prevClsNames = this.classes.stream()
					.map(cls -> cls.getName())
					.collect(Collectors.toList());
			prevClsNames.add(n.getName().getValue());
			String name = StringHelper.implode(prevClsNames, ".");


			List<String> parents = n.getInheritance().stream()
					.map(p -> p.getValue().toString())
					.collect(Collectors.toList());

			model.Module curentModule = this.getCurentModule();
			Class c = new Class(name, curentModule, locInfo, parents);
			curentModule.addClass(c);

			this.classes.push(c);
			this.visitChildren(n);
			this.classes.pop();

			return null;
		}

		@Override
		public Void visit(Function n) {

			if (this.inClass()) {

				List<String> paramNames = this.getParamNames(n.getParams());

				if (n.isAccessor()) {
					this.getCurrentClass().addAccessor(n.getNameString(), n.getLocInfo(), paramNames);
					this.visitChildren(n);
				}
				else {
					this.getCurrentClass().addMethod(n.getNameString(), n.getLocInfo(), paramNames);
					this.visitChildren(n);
				}
			}
			else {
				this.visitChildren(n);
			}
			return null;
		}

		@Override
		public Void visit(Identifier n) {
			if (this.inClass()) {
				this.getCurrentClass().addVariable(n.getValue());
			}
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
			if (this.inClass()) {
				this.getCurrentClass().addVariable(n.toString());
//				this.linker.addIdentifier(this.getCurrentClass(), n.toString());
			}
			if (n.getBase() instanceof Identifier) {
				Identifier id = (Identifier) n.getBase();
				if (this.inMethod() && this.isClassOrInstanceVar(id.getValue())) {
					this.getCurrentClass().addVariable(n.toString(), this.getCurrentMethod().getName());
				}
				else if (this.inClass() && !this.inMethod()) {
					this.getCurrentClass().addVariable("self." + n.toString());
				}
			}
//			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(Call n) {
			return super.visit(n);
		}

		@Override
		public Void visit(DirectCall n) {
			return null;
		}

		public Boolean isClassOrInstanceVar(String name) {
			return this.inClass() && (name.equals(this.getCurrentClass().getName()) || name.equals("self"));
		}

		private model.Module getCurentModule() {
			if (this.modules.size() == 0) {
				return null;
			}
			return this.modules.get(this.currentFilePath);
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
