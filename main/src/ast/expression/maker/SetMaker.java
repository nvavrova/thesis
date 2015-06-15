package ast.expression.maker;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class SetMaker extends Maker {

	private final List<Expr> values;

	public SetMaker(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Expr> getValues() {
		return values;
	}
}
