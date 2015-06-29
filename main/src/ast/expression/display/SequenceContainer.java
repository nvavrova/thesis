package ast.expression.display;

import ast.LocInfo;
import ast.expression.ExprNoCond;

/**
 * Created by Nik on 25-05-15
 */
public abstract class SequenceContainer extends ExprNoCond {
	public SequenceContainer(LocInfo locInfo) {
		super(locInfo);
	}
}
