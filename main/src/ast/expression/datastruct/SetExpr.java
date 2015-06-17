package ast.expression.datastruct;

import ast.LocInfo;
import ast.expression.Expr;

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
}
