package ast.argument;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 09-10-2015
 */
public class Kwarg extends Argument {

	public Kwarg(Integer locInfo, @NotNull Expr value) {
		super(locInfo, value);
	}

	@Override
	public <T> T accept(ArgumentVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
