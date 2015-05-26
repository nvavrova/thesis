package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public class Float extends Numeric {

	private final Double value;

	public Float(LocInfo locInfo, Double value) {
		super(locInfo);
		this.value = value;
	}

	public Double getValue() {
		return value;
	}
}
