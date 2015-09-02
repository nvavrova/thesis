package ast.statement.simple;

import ast.Visitor;
import ast.expression.Expr;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 01-09-2015
 */
public class Exec extends Statement {

	private final Expr target;
	private final Expr vars;
	private final Expr localVars;

	public Exec(@NotNull Integer locInfo, @NotNull Expr target, Expr vars, Expr localVars) {
		super(locInfo);
		this.target = target;
		this.vars = vars;
		this.localVars = localVars;
	}

	public boolean hasVars() {
		return this.vars != null;
	}

	public boolean hasLocalVars() {
		return this.localVars != null;
	}

	public Expr getTarget() {
		return this.target;
	}

	public Expr getVars() {
		return this.vars;
	}

	public Expr getLocalVars() {
		return this.localVars;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
