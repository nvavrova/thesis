package ast;

import ast.expression.atom.Identifier;

/**
 * Created by Nik on 23-06-2015
 */
public abstract class Path extends Py3Node {

	protected final Identifier alias;

	public Path(LocInfo locInfo, Identifier alias) {
		super(locInfo);
		this.alias = alias;
	}

	public Identifier getAlias() {
		return this.alias;
	}

	public Boolean hasAlias() {
		return this.alias != null;
	}

	public abstract String getPath();
}
