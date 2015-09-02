package ast.param;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public class TypedParam extends SimpleParam {
	private final Expr returnVal;

	public TypedParam(@NotNull Integer locInfo, @NotNull Identifier id, @NotNull Expr returnVal) {
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
