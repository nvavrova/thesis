package ast.expression.bitwise;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Or extends Bitwise {
	public Or(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}
}
