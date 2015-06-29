package ast.statement.flow;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Raise extends Flow {

	private final Expr type;
	private final Expr source;

	public Raise(LocInfo locInfo, Expr type, Expr source) {
		super(locInfo);
		this.type = type;
		this.source = source;
	}

	public Expr getType() {
		return this.type;
	}

	public Expr getSource() {
		return this.source;
	}

	public Boolean hasType() {
		return this.type != null;
	}

	public Boolean hasSource() {
		return this.source != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
