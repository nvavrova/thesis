package ast.expression;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class ExprNoCond extends Expr {
	public ExprNoCond(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
