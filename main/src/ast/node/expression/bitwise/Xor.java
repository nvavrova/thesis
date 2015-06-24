package ast.node.expression.bitwise;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Xor extends Bitwise {

	public Xor(LocInfo locInfo, List<Expr> operands) {
		super(locInfo, operands);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
