package ast.expression.nocond.bitwise;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Shift extends Bitwise {

	private final List<String> operators;

	public Shift(@NotNull Integer locInfo, @NotNull List<Expr> operands, @NotNull List<String> operators) {
		super(locInfo, operands);
		this.operators = operators;
	}

	public List<String> getOperators() {
		return this.operators;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
