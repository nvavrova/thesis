package ast.expression.primary.atom;

import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Ellipsis extends Atom {

	public Ellipsis(@NotNull Integer locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
