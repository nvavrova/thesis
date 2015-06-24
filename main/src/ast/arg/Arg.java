package ast.arg;

import ast.LocInfo;
import ast.Py3Node;
import ast.expression.Expr;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class Arg extends Py3Node {

	private final Expr value;

	public Arg(LocInfo locInfo, Expr value) {
		super(locInfo);
		this.value = value;
	}

	public Expr getValue() {
		return this.value;
	}
}
