package ast.statement.simple;

import ast.LocInfo;
import ast.Path;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class SimpleImport extends Import {

    public SimpleImport(@NotNull LocInfo locInfo, @NotNull List<Path> paths) {
        super(locInfo, paths);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
