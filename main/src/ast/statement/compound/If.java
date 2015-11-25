package ast.statement.compound;

import ast.Suite;
import ast.Visitor;
import ast.expression.Expr;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class If extends Statement {

	private final List<Expr> conditions;
	private final List<Suite> bodies;
	private final Suite elseBody;

	public If(@NotNull Integer locInfo, @NotNull List<Expr> conditions, @NotNull List<Suite> condBodies, Suite elseBody) {
		super(locInfo);

		this.conditions = conditions;
		this.bodies = condBodies;
		this.elseBody = elseBody;
	}

	public List<Expr> getConditions() {
		return this.conditions;
	}

	public Suite getElseBody() {
		return this.elseBody;
	}

	public List<Suite> getBodies() {
		return this.bodies;
	}

	public Boolean hasElseBody() {
		return this.elseBody != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
