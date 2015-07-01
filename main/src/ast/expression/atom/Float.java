package ast.expression.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Float extends Numeric {

	private final Double value;

	public Float(LocInfo locInfo, @NotNull Double value) {
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
