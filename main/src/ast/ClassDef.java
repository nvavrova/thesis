package ast;

import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Py3Node {

    //TODO: arguments

	private final String name;
	private final List<Py3Node> body;

	public ClassDef(LocInfo locInfo, String name, List<Py3Node> body) {
		super(locInfo);
		this.name = name;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public List<Py3Node> getBody() {
		return body;
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
