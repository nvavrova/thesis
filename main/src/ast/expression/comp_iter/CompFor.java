package ast.expression.comp_iter;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.ExprList;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class CompFor extends CompIter {

	private final ExprList targets;
	private final List<Expr> source;

	public CompFor(@NotNull Integer locInfo, CompIter nextLink, @NotNull ExprList targets, @NotNull List<Expr> source) {
		super(locInfo, nextLink);
		this.targets = targets;
		this.source = source;
	}

	public ExprList getTargets() {
		return this.targets;
	}

	public List<Expr> getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
