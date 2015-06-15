package ast.expression.logical;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 14-06-2015
 */
public class And extends Binary {
	public And(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}
}
