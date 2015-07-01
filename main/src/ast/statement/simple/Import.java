package ast.statement.simple;

import ast.LocInfo;
import ast.Path;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Import extends Statement {

    private final List<Path> paths;

    public Import(LocInfo locInfo, @NotNull List<Path> paths) {
        super(locInfo);
        this.paths = paths;
    }

	public List<Path> getPaths() {
		return this.paths;
	}

	@Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
