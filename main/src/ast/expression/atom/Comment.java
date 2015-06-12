package ast.expression.atom;

import ast.LocInfo;

/**
 * Created by Nik on 12-06-2015
 */
public class Comment extends Atom {

	private final String value;

	public Comment(LocInfo locInfo, String value) {
		super(locInfo);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
