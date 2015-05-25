package ast.expression;

import ast.LocInfo;

import java.util.List;

/**
 * Created by Nik on 25-5-15.
 */
public class ListExpr extends Sequence {

    private final List<Expr> body;

    public ListExpr(LocInfo locInfo, List<Expr> body) {
        super(locInfo);
        this.body = body;
    }


}
