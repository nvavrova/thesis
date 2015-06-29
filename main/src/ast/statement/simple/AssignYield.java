package ast.statement.simple;

import ast.LocInfo;
import ast.statement.flow.Yield;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignYield extends Assign {

	public final Yield yield;

	public AssignYield(LocInfo locInfo, String operator, ExprList targets, Yield yield) {
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
