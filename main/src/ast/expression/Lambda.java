package ast.expression;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 26-05-2015
 */
public class Lambda extends Expr {

	//TODO: add arguments
	private final Expr test;

	public Lambda(LocInfo locInfo, Expr test) {
		super(locInfo);
		this.test = test;
	}

	public Expr getTest() {
		return test;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
