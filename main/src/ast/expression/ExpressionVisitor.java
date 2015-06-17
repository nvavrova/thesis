package ast.expression;

import ast.expression.atom.*;
import ast.expression.bitwise.And;
import ast.expression.bitwise.Or;
import ast.expression.bitwise.Xor;
import ast.expression.datastruct.Dictionary;
import ast.expression.datastruct.ListExpr;
import ast.expression.datastruct.SetExpr;
import ast.expression.datastruct.Tuple;
import ast.expression.logical.Not;
import ast.expression.maker.DictMaker;
import ast.expression.maker.SetMaker;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;

/**
 * Created by Nik on 17-06-2015
 */
public interface ExpressionVisitor<T> {

	public T visit(Comparison n);
	public T visit(Conditional n);
	public T visit(Lambda n);
	public T visit(Shift n);
	public T visit(Slice n);

	//arithmetic
	//TODO

	//atom
	public T visit(Bool n);
	public T visit(Comment n);
	public T visit(Ellipsis n);
	public T visit(Enclosure n);
	public T visit(ast.expression.atom.Float n);
	public T visit(Identifier n);
	public T visit(Imaginary n);
	public T visit(Int n);
	public T visit(Literal n);
	public T visit(None n);
	public T visit(Str n);

	//bitwise
	public T visit(And n);
	public T visit(Or n);
	public T visit(Xor n);

	//data structures
	public T visit(Dictionary n);
	public T visit(ListExpr n);
	public T visit(SetExpr n);
	public T visit(Tuple n);

	//logical
	public T visit(ast.expression.logical.And n);
	public T visit(Not n);
	public T visit(ast.expression.logical.Or n);

	//makers
	public T visit(DictMaker n);
	public T visit(SetMaker n);

	//unary
	public T visit(Invert n);
	public T visit(Minus n);
	public T visit(Plus n);
}
