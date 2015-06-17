package ast.statement.compound;

import ast.LocInfo;
import ast.statement.Statement;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class Function extends Statement {

    //TODO: arguments

    private final String name;
    private final List<Statement> body;

    public Function(LocInfo locInfo, String name, List<Statement> body) {
        super(locInfo);
        this.name = name;
        this.body = body;
    }

	public String getName() {
		return name;
	}

	public List<Statement> getBody() {
		return body;
	}

    @Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}