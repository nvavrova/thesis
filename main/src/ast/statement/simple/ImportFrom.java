package ast.statement.simple;

import ast.Visitor;
import ast.path.Path;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class ImportFrom extends Import {

	private final Path module;

	public ImportFrom(@NotNull Integer locInfo, @NotNull List<Path> paths, @NotNull Path module) {
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
