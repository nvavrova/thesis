package thesis;

import ast.*;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.CompFor;
import ast.expression.comprehension.CompIf;
import ast.expression.comprehension.CondComprehension;
import ast.expression.comprehension.EnumComprehension;
import ast.expression.logical.And;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.primary.*;
import ast.expression.primary.atom.*;
import ast.expression.primary.trailer.ArgList;
import ast.expression.primary.trailer.SliceBound;
import ast.expression.primary.trailer.SubscriptIndex;
import ast.expression.primary.trailer.SubscriptSliceList;
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
public interface Visitor<T> {

	//--------------------------------------------- TOP LEVEL ---------------------------------------------\\
	T visit(Decorator n);
	T visit(DottedPath n);
	T visit(Module n);
	T visit(SimplePath n);
	T visit(Suite n);

	//arg
	T visit(Kwarg n);
	T visit(SimpleArg n);

	//param
	T visit(Param n);
	T visit(Params n);

	//--------------------------------------------- STATEMENTS --------------------------------------------\\
	//simple
	T visit(Assert n);
	T visit(AssignExpr n);
	T visit(AssignYield n);
	T visit(Delete n);
	T visit(ExprList n);
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
	T visit(ClassDef n);
	T visit(Except n);
	T visit(For n);
	T visit(Function n);
	T visit(If n);
	T visit(Try n);
	T visit(While n);
	T visit(With n);
	T visit(WithItem n);

	//-------------------------------------------- EXPRESSIONS --------------------------------------------\\
	T visit(Comparison n);
	T visit(Conditional n);
	T visit(Lambda n);
	T visit(LambdaNoCond n);
	T visit(Power n);
	T visit(Shift n);

	//arithmetic
	T visit(Arithmetic n);


	//atom
	T visit(Bool n);
	T visit(DictMaker n);
	T visit(Ellipsis n);
	T visit(ast.expression.primary.atom.Float n);
	T visit(Identifier n);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(Literal n);
	T visit(None n);
	T visit(SetMaker n);
	T visit(Str n);
	T visit(AttributeRef n);
	T visit(Call n);
	T visit(DirectCall n);
	T visit(Slice n);
	T visit(Subscription n);

	//bitwise
	T visit(ast.expression.bitwise.And n);
	T visit(ast.expression.bitwise.Or n);
	T visit(Xor n);

	//comprehension
	T visit(CompFor n);
	T visit(CompIf n);
	T visit(CondComprehension n);
	T visit(EnumComprehension n);

	//logical
	T visit(And n);
	T visit(Not n);
	T visit(Or n);

	//primary
	T visit(ArgList n);
	T visit(SliceBound n);
	T visit(SubscriptSliceList n);
	T visit(SubscriptIndex n);

	//unary
	T visit(Invert n);
	T visit(Minus n);
	T visit(Plus n);
}