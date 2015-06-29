package ast.expression.atom;

import ast.LocInfo;
import ast.expression.ExprNoCond;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Atom extends ExprNoCond {
	public Atom(LocInfo locInfo) {
		super(locInfo);
	}
}
