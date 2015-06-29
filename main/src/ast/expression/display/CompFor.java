package ast.expression.display;

import ast.LocInfo;
import ast.expression.logical.Logical;
import ast.statement.simple.ExprList;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class CompFor extends CompIter {

	private final ExprList targets;
	private final Logical source;

	public CompFor(LocInfo locInfo, CompIter nextLink, ExprList targets, Logical source) {
		super(locInfo, nextLink);
		this.targets = targets;
		this.source = source;
	}

	public ExprList getTargets() {
		return this.targets;
	}

	public Logical getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
