package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 17-06-2015
 */
public class If extends Statement {

	private final List<Expr> conditions;
	private final Map<Expr, Suite> bodies;
	private final Suite elseBody;

	public If(LocInfo locInfo, Expr condition, Suite condBody, Suite elseBody) {
		super(locInfo);

		this.conditions = new ArrayList<>();
		this.bodies = new HashMap<>();
		this.elseBody = elseBody;

		this.addClause(condition, condBody);
	}

	public void addElseIf(Expr condition, Suite condBody) {
		this.addClause(condition, condBody);
	}

	private void addClause(Expr condition, Suite condBody) {
		this.conditions.add(condition);
		this.bodies.put(condition, condBody);
	}

	public List<Expr> getConditions() {
		return this.conditions;
	}

	public Suite getElseBody() {
		return this.elseBody;
	}

	public Suite getBody(Expr condition) {
		assert (this.bodies.containsKey(condition));
		return this.bodies.get(condition);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
