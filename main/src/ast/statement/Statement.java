package ast.statement;

import ast.AstNode;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Statement extends AstNode {
	public Statement(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
