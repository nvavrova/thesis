package ast.statement.compound;

import ast.Decorator;
import ast.LocInfo;
import ast.Suite;
import ast.expression.atom.Identifier;
import ast.expression.primary.ArgList;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Helper;
import thesis.Visitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Statement {

	private final Identifier name;
	private final Suite body;
	private final ArgList inheritance;
	private List<Decorator> decorators;

	public ClassDef(LocInfo locInfo, @NotNull Identifier name, @NotNull Suite body) {
		this(locInfo, name, body, null);
	}

	public ClassDef(LocInfo locInfo, @NotNull Identifier name, @NotNull Suite body, ArgList inheritance) {
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

	public ArgList getInheritance() {
		return this.inheritance;
	}

	public List<Decorator> getDecorators() {
		return this.decorators;
	}

	public void setDecorators(List<Decorator> decorators) {
		this.decorators = decorators;
	}

	public Boolean hasInheritance() {
		return this.inheritance != null;
	}

	public Boolean isController() {
		for (String name : Helper.getControllerNames()) {
			if (this.nameContains(name)){
				return true;
			}
		}
		return false;
	}

	public Boolean nameContains(String s) {
		return this.name.contains(s);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
