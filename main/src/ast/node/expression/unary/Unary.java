package ast.node.expression.unary;

import ast.node.LocInfo;
import ast.node.expression.Expr;

/**
 * Created by Nik on 10-06-2015
 */
//TODO: extend Logical?
public abstract class Unary extends Expr {

	protected final Expr value;

	public Unary(LocInfo locInfo, Expr value) {
		super(locInfo);
		this.value = value;
	}
}
