package ast.param;

import ast.AstNode;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 08-07-2015
 */
public abstract class Param extends AstNode {

	private Expr defaultVal;

	public Param(@NotNull Integer locInfo) {
		super(locInfo);
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

	public boolean isSelf() {
		return false;
	}

	public abstract List<String> getNames();
}
