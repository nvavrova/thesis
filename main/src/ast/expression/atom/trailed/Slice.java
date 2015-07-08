package ast.expression.atom.trailed;

import ast.LocInfo;
import ast.expression.atom.Atom;
import ast.expression.primary.SliceBound;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 08-07-2015
 */
public class Slice extends TrailedAtom {

	private final SliceBound bound;

	public Slice(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull SliceBound bound) {
		super(locInfo, base);
		this.bound = bound;
	}

	public SliceBound getBound() {
		return this.bound;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
