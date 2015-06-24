package ast.expression;

import ast.LocInfo;
import ast.param.Params;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 26-05-2015
 */
public class Lambda extends Expr {

	private final Params parameters;
	private final Expr expression;

	public Lambda(LocInfo locInfo, Params parameters, Expr expression) {
		super(locInfo);
		this.expression = expression;
		this.parameters = parameters;
	}

	public Expr getExpression() {
		return this.expression;
	}

	public Params getParameters() {
		return this.parameters;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
