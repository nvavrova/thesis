package ast.expression.display;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class EnumComprehension extends Comprehension {

	private final List<Expr> values;

	public EnumComprehension(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
