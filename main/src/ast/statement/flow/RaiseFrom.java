package ast.statement.flow;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-05-2015
 */
public class RaiseFrom extends Raise {

	private final Expr source;

	public RaiseFrom(@NotNull Integer locInfo, Expr type, Expr source) {
		super(locInfo, type);
		this.source = source;
	}

	public Expr getSource() {
		return this.source;
	}

	public Boolean hasSource() {
		return this.source != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
