package thesis;

import ast.LocInfo;
import ast.Module;
import ast.expression.primary.atom.trailed.AttributeRef;
import ast.expression.primary.atom.trailed.Call;
import ast.expression.primary.atom.trailed.DirectCall;
import ast.expression.primary.atom.Identifier;
import ast.param.Params;
import ast.param.SimpleParam;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Global;
import ast.statement.simple.ImportFrom;
import ast.statement.simple.SimpleImport;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by Nik on 17-05-2015
 */
public class ClassCollector {

	private final ClassVisitor classVisitor;

	public ClassCollector(Map<String, Module> trees) {
		this.classVisitor = new ClassVisitor(trees);
	}

	public Classes getClasses() {
		this.classVisitor.linker.link();
		return this.classVisitor.getClasses();
	}


	private class ClassVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Classes pyClasses;
		private final Stack<Class> classes;
		private final ClassLinker linker;
//		private final Map<Class, Stack<Method>> methods;

		public ClassVisitor(Map<String, Module> trees) {
			super();
			this.pyClasses = new Classes();
			this.classes = new Stack<>();
			this.linker = new ClassLinker();
//			this.methods = new HashMap<>();
			for (String filePath : trees.keySet()) {
				this.currentFilePath = filePath;
				Module n = trees.get(this.currentFilePath);
				n.accept(this);
			}
		}

		@Override
		public Void visit(Global n) {
			if (this.inClass()) {
				this.getCurrentClass().registerGlobalUse();
			}
			return null;
		}

		@Override
		public Void visit(SimpleImport n) {
			Map<String, String> paths = n.getFullPaths();
			for (String p : paths.keySet()) {
				this.linker.addModuleImport(this.currentFilePath, p, paths.get(p));
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			Map<String, String> paths = n.getFullPaths();
			for (String p : paths.keySet()) {
				this.linker.addClassImport(this.currentFilePath, p, paths.get(p));
			}
			this.visitChildren(n);
			return null;
		}

		@Override
		public Void visit(ClassDef n) {
			LocInfo locInfo = n.getLocInfo();
			locInfo.setFilePath(this.currentFilePath); //TODO: this needs to be done earlier! In AstBuilder or something

			List<String> parents = n.getInheritance().stream()
					.map(a -> a.getValue().toString())
					.filter(p -> !p.equals("object"))
					.collect(Collectors.toList());

			Class c = new Class(n.getName().getValue(), locInfo, n.getInheritance());
			this.linker.addClass(c, parents);

			this.classes.push(c);
			this.visitChildren(n);
			this.pyClasses.add(this.classes.pop());

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
				this.linker.addIdentifier(this.getCurrentClass(), n.getValue());
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
				this.linker.addIdentifier(this.getCurrentClass(), n.toString());
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

		public Class getCurrentClass() {
			return this.classes.peek();
		}

//		public Stack<Method> getCurrentMethodStack() {
//			return this.methods.get(this.getCurrentClass());
//		}

		public Method getCurrentMethod() {
//			return this.getCurrentMethodStack().peek();
			return this.getCurrentClass().getLastAddedMethod();
		}

		public Classes getClasses() {
			return this.pyClasses;
		}

		private boolean inClass() {
			return !this.classes.isEmpty();
		}

		private boolean inMethod() {
			return this.inClass() && !this.getCurrentClass().hasNoMethods();
		}
	}
}
