package ast.node.expression.primary;

import ast.node.LocInfo;
import ast.node.expression.Expr;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Subscript extends Expr {
	public Subscript(LocInfo locInfo) {
		super(locInfo);
	}
}
