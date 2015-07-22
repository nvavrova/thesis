package ast.statement.simple;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 24-06-2015
 */
public class AssignExpr extends Assign {

	private final ExprList source;

	public AssignExpr(@NotNull LocInfo locInfo, String operator, ExprList targets, @NotNull ExprList source) {
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
