package ast.expression.primary.atom;

import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 02-09-2015
 */
public class Long extends Atom {

	private final String value;

	public Long(@NotNull Integer locInfo, @NotNull String value) {
		super(locInfo);
		this.value = value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
