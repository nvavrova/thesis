package ast.param;

import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 01-09-2015
 */
public class ListParam extends Param {

	private final List<Param> params;

	public ListParam(@NotNull Integer locInfo, @NotNull List<Param> params) {
		super(locInfo);
		this.params = params;
	}

	public List<Param> getParams() {
		return this.params;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public List<String> getNames() {
		List<String> names = new ArrayList<>();
		params.forEach(p -> names.addAll(p.getNames()));
		return names;
	}
}
