package ast.expression.primary;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Subscript extends Expr {
	public Subscript(LocInfo locInfo) {
		super(locInfo);
	}
}
