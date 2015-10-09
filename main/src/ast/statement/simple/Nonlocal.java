package ast.statement.simple;

import ast.Visitor;
import ast.expression.no_cond.atom.Identifier;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Nonlocal extends Statement {

	private final List<Identifier> identifiers;

	public Nonlocal(@NotNull Integer locInfo, @NotNull List<Identifier> identifiers) {
		super(locInfo);
		this.identifiers = identifiers;
	}

	public List<Identifier> getIdentifiers() {
		return this.identifiers;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
