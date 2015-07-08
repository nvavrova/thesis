package ast.expression.primary.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Maker extends Atom {
	public Maker(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
