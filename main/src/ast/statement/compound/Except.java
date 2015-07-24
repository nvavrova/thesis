package ast.statement.compound;

import ast.expression.Expr;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 24-06-2015
 */
public class Except extends Statement {

	private final Expr exception;
	private final String alias;

	public Except(@NotNull Integer locInfo, Expr exception, String alias) {
		super(locInfo);
		this.exception = exception;
		this.alias = alias;
	}

	public Expr getException() {
		return this.exception;
	}

	public String getAlias() {
		return this.alias;
	}

	public Boolean hasException() {
		return this.exception != null;
	}

	public Boolean hasAlias() {
		return this.alias != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
