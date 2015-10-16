package ast.statement.compound;

import ast.Visitor;
import ast.expression.Expr;
import ast.expression.nocond.atom.Identifier;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 25-06-2015
 */
public class WithItem extends Statement {

	private final Expr item;
	private final Identifier name;
	private final Expr alias;

	public WithItem(@NotNull Integer locInfo, @NotNull Expr item, Identifier name, Expr alias) {
		super(locInfo);
		this.item = item;
		this.name = name;
		this.alias = alias;
	}

	public Expr getItem() {
		return this.item;
	}

	public Identifier getName() {
		return this.name;
	}

	public Expr getAlias() {
		return this.alias;
	}

	public boolean hasAlias() {
		return this.alias != null;
	}

	public boolean hasName() {
		return this.name != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
