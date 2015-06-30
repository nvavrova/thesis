package ast.expression.primary;

import ast.LocInfo;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class SubscriptList extends ExprNoCond {

	private final List<Subscript> values;

	public SubscriptList(LocInfo locInfo, @NotNull List<Subscript> values) {
		super(locInfo);
		this.values = values;
	}

	public List<Subscript> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
