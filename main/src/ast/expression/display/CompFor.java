package ast.expression.display;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.logical.Logical;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class CompFor extends CompIter {

	private final List<Expr> targets;
	private final Logical source;

	public CompFor(LocInfo locInfo, CompIter nextLink, List<Expr> targets, Logical source) {
		super(locInfo, nextLink);
		this.targets = targets;
		this.source = source;
	}

	public List<Expr> getTargets() {
		return targets;
	}

	public Logical getSource() {
		return source;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
