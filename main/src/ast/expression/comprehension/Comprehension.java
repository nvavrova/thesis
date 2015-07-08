package ast.expression.comprehension;

import ast.expression.atom.Atom;
import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Comprehension extends Atom {
	public Comprehension(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
