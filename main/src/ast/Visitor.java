package ast;

import ast.argument.*;
import ast.expression.*;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.CompFor;
import ast.expression.comprehension.CompIf;
import ast.expression.comprehension.CondComprehension;
import ast.expression.comprehension.EnumComprehension;
import ast.expression.logical.And;
import ast.expression.logical.Comparison;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.primary.atom.*;
import ast.expression.primary.atom.Float;
import ast.expression.primary.atom.Long;
import ast.expression.primary.atom.trailed.*;
import ast.expression.primary.trailer.ArgList;
import ast.expression.primary.trailer.SliceBound;
import ast.expression.primary.trailer.SubscriptIndex;
import ast.expression.primary.trailer.SubscriptSliceList;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.ListParam;
import ast.param.Params;
import ast.param.TypedParam;
import ast.param.UntypedParam;
import ast.path.DottedPath;
import ast.path.SimplePath;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

/**
 * Created by Nik on 17-06-2015
 */
public interface Visitor<T> {

	T visit(Decorator n);
	T visit(DottedPath n);
	T visit(Module n);
	T visit(SimplePath n);
	T visit(Suite n);

	//-----------------------------------------------------------------------------------------------------\\
	//------------------------------------------------ ARG ------------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	T visit(Arg n);
	T visit(CondArgument n);
	T visit(DefValArgument n);
	T visit(Kwarg n);
	T visit(SimpleArgument n);

	//-----------------------------------------------------------------------------------------------------\\
	//----------------------------------------------- PARAM -----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	T visit(Params n);
	T visit(ListParam n);
	T visit(UntypedParam n);
	T visit(TypedParam n);

	//-----------------------------------------------------------------------------------------------------\\
	//--------------------------------------------- STATEMENTS --------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\

	//............................................... SIMPLE ..............................................\\
	T visit(Assert n);
	T visit(AssignExpr n);
	T visit(AssignYield n);
	T visit(Delete n);
	T visit(Exec n);
	T visit(ExprList n);
	T visit(Global n);
	T visit(ImportFrom n);
	T visit(ImportPaths n);
	T visit(Nonlocal n);
	T visit(Pass n);
	T visit(Print n);

	//............................................... FLOW ................................................\\
	T visit(Break n);
	T visit(Continue n);
	T visit(RaiseEx n);
	T visit(RaiseFrom n);
	T visit(Return n);
	T visit(YieldValues n);
	T visit(YieldFrom n);

	//.............................................. COMPOUND .............................................\\
	T visit(ClassDef n);
	T visit(Except n);
	T visit(For n);
	T visit(Function n);
	T visit(If n);
	T visit(Try n);
	T visit(While n);
	T visit(With n);
	T visit(WithItem n);

	//-----------------------------------------------------------------------------------------------------\\
	//-------------------------------------------- EXPRESSIONS --------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	T visit(Comparison n);
	T visit(Conditional n);
	T visit(Lambda n);
	T visit(LambdaNoCond n);
	T visit(Power n);
	T visit(Shift n);

	//............................................. ARITHMETIC ............................................\\
	T visit(Arithmetic n);

	//.............................................. BITWISE ..............................................\\
	T visit(ast.expression.bitwise.And n);
	T visit(ast.expression.bitwise.Or n);
	T visit(Xor n);

	//........................................... COMPREHENSION ...........................................\\
	T visit(CompFor n);
	T visit(CompIf n);
	T visit(CondComprehension n);
	T visit(EnumComprehension n);

	//............................................... LOGICAL .............................................\\
	T visit(And n);
	T visit(Not n);
	T visit(Or n);

	//............................................... PRIMARY .............................................\\
	T visit(AttributeRef n);
	T visit(Call n);
	T visit(DirectCall n);
	T visit(Slice n);
	T visit(Subscription n);
	
	//atom
	T visit(Bool n);
	T visit(DictMaker n);
	T visit(Ellipsis n);
	T visit(Float n);
	T visit(Identifier n);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(Long n);
	T visit(None n);
	T visit(SetMaker n);
	T visit(Str n);
	T visit(StrConversion n);
	
	//trailer
	T visit(ArgList n);
	T visit(SliceBound n);
	T visit(SubscriptIndex n);
	T visit(SubscriptSliceList n);
	
	//................................................ UNARY ..............................................\\
	T visit(Invert n);
	T visit(Minus n);
	T visit(Plus n);
}