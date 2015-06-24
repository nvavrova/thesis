package ast.node.expression;

import ast.node.LocInfo;
import ast.node.param.Params;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 22-06-2015
 */
public class LambdaNoCond extends ExprNoCond {

	private final Params parameters;
	private final ExprNoCond expression;

	public LambdaNoCond(LocInfo locInfo, Params parameters, ExprNoCond expression) {
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
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
