package ast.expression.primary;

import ast.LocInfo;
import ast.arg.Arg;
import ast.expression.Expr;
import ast.expression.ExprNoCond;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class ArgList extends ExprNoCond {

	private final List<Arg> positional;
	private final Expr args;
	private final Expr kwargs;

	public ArgList(LocInfo locInfo) {
		this(locInfo, Collections.emptyList());
	}

	public ArgList(LocInfo locInfo, @NotNull List<Arg> positional) {
		this(locInfo, positional, null, null);
	}

	public ArgList(LocInfo locInfo, @NotNull List<Arg> positional, Expr args, Expr kwargs) {
		super(locInfo);
		this.positional = positional;
		this.args = args;
		this.kwargs = kwargs;
	}

	public List<Arg> getPositional() {
		return this.positional;
	}

	public Expr getArgs() {
		return this.args;
	}

	public Expr getKwargs() {
		return this.kwargs;
	}

	public Boolean hasArgs() {
		return this.args != null;
	}

	public Boolean hasKwargs() {
		return this.kwargs != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
