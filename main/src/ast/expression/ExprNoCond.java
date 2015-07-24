package ast.expression;

import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class ExprNoCond extends Expr {
	public ExprNoCond(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
