package ast.statement.compound;

import ast.Suite;
import ast.Visitor;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 25-06-2015
 */
public class With extends Statement {

	private final List<WithItem> items;
	private final Suite body;
	private Boolean async;

	public With(@NotNull Integer locInfo, @NotNull List<WithItem> items, @NotNull Suite body) {
		super(locInfo);
		this.items = items;
		this.body = body;
		this.async = false;
	}

	public List<WithItem> getItems() {
		return this.items;
	}

	public Suite getBody() {
		return this.body;
	}

	public void markAsAsync() {
		this.async = true;
	}

	public Boolean isAsync() {
		return this.async;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
