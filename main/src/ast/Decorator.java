package ast;

import ast.expression.primary.ArgList;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Decorator extends Py3Node {

	private final DottedPath name;
	private final ArgList argList;

	public Decorator(LocInfo locInfo, DottedPath name, ArgList argList) {
		super(locInfo);
		this.name = name;
		this.argList = argList;
	}

	public DottedPath getName() {
		return this.name;
	}

	public ArgList getArgList() {
		return this.argList;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
