package ast.path;

import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;
import helpers.StringHelper;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class DottedPath extends Path {

	private static final String DELIMITER = ".";
	private List<String> path;

	public DottedPath(@NotNull Integer locInfo, @NotNull List<String> path) {
		super(locInfo);
		this.path = path;
	}

	public void addPrefixes(List<String> prefixes) {
		prefixes.addAll(this.path);
		this.path = prefixes;
	}

	@Override
	public String getPath() {
		return StringHelper.implode(this.path, DELIMITER);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
