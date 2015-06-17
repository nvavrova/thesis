package ast.statement.simple;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class Delete extends Statement {

	private final List<Expr> items;

	public Delete(LocInfo locInfo, List<Expr> items) {
		super(locInfo);
		this.items = items;
	}

	public List<Expr> getItems() {
		return items;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
