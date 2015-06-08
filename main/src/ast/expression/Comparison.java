package ast.expression;

import ast.LocInfo;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Comparison extends Expr {

	private final List<Expr> operands;
	private final List<String> operators; //TODO: enum?

	public Comparison(LocInfo locInfo, List<Expr> operands, List<String> operators) {
		super(locInfo);

		assert operands.size() - 1 == operators.size();

		this.operands = operands;
		this.operators = operators;
	}

	public List<Expr> getOperands() {
		return operands;
	}

	public List<String> getOperators() {
		return operators;
	}

	public Integer getArity() {
		return operands.size();
	}
}
