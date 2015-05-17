package thesis;

import org.python.antlr.PythonTree;
import org.python.antlr.ast.ClassDef;

import java.util.Collection;

/**
 * Created by Nik on 17-05-2015
 */
public class ClassCollector extends DataCollector {

	private final ClassVisitor classVisitor;

	public ClassCollector(Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler) {
		super(trees, exceptionHandler);
		this.classVisitor = new ClassVisitor(trees, exceptionHandler);
	}

	public Classes getClasses() {
		return this.classVisitor.getClasses();
	}

	private class ClassVisitor extends PyTreeVisitor {

		private final Classes pyClasses;

		public ClassVisitor(Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler) {
			super(trees, exceptionHandler);
			this.pyClasses = new Classes();
			this.visit();
		}

		public Classes getClasses() {
			return this.pyClasses;
		}

		@Override
		public Void visitClassDef(ClassDef node) throws Exception {
			this.pyClasses.add(node.getInternalName());
			this.visitChildren(node);
			return null;
		}
	}
}
