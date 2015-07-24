package ast.statement.flow;

import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Break extends Statement {
	public Break(@NotNull Integer locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
