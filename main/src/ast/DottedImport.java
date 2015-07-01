package ast;

import ast.statement.simple.Import;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 17-06-2015
 */
public class DottedImport extends Import {

	public DottedImport(LocInfo locInfo, List<Path> paths) {
		super(locInfo, paths);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
