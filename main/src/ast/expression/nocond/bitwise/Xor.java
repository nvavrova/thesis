package ast.expression.nocond.bitwise;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Xor extends Bitwise {

	public Xor(@NotNull Integer locInfo, @NotNull List<Expr> operands) {
		super(locInfo, operands);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
