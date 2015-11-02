package ast.expression.nocond.arithmetic;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.nocond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 10-06-2015
 */
public class Unary extends NonConditional {

	private final Expr value;
	private final String operator;

	public Unary(@NotNull Integer locInfo, @NotNull Expr value, @NotNull String operator) {
		super(locInfo);
		this.value = value;
		this.operator = operator;
	}

	public Expr getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
