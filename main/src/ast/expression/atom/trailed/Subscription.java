package ast.expression.atom.trailed;

import ast.LocInfo;
import ast.expression.atom.Atom;
import ast.expression.primary.SubscriptList;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 07-07-2015
 */
public class Subscription extends TrailedAtom {

	private final SubscriptList subscript;

	public Subscription(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull SubscriptList subscript) {
		super(locInfo, base);
		this.subscript = subscript;
	}

	public SubscriptList getSubscript() {
		return this.subscript;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
