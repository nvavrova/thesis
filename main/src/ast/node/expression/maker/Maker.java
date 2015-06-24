package ast.node.expression.maker;

import ast.node.LocInfo;
import ast.node.expression.Expr;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Maker extends Expr {
	public Maker(LocInfo locInfo) {
		super(locInfo);
	}
}
