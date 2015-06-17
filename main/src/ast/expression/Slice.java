package ast.expression;

import ast.LocInfo;

/**
 * Created by Nik on 15-06-2015
 */
public class Slice extends Expr {

	private final Expr value;

	public Slice(LocInfo locInfo) {
		this(locInfo, null);
	}

	public Slice(LocInfo locInfo, Expr value) {
		super(locInfo);
		this.value = value;
	}

	public Boolean hasValue() {
		return this.value != null;
	}

	public Expr getValue() {
		return value;
	}
}
