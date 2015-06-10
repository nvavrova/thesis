package ast.expression.arithmetic;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Addition extends Arithmetic {
	public Addition(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}
}
