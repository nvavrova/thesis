package ast.node.expression.atom;

import ast.node.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 10-06-2015
 */
//TODO: implement this
public abstract class Enclosure extends Atom {
	public Enclosure(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
