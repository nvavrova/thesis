package ast;

import ast.argument.*;
import ast.expression.Conditional;
import ast.expression.ExprList;
import ast.expression.Lambda;
import ast.expression.compiter.CompFor;
import ast.expression.compiter.CompIf;
import ast.expression.nocond.Arithmetic;
import ast.expression.nocond.LambdaNoCond;
import ast.expression.nocond.Power;
import ast.expression.nocond.bitwise.Shift;
import ast.expression.nocond.atom.*;
import ast.expression.nocond.atom.numeric.Float;
import ast.expression.nocond.atom.numeric.Imaginary;
import ast.expression.nocond.atom.numeric.Int;
import ast.expression.nocond.atom.numeric.Long;
import ast.expression.nocond.atom.comprehension.CondComprehension;
import ast.expression.nocond.atom.comprehension.EnumComprehension;
import ast.expression.nocond.atom.maker.DictMaker;
import ast.expression.nocond.atom.maker.SetMaker;
import ast.expression.nocond.atom.trailed.*;
import ast.expression.nocond.bitwise.Xor;
import ast.expression.nocond.logical.And;
import ast.expression.nocond.logical.Comparison;
import ast.expression.nocond.logical.Not;
import ast.expression.nocond.logical.Or;
import ast.expression.nocond.trailer.ArgList;
import ast.expression.nocond.trailer.SliceBound;
import ast.expression.nocond.trailer.SubscriptIndex;
import ast.expression.nocond.trailer.SubscriptSliceList;
import ast.expression.nocond.unary.Invert;
import ast.expression.nocond.unary.Minus;
import ast.expression.nocond.unary.Plus;
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
	T visit(Assign n);
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
	T visit(ast.expression.nocond.bitwise.And n);
	T visit(ast.expression.nocond.bitwise.Or n);
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