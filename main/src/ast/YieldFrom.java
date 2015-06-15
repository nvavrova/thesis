package ast;

import ast.expression.Expr;

/**
 * Created by Nik on 09-06-2015
 */
public class YieldFrom extends Py3Node{

	private final Expr from;

	public YieldFrom(LocInfo locInfo, Expr from) {
		super(locInfo);
		this.from = from;
	}
}
