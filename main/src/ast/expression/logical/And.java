package ast.expression.logical;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 08-06-2015
 */
public class And extends Binary {

	public And(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
