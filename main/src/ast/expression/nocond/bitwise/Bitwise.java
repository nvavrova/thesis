package ast.expression.nocond.bitwise;

import ast.expression.Expr;
import ast.expression.nocond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Bitwise extends NonConditional {

	protected final List<Expr> operands;

	public Bitwise(@NotNull Integer locInfo, @NotNull List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}

	public List<Expr> getOperands() {
		return this.operands;
	}
}
