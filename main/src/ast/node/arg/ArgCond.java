package ast.node.arg;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.expression.display.CompFor;

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
