package ast.expression.maker;

import ast.LocInfo;
import ast.expression.Expr;
import ast.expression.display.CompFor;
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
		return compFor;
	}

	public List<Expr> getValues() {
		return values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
