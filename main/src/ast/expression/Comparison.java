package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public class Comparison extends Expr {

	//TODO: how to do this?
	// comparison: expr (comp_op expr)*

	public Comparison(LocInfo locInfo) {
		super(locInfo);
	}

}
