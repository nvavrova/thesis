package ast.expression;

import ast.LocInfo;
import ast.Py3Node;

/**
 * Created by Nik on 25-05-15
 */
public abstract class Expr extends Py3Node {

	public Expr(LocInfo locInfo) {
		super(locInfo);
	}

}
