package ast.statement.compound;

import ast.Decorator;
import ast.LocInfo;
import ast.Suite;
import ast.expression.Expr;
import ast.expression.atom.Identifier;
import ast.param.Params;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Function extends Statement {

	private final Identifier name;
	private final Expr returnType;
	private final Suite body;
	private final Params params;
	private List<Decorator> decorators;

	public Function(LocInfo locInfo, @NotNull Identifier name, Expr returnType, @NotNull Suite body, @NotNull Params params) {
		super(locInfo);
		this.name = name;
		this.returnType = returnType;
		this.body = body;
		this.params = params;
		this.decorators = Collections.emptyList();
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

	public List<Decorator> getDecorators() {
		return this.decorators;
	}

	public void setDecorators(List<Decorator> decorators) {
		this.decorators = decorators;
	}

	public Boolean hasReturnType() {
		return this.returnType != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}

}