package ast.expression.no_cond.atom.comprehension;

import ast.expression.no_cond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Comprehension extends Atom {
	public Comprehension(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
