package ast.statement.simple;

import ast.expression.ExprList;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Assign extends ast.statement.Statement {

	private final String operator;
	private final List<ExprList> targets;

	public Assign(@NotNull Integer locInfo, @NotNull String operator, @NotNull ExprList targets) {
		super(locInfo);
		this.operator = operator;
		this.targets = new ArrayList<>();
		this.targets.add(targets);
	}

	public Assign(@NotNull Integer locInfo, @NotNull String operator, @NotNull List<ExprList> targets) {
		super(locInfo);
		this.operator = operator;
		this.targets = targets;
	}

	public String getOperator() {
		return this.operator;
	}

	public List<ExprList> getTargets() {
		return this.targets;
	}
}
