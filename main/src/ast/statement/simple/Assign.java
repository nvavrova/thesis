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
	private final List<ExprList> elements;

	public Assign(@NotNull Integer locInfo, @NotNull String operator, @NotNull ExprList elements) {
		super(locInfo);
		this.operator = operator;
		this.elements = new ArrayList<>();
		this.elements.add(elements);
	}

	public Assign(@NotNull Integer locInfo, @NotNull String operator, @NotNull List<ExprList> elements) {
		super(locInfo);
		this.operator = operator;
		this.elements = elements;
	}

	public String getOperator() {
		return this.operator;
	}

	public List<ExprList> getElements() {
		return this.elements;
	}
}
