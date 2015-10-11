package ast.statement.flow;


import ast.expression.ExprList;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class YieldValues extends Yield {

	private final ExprList values;

	public YieldValues(@NotNull Integer locInfo) {
		this(locInfo, null);
	}

	public YieldValues(@NotNull Integer locInfo, ExprList values) {
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
