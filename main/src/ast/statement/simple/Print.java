package ast.statement.simple;

import ast.Visitor;
import ast.expression.Expr;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 01-09-2015
 */
public class Print extends Statement {

	private final List<Expr> expressions;

	public Print(@NotNull Integer locInfo, @NotNull List<Expr> expressions) {
		super(locInfo);
		this.expressions = expressions;
	}

	public List<Expr> getExpressions() {
		return this.expressions;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
