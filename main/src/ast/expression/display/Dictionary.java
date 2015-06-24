package ast.expression.display;

import ast.LocInfo;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

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

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
