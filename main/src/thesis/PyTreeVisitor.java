package thesis;

import org.python.antlr.PythonTree;
import org.python.antlr.ast.*;

import java.util.Collection;

/**
 * Created by Nik on 15-05-2015
 */
public abstract class PyTreeVisitor<T> implements VisitorIF<T> {

	protected final Collection<PythonTree> trees;
	protected final PyVisitorExceptionHandler exceptionHandler;

	public PyTreeVisitor(Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler) {
		this.trees = trees;
		this.exceptionHandler = exceptionHandler;
	}

	protected void visit() {
		this.trees.forEach(this::visitTree);
	}

	private void visitTree(PythonTree tree) {
		try {
			tree.accept(this);
		}
		catch (Exception e) {
			this.exceptionHandler.handle(e);
		}
	}

	@Override
	public T visitModule(Module node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitInteractive(Interactive node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitExpression(Expression node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitSuite(Suite node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitFunctionDef(FunctionDef node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitClassDef(ClassDef node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitReturn(Return node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitDelete(Delete node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitAssign(Assign node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitAugAssign(AugAssign node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitPrint(Print node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitFor(For node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitWhile(While node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitIf(If node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitWith(With node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitRaise(Raise node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitTryExcept(TryExcept node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitTryFinally(TryFinally node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitAssert(Assert node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitImport(Import node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitImportFrom(ImportFrom node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitExec(Exec node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitGlobal(Global node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitExpr(Expr node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitPass(Pass node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitBreak(Break node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitContinue(Continue node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitBoolOp(BoolOp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitBinOp(BinOp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitUnaryOp(UnaryOp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitLambda(Lambda node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitIfExp(IfExp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitDict(Dict node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitSet(Set node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitListComp(ListComp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitSetComp(SetComp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitDictComp(DictComp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitGeneratorExp(GeneratorExp node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitYield(Yield node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitCompare(Compare node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitCall(Call node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitRepr(Repr node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitNum(Num node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitStr(Str node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitAttribute(Attribute node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitSubscript(Subscript node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitName(Name node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitList(List node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitTuple(Tuple node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitEllipsis(Ellipsis node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitSlice(Slice node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitExtSlice(ExtSlice node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitIndex(Index node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	@Override
	public T visitExceptHandler(ExceptHandler node) throws Exception {
		this.visitChildren(node);
		return null;
	}

	protected T visitChildren(PythonTree node) throws Exception {
		if (node.getChildCount() > 0) {
			for (PythonTree t : node.getChildren()) {
				t.accept(this);
			}
		}
		return null;
	}
}
