package ast.expression.primary;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class SliceBound extends Subscript {

	private final Expr lowerBound;
	private final Expr upperBound;
	private final Expr stride;

	public SliceBound(LocInfo locInfo, Expr lowerBound, Expr upperBound, Expr stride) {
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
