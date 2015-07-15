package ast;

import org.antlr.v4.runtime.misc.NotNull;
import thesis.StringHelper;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class DottedPath extends Path {

	private static final String DELIMITER = ".";
	private final List<String> path;

	public DottedPath(@NotNull LocInfo locInfo, @NotNull List<String> path) {
		super(locInfo);
		this.path = path;
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
