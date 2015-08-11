package ast.statement.simple;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 29-06-2015
 */
public class ExprList extends ast.statement.Statement {

	private final List<Expr> values;

	public ExprList(@NotNull Integer locInfo, @NotNull List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	public Integer size() {
		return this.values.size();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
