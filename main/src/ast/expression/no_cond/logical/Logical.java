package ast.expression.no_cond.logical;

import ast.expression.no_cond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Logical extends NonConditional {
	public Logical(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
