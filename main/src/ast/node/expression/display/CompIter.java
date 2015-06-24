package ast.node.expression.display;

import ast.node.LocInfo;
import ast.node.expression.Expr;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class CompIter extends Expr {

	private final CompIter nextLink;

	public CompIter(LocInfo locInfo, CompIter nextLink) {
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
