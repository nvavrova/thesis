package ast.param;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.atom.Identifier;

/**
 * Created by Nik on 23-06-2015
 */
public class TypedParam extends Param {
	private final Expr returnVal;

	public TypedParam(LocInfo locInfo, Identifier id, Expr returnVal) {
		super(locInfo, id);
		this.returnVal = returnVal;
	}

	public Boolean hasReturnVal() {
		return this.returnVal != null;
	}

	public Expr getReturnVal() {
		return this.returnVal;
	}
}
