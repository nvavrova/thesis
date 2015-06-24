package ast.node.expression.display;

import ast.node.LocInfo;
import ast.node.expression.atom.Atom;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Comprehension extends Atom {
	public Comprehension(LocInfo locInfo) {
		super(locInfo);
	}
}
