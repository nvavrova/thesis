package ast;

import ast.arg.Args;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Decorator extends Py3Node {

	private final DottedImport name;
	private final Args args;

	public Decorator(LocInfo locInfo, DottedImport name, Args args) {
		super(locInfo);
		this.name = name;
		this.args = args;
	}

	public DottedImport getName() {
		return this.name;
	}

	public Args getArgs() {
		return this.args;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
