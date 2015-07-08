package ast.expression.primary;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 07-07-2015
 */
public abstract class TrailedAtom extends Atom {

	private Atom base;

	public TrailedAtom(@NotNull LocInfo locInfo, @NotNull Atom base) {
		super(locInfo);
		this.base = base;
	}

	public Atom getBase() {
		return this.base;
	}

	public void setBase(@NotNull Atom base) {
		this.base = base;
	}
}
