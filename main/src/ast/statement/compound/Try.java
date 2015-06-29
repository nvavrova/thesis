package ast.statement.compound;

import ast.LocInfo;
import ast.Suite;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.Map;

/**
 * Created by Nik on 24-06-2015
 */
public class Try extends Statement {

	private final Suite tryBlock;
	private final Map<Except, Suite> exceptBlocks;
	private final Suite elseBlock;
	private final Suite finallyBlock;

	public Try(LocInfo locInfo, Suite tryBlock, Map<Except, Suite> exceptBlocks,
	           Suite elseBlock, Suite finallyBlock) {
		super(locInfo);
		this.tryBlock = tryBlock;
		this.exceptBlocks = exceptBlocks;
		this.elseBlock = elseBlock;
		this.finallyBlock = finallyBlock;
	}

	public Suite getTryBlock() {
		return this.tryBlock;
	}

	public Map<Except, Suite> getExceptBlocks() {
		return this.exceptBlocks;
	}

	public Suite getElseBlock() {
		return this.elseBlock;
	}

	public Suite getFinallyBlock() {
		return this.finallyBlock;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
