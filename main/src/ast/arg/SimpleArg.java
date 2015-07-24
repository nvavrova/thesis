package ast.arg;

import ast.expression.Expr;
import ast.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimpleArg extends Arg {

	public SimpleArg(Integer locInfo, Expr value) {
		super(locInfo, value);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
