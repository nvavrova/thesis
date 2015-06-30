package ast.param;

import ast.LocInfo;
import ast.Py3Node;
import ast.expression.atom.Identifier;
import ast.expression.Expr;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 23-06-2015
 */
public class Param extends Py3Node {

	private final Identifier id;
	private Expr defaultVal;

	public Param(LocInfo locInfo, @NotNull Identifier id) {
		super(locInfo);
		this.id = id;
	}

	public Boolean hasDefaultVal() {
		return this.defaultVal != null;
	}

	public Identifier getId() {
		return this.id;
	}

	public Expr getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(@NotNull Expr defaultVal) {
		this.defaultVal = defaultVal;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
