package ast.expression.unary;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 10-06-2015
 */
public class Minus extends Unary {
	public Minus(@NotNull Integer locInfo, @NotNull Expr value) {
		super(locInfo, value);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
