package ast.statement.compound;

import ast.Decorator;
import ast.LocInfo;
import ast.Suite;
import ast.expression.Expr;
import ast.expression.atom.Identifier;
import ast.param.Params;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class DecoratedFunction extends Function {

	private final List<Decorator> decorators;

	public DecoratedFunction(LocInfo locInfo, Identifier name, Expr returnType, Suite body, Params params, List<Decorator> decorators) {
		super(locInfo, name, returnType, body, params);
		this.decorators = decorators;
	}

	public List<Decorator> getDecorators() {
		return this.decorators;
	}
}
