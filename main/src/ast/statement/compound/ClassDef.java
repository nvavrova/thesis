package ast.statement.compound;

import ast.Decorator;
import ast.LocInfo;
import ast.Suite;
import ast.arg.Arg;
import ast.expression.primary.atom.Identifier;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Statement {

	private final Identifier name;
	private final Suite body;
	private final List<Arg> inheritance; //either power(a.b) or identifier(a)
	private List<Decorator> decorators;

	public ClassDef(@NotNull LocInfo locInfo, @NotNull Identifier name, @NotNull Suite body, @NotNull List<Arg> inheritance) {
		super(locInfo);
		this.name = name;
		this.body = body;
		this.inheritance = inheritance;
		this.decorators = Collections.emptyList();
	}

	public Identifier getName() {
		return this.name;
	}

	public Suite getBody() {
		return this.body;
	}

	public List<Arg> getInheritance() {
		return this.inheritance;
	}

	public List<Decorator> getDecorators() {
		return this.decorators;
	}

	public void setDecorators(List<Decorator> decorators) {
		this.decorators = decorators;
	}

	public Boolean hasInheritance() {
		return this.inheritance.size() > 0;
	}

	public Boolean nameContains(String s) {
		return this.name.contains(s);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
