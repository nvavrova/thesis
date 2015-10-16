package ast;

import ast.expression.nocond.trailer.ArgList;
import ast.path.DottedPath;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 15-06-2015
 */
public class Decorator extends AstNode {

	private final DottedPath name;
	private final ArgList argList;

	public Decorator(@NotNull Integer locInfo, @NotNull DottedPath name, ArgList argList) {
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

	public Boolean hasArgList() {
		return this.argList != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
