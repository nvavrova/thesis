package ast.expression.nocond;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class NonConditional extends Expr {
	public NonConditional(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
