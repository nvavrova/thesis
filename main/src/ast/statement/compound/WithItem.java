package ast.statement.compound;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-06-2015
 */
public class WithItem extends Statement {

	private final Expr item;
	private final Expr alias;

	public WithItem(LocInfo locInfo, Expr item, Expr alias) {
		super(locInfo);
		this.item = item;
		this.alias = alias;
	}

	public Expr getItem() {
		return this.item;
	}

	public Expr getAlias() {
		return this.alias;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
