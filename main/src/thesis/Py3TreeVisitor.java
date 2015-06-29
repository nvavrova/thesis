package thesis;

import ast.*;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.atom.maker.DictMaker;
import ast.expression.atom.maker.SetMaker;
import ast.expression.bitwise.Xor;
import ast.expression.display.*;
import ast.expression.logical.And;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.primary.ArgList;
import ast.expression.primary.SliceBound;
import ast.expression.primary.SubscriptIndex;
import ast.expression.primary.SubscriptList;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Param;
import ast.param.Params;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

/**
 * Created by Nik on 17-06-2015
 */
public interface Py3TreeVisitor<T> {

	//--------------------------------------------- TOP LEVEL ---------------------------------------------\\
	T visit(Module n);
	T visit(Decorator n);
	T visit(SimpleArg n);
	T visit(Kwarg n);
	T visit(SimplePath n);
	T visit(DottedPath n);
	T visit(Param param);
	T visit(Params params);
	T visit(Suite suite);

	//--------------------------------------------- STATEMENTS --------------------------------------------\\
	//simple
	T visit(Assert n);
	T visit(AssignExpr n);
	T visit(AssignYield n);
	T visit(ClassDef n);
	T visit(DecoratedClass n);
	T visit(Delete n);
	T visit(ExprList exprList);
	T visit(Global n);
	T visit(Import n);
	T visit(ImportFrom n);
	T visit(Nonlocal n);
	T visit(Pass n);

	//flow
	T visit(Break n);
	T visit(Continue n);
	T visit(Raise n);
	T visit(Return n);
	T visit(YieldValues n);
	T visit(YieldFrom n);

	//compound
	T visit(Except except);
	T visit(For n);
	T visit(Function n);
	T visit(DecoratedFunction n);
	T visit(If n);
	T visit(Try n);
	T visit(While n);
	T visit(With with);
	T visit(WithItem withItem);


	//-------------------------------------------- EXPRESSIONS --------------------------------------------\\
	T visit(Comparison n);
	T visit(Conditional n);
	T visit(Lambda n);
	T visit(LambdaNoCond lambdaNoCond);
	T visit(Power power);
	T visit(Shift n);

	//arithmetic
	T visit(Arithmetic n);

	//atom
	T visit(Bool n);
	T visit(Comment n);
	T visit(DottedImport n);
	T visit(Ellipsis n);
	T visit(Enclosure n);
	T visit(ast.expression.atom.Float n);
	T visit(Identifier identifier);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(Literal n);
	T visit(None n);
	T visit(Str n);

	//makers
	T visit(DictMaker n);
	T visit(SetMaker n);

	//bitwise
	T visit(ast.expression.bitwise.And n);
	T visit(ast.expression.bitwise.Or n);
	T visit(Xor n);

	//display
	T visit(CompFor n);
	T visit(CompIf n);
	T visit(CondComprehension n);
	T visit(Dictionary n);
	T visit(EnumComprehension enumComprehension);
	T visit(ListExpr n);
	T visit(SetExpr n);
	T visit(Tuple n);

	//logical
	T visit(And n);
	T visit(Not n);
	T visit(Or n);

	//primary
	T visit(ArgList argList);
	T visit(SubscriptIndex n);
	T visit(SliceBound n);
	T visit(SubscriptList subscriptList);

	//unary
	T visit(Invert n);
	T visit(Minus n);
	T visit(Plus n);
}