package ast.expression.atom;

import ast.LocInfo;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-2015
 */
public class Bool extends Literal {

	private final Boolean value;

	public Bool(LocInfo locInfo, @NotNull Boolean value) {
		super(locInfo);
		this.value = value;
	}

	public Boolean getValue() {
		return this.value;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
