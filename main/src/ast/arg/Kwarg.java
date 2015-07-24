package ast.arg;

import ast.expression.primary.atom.Identifier;
import ast.expression.Expr;
import ast.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class Kwarg extends Arg {

	private final Identifier name;

	//TODO: fix the Identifier! @NotNull ?
	public Kwarg(Integer locInfo, Expr value, Identifier name) {
		super(locInfo, value);
		this.name = name;
	}

	public Identifier getName() {
		return this.name;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
