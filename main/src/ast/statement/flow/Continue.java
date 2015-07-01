package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Visitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Continue extends Statement {
	public Continue(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
