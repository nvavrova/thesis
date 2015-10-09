package ast.expression.no_cond.atom.numeric;

import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 12-06-2015
 */
public class Imaginary extends Numeric {

	private final Double value;

	public Imaginary(@NotNull Integer locInfo, @NotNull Double value) {
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
