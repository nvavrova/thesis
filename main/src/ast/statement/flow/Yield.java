package ast.statement.flow;


import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Yield extends Flow {

	private final List<Expr> values;

	public Yield(LocInfo locInfo) {
		super(locInfo);
		this.values = null;
	}

	public Yield(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
