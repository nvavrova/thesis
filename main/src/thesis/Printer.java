package thesis;

import ast.Py3Node;

/**
 * Created by Nik on 17-06-2015
 */
public class Printer {

	private final Py3Node tree;
	private final PrinterVisitor visitor;

	public Printer(Py3Node tree) {
		this.tree = tree;
		this.visitor = new PrinterVisitor();
	}

	public void print() {
		//this.tree.accept(this.visitor);
	}

	private class PrinterVisitor {
	}
}
