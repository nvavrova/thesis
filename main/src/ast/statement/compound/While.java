package ast.statement.compound;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class While extends Statement {

	private final Expr condition;
	private final List<Statement> body;
	private final List<Statement> elseBody;

	public While(LocInfo locInfo, Expr condition, List<Statement> body, List<Statement> elseBody) {
		super(locInfo);
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}

	public Expr getCondition() {
		return condition;
	}

	public List<Statement> getBody() {
		return body;
	}

	public List<Statement> getElseBody() {
		return elseBody;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
