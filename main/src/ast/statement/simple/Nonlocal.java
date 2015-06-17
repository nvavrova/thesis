package ast.statement.simple;

import ast.expression.atom.Identifier;
import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Nonlocal extends Statement {

	private final List<Identifier> identifiers;

	public Nonlocal(LocInfo locInfo, List<Identifier> identifiers) {
		super(locInfo);
		this.identifiers = identifiers;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
