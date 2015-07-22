package ast.path;

import ast.LocInfo;
import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public class SimplePath extends Path {

	private final String path;

	public SimplePath(@NotNull LocInfo locInfo, @NotNull String path) {
		super(locInfo);
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
