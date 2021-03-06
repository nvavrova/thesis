package ast.expression.nocond.trailer;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 17-06-2015
 */
public class SubscriptIndex extends SubscriptSliceListElem {

	private final Expr index;

	public SubscriptIndex(@NotNull Integer locInfo, @NotNull Expr index) {
		super(locInfo);
		this.index = index;
	}

	public Expr getIndex() {
		return this.index;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.index.toString();
	}
}
