package ast.expression.unary;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.ExprNoCond;

/**
 * Created by Nik on 10-06-2015
 */
//TODO: extend Logical?
public abstract class Unary extends ExprNoCond {

	protected final Expr value;

	public Unary(LocInfo locInfo, Expr value) {
		super(locInfo);
		this.value = value;
	}
}
