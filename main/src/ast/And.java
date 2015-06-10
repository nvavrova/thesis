package ast;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 08-06-2015
 */
public class And extends Expr {

	private final List<Expr> children;

	public And(LocInfo locInfo, List<Expr> children) {
		super(locInfo);
		this.children = children;
	}

	public List<Expr> getChildren() {
		return children;
	}
}
