package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 25-05-2015
 */
public class StringExpr extends Sequence {

	private final String value;

	public StringExpr(LocInfo locInfo, String value) {
		super(locInfo);
		this.value = value;
	}



}
