package ast.expression;

import ast.LocInfo;
import ast.expression.atom.Atom;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class Power extends ExprNoCond {

	private final Atom atom;
	private final List<Expr> trailers;
	private final Expr exponent;

	public Power(LocInfo locInfo, @NotNull Atom atom, @NotNull List<Expr> trailers, Expr exponent) {
		super(locInfo);
		this.atom = atom;
		this.trailers = trailers;
		this.exponent = exponent;
	}

	public Atom getAtom() {
		return this.atom;
	}

	public List<Expr> getTrailers() {
		return this.trailers;
	}

	public Expr getExponent() {
		return this.exponent;
	}

	public Boolean hasExponent() {
		return this.exponent != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
