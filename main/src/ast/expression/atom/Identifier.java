package ast.expression.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Identifier extends Atom {

	private final String value;

	public Identifier(LocInfo locInfo, @NotNull String value) {
		super(locInfo);
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public Boolean startsWith(String prefix) {
		return this.value.startsWith(prefix);
	}

	public Boolean contains(String s) {
		return this.contains(s, true);
	}

	public Boolean contains(String s, Boolean caseSensitive) {
		if (caseSensitive) {
			return this.value.toLowerCase().contains(s.toLowerCase());
		}
		return this.value.contains(s);
	}

	public Boolean isSelf() {
		return this.value.equals("self");
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
