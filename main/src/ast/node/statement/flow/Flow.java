package ast.node.statement.flow;

import ast.node.LocInfo;
import ast.node.statement.Statement;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Flow extends Statement {
	public Flow(LocInfo locInfo) {
		super(locInfo);
	}
}
