package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public class Bool extends Expr {

	private final Boolean value;

	public Bool(LocInfo locInfo, Boolean value) {
		super(locInfo);
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}
}
