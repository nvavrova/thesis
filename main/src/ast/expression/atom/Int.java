package ast.expression.atom;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

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
		return this.value;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
