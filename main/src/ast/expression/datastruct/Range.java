package ast.expression.datastruct;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-15
 */
public class Range extends Sequence {
	public Range(LocInfo locInfo) {
		super(locInfo);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
