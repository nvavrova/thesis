package ast.expression.primary;

import ast.LocInfo;
import ast.arg.Arg;
import ast.expression.Expr;
import ast.expression.ExprNoCond;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class ArgList extends ExprNoCond {

	private final List<Arg> positional;
	private final Expr args;
	private final Expr kwargs;

	public ArgList(LocInfo locInfo, List<Arg> positional, Expr args, Expr kwargs) {
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

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
