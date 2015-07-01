package ast.statement.simple;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Assert extends Statement {

	private final Expr assertion;
	private final Expr assertionError;

	public Assert(LocInfo locInfo, @NotNull Expr assertion) {
		this(locInfo, assertion, null);
	}

	public Assert(LocInfo locInfo, @NotNull Expr assertion, Expr assertionError) {
		super(locInfo);
		this.assertion = assertion;
		this.assertionError = assertionError;
	}

	public Expr getAssertion() {
		return this.assertion;
	}

	public Expr getAssertionError() {
		return this.assertionError;
	}

	public Boolean hasAssertionError() {
		return this.assertionError != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
