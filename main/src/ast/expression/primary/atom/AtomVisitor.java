package ast.expression.primary.atom;

import ast.expression.primary.atom.trailed.TrailedAtomVisitor;

/**
 * Created by Nik on 30-06-2015
 */
public interface AtomVisitor <T> extends TrailedAtomVisitor<T> {
	T visit(Bool n);
	T visit(DictMaker n);
	T visit(Ellipsis n);
	T visit(Float n);
	T visit(Identifier n);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(None n);
	T visit(SetMaker n);
	T visit(Str n);
}
