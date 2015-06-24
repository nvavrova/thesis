package ast.node.expression.logical;

import ast.node.LocInfo;
import ast.node.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public abstract class Binary extends Logical {

	private final List<Expr> operands;

	public Binary(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}
}
