package model;

import ast.argument.*;

/**
 * Created by Nik on 03-11-2015
 */
public class ClassArgumentCollector implements ArgumentVisitor<String> {

	@Override
	public String visit(Arg n) {
		throw new IllegalArgumentException();
	}

	@Override
	public String visit(CondArgument n) {
		throw new IllegalArgumentException();
	}

	@Override
	public String visit(DefValArgument n) {
		return n.getValue().toString(); //in case it's an identifier - name of another class
	}

	@Override
	public String visit(Kwarg n) {
		throw new IllegalArgumentException();
	}

	@Override
	public String visit(SimpleArgument n) {
		return n.getValue().toString();
	}
}
