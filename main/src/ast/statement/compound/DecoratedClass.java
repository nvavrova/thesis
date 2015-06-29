package ast.statement.compound;

import ast.Decorator;
import ast.LocInfo;
import ast.Suite;
import ast.expression.primary.ArgList;
import ast.expression.atom.Identifier;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class DecoratedClass extends ClassDef {

	private final List<Decorator> decorators;

	public DecoratedClass(LocInfo locInfo, List<Decorator> decorators, Identifier name, Suite body, ArgList inheritance) {
		super(locInfo, name, body, inheritance);
		this.decorators = decorators;
	}

	public List<Decorator> getDecorators() {
		return this.decorators;
	}
}
