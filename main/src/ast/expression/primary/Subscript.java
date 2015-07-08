package ast.expression.primary;

import ast.LocInfo;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Subscript extends ExprNoCond {
	public Subscript(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
