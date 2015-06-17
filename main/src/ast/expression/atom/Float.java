package ast.expression.atom;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Float extends Literal {

	private final Double value;

	public Float(LocInfo locInfo, Double value) {
		super(locInfo);
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
