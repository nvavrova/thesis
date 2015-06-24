package ast.node.statement.compound;

import ast.node.LocInfo;
import ast.node.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 24-06-2015
 */
public class Try extends Statement {

	private final List<Statement> tryBlock;
	private final Map<Except, List<Statement>> exceptBlocks;
	private final List<Statement> elseBlock;
	private final List<Statement> finallyBlock;

	public Try(LocInfo locInfo, List<Statement> tryBlock, Map<Except, List<Statement>> exceptBlocks,
	           List<Statement> elseBlock, List<Statement> finallyBlock) {
		super(locInfo);
		this.tryBlock = tryBlock;
		this.exceptBlocks = exceptBlocks;
		this.elseBlock = elseBlock;
		this.finallyBlock = finallyBlock;
	}

	public List<Statement> getTryBlock() {
		return this.tryBlock;
	}

	public Map<Except, List<Statement>> getExceptBlocks() {
		return this.exceptBlocks;
	}

	public List<Statement> getElseBlock() {
		return this.elseBlock;
	}

	public List<Statement> getFinallyBlock() {
		return this.finallyBlock;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
