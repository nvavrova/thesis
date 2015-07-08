package ast.expression;

import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.bitwise.And;
import ast.expression.bitwise.Or;
import ast.expression.bitwise.Xor;
import ast.expression.logical.Not;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;

/**
 * Created by Nik on 17-06-2015
 */
public interface ExpressionVisitor<T> {

	T visit(Comparison n);
	T visit(Conditional n);
	T visit(Lambda n);
	T visit(Shift n);

	//arithmetic
	T visit(Arithmetic n);

	//atom
	T visit(Bool n);
	T visit(Ellipsis n);
	T visit(ast.expression.atom.Float n);
	T visit(Identifier n);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(Literal n);
	T visit(None n);
	T visit(Str n);

	//bitwise
	T visit(And n);
	T visit(Or n);
	T visit(Xor n);

	//logical
	T visit(ast.expression.logical.And n);
	T visit(Not n);
	T visit(ast.expression.logical.Or n);

	//makers
	T visit(DictMaker n);
	T visit(SetMaker n);

	//unary
	T visit(Invert n);
	T visit(Minus n);
	T visit(Plus n);
}
