package ast.expression.logical;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 09-06-2015
 */
public class Not extends Logical {

	private final Expr expression;

	public Not(LocInfo locInfo, Expr expression) {
		super(locInfo);
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}
}
