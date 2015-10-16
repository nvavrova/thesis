package ast.expression.nocond.trailer;

import ast.Visitor;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public class SliceBound extends SubscriptSliceListElem {

	private final Expr lowerBound;
	private final Expr upperBound;
	private final Expr stride;

	public SliceBound(@NotNull Integer locInfo, Expr lowerBound, Expr upperBound, Expr stride) {
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
