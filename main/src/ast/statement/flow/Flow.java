package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;

/**
 * Created by Nik on 15-06-2015
 */
public abstract class Flow extends Statement {
	public Flow(LocInfo locInfo) {
		super(locInfo);
	}
}
