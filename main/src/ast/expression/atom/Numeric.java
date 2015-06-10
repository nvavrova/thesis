package ast.expression.atom;

import ast.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Numeric extends Literal {
	public Numeric(LocInfo locInfo) {
		super(locInfo);
	}
}
