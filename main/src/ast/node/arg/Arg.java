package ast.node.arg;

import ast.node.LocInfo;
import ast.node.Py3Node;
import ast.node.expression.Expr;

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
