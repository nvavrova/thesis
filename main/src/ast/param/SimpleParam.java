package ast.param;

import ast.LocInfo;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;
import ast.Visitor;

/**
 * Created by Nik on 23-06-2015
 */
public class SimpleParam extends Param {

	public SimpleParam(@NotNull LocInfo locInfo, @NotNull Identifier id) {
		super(locInfo, id);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
