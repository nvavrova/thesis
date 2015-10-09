package ast.expression.no_cond.atom.trailed;

import ast.Visitor;
import ast.expression.no_cond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 08-07-2015
 */
public class DirectCall extends TrailedAtom {

	private final Call call;

	public DirectCall(@NotNull Integer locInfo, @NotNull Atom base, @NotNull Call call) {
		super(locInfo, base);
		this.call = call;
	}

	public Call getCall() {
		return this.call;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
