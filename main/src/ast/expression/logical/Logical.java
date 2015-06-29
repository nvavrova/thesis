package ast.expression.logical;

import ast.LocInfo;
import ast.expression.ExprNoCond;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Logical extends ExprNoCond {
	public Logical(LocInfo locInfo) {
		super(locInfo);
	}
}
