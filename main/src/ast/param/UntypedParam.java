package ast.param;

import ast.Visitor;
import ast.expression.nocond.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 23-06-2015
 */
public class UntypedParam extends SimpleParam {

	public UntypedParam(@NotNull Integer locInfo, @NotNull Identifier id) {
		super(locInfo, id);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
