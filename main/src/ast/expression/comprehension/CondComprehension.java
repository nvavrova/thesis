package ast.expression.comprehension;

import ast.LocInfo;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class CondComprehension extends Comprehension {

	private final Expr expression;
	private final CompFor compFor;

	public CondComprehension(@NotNull LocInfo locInfo, @NotNull Expr expression, @NotNull CompFor compFor) {
		super(locInfo);
		this.expression = expression;
		this.compFor = compFor;
	}

	public Expr getExpression() {
		return this.expression;
	}

	public CompFor getCompFor() {
		return this.compFor;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
