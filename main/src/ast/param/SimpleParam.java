package ast.param;

import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 01-09-2015
 */
public abstract class SimpleParam extends Param {

	private final Identifier id;

	public SimpleParam(@NotNull Integer locInfo, Identifier id) {
		super(locInfo);
		this.id = id;
	}

	public Identifier getId() {
		return this.id;
	}

	@Override
	public boolean isSelf() {
		return this.id.isSelf();
	}

	@Override
	public List<String> getNames() {
		List<String> names = new ArrayList<>();
		names.add(this.id.getValue());
		return names;
	}
}
