package ast.statement.simple;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class Assign extends Statement {

	//TODO

	public Assign(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
