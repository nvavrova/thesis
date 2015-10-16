package ast.expression.nocond;

import ast.param.Params;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 22-06-2015
 */
public class LambdaNoCond extends NonConditional {

	private final Params parameters;
	private final NonConditional expression;

	public LambdaNoCond(@NotNull Integer locInfo, @NotNull NonConditional expression, Params parameters) {
		super(locInfo);
		this.expression = expression;
		this.parameters = parameters;
	}

	public Boolean hasParameters() {
		return this.parameters != null;
	}

	public Params getParameters() {
		return this.parameters;
	}

	public NonConditional getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
