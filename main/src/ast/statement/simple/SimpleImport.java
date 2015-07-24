package ast.statement.simple;

import ast.path.Path;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 25-05-15
 */
public class SimpleImport extends Import {

    public SimpleImport(@NotNull Integer locInfo, @NotNull List<Path> paths) {
        super(locInfo, paths);
    }

    @Override
    public Map<String, String> getFullPaths() {
	    Map<String, String> paths = new HashMap<>();
	    for (Path p : this.getPaths()) {
		    String key = p.getPath();
		    String val = p.hasAlias() ? p.getAlias().toString() : null;
		    paths.put(key, val);
	    }
	    return paths;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
