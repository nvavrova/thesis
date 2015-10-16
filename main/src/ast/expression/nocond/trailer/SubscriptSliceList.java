package ast.expression.nocond.trailer;

import ast.expression.nocond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.List;

/**
 * Created by Nik on 08-07-2015
 */
public class SubscriptSliceList extends NonConditional implements Trailer {

	private final List<SubscriptSliceListElem> indexes;

	public SubscriptSliceList(@NotNull Integer locInfo, List<SubscriptSliceListElem> indexes) {
		super(locInfo);
		this.indexes = indexes;
	}

	public List<SubscriptSliceListElem> getIndexes() {
		return this.indexes;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <T> T accept(TrailerVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
