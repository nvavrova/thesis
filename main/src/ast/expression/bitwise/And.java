package ast.expression.bitwise;

import ast.LocInfo;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class And extends Bitwise {
	public And(@NotNull LocInfo locInfo, @NotNull List<Expr> operands) {
		super(locInfo, operands);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
