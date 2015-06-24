package ast.expression.display;

import ast.expression.atom.Atom;
import ast.LocInfo;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Comprehension extends Atom {
	public Comprehension(LocInfo locInfo) {
		super(locInfo);
	}
}
