package ast.statement.simple;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Assert extends Statement {

	private final Expr assertion;
	private final Expr assertionError;

	public Assert(LocInfo locInfo, Expr assertion) {
		this(locInfo, assertion, null);
	}

	public Assert(LocInfo locInfo, Expr assertion, Expr assertionError) {
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

	public Boolean hasError() {
		return this.assertionError != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
