package ast;

import ast.expression.atom.Identifier;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimplePath extends Path {

	private final String path;

	public SimplePath(LocInfo locInfo, Identifier alias, String path) {
		super(locInfo, alias);
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
