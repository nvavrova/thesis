package ast.statement.simple;

import ast.LocInfo;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 17-06-2015
 */
public abstract class Assign extends Statement {

	private final String operator;
	private final ExprList targets;

	public Assign(@NotNull LocInfo locInfo, @NotNull String operator, @NotNull ExprList targets) {
		super(locInfo);
		this.operator = operator;
		this.targets = targets;
	}

	public String getOperator() {
		return this.operator;
	}

	public ExprList getTargets() {
		return this.targets;
	}
}
