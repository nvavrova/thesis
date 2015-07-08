package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 24-06-2015
 */
public class Try extends Statement {

	private final Suite tryBlock;
	private final List<Except> exceptions;
	private final Map<Except, Suite> exceptBlocks;
	private final Suite elseBlock;
	private final Suite finallyBlock;

	public Try(@NotNull LocInfo locInfo, @NotNull Suite tryBlock, @NotNull List<Except> exceptions,
	           @NotNull Map<Except, Suite> exceptBlocks, Suite elseBlock, Suite finallyBlock) {
		super(locInfo);
		this.tryBlock = tryBlock;
		this.exceptions = exceptions;
		this.exceptBlocks = exceptBlocks;
		this.elseBlock = elseBlock;
		this.finallyBlock = finallyBlock;
	}

	public Suite getTryBlock() {
		return this.tryBlock;
	}

	public List<Except> getExceptions() {
		return this.exceptions;
	}

	public Suite getSuite(Except except) {
		assert (this.exceptBlocks.containsKey(except));
		return this.exceptBlocks.get(except);
	}

	public Suite getElseBlock() {
		return this.elseBlock;
	}

	public Suite getFinallyBlock() {
		return this.finallyBlock;
	}

	public Boolean hasElseBlock() {
		return this.elseBlock != null;
	}

	public Boolean hasFinallyBlock() {
		return this.finallyBlock != null;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
