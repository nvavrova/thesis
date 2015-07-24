package ast.expression;

import ast.param.Params;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 22-06-2015
 */
public class LambdaNoCond extends ExprNoCond {

	private final Params parameters;
	private final ExprNoCond expression;

	public LambdaNoCond(@NotNull Integer locInfo, @NotNull ExprNoCond expression, @NotNull Params parameters) {
		super(locInfo);
		this.expression = expression;
		this.parameters = parameters;
	}

	public Params getParameters() {
		return this.parameters;
	}

	public ExprNoCond getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
