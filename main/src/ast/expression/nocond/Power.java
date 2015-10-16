package ast.expression.nocond;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.nocond.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public class Power extends NonConditional {

	private final Atom base;
	private final Expr exponent;

	public Power(Integer locInfo, @NotNull Atom base, @NotNull Expr exponent) {
		super(locInfo);
		this.base = base;
		this.exponent = exponent;
	}

	public Atom getBase() {
		return this.base;
	}

	public Expr getExponent() {
		return this.exponent;
	}

	public Boolean hasExponent() {
		return this.exponent != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.base.toString() + (this.hasExponent() ? "**" + this.exponent.toString() : "");
	}
}
