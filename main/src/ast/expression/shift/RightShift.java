package ast.expression.shift;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class RightShift extends Shift {
	public RightShift(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}
}
