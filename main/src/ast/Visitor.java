package ast;

import ast.argument.*;
import ast.expression.Conditional;
import ast.expression.ExprList;
import ast.expression.Lambda;
import ast.expression.compiter.CompFor;
import ast.expression.compiter.CompIf;
import ast.expression.nocond.LambdaNoCond;
import ast.expression.nocond.arithmetic.Nnary;
import ast.expression.nocond.arithmetic.Power;
import ast.expression.nocond.arithmetic.Unary;
import ast.expression.nocond.atom.*;
import ast.expression.nocond.atom.comprehension.CondComprehension;
import ast.expression.nocond.atom.comprehension.EnumComprehension;
import ast.expression.nocond.atom.maker.DictMaker;
import ast.expression.nocond.atom.maker.SetMaker;
import ast.expression.nocond.atom.numeric.Float;
import ast.expression.nocond.atom.numeric.Imaginary;
import ast.expression.nocond.atom.numeric.Int;
import ast.expression.nocond.atom.numeric.Long;
import ast.expression.nocond.atom.trailed.AttributeRef;
import ast.expression.nocond.atom.trailed.Call;
import ast.expression.nocond.atom.trailed.ObjectMethodCall;
import ast.expression.nocond.atom.trailed.Slice;
import ast.expression.nocond.atom.yield.YieldFrom;
import ast.expression.nocond.atom.yield.YieldValues;
import ast.expression.nocond.bitwise.And;
import ast.expression.nocond.bitwise.Or;
import ast.expression.nocond.bitwise.Shift;
import ast.expression.nocond.bitwise.Xor;
import ast.expression.nocond.logical.Binary;
import ast.expression.nocond.logical.Comparison;
import ast.expression.nocond.logical.Not;
import ast.expression.nocond.trailer.ArgList;
import ast.expression.nocond.trailer.SliceBound;
import ast.expression.nocond.trailer.SubscriptIndex;
import ast.expression.nocond.trailer.SubscriptSliceList;
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
	T visit(Module n);
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
	T visit(ListParam n);
	T visit(Params n);
	T visit(TypedParam n);
	T visit(UntypedParam n);

	//-----------------------------------------------------------------------------------------------------\\
	//----------------------------------------------- PATH ------------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	T visit(DottedPath n);
	T visit(SimplePath n);

	//-----------------------------------------------------------------------------------------------------\\
	//--------------------------------------------- STATEMENTS --------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\

	//............................................... SIMPLE ..............................................\\
	T visit(Assert n);
	T visit(Assign n);
	T visit(Delete n);
	T visit(Exec n);
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
	T visit(Yield n);

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
	T visit(Conditional n);
	T visit(ExprList n);
	T visit(Lambda n);

	//............................................. COMP ITER .............................................\\
	T visit(CompFor n);
	T visit(CompIf n);

	//.............................................. NO COND ..............................................\\
	T visit(LambdaNoCond n);

	//NO COND - ARITHMETIC
	T visit(Nnary n);
	T visit(Unary n);
	T visit(Power n);

	//NO COND - ATOM
	T visit(Bool n);
	T visit(Ellipsis n);
	T visit(Identifier n);
	T visit(None n);
	T visit(Str n);
	T visit(StrConversion n);

	//atom - comprehension
	T visit(CondComprehension n);
	T visit(EnumComprehension n);

	//atom - maker
	T visit(DictMaker n);
	T visit(SetMaker n);

	//atom - numeric
	T visit(Float n);
	T visit(Imaginary n);
	T visit(Int n);
	T visit(Long n);

	//atom - trailed
	T visit(AttributeRef n);
	T visit(Call n);
	T visit(ObjectMethodCall n);
	T visit(Slice n);

	//atom - yield
	T visit(YieldValues n);
	T visit(YieldFrom n);

	//NO COND - BITWISE
	T visit(And n);
	T visit(Or n);
	T visit(Shift n);
	T visit(Xor n);

	//NO COND - LOGICAL
	T visit(Binary n);
	T visit(Comparison n);
	T visit(Not n);

	//NO COND - TRAILER
	T visit(ArgList n);
	T visit(SliceBound n);
	T visit(SubscriptIndex n);
	T visit(SubscriptSliceList n);
}