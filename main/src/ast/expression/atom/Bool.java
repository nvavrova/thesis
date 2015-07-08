package ast.expression.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Bool extends Literal {

	private final Boolean value;

	public Bool(@NotNull LocInfo locInfo, @NotNull Boolean value) {
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
