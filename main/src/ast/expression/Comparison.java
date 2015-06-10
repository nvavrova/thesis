package ast.expression;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Comparison extends Expr {

	//TODO: fix operators somehow
	private final List<Expr> operands;
//	private final List<String> operators;

	public Comparison(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);

//		assert operands.size() - 1 == operators.size();

		this.operands = operands;
//		this.operators = operators;
	}

	public List<Expr> getOperands() {
		return operands;
	}

//	public List<String> getOperators() {
//		return operators;
//	}

	public Integer getArity() {
		return operands.size();
	}
}
