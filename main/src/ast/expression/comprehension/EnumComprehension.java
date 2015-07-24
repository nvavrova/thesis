package ast.expression.comprehension;

import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class EnumComprehension extends Comprehension {

	private final List<Expr> values;

	public EnumComprehension(@NotNull Integer locInfo, @NotNull List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
