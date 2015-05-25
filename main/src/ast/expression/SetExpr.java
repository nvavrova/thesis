package ast.expression;

import ast.LocInfo;

import java.util.Set;

/**
 * Created by Nik on 25-5-15.
 */
public class SetExpr extends Expr {

    private final Set<Expr> body;

    public SetExpr(LocInfo locInfo, Set<Expr> body) {
        super(locInfo);
        this.body = body;
    }
}
