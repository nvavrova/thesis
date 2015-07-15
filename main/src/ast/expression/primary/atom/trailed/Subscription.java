package ast.expression.primary.atom.trailed;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import ast.expression.primary.trailer.SubscriptSliceList;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 07-07-2015
 */
public class Subscription extends TrailedAtom {

	private final SubscriptSliceList subscript;

	public Subscription(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull SubscriptSliceList subscript) {
		super(locInfo, base);
		this.subscript = subscript;
	}

	public SubscriptSliceList getSubscript() {
		return this.subscript;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
