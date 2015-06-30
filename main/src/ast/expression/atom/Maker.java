package ast.expression.atom;

import ast.LocInfo;
import ast.expression.atom.Atom;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Maker extends Atom {
	public Maker(LocInfo locInfo) {
		super(locInfo);
	}
}
