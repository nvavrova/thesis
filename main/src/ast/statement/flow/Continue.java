package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Continue extends Statement {
	public Continue(@NotNull LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
