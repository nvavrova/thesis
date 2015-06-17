package ast.expression.maker;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.Map;

/**
 * Created by Nik on 15-06-2015
 */
public class DictMaker extends Maker {

	private final Map<Expr, Expr> values;

	public DictMaker(LocInfo locInfo, Map<Expr, Expr> values) {
		super(locInfo);
		this.values = values;
	}

	public Map<Expr, Expr> getValues() {
		return values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
