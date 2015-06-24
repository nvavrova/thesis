package ast.node.expression.display;

import ast.node.LocInfo;
import ast.node.expression.Expr;

/**
 * Created by Nik on 25-05-15
 */
public abstract class Sequence extends Expr {

	public Sequence(LocInfo locInfo) {
		super(locInfo);
	}

}
