package ast.expression.nocond.atom.trailed;

import ast.Visitor;
import ast.expression.nocond.atom.Atom;
import ast.expression.nocond.trailer.ArgList;
import org.antlr.v4.runtime.misc.NotNull;

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
