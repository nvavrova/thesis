package ast;

import ast.expression.atom.Identifier;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Py3Node {

	private final Identifier name;
//	private final List<Expr> inheritance;
	private final List<Statement> body;

	public ClassDef(LocInfo locInfo, Identifier name, List<Statement> body) {
		super(locInfo);
		this.name = name;
		this.body = body;
	}

	public Identifier getName() {
		return name;
	}

	public List<Statement> getBody() {
		return body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
