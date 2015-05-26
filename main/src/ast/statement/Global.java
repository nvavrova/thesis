package ast.statement;

import ast.Identifier;
import ast.LocInfo;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Global extends Statement {

	private final List<Identifier> identifiers;

	public Global(LocInfo locInfo, List<Identifier> identifiers) {
		super(locInfo);
		this.identifiers = identifiers;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}
}
