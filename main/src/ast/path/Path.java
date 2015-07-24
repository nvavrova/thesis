package ast.path;

import ast.AstNode;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class Path extends AstNode {

	private Identifier alias;

	public Path(@NotNull Integer locInfo) {
		super(locInfo);
	}

	public Identifier getAlias() {
		return this.alias;
	}

	public void setAlias(Identifier alias) {
		this.alias = alias;
	}

	public Boolean hasAlias() {
		return this.alias != null;
	}

	public abstract String getPath();
}
