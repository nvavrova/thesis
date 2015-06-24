package ast.param;

import ast.LocInfo;
import ast.Py3Node;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Parameter extends Py3Node {

	private final String name;
	private final Expr defValue;
	private final Expr type;

	public Parameter(LocInfo locInfo, String name) {
		this(locInfo, name, null, null);
	}

	public Parameter(LocInfo locInfo, String name, Expr defValue) {
		this(locInfo, name, defValue, null);
	}

	public Parameter(LocInfo locInfo, String name, Expr defValue, Expr type) {
		super(locInfo);
		this.name = name;
		this.defValue = defValue;
		this.type = type;
	}

	public Boolean isTyped() {
		return this.type != null;
	}

	public Boolean hasDefValue() {
		return this.defValue != null;
	}

	public String getName() {
		return this.name;
	}

	public Expr getType() {
		return this.type;
	}

	public Expr getDefValue() {
		return this.defValue;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
