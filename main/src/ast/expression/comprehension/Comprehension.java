package ast.expression.comprehension;

import ast.expression.primary.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Comprehension extends Atom {
	public Comprehension(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
