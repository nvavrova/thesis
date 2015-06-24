package ast.node.statement.simple;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.statement.Statement;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Assign extends Statement {

	private final String operator;
	private final List<Expr> targets;

	public Assign(LocInfo locInfo, String operator, List<Expr> targets) {
		super(locInfo);
		this.operator = operator;
		this.targets = targets;
	}

	public String getOperator() {
		return this.operator;
	}

	public List<Expr> getTargets() {
		return this.targets;
	}
}
