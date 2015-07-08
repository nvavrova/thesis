package ast.expression.primary.trailer;

import ast.expression.primary.atom.Identifier;

/**
 * Created by Nik on 08-07-2015
 */
public interface TrailerVisitor<T> {
	T visit(Identifier n);
	T visit(ArgList n);
	T visit(SubscriptSliceList n);
}
