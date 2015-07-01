package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Break extends Statement {
	public Break(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
