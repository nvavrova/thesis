package ast.arg;

import ast.LocInfo;
import ast.Py3Node;
import ast.expression.Expr;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class Args extends Py3Node {

	private final List<Arg> positional;
	private final Expr args;
	private final Expr kwargs;

	public Args(LocInfo locInfo, List<Arg> positional, Expr args, Expr kwargs) {
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
