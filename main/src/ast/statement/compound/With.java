package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-06-2015
 */
public class With extends Statement {

	private final List<WithItem> items;
	private final Suite body;

	public With(LocInfo locInfo, @NotNull List<WithItem> items, @NotNull Suite body) {
		super(locInfo);
		this.items = items;
		this.body = body;
	}

	public List<WithItem> getItems() {
		return this.items;
	}

	public Suite getBody() {
		return this.body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
