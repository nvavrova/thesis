package ast.expression.unary;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Unary extends ExprNoCond {

	protected final Expr value;

	public Unary(@NotNull LocInfo locInfo, @NotNull Expr value) {
		super(locInfo);
		this.value = value;
	}
}
