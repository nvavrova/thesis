package ast;

import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimplePath extends Path {

	private final String path;

	public SimplePath(LocInfo locInfo, @NotNull String path) {
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
