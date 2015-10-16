package ast.expression.nocond.atom.maker;

import ast.expression.Expr;
import ast.expression.compiter.CompFor;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class SetMaker extends Maker {

	private final CompFor comprehension;
	private final List<Expr> values;

	public SetMaker(@NotNull Integer locInfo, @NotNull CompFor comprehension) {
		super(locInfo);

		this.comprehension = comprehension;
		this.values = Collections.emptyList();
	}

	public SetMaker(@NotNull Integer locInfo, @NotNull List<Expr> values) {
		super(locInfo);

		this.values = values;
		this.comprehension = null;
	}

	public CompFor getComprehension() {
		return this.comprehension;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	public Boolean hasComprehension() {
		return this.comprehension != null;
	}

	public Boolean hasValues() {
		return this.values != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
