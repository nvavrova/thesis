package ast.expression.atom;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 12-06-2015
 */
public class Imaginary extends Numeric {

	private final Double value;

	public Imaginary(LocInfo locInfo, Double value) {
		super(locInfo);
		this.value = value;
	}

	public Double getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
