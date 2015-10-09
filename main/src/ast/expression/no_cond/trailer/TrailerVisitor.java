package ast.expression.no_cond.trailer;

import ast.expression.no_cond.atom.Identifier;

/**
 * Created by Nik on 08-07-2015
 */
public interface TrailerVisitor<T> {
	T visit(Identifier n);
	T visit(ArgList n);
	T visit(SubscriptSliceList n);
}
