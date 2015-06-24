package ast.node.statement.flow;

import ast.node.LocInfo;
import ast.node.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Continue extends Statement {
	public Continue(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
