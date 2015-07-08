package ast.expression.primary;

import ast.expression.atom.Identifier;

/**
 * Created by Nik on 08-07-2015
 */
public interface TrailerVisitor<T> {
	T visit(Identifier n);
	T visit(ArgList n);
	T visit(SliceBound n);
	T visit(SubscriptList n);
}
