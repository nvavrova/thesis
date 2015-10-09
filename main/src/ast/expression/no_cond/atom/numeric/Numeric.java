package ast.expression.no_cond.atom.numeric;

import ast.expression.no_cond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 09-10-2015
 */
public abstract class Numeric extends Atom {
	public Numeric(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
