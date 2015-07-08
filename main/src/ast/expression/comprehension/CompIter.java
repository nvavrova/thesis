package ast.expression.comprehension;

import ast.LocInfo;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class CompIter extends Expr {

	private final CompIter nextLink;

	public CompIter(@NotNull LocInfo locInfo, CompIter nextLink) {
		super(locInfo);
		this.nextLink = nextLink;
	}

	public Boolean hasNextLink() {
		return this.nextLink != null;
	}

	public CompIter getNextLink() {
		return this.nextLink;
	}
}
