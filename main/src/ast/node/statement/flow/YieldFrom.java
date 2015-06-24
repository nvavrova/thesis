package ast.node.statement.flow;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 09-06-2015
 */
public class YieldFrom extends Yield {

	private final Expr from;

	public YieldFrom(LocInfo locInfo, Expr from) {
		super(locInfo);
		this.from = from;
	}

	public Expr getFrom() {
		return this.from;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
