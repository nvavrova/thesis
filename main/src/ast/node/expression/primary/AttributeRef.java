package ast.node.expression.primary;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class AttributeRef extends Expr {
	public AttributeRef(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
