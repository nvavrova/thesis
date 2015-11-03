package ast.expression.nocond.atom.yield;

import ast.expression.nocond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 24-06-2015
 */
public abstract class Yield extends Atom {
	public Yield(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
