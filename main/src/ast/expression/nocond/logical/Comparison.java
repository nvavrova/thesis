package ast.expression.nocond.logical;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Comparison extends Logical {

	private final List<Expr> operands;
	private final List<String> operators;

	public Comparison(@NotNull Integer locInfo, @NotNull List<Expr> operands, @NotNull List<String> operators) {
		super(locInfo);

		assert operands.size() - 1 == operators.size();

		this.operands = operands;
		this.operators = operators;
	}

	public List<Expr> getOperands() {
		return this.operands;
	}

	public List<String> getOperators() {
		return this.operators;
	}

	public Integer getArity() {
		return this.operands.size();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
