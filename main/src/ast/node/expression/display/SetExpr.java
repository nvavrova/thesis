package ast.node.expression.display;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.Set;

/**
 * Created by Nik on 25-05-15
 */
public class SetExpr extends Expr {

	private final Set<Expr> body;

	public SetExpr(LocInfo locInfo, Set<Expr> body) {
		super(locInfo);
		this.body = body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
