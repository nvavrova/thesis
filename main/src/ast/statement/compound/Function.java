package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.expression.Expr;
import ast.expression.atom.Identifier;
import ast.param.Params;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-15
 */
public class Function extends Statement {

	private final Identifier name;
	private final Expr returnType;
	private final Suite body;
	private final Params params;

	public Function(LocInfo locInfo, Identifier name, Expr returnType, Suite body, Params params) {
		super(locInfo);
		this.name = name;
		this.returnType = returnType;
		this.body = body;
		this.params = params;
	}

	public Identifier getName() {
		return this.name;
	}

	public Expr getReturnType() {
		return this.returnType;
	}

	public Suite getBody() {
		return this.body;
	}

	public Params getParams() {
		return this.params;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}

}