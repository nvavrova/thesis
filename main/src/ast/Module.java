package ast;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Module extends Py3Node {

    private final List<Py3Node> body;

    public Module(LocInfo locInfo, List<Py3Node> body) {
        super(locInfo);
        this.body = body;
    }

    public List<Py3Node> getBody() {
        return body;
    }
}
