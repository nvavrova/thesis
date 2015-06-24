package ast.expression;

import ast.LocInfo;
import ast.Py3Node;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class ExprNoCond extends Py3Node {
	public ExprNoCond(LocInfo locInfo) {
		super(locInfo);
	}
}
