package thesis;

import ast.Module;
import ast.expression.primary.atom.Identifier;
import ast.expression.primary.AttributeRef;
import ast.statement.compound.ClassDef;
import ast.statement.compound.Function;

import java.util.Map;
import java.util.Stack;

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

		private String currentFileName;
		private final Classes pyClasses;
		private final Stack<Class> classes;
		private Function currentFunction;
		private Boolean collectIdentifier;

		public ClassVisitor(Map<String, Module> trees) {
			super();
			this.pyClasses = new Classes();
			this.classes = new Stack<>();
			this.collectIdentifier = false;
			for (String fileName : trees.keySet()) {
				this.currentFileName = fileName;
				Module n = trees.get(this.currentFileName);
				n.accept(this);
			}
		}

		@Override
		public Void visit(ClassDef n) {
			Class c = new Class(n.getName().getValue(), n.getLocSpan(), n.isController(), n.getInheritance());
			this.classes.push(c);
			this.visitChildren(n);
			this.pyClasses.add(this.currentFileName, this.classes.pop());
			return null;
		}

		@Override
		public Void visit(Function n) {
			this.currentFunction = n;
			if (this.amIInClass()) {
				if (n.isAccessor()) {
					this.getCurrentClass().addAccessor(n.getNameString(), n.getLocSpan());
				}
				else {
					this.getCurrentClass().addMethod(n.getNameString(), n.getLocSpan(), n.isController());
				}
			}
			this.visitChildren(n);
			this.currentFunction = null;
			return null;
		}

		@Override
		public Void visit(AttributeRef n) {
			if (n.getBase() instanceof Identifier) {
				Identifier id = (Identifier) n.getBase();
				if (this.amIInFunction() && this.isClassOrInstanceVar(id.getValue())) {
					this.getCurrentClass().addVariable(n.toString());
				}
			}
			this.visitChildren(n);
			return null;
		}

		public Boolean isClassOrInstanceVar(String name) {
			return this.amIInClass() && (name.equals(this.getCurrentClass().getName()) || name.equals("self"));
		}

		public Class getCurrentClass() {
			return this.classes.peek();
		}

		public Classes getClasses() {
			return this.pyClasses;
		}

		private Boolean amIInClass() {
			return !this.classes.isEmpty();
		}

		private Boolean amIInFunction() {
			return this.currentFunction != null;
		}
	}
}
