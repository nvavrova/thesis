package ast.expression.comprehension;

import ast.LocInfo;
import ast.expression.Expr;
import ast.statement.simple.ExprList;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 17-06-2015
 */
public class CompFor extends CompIter {

	private final ExprList targets;
	private final Expr source;

	public CompFor(@NotNull LocInfo locInfo, CompIter nextLink, @NotNull ExprList targets, @NotNull Expr source) {
		super(locInfo, nextLink);
		this.targets = targets;
		this.source = source;
	}

	public ExprList getTargets() {
		return this.targets;
	}

	public Expr getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
