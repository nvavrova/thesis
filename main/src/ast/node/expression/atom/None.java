package ast.node.expression.atom;

import ast.node.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class None extends Atom {
	public None(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
