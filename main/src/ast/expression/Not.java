package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 09-06-2015
 */
public class Not extends Expr {

	private final Expr expression;

	public Not(LocInfo locInfo, Expr expression) {
		super(locInfo);
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}
}
