package ast.expression.nocond.arithmetic;

import ast.expression.Expr;
import ast.expression.nocond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Nnary extends NonConditional {

	private final List<Expr> operands;
	private final List<String> operators;

	public Nnary(@NotNull Integer locInfo, @NotNull List<Expr> operands, @NotNull List<String> operators) {
		super(locInfo);

		this.operands = operands;
		this.operators = operators;
	}

	public List<Expr> getOperands() {
		return this.operands;
	}

	public List<String> getOperators() {
		return this.operators;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
