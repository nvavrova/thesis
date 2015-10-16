package ast.expression.nocond.trailer;

import ast.expression.nocond.NonConditional;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 08-07-2015
 */
public abstract class SubscriptSliceListElem extends NonConditional {
	public SubscriptSliceListElem(@NotNull Integer locInfo) {
		super(locInfo);
	}
}
