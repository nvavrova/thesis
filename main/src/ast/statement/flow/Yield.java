package ast.statement.flow;

import ast.LocInfo;
import ast.statement.Statement;

/**
 * Created by Nik on 24-06-2015
 */
public abstract class Yield extends Statement {
	public Yield(LocInfo locInfo) {
		super(locInfo);
	}
}
