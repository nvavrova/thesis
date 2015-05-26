package ast;

/**
 * Created by Nik on 25-05-2015
 */
public class Identifier extends Py3Node {

	private final String value;

	public Identifier(LocInfo locInfo, String value) {
		super(locInfo);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
