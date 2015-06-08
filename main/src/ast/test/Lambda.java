package ast.test;

import ast.LocInfo;

/**
 * Created by Nik on 26-05-2015
 */
public class Lambda extends Test {

	//TODO: add arguments
	private final Test test;

	public Lambda(LocInfo locInfo, Test test) {
		super(locInfo);
		this.test = test;
	}

	public Test getTest() {
		return test;
	}
}
