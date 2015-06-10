package ast.expression.atom;

import ast.LocInfo;

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
}
