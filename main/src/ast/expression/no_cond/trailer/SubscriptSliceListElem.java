package ast.expression.no_cond.trailer;

import ast.expression.no_cond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 08-07-2015
 */
public abstract class SubscriptSliceListElem extends NonConditional {
	public SubscriptSliceListElem(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
