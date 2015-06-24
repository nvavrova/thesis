package ast.node.arg;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimpleArg extends Arg {

	public SimpleArg(LocInfo locInfo, Expr value) {
		super(locInfo, value);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
