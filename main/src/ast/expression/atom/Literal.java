package ast.expression.atom;

import ast.LocInfo;
import thesis.Visitor;

/**
 * Created by Nik on 10-06-2015
 */
public class Literal extends Atom {
	public Literal(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
