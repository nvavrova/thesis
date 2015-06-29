package ast.statement.simple;

import ast.LocInfo;
import ast.statement.Statement;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Assign extends Statement {

	private final String operator;
	private final ExprList targets;

	public Assign(LocInfo locInfo, String operator, ExprList targets) {
		super(locInfo);
		this.operator = operator;
		this.targets = targets;
	}

	public String getOperator() {
		return this.operator;
	}

	public ExprList getTargets() {
		return this.targets;
	}
}
