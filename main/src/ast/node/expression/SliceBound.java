package ast.node.expression;

/**
 * Created by Nik on 24-06-2015
 */
public class SliceBound {

	private final Expr lowerBound;
	private final Expr upperBound;
	private final Expr stride;

	public SliceBound(Expr lowerBound) {
		this(lowerBound, null, null);
	}

	public SliceBound(Expr lowerBound, Expr upperBound) {
		this(lowerBound, upperBound, null);
	}

	public SliceBound(Expr lowerBound, Expr upperBound, Expr stride) {
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
}
