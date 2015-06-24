package ast.node.expression.maker;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.expression.display.CompFor;
import thesis.Py3TreeVisitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class SetMaker extends Maker {

	private final CompFor compFor;
	private final List<Expr> values;

	public SetMaker(LocInfo locInfo, CompFor compFor) {
		super(locInfo);
		this.compFor = compFor;
		this.values = Collections.emptyList();
	}

	public SetMaker(LocInfo locInfo, List<Expr> values) {
		super(locInfo);
		this.values = values;
		this.compFor = null;
	}

	public CompFor getCompFor() {
		return this.compFor;
	}

	public List<Expr> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
