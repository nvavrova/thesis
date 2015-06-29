package ast.statement.simple;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Delete extends Statement {

	private final ExprList items;

	public Delete(LocInfo locInfo, ExprList items) {
		super(locInfo);
		this.items = items;
	}

	public ExprList getItems() {
		return this.items;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
