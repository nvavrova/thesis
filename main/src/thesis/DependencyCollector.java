package thesis;

import org.python.antlr.PythonTree;
import org.python.antlr.ast.Call;
import org.python.antlr.ast.ClassDef;

import java.util.Collection;

/**
 * Created by Nik on 08-05-2015
 */
public class DependencyCollector extends DataCollector {

	private final DependencyVisitor dependencyVisitor;

	public DependencyCollector(Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler, Classes pyClasses) {
		super(trees, exceptionHandler);

		this.dependencyVisitor = new DependencyVisitor(pyClasses, this.trees, this.exceptionHandler);
		this.dependencyVisitor.visit();
	}

	public DependencyGraph getDependencyGraph() {
		return this.dependencyVisitor.getDependencyGraph();
	}


	private class DependencyVisitor extends PyTreeVisitor<Void> {

		private final DependencyGraph dependencyGraph;

		private String currentClass;
		private final Classes pyClasses;

		public DependencyVisitor(Classes pyClasses, Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler) {
			super(trees, exceptionHandler);

			this.pyClasses = pyClasses;
			this.dependencyGraph = new DependencyGraph();

			this.visit();
		}

		public DependencyGraph getDependencyGraph() {
			return this.dependencyGraph;
		}

		@Override
		public Void visitClassDef(ClassDef node) throws Exception {

			this.currentClass = node.getInternalName();
			this.dependencyGraph.init(this.currentClass);
			this.visitChildren(node);
			this.currentClass = null;

			return null;
		}

		@Override
		public Void visitCall(Call node) throws Exception {

			String className = node.getText();
			if (this.amIInClass() && this.isValidClass(className) && this.isNotMe(className)) {
				this.dependencyGraph.add(this.currentClass, className);
			}

			this.visitChildren(node);
			return null;
		}

		private boolean amIInClass() {
			return this.currentClass != null;
		}

		private boolean isNotMe(String className) {
			return !this.currentClass.equals(className);
		}

		private boolean isValidClass(String className) {
			return this.pyClasses.classExists(className);
		}

	}
}