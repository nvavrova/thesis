package ast.statement;

import ast.LocInfo;
import ast.Py3Node;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Statement extends Py3Node {
	public Statement(LocInfo locInfo) {
		super(locInfo);
	}
}
