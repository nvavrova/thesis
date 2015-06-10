package ast;

import ast.test.Test;

/**
 * Created by Nik on 09-06-2015
 */
public class YieldFrom extends Py3Node{

	private final Test from;

	public YieldFrom(LocInfo locInfo, Test from) {
		super(locInfo);
		this.from = from;
	}
}
