package ast.expression;

import ast.LocInfo;
import ast.expression.atom.Atom;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class Power extends Expr {

	private final Atom atom;
	private final List<Expr> trailers;
	private final Expr exponent;

	public Power(LocInfo locInfo, Atom atom, List<Expr> trailers, Expr exponent) {
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

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
