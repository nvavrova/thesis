package ast;

import ast.test.Test;

import java.util.List;

/**
 * Created by Nik on 25-05-2015
 */
public class Yield extends Py3Node {

	private final List<Test> values;

	public Yield(LocInfo locInfo, List<Test> values) {
		super(locInfo);
		this.values = values;
	}
}
