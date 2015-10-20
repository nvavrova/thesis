package ast.statement.simple;

import ast.Visitor;
import ast.expression.ExprList;
import ast.statement.flow.Yield;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignExpr extends Assign {

	private final List<Yield> yieldElements;

	public AssignExpr(@NotNull Integer locInfo, @NotNull String operator, @NotNull List<ExprList> elements,
	                  @NotNull List<Yield> yieldElements) {
		super(locInfo, operator, elements);
		this.yieldElements = yieldElements;
	}

	public List<Yield> getYieldElements() {
		return this.yieldElements;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
