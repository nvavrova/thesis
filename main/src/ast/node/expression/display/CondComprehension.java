package ast.node.expression.display;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class CondComprehension extends Comprehension {

	private final Expr expression;
	private final CompFor compFor;

	public CondComprehension(LocInfo locInfo, Expr expression, CompFor compFor) {
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
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
