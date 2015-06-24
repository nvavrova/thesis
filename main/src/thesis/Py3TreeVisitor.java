package thesis;

import ast.*;
import ast.arg.Args;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.bitwise.Xor;
import ast.expression.display.*;
import ast.expression.logical.And;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.maker.DictMaker;
import ast.expression.maker.SetMaker;
import ast.expression.primary.SliceBound;
import ast.expression.primary.SubscriptIndex;
import ast.expression.primary.Subscripts;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Param;
import ast.param.Parameter;
import ast.param.Params;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

/**
 * Created by Nik on 17-06-2015
 */
public interface Py3TreeVisitor<T> {

	public T visit(Parameter n);

	//--------------------------------------------- TOP LEVEL ---------------------------------------------\\
	public T visit(Module n);
	public T visit(ClassDef n);
	public T visit(DecoratedClass n);
	public T visit(Import n);
	public T visit(Decorator n);

	//--------------------------------------------- STATEMENTS --------------------------------------------\\
	//simple
	public T visit(Assert n);
	public T visit(AssignExpr n);
	public T visit(AssignYield n);
	public T visit(Delete n);
	public T visit(Global n);
	public T visit(Nonlocal n);
	public T visit(Pass n);

	//flow
	public T visit(Break n);
	public T visit(Continue n);
	public T visit(Raise n);
	public T visit(Return n);
	public T visit(YieldValues n);
	public T visit(YieldFrom n);

	//compound
	public T visit(For n);
	public T visit(Function n);
	public T visit(DecoratedFunction n);
	public T visit(If n);
	public T visit(While n);


	//-------------------------------------------- EXPRESSIONS --------------------------------------------\\
	public T visit(Comparison n);
	public T visit(Conditional n);
	public T visit(Lambda n);
	public T visit(Shift n);
	public T visit(Slice n);

	//arithmetic
	public T visit(Arithmetic n);

	//atom
	public T visit(Bool n);
	public T visit(Comment n);
	public T visit(DottedImport n);
	public T visit(Ellipsis n);
	public T visit(Enclosure n);
	public T visit(ast.expression.atom.Float n);
	public T visit(Identifier identifier);
	public T visit(Imaginary n);
	public T visit(Int n);
	public T visit(Literal n);
	public T visit(None n);
	public T visit(Str n);

	//bitwise
	public T visit(ast.expression.bitwise.And n);
	public T visit(ast.expression.bitwise.Or n);

	public T visit(Xor n);
	//display
	public T visit(CompFor n);
	public T visit(CompIf n);
	public T visit(CondComprehension n);
	public T visit(Dictionary n);
	public T visit(EnumComprehension enumComprehension);
	public T visit(ListExpr n);
	public T visit(SetExpr n);
	public T visit(Tuple n);

	//logical
	public T visit(And n);
	public T visit(Not n);
	public T visit(Or n);

	//makers
	public T visit(DictMaker n);
	public T visit(SetMaker n);

	//primary
	public T visit(SubscriptIndex n);
	public T visit(SliceBound n);

	//unary
	public T visit(Invert n);
	public T visit(Minus n);
	public T visit(Plus n);

	//TODO: sort
	public T visit(SimpleArg n);
	public T visit(Kwarg n);
	public T visit(SimplePath n);
	public T visit(DottedPath n);
	public T visit(Try n);
	public T visit(With with);
	public T visit(Args args);
	public T visit(LambdaNoCond lambdaNoCond);
	public T visit(Power power);
	public T visit(Subscripts subscripts);
	public T visit(Param param);
	public T visit(Params params);
	public T visit(Except except);
	public T visit(WithItem withItem);
}