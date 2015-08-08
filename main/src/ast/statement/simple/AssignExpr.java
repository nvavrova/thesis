package ast.statement.simple;

import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignExpr extends Assign {

	private final ExprList source;

	public AssignExpr(@NotNull Integer locInfo, String operator, ExprList targets, @NotNull ExprList source) {
		super(locInfo, operator, targets);
		this.source = source;
	}

	public AssignExpr(@NotNull Integer locInfo, String operator, List < ExprList > targets, @NotNull ExprList source) {
		super(locInfo, operator, targets);
		this.source = source;
	}

	public ExprList getSource() {
		return this.source;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
