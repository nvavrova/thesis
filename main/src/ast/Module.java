package ast;

import ast.statement.Statement;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Module extends Py3Node {

    private final List<Statement> body;

    public Module(@NotNull LocInfo locInfo, @NotNull List<Statement> body) {
        super(locInfo);
        this.body = body;
    }

    public List<Statement> getBody() {
        return this.body;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
