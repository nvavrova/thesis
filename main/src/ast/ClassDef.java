package ast;

import java.util.List;

/**
 * Created by Nik on 19-05-2015
 */
public class ClassDef extends Py3Node {

    //TODO: arguments

	private final List<Py3Node> body;
	private final String name;

	public ClassDef(LocInfo locInfo, String name, List<Py3Node> body) {
		super(locInfo);
		this.name = name;
		this.body = body;
	}

    
}
