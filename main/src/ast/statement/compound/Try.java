package ast.statement.compound;

import ast.Suite;
import ast.Visitor;
import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Created by Nik on 24-06-2015
 */
public class Try extends Statement {

	private final Suite tryBlock;
	private final List<Except> exceptions;
	private final List<Suite> exceptBodies;
	private final Suite elseBlock;
	private final Suite finallyBlock;

	public Try(@NotNull Integer locInfo, @NotNull Suite tryBlock, @NotNull List<Except> exceptions,
	           @NotNull List<Suite> exceptBodies, Suite elseBlock, Suite finallyBlock) {
		super(locInfo);
		this.tryBlock = tryBlock;
		this.exceptions = exceptions;
		this.exceptBodies = exceptBodies;
		this.elseBlock = elseBlock;
		this.finallyBlock = finallyBlock;
	}

	public Suite getTryBlock() {
		return this.tryBlock;
	}

	public List<Except> getExceptions() {
		return this.exceptions;
	}

	public List<Suite> getExceptBodies() {
		return this.exceptBodies;
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
