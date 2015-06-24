package ast.node.statement;

import ast.node.LocInfo;
import ast.node.Py3Node;

/**
 * Created by Nik on 25-05-2015
 */
public abstract class Statement extends Py3Node {
	public Statement(LocInfo locInfo) {
		super(locInfo);
	}
}
