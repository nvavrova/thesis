package ast.expression.primary;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import ast.expression.primary.trailer.ArgList;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 07-07-2015
 */
public class Call extends TrailedAtom {

	private final ArgList args;

	public Call(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull ArgList args) {
		super(locInfo, base);
		this.args = args;
	}

	public ArgList getArgs() {
		return this.args;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
