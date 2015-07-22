package ast.expression;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Power extends ExprNoCond {

	private final Atom base;
	private final Expr exponent;

	public Power(LocInfo locInfo, @NotNull Atom base, @NotNull Expr exponent) {
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
