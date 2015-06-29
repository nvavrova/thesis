package ast.statement.simple;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 29-06-2015
 */
public class ExprList extends Statement {

	private final List<Expr> values;

	public ExprList(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
