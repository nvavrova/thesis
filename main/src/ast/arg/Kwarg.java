package ast.arg;

import ast.LocInfo;
import ast.expression.atom.Identifier;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class Kwarg extends Arg {

	private final Identifier name;

	//TODO: fix the Identifier! @NotNull ?
	public Kwarg(LocInfo locInfo, Expr value, Identifier name) {
		super(locInfo, value);
		this.name = name;
	}

	public Identifier getName() {
		return this.name;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
