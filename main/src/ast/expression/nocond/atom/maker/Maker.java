package ast.expression.nocond.atom.maker;

import ast.expression.nocond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Maker extends Atom {
	public Maker(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
