package thesis;

import ast.AstNode;

/**
 * Created by Nik on 17-06-2015
 */
public class Printer {

	private final AstNode tree;
	private final PrinterVisitor visitor;

	public Printer(AstNode tree) {
		this.tree = tree;
		this.visitor = new PrinterVisitor();
	}

	public void print() {
		//this.tree.accept(this.visitor);
	}

	private class PrinterVisitor {
	}
}
