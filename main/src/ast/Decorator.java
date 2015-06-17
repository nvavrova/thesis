package ast;

import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 15-06-2015
 */
public class Decorator extends Py3Node {

	private final String name;
	//TODO: args

	public Decorator(LocInfo locInfo, String name) {
		super(locInfo);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
