package ast.expression.unary;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 10-06-2015
 */
public class Minus extends Unary {
	public Minus(LocInfo locInfo, Expr value) {
		super(locInfo, value);
	}
}
