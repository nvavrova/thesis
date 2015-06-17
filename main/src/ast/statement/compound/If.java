package ast.statement.compound;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.*;

/**
 * Created by Nik on 17-06-2015
 */
public class If extends Statement {

	private final List<Expr> conditions;
	private final Map<Expr, List<Statement>> bodies;
	private final List<Statement> elseBody;

	public If(LocInfo locInfo, Expr condition, List<Statement> condBody, List<Statement> elseBody) {
		super(locInfo);

		this.conditions = new ArrayList<>();
		this.bodies = new HashMap<>();
		this.elseBody = elseBody;

		this.addClause(condition, condBody);
	}

	public void addElseIf(Expr condition, List<Statement> condBody) {
		this.addClause(condition, condBody);
	}

	private void addClause(Expr condition, List<Statement> condBody) {
		this.conditions.add(condition);
		this.bodies.put(condition, condBody);
	}

	public List<Expr> getConditions() {
		return conditions;
	}

	public List<Statement> getElseBody() {
		return elseBody;
	}

	public List<Statement> getBody(Expr condition) {
		assert (this.bodies.containsKey(condition));
		return this.bodies.get(condition);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
