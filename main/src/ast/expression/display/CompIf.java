package ast.expression.display;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class CompIf extends CompIter {

	public CompIf(LocInfo locInfo, CompIter nextLink) {
		super(locInfo, nextLink);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
