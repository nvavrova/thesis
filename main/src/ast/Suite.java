package ast;

import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-06-2015
 */
public class Suite extends Py3Node {

	private final List<Statement> statements;

	public Suite(LocInfo locInfo, List<Statement> statements) {
		super(locInfo);
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return this.statements;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
