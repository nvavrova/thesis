package ast.node.expression.primary;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class SubscriptSlice extends Subscript {

	private final Expr lowerBound;
	private final Expr upperBound;
	private final Expr stride;

	public SubscriptSlice(LocInfo locInfo, Expr lowerBound, Expr upperBound, Expr stride) {
		super(locInfo);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.stride = stride;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
