package ast.expression;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 14-06-2015
 */
public class Conditional extends Expr {

	private final Expr value;
	private final Expr condition;
	private final Expr conditionFalseValue;

	public Conditional(LocInfo locInfo, @NotNull Expr value, @NotNull Expr condition, @NotNull Expr conditionFalseValue) {
		super(locInfo);
		this.value = value;
		this.condition = condition;
		this.conditionFalseValue = conditionFalseValue;
	}

	public Expr getValue() {
		return this.value;
	}

	public Expr getCondition() {
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
