package ast.expression.primary.atom;

import ast.Visitor;
import ast.statement.simple.ExprList;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 02-09-2015
 */
public class StrConversion extends Atom {

	private final ExprList exprList;

	public StrConversion(@NotNull Integer locInfo, @NotNull ExprList exprList) {
		super(locInfo);
		this.exprList = exprList;
	}

	public ExprList getExprList() {
		return this.exprList;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
