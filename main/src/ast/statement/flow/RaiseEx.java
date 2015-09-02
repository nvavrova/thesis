package ast.statement.flow;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 01-09-2015
 */
public class RaiseEx extends Raise {

	private final Expr value;
	private final Expr trace;

	public RaiseEx(@NotNull Integer locInfo, Expr type, Expr value, Expr trace) {
		super(locInfo, type);
		this.value = value;
		this.trace = trace;
	}

	public boolean hasValue() {
		return this.value != null;
	}

	public boolean hasTrace() {
		return this.trace != null;
	}

	public Expr getValue() {
		return this.value;
	}

	public Expr getTrace() {
		return this.trace;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
