package ast.expression.nocond.atom.maker;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.compiter.CompFor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class DictMaker extends Maker {

	private final CompFor comprehension;
	private final List<Expr> keys;
	private final List<Expr> values;

	public DictMaker(@NotNull Integer locInfo, CompFor comprehension, @NotNull List<Expr> keys, @NotNull List<Expr> values) {
		super(locInfo);

		this.comprehension = comprehension;
		this.keys = keys;
		this.values = values;
	}

	public CompFor getComprehension() {
		return this.comprehension;
	}

	public List<Expr> getKeys() {
		return this.keys;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	public Boolean hasComprehension() {
		return this.comprehension != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
