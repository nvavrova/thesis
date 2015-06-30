package ast.param;

import ast.LocInfo;
import ast.Py3Node;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class Params extends Py3Node {

	private final List<Param> positionalArgs;
	private final List<Param> args;
	private final List<Param> kwargs;

	public Params(LocInfo locInfo) {
		this(locInfo, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
	}

	public Params(LocInfo locInfo, @NotNull List<Param> positionalArgs, @NotNull List<Param> args, @NotNull List<Param> kwargs) {
		super(locInfo);
		this.positionalArgs = positionalArgs;
		this.args = args;
		this.kwargs = kwargs;
	}

	public List<Param> getPositionalArgs() {
		return this.positionalArgs;
	}

	public List<Param> getArgs() {
		return this.args;
	}

	public List<Param> getKwargs() {
		return this.kwargs;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
