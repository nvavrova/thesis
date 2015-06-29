package ast.expression.display;

import ast.LocInfo;
import ast.expression.ExprNoCond;

/**
 * Created by Nik on 25-05-15
 */
public abstract class Sequence extends ExprNoCond {

	public Sequence(LocInfo locInfo) {
		super(locInfo);
	}

}
