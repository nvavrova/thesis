package thesis;

import ast.*;
import ast.arg.Arg;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.atom.Float;
import ast.expression.atom.trailed.*;
import ast.expression.bitwise.And;
import ast.expression.bitwise.Or;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.CompFor;
import ast.expression.comprehension.CompIf;
import ast.expression.comprehension.CondComprehension;
import ast.expression.comprehension.EnumComprehension;
import ast.expression.logical.Not;
import ast.expression.primary.*;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Param;
import ast.param.Params;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

/**
 * Created by Nik on 29-06-2015
 */
public abstract class DefaultVisitor<T> implements Visitor<T> {

	@Override
	public T visit(Decorator n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(DottedPath n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Module n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SimplePath n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Suite n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Kwarg n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SimpleArg n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Param n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Params n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Assert n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(AssignExpr n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(AssignYield n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Delete n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ExprList n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Global n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Import n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ImportFrom n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Nonlocal n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Pass n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Break n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Continue n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Raise n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Return n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(YieldValues n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(YieldFrom n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ClassDef n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Except n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(For n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Function n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(If n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Try n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(While n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(With n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(WithItem n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Comparison n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Conditional n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Lambda n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(LambdaNoCond n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Power n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Shift n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Arithmetic n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Bool n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(DictMaker n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Ellipsis n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Float n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Identifier n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Imaginary n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Int n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Literal n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(None n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SetMaker n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Str n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(AttributeRef n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Call n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(DirectCall n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Slice n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Subscription n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(And n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Or n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Xor n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(CompFor n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(CompIf n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(CondComprehension n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(EnumComprehension n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ast.expression.logical.And n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Not n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ast.expression.logical.Or n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(ArgList n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SliceBound n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SubscriptIndex n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(SubscriptList n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Invert n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Minus n) {
		this.visitChildren(n);
		return null;
	}

	@Override
	public T visit(Plus n) {
		this.visitChildren(n);
		return null;
	}


	public void visitChildren(Module n) {
		n.getBody().forEach(s -> s.accept(this));
	}

	public void visitChildren(Decorator n) {
		n.getName().accept(this);
		if (n.hasArgList()) {
			n.getArgList().accept(this);
		}
	}

	public void visitChildren(SimpleArg n) {
		n.getValue().accept(this);
	}

	public void visitChildren(Kwarg n) {
		n.getValue().accept(this);
		//TODO: fix the Identifier! @NotNull ?
		if (n.getName() != null) {
			n.getName().accept(this);
		}
	}

	public void visitChildren(SimplePath n) {
	}

	public void visitChildren(DottedPath n) {
	}

	public void visitChildren(Param n) {
		n.getId().accept(this);

		if (n.hasDefaultVal()) {
			n.getDefaultVal().accept(this);
		}
	}

	public void visitChildren(Params n) {
		n.getArgs().forEach(p -> p.accept(this));
		n.getKwargs().forEach(p -> p.accept(this));
		n.getPositionalArgs().forEach(p -> p.accept(this));
	}

	public void visitChildren(Suite n) {
		n.getStatements().forEach(s -> s.accept(this));
	}

	public void visitChildren(Assert n) {
		n.getAssertion().accept(this);

		if (n.hasAssertionError()) {
			n.getAssertionError().accept(this);
		}
	}

	public void visitChildren(AssignExpr n) {
		n.getSource().accept(this);
		n.getTargets().accept(this);
	}

	public void visitChildren(AssignYield n) {
		n.getTargets().accept(this);
		n.getYield().accept(this);
	}

	public void visitChildren(ClassDef n) {
		n.getBody().accept(this);

		n.getName().accept(this);

		for (Arg arg : n.getInheritance()) {
			arg.accept(this);
		}
		n.getDecorators().forEach(d -> d.accept(this));
	}

	public void visitChildren(Delete n) {
		n.getItems().accept(this);
	}

	public void visitChildren(ExprList n) {
		n.getValues().forEach(e -> e.accept(this));
	}

	public void visitChildren(Global n) {
		n.getIdentifiers().forEach(i -> i.accept(this));
	}

	public void visitChildren(Import n) {
		n.getPaths().forEach(p -> p.accept(this));
	}

	public void visitChildren(ImportFrom n) {
		if (n.hasModule()) {
			n.getModule().accept(this);
		}
		n.getPaths().forEach(p -> p.accept(this));
	}

	public void visitChildren(Nonlocal n) {
		n.getIdentifiers().forEach(i -> i.accept(this));
	}

	public void visitChildren(Pass n) {
	}

	public void visitChildren(Break n) {
	}

	public void visitChildren(Continue n) {
	}

	public void visitChildren(Raise n) {
		if (n.hasSource()) {
			n.getSource().accept(this);
		}
		if (n.hasType()) {
			n.getType().accept(this);
		}
	}

	public void visitChildren(Return n) {
		if (n.hasValues()) {
			n.getValues().accept(this);
		}
	}

	public void visitChildren(YieldValues n) {
		if (n.hasValues()) {
			n.getValues().accept(this);
		}
	}

	public void visitChildren(YieldFrom n) {
		n.getFrom().accept(this);
	}

	public void visitChildren(Except n) {
		if (n.hasException()) {
			n.getException().accept(this);
		}
	}

	public void visitChildren(For n) {
		n.getBody().accept(this);
		if (n.hasElseBody()) {
			n.getElseBody().accept(this);
		}
		n.getIterator().accept(this);
		n.getSource().accept(this);
	}

	public void visitChildren(Function n) {
		n.getBody().accept(this);
		if (n.hasReturnType()) {
			n.getReturnType().accept(this);
		}
		n.getName().accept(this);
		n.getParams().accept(this);
		n.getDecorators().forEach(d -> d.accept(this));
	}

	public void visitChildren(If n) {
		for (Expr condition : n.getConditions()) {
			condition.accept(this);
			n.getBody(condition).accept(this);
		}

		if (n.hasElseBody()) {
			n.getElseBody().accept(this);
		}
	}

	public void visitChildren(Try n) {
		for (Except except : n.getExceptions()) {
			except.accept(this);

			Suite suite = n.getSuite(except);
			suite.accept(this);
		}

		n.getTryBlock().accept(this);
		if (n.hasElseBlock()) {
			n.getElseBlock().accept(this);
		}
		if (n.hasFinallyBlock()) {
			n.getFinallyBlock().accept(this);
		}
	}

	public void visitChildren(While n) {
		n.getBody().accept(this);
		if (n.hasElseBody()) {
			n.getElseBody().accept(this);
		}
		n.getCondition().accept(this);
	}

	public void visitChildren(With n) {
		n.getBody().accept(this);

		n.getItems().forEach(i -> i.accept(this));
	}

	public void visitChildren(WithItem n) {
		n.getItem().accept(this);
		if (n.hasAlias()) {
			n.getAlias().accept(this);
		}
	}

	public void visitChildren(Comparison n) {
		n.getOperands().forEach(e -> e.accept(this));
	}

	public void visitChildren(Conditional n) {
		n.getValue().accept(this);
		n.getCondition().accept(this);
		n.getConditionFalseValue().accept(this);
	}

	public void visitChildren(Lambda n) {
		if (n.hasParameters()) {
			n.getParameters().accept(this);
		}
		n.getExpression().accept(this);
	}

	public void visitChildren(LambdaNoCond n) {
		n.getParameters().accept(this);
		n.getExpression().accept(this);
	}

	public void visitChildren(Power n) {
		n.getBase().accept(this);
		if (n.hasExponent()) {
			n.getExponent().accept(this);
		}
	}

	public void visitChildren(Shift n) {
		n.getOperands().forEach(e -> e.accept(this));
	}

	public void visitChildren(Arithmetic n) {
		n.getOperands().forEach(e -> e.accept(this));
	}

	public void visitChildren(Bool n) {
	}

	public void visitChildren(Ellipsis n) {
	}

	public void visitChildren(ast.expression.atom.Float n) {
	}

	public void visitChildren(Identifier n) {
	}

	public void visitChildren(Imaginary n) {
	}

	public void visitChildren(Int n) {
	}

	public void visitChildren(Literal n) {
	}

	public void visitChildren(None n) {
	}

	public void visitChildren(Str n) {
	}

	public void visitChildren(DictMaker n) {
		if (n.hasComprehension()) {
			n.getComprehension().accept(this);
		}

		if (n.hasValues()) {
			for (Expr expr : n.getValues().keySet()) {
				expr.accept(this);
				n.getValues().get(expr).accept(this);
			}
		}
	}

	public void visitChildren(AttributeRef n) {
		n.getBase().accept(this);
		n.getAttribute().accept(this);
	}

	public void visitChildren(Call n) {
		n.getBase().accept(this);
		n.getArgs().accept(this);
	}

	public void visitChildren(DirectCall n) {
		n.getBase().accept(this);
		n.getCall().accept(this);
	}

	public void visitChildren(Slice n) {
		n.getBase().accept(this);
		n.getBound().accept(this);
	}

	public void visitChildren(Subscription n) {
		n.getBase().accept(this);

	}

	public void visitChildren(SetMaker n) {
		if (n.hasComprehension()) {
			n.getComprehension().accept(this);
		}
		if (n.hasValues()) {
			n.getValues().forEach(e -> e.accept(this));
		}
	}

	public void visitChildren(And n) {
	}

	public void visitChildren(Or n) {
	}

	public void visitChildren(Xor n) {
	}

	public void visitChildren(CompFor n) {
		if (n.hasNextLink()) {
			n.getNextLink().accept(this);
		}
		n.getSource().accept(this);
		n.getTargets().accept(this);
	}

	public void visitChildren(CompIf n) {
		if (n.hasNextLink()) {
			n.getNextLink().accept(this);
		}
		n.getExpression().accept(this);
	}

	public void visitChildren(CondComprehension n) {
		n.getCompFor().accept(this);
		n.getExpression().accept(this);
	}

	public void visitChildren(EnumComprehension n) {
		n.getValues().forEach(e -> e.accept(this));
	}

	public void visitChildren(ast.expression.logical.And n) {
		n.getOperands().forEach(e -> e.accept(this));
	}

	public void visitChildren(Not n) {
	}

	public void visitChildren(ast.expression.logical.Or n) {
		n.getOperands().forEach(e -> e.accept(this));
	}

	public void visitChildren(ArgList n) {
		if (n.hasArgs()) {
			n.getArgs().accept(this);
		}
		if (n.hasKwargs()) {
			n.getKwargs().accept(this);
		}

		n.getPositional().forEach(p -> p.accept(this));
	}

	public void visitChildren(SubscriptIndex n) {
		n.getIndex().accept(this);
	}

	public void visitChildren(SliceBound n) {
		if (n.hasLowerBound()) {
			n.getLowerBound().accept(this);
		}
		if (n.hasUpperBound()) {
			n.getUpperBound().accept(this);
		}
		if (n.hasStride()) {
			n.getStride().accept(this);
		}
	}

	public void visitChildren(SubscriptList n) {
		n.getValues().forEach(s -> s.accept(this));
	}

	public void visitChildren(Invert n) {
	}

	public void visitChildren(Minus n) {
	}

	public void visitChildren(Plus n) {
	}

}
