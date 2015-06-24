package ast.expression.primary;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class Subscripts extends Expr {

	private final List<Subscript> values;

	public Subscripts(LocInfo locInfo, List<Subscript> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Subscript> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return null;
	}
}
