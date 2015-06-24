package ast.expression;

import ast.LocInfo;
import ast.expression.primary.Subscript;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 24-06-2015
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

	public Expr getLowerBound() {
		return this.lowerBound;
	}

	public Expr getUpperBound() {
		return this.upperBound;
	}

	public Expr getStride() {
		return this.stride;
	}

	public Boolean hasLowerBound() {
		return this.lowerBound != null;
	}

	public Boolean hasUpperBound() {
		return this.upperBound != null;
	}

	public Boolean hasStride() {
		return this.stride != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
