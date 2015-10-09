package ast.expression.no_cond.atom;

import ast.expression.no_cond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Atom extends NonConditional {

	public Atom(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
