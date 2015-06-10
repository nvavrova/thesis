package ast.expression.atom;

import ast.LocInfo;

import java.math.BigInteger;

/**
 * Created by Nik on 25-05-2015
 */
public class Int extends Numeric {

	private final BigInteger value;

	public Int(LocInfo locInfo, BigInteger value) {
		super(locInfo);
		this.value = value;
	}

	public BigInteger getValue() {
		return value;
	}
}
