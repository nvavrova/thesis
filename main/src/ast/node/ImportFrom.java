package ast.node;

import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class ImportFrom extends Import {

    private final Path module;

    public ImportFrom(LocInfo locInfo, List<Path> paths, Path module) {
        super(locInfo, paths);
        this.module = module;
    }

    public Path getModule() {
        return this.module;
    }

    @Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
