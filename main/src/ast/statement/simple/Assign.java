package ast.statement.simple;

import ast.Visitor;
import ast.expression.ExprList;
import ast.statement.flow.Yield;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class Assign extends ast.statement.Statement {

	private final List<Yield> yieldElements;
	private final String operator;
	private final List<ExprList> exprElements;

	public Assign(@NotNull Integer locInfo, @NotNull String operator, @NotNull List<ExprList> exprElements, @NotNull List<Yield> yieldElements) {
		super(locInfo);
		this.operator = operator;
		this.exprElements = exprElements;
		this.yieldElements = yieldElements;
	}

	public String getOperator() {
		return this.operator;
	}

	public List<ExprList> getExprElements() {
		return this.exprElements;
	}

	public List<Yield> getYieldElements() {
		return this.yieldElements;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
