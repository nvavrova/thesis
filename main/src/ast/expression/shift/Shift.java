package ast.expression.shift;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Shift extends Expr {

	//TODO: add type of shift / operator!

	public Shift(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);
	}

}
