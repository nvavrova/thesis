package ast.node.expression;

import ast.node.expression.atom.*;
import ast.node.expression.bitwise.And;
import ast.node.expression.bitwise.Or;
import ast.node.expression.bitwise.Xor;
import ast.node.expression.display.Dictionary;
import ast.node.expression.display.ListExpr;
import ast.node.expression.display.SetExpr;
import ast.node.expression.display.Tuple;
import ast.node.expression.logical.Not;
import ast.node.expression.maker.DictMaker;
import ast.node.expression.maker.SetMaker;
import ast.node.expression.unary.Invert;
import ast.node.expression.unary.Minus;
import ast.node.expression.unary.Plus;

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
	public T visit(ast.node.expression.atom.Float n);
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
	public T visit(ast.node.expression.logical.And n);
	public T visit(Not n);
	public T visit(ast.node.expression.logical.Or n);

	//makers
	public T visit(DictMaker n);
	public T visit(SetMaker n);

	//unary
	public T visit(Invert n);
	public T visit(Minus n);
	public T visit(Plus n);
}
