package ast;

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
}
