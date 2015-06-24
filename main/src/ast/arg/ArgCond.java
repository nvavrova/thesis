package ast.arg;

import ast.LocInfo;
import ast.expression.display.CompFor;
import ast.expression.Expr;

/**
 * Created by Nik on 23-06-2015
 */
public class ArgCond extends SimpleArg {

	private final CompFor condition;

	public ArgCond(LocInfo locInfo, Expr value, CompFor condition) {
		super(locInfo, value);
		this.condition = condition;
	}

	public CompFor getCondition() {
		return this.condition;
	}
}
