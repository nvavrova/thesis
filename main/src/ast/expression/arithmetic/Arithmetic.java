package ast.expression.arithmetic;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Arithmetic extends Expr {

	private final List<Expr> operands;
	private final List<String> operators;

	public Arithmetic(LocInfo locInfo, List<Expr> operands, List<String> operators) {
		super(locInfo);
		this.operands = operands;
		this.operators = operators;
	}

	public List<Expr> getOperands() {
		return operands;
	}

	public List<String> getOperators() {
		return operators;
	}
}
