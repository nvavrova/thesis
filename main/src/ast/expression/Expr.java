package ast.expression;

import ast.AstNode;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-15
 */
public abstract class Expr extends AstNode {
	public Expr(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
