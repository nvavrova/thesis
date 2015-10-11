package ast.statement.simple;

import ast.expression.ExprList;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Delete extends ast.statement.Statement {

	private final ExprList items;

	public Delete(@NotNull Integer locInfo, @NotNull ExprList items) {
		super(locInfo);
		this.items = items;
	}

	public ExprList getItems() {
		return this.items;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
