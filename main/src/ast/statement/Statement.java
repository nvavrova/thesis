package ast.statement;

import ast.LocInfo;
import ast.AstNode;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Statement extends AstNode {
	public Statement(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
