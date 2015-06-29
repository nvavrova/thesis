package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.expression.Expr;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class For extends Statement {

	private final List<Expr> iterator;
	private final List<Expr> source;
	private final Suite body;
	private final Suite elseBody;

	public For(LocInfo locInfo, List<Expr> iterator, List<Expr> source, Suite body, Suite elseBody) {
		super(locInfo);
		this.iterator = iterator;
		this.source = source;
		this.body = body;
		this.elseBody = elseBody;
	}

	public Suite getBody() {
		return this.body;
	}

	public Suite getElseBody() {
		return this.elseBody;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
