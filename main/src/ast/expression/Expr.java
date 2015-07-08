package ast.expression;

import ast.LocInfo;
import ast.Py3Node;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-15
 */
public abstract class Expr extends Py3Node {
	public Expr(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
