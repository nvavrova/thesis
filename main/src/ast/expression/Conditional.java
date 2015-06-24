package ast.expression;

import ast.LocInfo;
import ast.expression.logical.Logical;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 14-06-2015
 */
public class Conditional extends Expr {

	private final Logical value;
	private final Logical condition;
	private final Expr conditionFalseValue;

	public Conditional(LocInfo locInfo, Logical value) {
		this(locInfo, value, null, null);
	}

	public Conditional(LocInfo locInfo, Logical value, Logical condition, Expr conditionFalseValue) {
		super(locInfo);
		this.value = value;
		this.condition = condition;
		this.conditionFalseValue = conditionFalseValue;
	}

	public Logical getValue() {
		return this.value;
	}

	public Logical getCondition() {
		return this.condition;
	}

	public Expr getConditionFalseValue() {
		return this.conditionFalseValue;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
