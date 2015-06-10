package ast.expression;

import ast.LocInfo;
import ast.expression.atom.Numeric;

/**
 * Created by Nik on 08-06-2015
 */
public class UnaryOperatorExpr extends Expr {

	private final String operator; //TODO: enum?
	private final Numeric numericExpr;

	public UnaryOperatorExpr(LocInfo locInfo, String operator, Numeric numericExpr) {
		super(locInfo);
		this.operator = operator;
		this.numericExpr = numericExpr;
	}

	public String getOperator() {
		return operator;
	}

	public Numeric getNumericExpr() {
		return numericExpr;
	}
}
