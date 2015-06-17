package ast.expression.datastruct;

import ast.LocInfo;
import ast.expression.Expr;

import java.util.List;

/**
 * Created by Nik on 25-05-15
 */
public class ListExpr extends Sequence {

    private final List<Expr> body;

    public ListExpr(LocInfo locInfo, List<Expr> body) {
        super(locInfo);
        this.body = body;
    }


}
