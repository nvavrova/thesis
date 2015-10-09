package ast.param;

import ast.AstNode;
import ast.Visitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class Params extends AstNode {

	private final List<Param> regular;
	private final Param positional;
	private final Param keyword;

	public Params(@NotNull Integer locInfo) {
		this(locInfo, Collections.emptyList(), null, null);
	}

	public Params(@NotNull Integer locInfo, @NotNull List<Param> regular, Param positional, Param keyword) {
		super(locInfo);
		this.regular = regular;
		this.positional = positional;
		this.keyword = keyword;
	}

	public List<Param> getRegular() {
		return this.regular;
	}

	public Boolean hasPositionalParam() {
		return this.positional != null;
	}

	public Param getPositional() {
		return this.positional;
	}

	public Boolean hasKeywordParam() {
		return this.keyword != null;
	}

	public Param getKeyword() {
		return this.keyword;
	}

	public Boolean isEmptyExceptForSelf() {
		if (this.regular.size() == 1 && !this.hasPositionalParam() && !this.hasKeywordParam()) {
			Param p = this.regular.get(0);
			return p.isSelf();
		}
		return false;
	}

	public List<String> getParamNames() {
		List<String> names = new ArrayList<>();
		this.regular.forEach(p -> names.addAll(p.getNames()));
		if (this.hasPositionalParam()) {
			names.addAll(this.positional.getNames());
		}
		if (this.hasKeywordParam()) {
			names.addAll(this.keyword.getNames());
		}
		return names;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
