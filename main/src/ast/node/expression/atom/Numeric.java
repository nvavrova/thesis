package ast.node.expression.atom;

import ast.node.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Numeric extends Literal {
	public Numeric(LocInfo locInfo) {
		super(locInfo);
	}
}
