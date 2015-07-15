package ast.statement.simple;

import ast.LocInfo;
import ast.Path;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 25-05-15
 */
public class ImportFrom extends Import {

	private final Path module;

	public ImportFrom(@NotNull LocInfo locInfo, @NotNull List<Path> paths, Path module) {
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

	@Override
	public Map<String, String> getFullPaths() {
		Map<String, String> paths = new HashMap<>();
		String modulePath = this.hasModule() ? this.module.getPath() + "." : "";
		for (Path p : this.getPaths()) {
			String key = modulePath + p.getPath();
			String val = p.hasAlias() ? p.getAlias().toString() : null;
			paths.put(key, val);
		}
		return paths;
	}
}
