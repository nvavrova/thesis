package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.expression.primary.ArgList;
import ast.expression.atom.Identifier;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Statement {

	private final Identifier name;
	private final Suite body;
	private final ArgList inheritance;

	public ClassDef(LocInfo locInfo, Identifier name, Suite body) {
		this(locInfo, name, body, null);
	}

	public ClassDef(LocInfo locInfo, Identifier name, Suite body, ArgList inheritance) {
		super(locInfo);
		this.name = name;
		this.body = body;
		this.inheritance = inheritance;
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

	public Boolean inherits() {
		return this.inheritance != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
