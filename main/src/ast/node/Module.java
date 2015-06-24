package ast.node;

import thesis.Py3TreeVisitor;

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
        return this.body;
    }

    @Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
