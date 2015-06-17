package ast;

import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 25-05-15
 */
public class ImportFrom extends Import {

    private final String location;

    public ImportFrom(LocInfo locInfo, String location) {
        super(locInfo);
        this.location = location;
    }

    @Override
    public <T> T accept(Py3TreeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
