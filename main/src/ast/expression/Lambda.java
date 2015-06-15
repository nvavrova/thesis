package ast.expression;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 26-05-2015
 */
public class Lambda extends Expr {

	//TODO: add arguments
	private final Expr test;

	public Lambda(LocInfo locInfo, Expr test) {
		super(locInfo);
		this.test = test;
	}

	public Expr getTest() {
		return test;
	}
}
