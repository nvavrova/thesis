package ast.expression.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 12-06-2015
 */
public class Str extends Atom {

	private final String value;

	public Str(LocInfo locInfo, @NotNull String value) {
		super(locInfo);
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
