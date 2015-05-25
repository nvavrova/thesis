package ast.expression;

import ast.LocInfo;

import java.util.Map;

/**
 * Created by Nik on 25-5-15.
 */
public class Dictionary extends Expr{

    private final Map<Expr, Expr> body;

    public Dictionary(LocInfo locInfo, Map<Expr, Expr> body) {
        super(locInfo);
        this.body = body;
    }
}
