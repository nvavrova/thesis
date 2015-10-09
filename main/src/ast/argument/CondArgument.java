package ast.argument;

import ast.Visitor;
import ast.expression.comprehension.CompFor;
import ast.expression.Expr;

/**
 * Created by Nik on 23-06-2015
 */
public class CondArgument extends Argument {

	private final CompFor condition;

	public CondArgument(Integer locInfo, Expr value, CompFor condition) {
		super(locInfo, value);
		this.condition = condition;
	}

	public CompFor getCondition() {
		return this.condition;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
