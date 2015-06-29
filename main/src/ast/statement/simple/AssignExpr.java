package ast.statement.simple;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignExpr extends Assign {

	private final ExprList source;

	public AssignExpr(LocInfo locInfo, String operator, ExprList targets, ExprList source) {
		super(locInfo, operator, targets);
		this.source = source;
	}

	public ExprList getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
