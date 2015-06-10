package ast.expression.unary;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 10-06-2015
 */
public class Unary extends Expr {

	protected final Expr value;

	public Unary(LocInfo locInfo, Expr value) {
		super(locInfo);
		this.value = value;
	}
}
