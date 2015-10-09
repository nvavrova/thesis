package ast.argument;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.no_cond.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public class DefValArgument extends Argument {

	private final Identifier name;

	public DefValArgument(@NotNull Integer locInfo, @NotNull Expr value, @NotNull Identifier name) {
		super(locInfo, value);
		this.name = name;
	}

	public Identifier getName() {
		return this.name;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
