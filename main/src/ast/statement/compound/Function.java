package ast.statement.compound;

import ast.Decorator;
import ast.Suite;
import ast.Visitor;
import ast.expression.Expr;
import ast.expression.no_cond.atom.Identifier;
import ast.param.Params;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

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
	private Boolean async;
	private List<Decorator> decorators;

	public Function(@NotNull Integer locInfo, @NotNull Identifier name, Expr returnType, @NotNull Suite body, @NotNull Params params) {
		super(locInfo);
		this.name = name;
		this.returnType = returnType;
		this.body = body;
		this.params = params;
		this.decorators = Collections.emptyList();
		this.async = false;
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

	public String getNameString() {
		return this.name.getValue();
	}

	public void markAsAsync() {
		this.async = true;
	}

	public Boolean hasReturnType() {
		return this.returnType != null;
	}

	public Boolean isAsync() {
		return this.async;
	}

	public Boolean isAccessor() {
		//TODO: fix this
		if (this.params.isEmptyExceptForSelf() && this.name.startsWith("get")) {
			return this.body.isAccessorBody();
		}
		return false;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}