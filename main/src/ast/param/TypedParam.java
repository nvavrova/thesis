package ast.param;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public class TypedParam extends Param {
	private final Expr returnVal;

	public TypedParam(LocInfo locInfo, @NotNull Identifier id, @NotNull Expr returnVal) {
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
