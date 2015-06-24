package ast.statement.compound;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 24-06-2015
 */
public class Try extends Statement {

	private final List<Statement> tryBlock;
	private final Map<Object, List<Statement>> exceptBlocks; //TODO: replace Object with caught exception
	private final List<Statement> elseBlock;
	private final List<Statement> finallyBlock;

	public Try(LocInfo locInfo, List<Statement> tryBlock, Map<Object, List<Statement>> exceptBlocks,
	           List<Statement> elseBlock, List<Statement> finallyBlock) {
		super(locInfo);
		this.tryBlock = tryBlock;
		this.exceptBlocks = exceptBlocks;
		this.elseBlock = elseBlock;
		this.finallyBlock = finallyBlock;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
