package ast.expression.atom;

import ast.LocInfo;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class DottedIdentifier extends Atom {

	private final List<String> names;

	public DottedIdentifier(LocInfo locInfo, List<String> names) {
		super(locInfo);
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
