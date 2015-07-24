package ast.statement.flow;

import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 24-06-2015
 */
public abstract class Yield extends Statement {
	public Yield(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
