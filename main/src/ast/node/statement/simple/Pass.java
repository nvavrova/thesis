package ast.node.statement.simple;

import ast.node.LocInfo;
import ast.node.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Pass extends Statement {
	public Pass(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
