package ast.expression.display;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

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

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
