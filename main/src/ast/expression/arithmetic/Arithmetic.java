package ast.expression.arithmetic;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Arithmetic extends Expr {

	protected final List<Expr> operands;

	public Arithmetic(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}

	public List<Expr> getOperands() {
		return operands;
	}
}
