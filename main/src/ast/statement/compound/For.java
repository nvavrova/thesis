package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.statement.Statement;
import ast.statement.simple.ExprList;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 17-06-2015
 */
public class For extends Statement {

	private final ExprList iterator;
	private final ExprList source;
	private final Suite body;
	private final Suite elseBody;

	public For(LocInfo locInfo, @NotNull ExprList iterator, @NotNull ExprList source, @NotNull Suite body, Suite elseBody) {
		super(locInfo);
		this.iterator = iterator;
		this.source = source;
		this.body = body;
		this.elseBody = elseBody;
	}

	public ExprList getIterator() {
		return this.iterator;
	}

	public ExprList getSource() {
		return this.source;
	}

	public Suite getBody() {
		return this.body;
	}

	public Suite getElseBody() {
		return this.elseBody;
	}

	public Boolean hasElseBody () {
		return this.elseBody != null;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
