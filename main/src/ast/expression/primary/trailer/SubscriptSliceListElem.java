package ast.expression.primary.trailer;

import ast.LocInfo;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 08-07-2015
 */
public abstract class SubscriptSliceListElem extends ExprNoCond {
	public SubscriptSliceListElem(@NotNull LocInfo locInfo) {
		super(locInfo);
	}
}
