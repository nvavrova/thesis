package ast.statement;

import ast.Identifier;
import ast.LocInfo;

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
}
