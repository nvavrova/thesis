package ast.arg;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.atom.Identifier;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class Kwarg extends Arg {

	private final Identifier name;

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
