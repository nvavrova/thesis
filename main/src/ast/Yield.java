package ast;


import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Yield extends Py3Node {

	private final List<Expr> values;

	public Yield(LocInfo locInfo) {
		super(locInfo);
		this.values = null;
	}

	public Yield(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}
}
