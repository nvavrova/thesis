package ast.node.expression.maker;

import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.expression.display.CompFor;
import thesis.Py3TreeVisitor;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Nik on 15-06-2015
 */
public class DictMaker extends Maker {

	private final CompFor compFor;
	private final Map<Expr, Expr> values;

	public DictMaker(LocInfo locInfo, CompFor compFor) {
		super(locInfo);
		this.compFor = compFor;
		this.values = Collections.emptyMap();
	}
	public DictMaker(LocInfo locInfo, Map<Expr, Expr> values) {
		super(locInfo);
		this.values = values;
		this.compFor = null;
	}

	public CompFor getCompFor() {
		return this.compFor;
	}

	public Map<Expr, Expr> getValues() {
		return this.values;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
