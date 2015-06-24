package ast.statement.simple;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignExpr extends Assign {

	private final List<Expr> source;

	public AssignExpr(LocInfo locInfo, String operator, List<Expr> targets, List<Expr> source) {
		super(locInfo, operator, targets);
		this.source = source;
	}

	public List<Expr> getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
