package ast.node.statement.simple;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.statement.flow.Yield;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignYield extends Assign {

	public final Yield yield;

	public AssignYield(LocInfo locInfo, String operator, List<Expr> targets, Yield yield) {
		super(locInfo, operator, targets);
		this.yield = yield;
	}

	public Yield getYield() {
		return this.yield;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
