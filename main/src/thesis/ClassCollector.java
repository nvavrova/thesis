package thesis;

import org.python.antlr.PythonTree;
import org.python.antlr.ast.*;

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


	private class ClassVisitor extends PyTreeVisitor<Integer> {

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
		public Integer visitClassDef(ClassDef node) throws Exception {
			Integer loc = this.visitChildren(node);
			this.pyClasses.add(node.getInternalName(), loc);
			return null;
		}

		@Override
		public Integer visitModule(Module node) throws Exception {
			return super.visitModule(node);
		}

		@Override
		public Integer visitInteractive(Interactive node) throws Exception {
			return super.visitInteractive(node);
		}

		@Override
		public Integer visitExpression(Expression node) throws Exception {
			return super.visitExpression(node);
		}

		@Override
		public Integer visitSuite(Suite node) throws Exception {
			return super.visitSuite(node);
		}

		@Override
		public Integer visitFunctionDef(FunctionDef node) throws Exception {
			return super.visitFunctionDef(node);
		}

		@Override
		public Integer visitReturn(Return node) throws Exception {
			return super.visitReturn(node);
		}

		@Override
		public Integer visitDelete(Delete node) throws Exception {
			return super.visitDelete(node);
		}

		@Override
		public Integer visitAssign(Assign node) throws Exception {
			return super.visitAssign(node);
		}

		@Override
		public Integer visitAugAssign(AugAssign node) throws Exception {
			return super.visitAugAssign(node);
		}

		@Override
		public Integer visitPrint(Print node) throws Exception {
			return super.visitPrint(node);
		}

		@Override
		public Integer visitFor(For node) throws Exception {
			return super.visitFor(node);
		}

		@Override
		public Integer visitWhile(While node) throws Exception {
			return super.visitWhile(node);
		}

		@Override
		public Integer visitIf(If node) throws Exception {
			return super.visitIf(node);
		}

		@Override
		public Integer visitWith(With node) throws Exception {
			return super.visitWith(node);
		}

		@Override
		public Integer visitRaise(Raise node) throws Exception {
			return super.visitRaise(node);
		}

		@Override
		public Integer visitTryExcept(TryExcept node) throws Exception {
			return super.visitTryExcept(node);
		}

		@Override
		public Integer visitTryFinally(TryFinally node) throws Exception {
			return super.visitTryFinally(node);
		}

		@Override
		public Integer visitAssert(Assert node) throws Exception {
			return super.visitAssert(node);
		}

		@Override
		public Integer visitImport(Import node) throws Exception {
			return super.visitImport(node);
		}

		@Override
		public Integer visitImportFrom(ImportFrom node) throws Exception {
			return super.visitImportFrom(node);
		}

		@Override
		public Integer visitExec(Exec node) throws Exception {
			return super.visitExec(node);
		}

		@Override
		public Integer visitGlobal(Global node) throws Exception {
			return super.visitGlobal(node);
		}

		@Override
		public Integer visitExpr(Expr node) throws Exception {
			return super.visitExpr(node);
		}

		@Override
		public Integer visitPass(Pass node) throws Exception {
			return super.visitPass(node);
		}

		@Override
		public Integer visitBreak(Break node) throws Exception {
			return super.visitBreak(node);
		}

		@Override
		public Integer visitContinue(Continue node) throws Exception {
			return super.visitContinue(node);
		}

		@Override
		public Integer visitBoolOp(BoolOp node) throws Exception {
			return super.visitBoolOp(node);
		}

		@Override
		public Integer visitBinOp(BinOp node) throws Exception {
			return super.visitBinOp(node);
		}

		@Override
		public Integer visitUnaryOp(UnaryOp node) throws Exception {
			return super.visitUnaryOp(node);
		}

		@Override
		public Integer visitLambda(Lambda node) throws Exception {
			return super.visitLambda(node);
		}

		@Override
		public Integer visitIfExp(IfExp node) throws Exception {
			return super.visitIfExp(node);
		}

		@Override
		public Integer visitDict(Dict node) throws Exception {
			return super.visitDict(node);
		}

		@Override
		public Integer visitSet(Set node) throws Exception {
			return super.visitSet(node);
		}

		@Override
		public Integer visitListComp(ListComp node) throws Exception {
			return super.visitListComp(node);
		}

		@Override
		public Integer visitSetComp(SetComp node) throws Exception {
			return super.visitSetComp(node);
		}

		@Override
		public Integer visitDictComp(DictComp node) throws Exception {
			return super.visitDictComp(node);
		}

		@Override
		public Integer visitGeneratorExp(GeneratorExp node) throws Exception {
			return super.visitGeneratorExp(node);
		}

		@Override
		public Integer visitYield(Yield node) throws Exception {
			return super.visitYield(node);
		}

		@Override
		public Integer visitCompare(Compare node) throws Exception {
			return super.visitCompare(node);
		}

		@Override
		public Integer visitCall(Call node) throws Exception {
			return super.visitCall(node);
		}

		@Override
		public Integer visitRepr(Repr node) throws Exception {
			return super.visitRepr(node);
		}

		@Override
		public Integer visitNum(Num node) throws Exception {
			return super.visitNum(node);
		}

		@Override
		public Integer visitStr(Str node) throws Exception {
			return super.visitStr(node);
		}

		@Override
		public Integer visitAttribute(Attribute node) throws Exception {
			return super.visitAttribute(node);
		}

		@Override
		public Integer visitSubscript(Subscript node) throws Exception {
			return super.visitSubscript(node);
		}

		@Override
		public Integer visitName(Name node) throws Exception {
			return super.visitName(node);
		}

		@Override
		public Integer visitList(List node) throws Exception {
			System.out.println("char position in line: " + node);

			System.out.println("LIST start & stop: " + node.getTokenStopIndex() + " & " + node.getTokenStopIndex() +  "  " );
			System.out.println("LIST char position in line: " + node.getCharPositionInLine());
			return super.visitList(node);
		}

		@Override
		public Integer visitTuple(Tuple node) throws Exception {
			return super.visitTuple(node);
		}

		@Override
		public Integer visitEllipsis(Ellipsis node) throws Exception {
			return super.visitEllipsis(node);
		}

		@Override
		public Integer visitSlice(Slice node) throws Exception {
			return super.visitSlice(node);
		}

		@Override
		public Integer visitExtSlice(ExtSlice node) throws Exception {
			return super.visitExtSlice(node);
		}

		@Override
		public Integer visitIndex(Index node) throws Exception {
			return super.visitIndex(node);
		}

		@Override
		public Integer visitExceptHandler(ExceptHandler node) throws Exception {
			return super.visitExceptHandler(node);
		}

		@Override
		protected Integer visitChildren(PythonTree node) throws Exception {
			return super.visitChildren(node);
		}
	}
}
