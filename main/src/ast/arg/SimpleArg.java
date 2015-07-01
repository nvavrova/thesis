package ast.arg;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimpleArg extends Arg {

	public SimpleArg(LocInfo locInfo, Expr value) {
		super(locInfo, value);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
