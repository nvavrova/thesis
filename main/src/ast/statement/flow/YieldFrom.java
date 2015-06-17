package ast.statement.flow;

import ast.LocInfo;
import ast.expression.Expr;

/**
 * Created by Nik on 09-06-2015
 */
public class YieldFrom extends Flow {

	private final Expr from;

	public YieldFrom(LocInfo locInfo, Expr from) {
		super(locInfo);
		this.from = from;
	}

	public Expr getFrom() {
		return from;
	}
}
