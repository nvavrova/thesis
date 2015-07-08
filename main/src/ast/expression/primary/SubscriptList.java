package ast.expression.primary;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class SubscriptList extends Subscript implements Trailer {

	private final List<SubscriptIndex> values;

	public SubscriptList(@NotNull LocInfo locInfo, @NotNull List<SubscriptIndex> values) {
		super(locInfo);
		this.values = values;
	}

	public List<SubscriptIndex> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		this.values.forEach(s -> sb.append(s.toString()));
		sb.append("]");
		return sb.toString();
	}

	@Override
	public Boolean isCall() {
		return false;
	}

	@Override
	public Boolean isAttribute() {
		return false;
	}

	@Override
	public Boolean isSubscript() {
		return true;
	}

	@Override
	public <T> T accept(TrailerVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
