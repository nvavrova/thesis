package ast.expression.bitwise;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Bitwise extends ExprNoCond {

	protected final List<Expr> operands;

	public Bitwise(@NotNull LocInfo locInfo, @NotNull List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}

	public List<Expr> getOperands() {
		return this.operands;
	}
}
