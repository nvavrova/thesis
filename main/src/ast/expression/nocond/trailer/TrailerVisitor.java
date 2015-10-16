package ast.expression.nocond.trailer;

import ast.expression.nocond.atom.Identifier;

/**
 * Created by Nik on 08-07-2015
 */
public interface TrailerVisitor<T> {
	T visit(Identifier n);
	T visit(ArgList n);
	T visit(SubscriptSliceList n);
}
