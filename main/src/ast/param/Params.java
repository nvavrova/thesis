package ast.param;

import ast.LocInfo;
import ast.Py3Node;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class Params extends Py3Node {

	private final List<SimpleParam> positionalArgs;
	private final List<SimpleParam> args;
	private final List<SimpleParam> kwargs;

	public Params(@NotNull LocInfo locInfo) {
		this(locInfo, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
	}

	public Params(@NotNull LocInfo locInfo, @NotNull List<SimpleParam> positionalArgs, @NotNull List<SimpleParam> args, @NotNull List<SimpleParam> kwargs) {
		super(locInfo);
		this.positionalArgs = positionalArgs;
		this.args = args;
		this.kwargs = kwargs;
	}

	public List<SimpleParam> getPositionalArgs() {
		return this.positionalArgs;
	}

	public List<SimpleParam> getArgs() {
		return this.args;
	}

	public List<SimpleParam> getKwargs() {
		return this.kwargs;
	}

	public Boolean isEmpty() {
		return this.positionalArgs.size() == 0 && this.args.size() == 0 && this.kwargs.size() == 0;
	}

	public Boolean isEmptyExceptForSelf() {
		if (this.positionalArgs.size() == 1 && this.args.size() == 0 && this.kwargs.size() == 0) {
			SimpleParam p = this.positionalArgs.get(0);
			return p.isSelf();
		}
		return false;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
