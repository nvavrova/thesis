package ast.expression.compiter;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public class CompIf extends CompIter {

	private final Expr expression;

	public CompIf(@NotNull Integer locInfo, CompIter nextLink, @NotNull Expr expression) {
		super(locInfo, nextLink);
		this.expression = expression;
	}

	public Expr getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
