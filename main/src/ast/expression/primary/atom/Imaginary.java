package ast.expression.primary.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 12-06-2015
 */
public class Imaginary extends Atom {

	private final Double value;

	public Imaginary(@NotNull LocInfo locInfo, @NotNull Double value) {
		super(locInfo);
		this.value = value;
	}

	public Double getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
