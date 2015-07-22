package ast.param;

import ast.LocInfo;
import ast.AstNode;
import ast.expression.Expr;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 08-07-2015
 */
public abstract class Param extends AstNode {

	private final Identifier id;
	private Expr defaultVal;

	public Param(@NotNull LocInfo locInfo, @NotNull Identifier id) {
		super(locInfo);
		this.id = id;
	}

	public Identifier getId() {
		return this.id;
	}

	public Expr getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(@NotNull Expr defaultVal) {
		this.defaultVal = defaultVal;
	}

	public Boolean hasDefaultVal() {
		return this.defaultVal != null;
	}

	public Boolean isSelf() {
		return this.id.isSelf();
	}
}
