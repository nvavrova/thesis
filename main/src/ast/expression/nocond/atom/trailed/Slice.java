package ast.expression.nocond.atom.trailed;

import ast.expression.nocond.atom.Atom;
import ast.expression.nocond.trailer.SubscriptSliceList;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 08-07-2015
 */
public class Slice extends TrailedAtom {

	private final SubscriptSliceList bound;

	public Slice(@NotNull Integer locInfo, @NotNull Atom base, @NotNull SubscriptSliceList bound) {
		super(locInfo, base);
		this.bound = bound;
	}

	public SubscriptSliceList getBounds() {
		return this.bound;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
