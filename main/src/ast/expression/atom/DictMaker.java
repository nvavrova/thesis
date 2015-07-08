package ast.expression.atom;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.comprehension.CompFor;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.Map;

/**
 * Created by Nik on 15-06-2015
 */
public class DictMaker extends Maker {

	private final CompFor comprehension;
	private final Map<Expr, Expr> values;

	public DictMaker(@NotNull LocInfo locInfo, @NotNull CompFor comprehension) {
		super(locInfo);

		this.comprehension = comprehension;
		this.values = null;
	}
	public DictMaker(@NotNull LocInfo locInfo, @NotNull Map<Expr, Expr> values) {
		super(locInfo);

		this.values = values;
		this.comprehension = null;
	}

	public CompFor getComprehension() {
		return this.comprehension;
	}

	public Map<Expr, Expr> getValues() {
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
