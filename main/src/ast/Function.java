package ast;

/**
 * Created by Nik on 25-5-15.
 */
public class Function extends Py3Node {

    //TODO: arguments

    private final String name;

    public Function(LocInfo locInfo, String name) {
        super(locInfo);
        this.name = name;
    }


}
