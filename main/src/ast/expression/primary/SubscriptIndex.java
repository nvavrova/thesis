package ast.expression.primary;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class SubscriptIndex extends Subscript {

	private final Expr index;

	public SubscriptIndex(LocInfo locInfo, Expr index) {
		super(locInfo);
		this.index = index;
	}

	public Expr getIndex() {
		return this.index;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
