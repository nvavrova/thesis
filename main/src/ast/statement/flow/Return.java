package ast.statement.flow;

import ast.statement.Statement;
import ast.statement.simple.ExprList;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Return extends Statement {

	private final ExprList values;

	public Return(@NotNull Integer locInfo) {
		this(locInfo, null);
	}

	public Return(@NotNull Integer locInfo, ExprList values) {
		super(locInfo);
		this.values = values;
	}

	public ExprList getValues() {
		return this.values;
	}

	public Boolean hasSingleReturnValue() {
		return this.hasValues() && this.values.size() == 1;
	}

	public Boolean hasValues() {
		return this.values != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
