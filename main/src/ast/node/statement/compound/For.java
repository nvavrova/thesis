package ast.node.statement.compound;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class For extends Statement {

	private final List<Expr> iterator;
	private final List<Expr> source;
	private final List<Statement> body;
	private final List<Statement> elseBody;

	public For(LocInfo locInfo, List<Expr> iterator, List<Expr> source, List<Statement> body, List<Statement> elseBody) {
		super(locInfo);
		this.iterator = iterator;
		this.source = source;
		this.body = body;
		this.elseBody = elseBody;
	}

	public List<Statement> getBody() {
		return this.body;
	}

	public List<Statement> getElseBody() {
		return this.elseBody;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
