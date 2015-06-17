package ast.expression.display;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Comprehension extends Expr {

	private final Expr expression;
	private final CompFor compFor;

	public Comprehension(LocInfo locInfo, Expr expression, CompFor compFor) {
		super(locInfo);
		this.expression = expression;
		this.compFor = compFor;
	}

	public Expr getExpression() {
		return expression;
	}

	public CompFor getCompFor() {
		return compFor;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
