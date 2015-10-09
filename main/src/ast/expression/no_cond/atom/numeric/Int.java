package ast.expression.no_cond.atom.numeric;

import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.math.BigInteger;

/**
 * Created by Nik on 25-05-2015
 */
public class Int extends Numeric {

	private final BigInteger value;

	public Int(@NotNull Integer locInfo, @NotNull BigInteger value) {
		super(locInfo);
		this.value = value;
	}

	public BigInteger getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
