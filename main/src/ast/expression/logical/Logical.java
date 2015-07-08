package ast.expression.logical;

import ast.LocInfo;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Logical extends ExprNoCond {
	public Logical(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
