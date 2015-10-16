package ast.expression.nocond.atom;

import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Bool extends Atom {

	private final Boolean value;

	public Bool(@NotNull Integer locInfo, @NotNull Boolean value) {
		super(locInfo);
		this.value = value;
	}

	public Boolean getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
