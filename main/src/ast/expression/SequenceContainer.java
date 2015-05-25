package ast.expression;

import ast.LocInfo;
import ast.Py3Node;

/**
 * Created by Nik on 25-5-15.
 */
public abstract class SequenceContainer extends Py3Node{
    public SequenceContainer(LocInfo locInfo) {
        super(locInfo);
    }
}
