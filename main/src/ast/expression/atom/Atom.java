package ast.expression.atom;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Atom extends Expr {
	public Atom(LocInfo locInfo) {
		super(locInfo);
	}
}
