package ast;

import java.util.List;

/**
 * Created by Nik on 15-06-2015
 */
public class DecoratedClass extends ClassDef {

	private final List<Decorator> decorators;

	public DecoratedClass(LocInfo locInfo, List<Decorator> decorators, String name, List<Py3Node> body) {
		super(locInfo, name, body);
		this.decorators = decorators;
	}

	public List<Decorator> getDecorators() {
		return decorators;
	}
}
