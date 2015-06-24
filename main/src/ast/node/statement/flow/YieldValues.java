package ast.node.statement.flow;


import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class YieldValues extends Yield {

	private final List<Expr> values;

	public YieldValues(LocInfo locInfo) {
		super(locInfo);
		this.values = null;
	}

	public YieldValues(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
