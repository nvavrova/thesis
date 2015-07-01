package ast.statement.simple;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Pass extends Statement {
	public Pass(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
