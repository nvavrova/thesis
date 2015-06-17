package ast.expression.datastruct;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.Map;

/**
 * Created by Nik on 25-5-15
 */
public class Dictionary extends SequenceContainer {

	private final Map<Expr, Expr> body;

	public Dictionary(LocInfo locInfo, Map<Expr, Expr> body) {
		super(locInfo);
		this.body = body;
	}
}
