package ast.expression.bitwise;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Bitwise extends Expr {

	protected final List<Expr> operands;

	public Bitwise(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}

	public List<Expr> getOperands() {
		return this.operands;
	}
}
