package ast.expression.primary.atom.trailed;

import ast.expression.primary.atom.Atom;
import ast.expression.primary.trailer.ArgList;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 07-07-2015
 */
public class Call extends TrailedAtom {

	private final ArgList args;

	public Call(@NotNull Integer locInfo, @NotNull Atom base, @NotNull ArgList args) {
		super(locInfo, base);
		this.args = args;
	}

	public ArgList getArgs() {
		return this.args;
	}

	public String getName() {
		return this.getBase().toString();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
