package ast.statement.simple;

import ast.LocInfo;
import ast.Path;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Import extends Statement {

    private final List<Path> paths;

    public Import(LocInfo locInfo, List<Path> paths) {
        super(locInfo);
        this.paths = paths;
    }

	public List<Path> getPaths() {
		return this.paths;
	}

	@Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
