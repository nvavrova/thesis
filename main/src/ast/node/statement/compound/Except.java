package ast.node.statement.compound;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 24-06-2015
 */
public class Except extends Statement {

	private final Expr exception;
	private final String alias;

	public Except(LocInfo locInfo, Expr exception, String alias) {
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

	public Boolean hasAlias() {
		return this.alias != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
