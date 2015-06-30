package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Break extends Statement {
	public Break(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
