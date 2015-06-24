package ast.node.expression.logical;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 08-06-2015
 */
public class Or extends Binary {

	public Or(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
