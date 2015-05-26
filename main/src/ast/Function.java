package ast;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Function extends Py3Node {

    //TODO: arguments

    private final String name;
    private final List<Py3Node> body;

    public Function(LocInfo locInfo, String name, List<Py3Node> body) {
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


}
