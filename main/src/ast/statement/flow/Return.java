package ast.statement.flow;

import ast.LocInfo;
import ast.statement.simple.ExprList;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Return extends Flow {

	private final ExprList values;

	public Return(LocInfo locInfo) {
		this(locInfo, null);
	}

	public Return(LocInfo locInfo, ExprList values) {
		super(locInfo);
		this.values = values;
	}

	public ExprList getValues() {
		return this.values;
	}

	public Boolean hasValues() {
		return this.values != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
