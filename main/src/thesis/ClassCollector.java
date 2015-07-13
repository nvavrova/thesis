package thesis;

import ast.LocInfo;
import ast.Module;
import ast.expression.primary.AttributeRef;
import ast.expression.primary.atom.Identifier;
import ast.param.Params;
import ast.param.SimpleParam;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;
import ast.statement.simple.Global;

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
		return this.classVisitor.getClasses();
	}


	private class ClassVisitor extends DefaultVisitor<Void> {

		private String currentFilePath;
		private final Classes pyClasses;
		private final Stack<Class> classes;
//		private final Map<Class, Stack<Method>> methods;

		public ClassVisitor(Map<String, Module> trees) {
			super();
			this.pyClasses = new Classes();
			this.classes = new Stack<>();
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
		public Void visit(ClassDef n) {
			LocInfo locInfo = n.getLocInfo();
			locInfo.setFilePath(this.currentFilePath);
			Class c = new Class(n.getName().getValue(), locInfo, n.getInheritance());
			this.classes.push(c);
			this.visitChildren(n);
			this.pyClasses.add(this.classes.pop());
			return null;
		}

		@Override
		public Void visit(Function n) {

			if (this.inClass()) {

				Params params = n.getParams();
				List<String> paramNames = this.getParamNames(params.getPositionalArgs());
				paramNames.addAll(this.getParamNames(params.getArgs()));
				paramNames.addAll(this.getParamNames(params.getKwargs()));

				if (n.isAccessor()) {
					Method m = this.getCurrentClass().addAccessor(n.getNameString(), n.getLocInfo(), paramNames);
					this.handleMethod(m, n);
				}
				else {
					Method m = this.getCurrentClass().addMethod(n.getNameString(), n.getLocInfo(), paramNames);
					this.handleMethod(m, n);
				}
			}
			else {
				this.visitChildren(n);
			}
			return null;
		}

		private void handleMethod(Method m, Function fnc) {
//			this.getCurrentMethodStack().push(m);
			this.visitChildren(fnc);
//			this.getCurrentMethodStack().pop();
		}

		private List<String> getParamNames(List<SimpleParam> params) {
			return params.stream()
					.map(p -> p.getId().getValue())
					.collect(Collectors.toList());
		}

		@Override
		public Void visit(AttributeRef n) {
			if (n.getBase() instanceof Identifier) {
				Identifier id = (Identifier) n.getBase();
				if (this.inMethod() && this.isClassOrInstanceVar(id.getValue())) {
					this.getCurrentClass().addVariable(n.toString(), this.getCurrentMethod().getName());
				}
				else if (this.inClass() && !this.inMethod()) {
					this.getCurrentClass().addVariable("self." + n.toString());
				}
			}
			this.visitChildren(n);
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
