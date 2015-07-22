package ast.param;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class TypedParam extends Param {
	private final Expr returnVal;

	public TypedParam(@NotNull LocInfo locInfo, @NotNull Identifier id, @NotNull Expr returnVal) {
		super(locInfo, id);
		this.returnVal = returnVal;
	}

	public Boolean hasReturnVal() {
		return this.returnVal != null;
	}

	public Expr getReturnVal() {
		return this.returnVal;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
