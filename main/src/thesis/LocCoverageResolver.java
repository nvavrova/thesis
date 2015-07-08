package thesis;

import ast.*;
import ast.arg.Arg;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.atom.trailed.*;
import ast.expression.bitwise.And;
import ast.expression.bitwise.Or;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.CompFor;
import ast.expression.comprehension.CompIf;
import ast.expression.comprehension.CondComprehension;
import ast.expression.comprehension.EnumComprehension;
import ast.expression.logical.Not;
import ast.expression.primary.ArgList;
import ast.expression.primary.SliceBound;
import ast.expression.primary.SubscriptIndex;
import ast.expression.primary.SubscriptList;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Param;
import ast.param.Params;
import ast.statement.Statement;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Nik on 29-06-2015
 */
public class LocCoverageResolver {

	private final LCRVisitor visitor;

	public static void resolve(Py3Node root) {
		LocCoverageResolver lr = new LocCoverageResolver();
		root.accept(lr.visitor);
	}

	private LocCoverageResolver() {
		this.visitor = new LCRVisitor();
	}

	private class LCRVisitor implements Visitor<Void> {

		private Integer ident;
		private PrintWriter writer;

		public LCRVisitor() {
			this.ident = 0;
			File file = new File("D:\\intellij_projects\\thesis\\data\\result.txt");
			try {
				this.writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				this.writer.println("----------------------------------------------------------------------------------------");
				this.writer.println("Run started: " + LocalDateTime.now());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}


		@Override
		public Void visit(Module n) {
			this.ident++;
			this.print("Module");
			this.visitStatements(n.getLocInfo(), n.getBody());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Decorator n) {
			this.ident++;
			this.print("Decorator");
			LocInfo locInfo = n.getLocInfo();

			this.visitPath(locInfo, n.getName());
			if (n.hasArgList()) {
				this.visitExpr(locInfo, n.getArgList());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SimpleArg n) {
			this.ident++;
			this.print("SimpleArg");
			this.visitExpr(n.getLocInfo(), n.getValue());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Kwarg n) {
			this.ident++;
			this.print("Kwarg");
			LocInfo locInfo = n.getLocInfo();

			this.visitExpr(locInfo, n.getValue());
			//TODO: fix the Identifier! @NotNull ?
			if (n.getName() != null) {
				this.visitExpr(locInfo, n.getName());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SimplePath n) {
			this.ident++;
			this.print("SimplePath");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(DottedPath n) {
			this.ident++;
			this.print("DottedPath");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Param n) {
			this.ident++;
			this.print("Param");
			LocInfo locInfo = n.getLocInfo();

			this.visitExpr(locInfo, n.getId());

			if (n.hasDefaultVal()) {
				this.visitExpr(locInfo, n.getDefaultVal());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Params n) {
			this.ident++;
			this.print("Params");
			LocInfo locInfo = n.getLocInfo();
			this.visitParams(locInfo, n);

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Suite n) {
			this.ident++;
			this.print("Suite");
			this.visitStatements(n.getLocInfo(), n.getStatements());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Assert n) {
			this.ident++;
			this.print("Assert");
			LocInfo locInfo = n.getLocInfo();

			n.getAssertion().accept(this);
			this.addLines(locInfo, n.getAssertion());

			if (n.hasAssertionError()) {
				n.getAssertionError().accept(this);
				this.addLines(locInfo, n.getAssertionError());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(AssignExpr n) {
			this.ident++;
			this.print("AssignExpr");
			LocInfo locInfo = n.getLocInfo();

			this.visitStatement(locInfo, n.getSource());
			this.visitStatement(locInfo, n.getTargets());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(AssignYield n) {
			this.ident++;
			this.print("AssignYield");
			LocInfo locInfo = n.getLocInfo();

			this.visitStatement(locInfo, n.getTargets());
			this.visitStatement(locInfo, n.getYield());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ClassDef n) {
			this.ident++;
			this.print("ClassDef");
			LocInfo locInfo = n.getLocInfo();

			n.getBody().accept(this);
			this.addLines(locInfo, n.getBody());

			this.visitIdentifier(locInfo, n.getName());

			for (Arg arg : n.getInheritance()) {
				arg.accept(this);
				this.addLines(locInfo, arg);
			}

			this.visitDecorators(locInfo, n.getDecorators());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Delete n) {
			this.ident++;
			this.print("Delete");
			this.visitStatement(n.getLocInfo(), n.getItems());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ExprList n) {
			this.ident++;
			this.print("ExprList");
			this.visitExprs(n.getLocInfo(), n.getValues());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Global n) {
			this.ident++;
			this.print("Global");
			this.visitIdentifiers(n.getLocInfo(), n.getIdentifiers());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Import n) {
			this.ident++;
			this.print("Import");
			this.visitPaths(n.getLocInfo(), n.getPaths());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ImportFrom n) {
			this.ident++;
			this.print("ImportFrom");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasModule()) {
				this.visitPath(locInfo, n.getModule());
			}
			this.visitPaths(locInfo, n.getPaths());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Nonlocal n) {
			this.ident++;
			this.print("Nonlocal");
			this.visitIdentifiers(n.getLocInfo(), n.getIdentifiers());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Pass n) {
			this.ident++;
			this.print("Pass");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Break n) {
			this.ident++;
			this.print("Break");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Continue n) {
			this.ident++;
			this.print("Continue");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Raise n) {
			this.ident++;
			this.print("Raise");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasSource()) {
				this.visitExpr(locInfo, n.getSource());
			}
			if (n.hasType()) {
				this.visitExpr(locInfo, n.getType());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Return n) {
			this.ident++;
			this.print("Return");
			if (n.hasValues()) {
				this.visitExprList(n.getLocInfo(), n.getValues());
			}
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(YieldValues n) {
			this.ident++;
			this.print("YieldValues");
			if (n.hasValues()) {
				this.visitExprList(n.getLocInfo(), n.getValues());
			}
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(YieldFrom n) {
			this.ident++;
			this.print("YieldFrom");
			this.visitExpr(n.getLocInfo(), n.getFrom());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Except n) {
			this.ident++;
			this.print("Except");
			if (n.hasException()) {
				this.visitExpr(n.getLocInfo(), n.getException());
			}
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(For n) {
			this.ident++;
			this.print("For");
			LocInfo locInfo = n.getLocInfo();

			this.visitSuite(locInfo, n.getBody());
			if (n.hasElseBody()) {
				this.visitSuite(locInfo, n.getElseBody());
			}
			this.visitExprList(locInfo, n.getIterator());
			this.visitExprList(locInfo, n.getSource());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Function n) {
			this.ident++;
			this.print("Function");
			LocInfo locInfo = n.getLocInfo();

			this.visitSuite(locInfo, n.getBody());
			if (n.hasReturnType()) {
				this.visitExpr(locInfo, n.getReturnType());
			}
			this.visitIdentifier(locInfo, n.getName());
			this.visitParams(locInfo, n.getParams());
			this.visitDecorators(locInfo, n.getDecorators());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(If n) {
			this.ident++;
			this.print("If");
			LocInfo locInfo = n.getLocInfo();

			for (Expr condition : n.getConditions()) {
				this.visitExpr(locInfo, condition);
				this.visitSuite(locInfo, n.getBody(condition));
			}

			if (n.hasElseBody()) {
				this.visitSuite(locInfo, n.getElseBody());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Try n) {
			this.ident++;
			this.print("Try");
			LocInfo locInfo = n.getLocInfo();

			for (Except except : n.getExceptions()) {
				except.accept(this);
				this.addLines(locInfo, except);

				Suite suite = n.getSuite(except);
				this.visitSuite(locInfo, suite);
			}

			this.visitSuite(locInfo, n.getTryBlock());
			if (n.hasElseBlock()) {
				this.visitSuite(locInfo, n.getElseBlock());
			}
			if (n.hasFinallyBlock()) {
				this.visitSuite(locInfo, n.getFinallyBlock());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(While n) {
			this.ident++;
			this.print("While");
			LocInfo locInfo = n.getLocInfo();

			this.visitSuite(locInfo, n.getBody());
			if (n.hasElseBody()) {
				this.visitSuite(locInfo, n.getElseBody());
			}
			this.visitExpr(locInfo, n.getCondition());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(With n) {
			this.ident++;
			this.print("With");
			LocInfo locInfo = n.getLocInfo();

			this.visitSuite(locInfo, n.getBody());

			for (WithItem item : n.getItems()) {
				item.accept(this);
				this.addLines(locInfo, item);
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(WithItem n) {
			this.ident++;
			this.print("WithItem");
			this.visitExpr(n.getLocInfo(), n.getItem());
			if (n.hasAlias()) {
				this.visitExpr(n.getLocInfo(), n.getAlias());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Comparison n) {
			this.ident++;
			this.print("Comparison");
			this.visitExprs(n.getLocInfo(), n.getOperands());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Conditional n) {
			this.ident++;
			this.print("Conditional");
			LocInfo locInfo = n.getLocInfo();

			this.visitExpr(locInfo, n.getValue());
			this.visitExpr(locInfo, n.getCondition());
			this.visitExpr(locInfo, n.getConditionFalseValue());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Lambda n) {
			this.ident++;
			this.print("Lambda");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasParameters()) {
				this.visitParams(locInfo, n.getParameters());
			}
			this.visitExpr(locInfo, n.getExpression());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(LambdaNoCond n) {
			this.ident++;
			this.print("LambdaNoCond");
			LocInfo locInfo = n.getLocInfo();

			this.visitParams(locInfo, n.getParameters());
			this.visitExpr(locInfo, n.getExpression());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Power n) {
			this.ident++;
			this.print("Power");
			LocInfo locInfo = n.getLocInfo();

			this.visitExpr(locInfo, n.getBase());
			if (n.hasExponent()) {
				this.visitExpr(locInfo, n.getExponent());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Shift n) {
			this.ident++;
			this.print("Shift");
			this.visitExprs(n.getLocInfo(), n.getOperands());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Arithmetic n) {
			this.ident++;
			this.print("Arithmetic");
			this.visitExprs(n.getLocInfo(), n.getOperands());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Bool n) {
			this.ident++;
			this.print("Bool");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Ellipsis n) {
			this.ident++;
			this.print("Ellipsis");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ast.expression.atom.Float n) {
			this.ident++;
			this.print("ast");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Identifier n) {
			this.ident++;
			this.print("Identifier");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Imaginary n) {
			this.ident++;
			this.print("Imaginary");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Int n) {
			this.ident++;
			this.print("Int");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Literal n) {
			this.ident++;
			this.print("Literal");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(None n) {
			this.ident++;
			this.print("None");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Str n) {
			this.ident++;
			this.print("Str");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(AttributeRef n) {
			this.ident++;
			this.print("AttributeRef");

			LocInfo locInfo = n.getLocInfo();
			this.visitExpr(locInfo, n.getBase());
			this.visitExpr(locInfo, n.getAttribute());

			this.print("LOC: " + locInfo.getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Call n) {
			this.ident++;
			this.print("Call");

			LocInfo locInfo = n.getLocInfo();
			this.visitExpr(locInfo, n.getBase());
			this.visitExpr(locInfo, n.getArgs());

			this.print("LOC: " + locInfo.getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(DirectCall n) {
			this.ident++;
			this.print("DirectCall");

			LocInfo locInfo = n.getLocInfo();
			this.visitExpr(locInfo, n.getBase());
			this.visitExpr(locInfo, n.getCall());

			this.print("LOC: " + locInfo.getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Slice n) {
			this.ident++;
			this.print("Slice");

			LocInfo locInfo = n.getLocInfo();
			this.visitExpr(locInfo, n.getBase());
			this.visitExpr(locInfo, n.getBound());

			this.print("LOC: " + locInfo.getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Subscription n) {
			this.ident++;
			this.print("Subscript");

			LocInfo locInfo = n.getLocInfo();
			this.visitExpr(locInfo, n.getBase());
			this.visitExpr(locInfo, n.getSubscript());

			this.print("LOC: " + locInfo.getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(DictMaker n) {
			this.ident++;
			this.print("DictMaker");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasComprehension()) {
				this.visitExpr(locInfo, n.getComprehension());
			}

			if (n.hasValues()) {
				for (Expr expr : n.getValues().keySet()) {
					this.visitExpr(locInfo, expr);
					this.visitExpr(locInfo, n.getValues().get(expr));
				}
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SetMaker n) {
			this.ident++;
			this.print("SetMaker");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasComprehension()) {
				this.visitExpr(locInfo, n.getComprehension());
			}
			if (n.hasValues()) {
				this.visitExprs(locInfo, n.getValues());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(And n) {
			this.ident++;
			this.print("And");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Or n) {
			this.ident++;
			this.print("Or");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Xor n) {
			this.ident++;
			this.print("Xor");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(CompFor n) {
			this.ident++;
			this.print("CompFor");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasNextLink()) {
				this.visitExpr(locInfo, n.getNextLink());
			}
			this.visitExpr(locInfo, n.getSource());
			this.visitExprList(locInfo, n.getTargets());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(CompIf n) {
			this.ident++;
			this.print("CompIf");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasNextLink()) {
				this.visitExpr(locInfo, n.getNextLink());
			}
			this.visitExpr(locInfo, n.getExpression());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(CondComprehension n) {
			this.ident++;
			this.print("CondComprehension");
			LocInfo locInfo = n.getLocInfo();

			this.visitExpr(locInfo, n.getCompFor());
			this.visitExpr(locInfo, n.getExpression());

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(EnumComprehension n) {
			this.ident++;
			this.print("EnumComprehension");
			this.visitExprs(n.getLocInfo(), n.getValues());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ast.expression.logical.And n) {
			this.ident++;
			this.print("ast");
			this.visitExprs(n.getLocInfo(), n.getOperands());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Not n) {
			this.ident++;
			this.print("Not");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ast.expression.logical.Or n) {
			this.ident++;
			this.print("ast");
			this.visitExprs(n.getLocInfo(), n.getOperands());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(ArgList n) {
			this.ident++;
			this.print("ArgList");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasArgs()) {
				this.visitExpr(locInfo, n.getArgs());
			}
			if (n.hasKwargs()) {
				this.visitExpr(locInfo, n.getKwargs());
			}

			for (Arg arg : n.getPositional()) {
				arg.accept(this);
				this.addLines(locInfo, arg);
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SubscriptIndex n) {
			this.ident++;
			this.print("SubscriptIndex");
			this.visitExpr(n.getLocInfo(), n.getIndex());
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SliceBound n) {
			this.ident++;
			this.print("SliceBound");
			LocInfo locInfo = n.getLocInfo();

			if (n.hasLowerBound()) {
				this.visitExpr(locInfo, n.getLowerBound());
			}
			if (n.hasUpperBound()) {
				this.visitExpr(locInfo, n.getUpperBound());
			}
			if (n.hasStride()) {
				this.visitExpr(locInfo, n.getStride());
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(SubscriptList n) {
			this.ident++;
			this.print("SubscriptList");
			LocInfo locInfo = n.getLocInfo();

			for (SubscriptIndex subscript : n.getValues()) {
				this.visitExpr(locInfo, subscript);
			}

			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Invert n) {
			this.ident++;
			this.print("Invert");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Minus n) {
			this.ident++;
			this.print("Minus");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		@Override
		public Void visit(Plus n) {
			this.ident++;
			this.print("Plus");
			n.getLocInfo().setRangeCovered();
			this.print("LOC: " + n.getLocInfo().getLocSpan());
			this.ident--;
			return null;
		}

		private void visitSuite(LocInfo locInfo, Suite suite) {
			suite.accept(this);
			this.addLines(locInfo, suite);
		}

		private void visitExprList(LocInfo locInfo, ExprList values) {
			values.accept(this);
			this.addLines(locInfo, values);
		}

		private void visitStatements(LocInfo locInfo, List<Statement> statements) {
			for (Statement statement : statements) {
				this.visitStatement(locInfo, statement);
			}
		}

		private void visitExprs(LocInfo locInfo, List<Expr> exprs) {
			for (Expr expr : exprs) {
				expr.accept(this);
				this.visitExpr(locInfo, expr);
			}
		}

		private void visitDecorators(LocInfo locInfo, List<Decorator> decorators) {
			for (Decorator decorator : decorators) {
				decorator.accept(this);
				this.addLines(locInfo, decorator);
			}
		}

		private void visitStatement(LocInfo locInfo, Statement statement) {
			statement.accept(this);
			this.addLines(locInfo, statement);
		}

		private void visitExpr(LocInfo locInfo, Expr expr) {
			expr.accept(this);
			this.addLines(locInfo, expr);
		}

		private void visitPaths(LocInfo locInfo, List<Path> paths) {
			for (Path path : paths) {
				this.visitPath(locInfo, path);
			}
		}

		private void visitPath(LocInfo locInfo, Path path) {
			path.accept(this);
			this.addLines(locInfo, path);
		}

		private void visitIdentifiers(LocInfo locInfo, List<Identifier> identifiers) {
			for (Identifier identifier : identifiers) {
				this.visitIdentifier(locInfo, identifier);
			}
		}

		private void visitIdentifier(LocInfo locInfo, Identifier identifier) {
			identifier.accept(this);
			this.addLines(locInfo, identifier);
		}

		private void visitParams(LocInfo locInfo, Params params) {
			this.visitParams(locInfo, params.getArgs());
			this.visitParams(locInfo, params.getKwargs());
			this.visitParams(locInfo, params.getPositionalArgs());
		}


		private void visitParams(LocInfo locInfo, List<Param> params) {
			for (Param param : params) {
				param.accept(this);
				this.addLines(locInfo, param);
			}
		}

		private void addLines(LocInfo locInfo, Py3Node node) {
			node.getLocInfo().getLines().forEach(line -> locInfo.addLine(line));
		}

		private void print(String s) {
//			for (int i = 0; i < this.ident; i++) {
//				System.out.print("\t");
//			}
//			System.out.println(s);
		}

//		private void print(String s) {
//			if (this.writer != null) {
//				for (int i = 0; i < this.ident; i++) {
//					this.writer.print("\t");
//				}
//				this.writer.println(s);
//			}
//		}
	}
}
