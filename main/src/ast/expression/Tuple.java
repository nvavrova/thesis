package ast.expression;

import ast.LocInfo;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Tuple extends Sequence {

	private final List<Expr> body;

	public Tuple(LocInfo locInfo, List<Expr> body) {
		super(locInfo);
		this.body = body;
	}
}
