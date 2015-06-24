package ast.node.statement.compound;

import ast.node.Decorator;
import ast.node.LocInfo;
import ast.node.expression.Expr;
import ast.node.param.Params;
import ast.node.expression.atom.Identifier;
import ast.node.statement.Statement;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class DecoratedFunction extends Function {

	private final List<Decorator> decorators;

	public DecoratedFunction(LocInfo locInfo, Identifier name, Expr returnType, List<Statement> body, Params params, List<Decorator> decorators) {
		super(locInfo, name, returnType, body, params);
		this.decorators = decorators;
	}

	public List<Decorator> getDecorators() {
		return this.decorators;
	}
}
