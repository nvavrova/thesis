package ast.statement.simple;

import ast.LocInfo;
import ast.Path;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class ImportFrom extends Import {

    private final Path module;

    public ImportFrom(LocInfo locInfo, @NotNull List<Path> paths, Path module) {
        super(locInfo, paths);
        this.module = module;
    }

    public Path getModule() {
        return this.module;
    }

    public Boolean hasModule() {
        return this.module != null;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
