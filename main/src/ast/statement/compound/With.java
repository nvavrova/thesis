package ast.statement.compound;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-06-2015
 */
public class With extends Statement {

	private final List<WithItem> items;
	private final List<Statement> body;

	public With(LocInfo locInfo, List<WithItem> items, List<Statement> body) {
		super(locInfo);
		this.items = items;
		this.body = body;
	}

	public List<WithItem> getItems() {
		return this.items;
	}

	public List<Statement> getBody() {
		return this.body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
