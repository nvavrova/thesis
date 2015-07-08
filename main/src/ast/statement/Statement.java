package ast.statement;

import ast.LocInfo;
import ast.Py3Node;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Statement extends Py3Node {
	public Statement(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
