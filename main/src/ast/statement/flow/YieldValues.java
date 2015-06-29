package ast.statement.flow;


import ast.LocInfo;
import ast.statement.simple.ExprList;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class YieldValues extends Yield {

	private final ExprList values;

	public YieldValues(LocInfo locInfo) {
		super(locInfo);
		this.values = null;
	}

	public YieldValues(LocInfo locInfo, ExprList values) {
		super(locInfo);
		this.values = values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
