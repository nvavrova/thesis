package ast.expression.arithmetic;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class FloorDivision extends Arithmetic {
	public FloorDivision(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}
}
