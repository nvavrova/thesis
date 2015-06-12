package ast.expression;

import ast.LocInfo;

import java.util.List;

/**
 * Created by Nik on 10-06-2015
 */
public class Shift extends Expr {

	private final List<Expr> operands;

	public Shift(LocInfo locInfo, List<Expr> operands) {
		super(locInfo);
		this.operands = operands;
	}

}
