package ast;

import ast.expression.atom.Identifier;
import ast.arg.Args;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Py3Node {

	private final Identifier name;
	private final Args inheritance;
	private final List<Statement> body;

	public ClassDef(LocInfo locInfo, Identifier name, Args inheritance, List<Statement> body) {
		super(locInfo);
		this.name = name;
		this.inheritance = inheritance;
		this.body = body;
	}

	public Identifier getName() {
		return this.name;
	}

	public Args getInheritance() {
		return this.inheritance;
	}

	public List<Statement> getBody() {
		return this.body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
