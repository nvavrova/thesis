package ast.expression.primary.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Numeric extends Literal {

	public Numeric(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
