package ast.expression.atom.trailed;

import ast.LocInfo;
import ast.expression.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 08-07-2015
 */
public class DirectCall extends TrailedAtom {

	private final Call call;

	public DirectCall(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull Call call) {
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
