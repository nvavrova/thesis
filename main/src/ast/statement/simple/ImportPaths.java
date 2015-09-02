package ast.statement.simple;

import ast.Visitor;
import ast.path.Path;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class ImportPaths extends Import {

    public ImportPaths(@NotNull Integer locInfo, @NotNull List<Path> paths) {
        super(locInfo, paths);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
