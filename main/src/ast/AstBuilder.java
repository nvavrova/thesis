package ast;

import ast.argument.*;
import ast.expression.Conditional;
import ast.expression.Expr;
import ast.expression.ExprList;
import ast.expression.Lambda;
import ast.expression.compiter.CompFor;
import ast.expression.compiter.CompIf;
import ast.expression.compiter.CompIter;
import ast.expression.nocond.LambdaNoCond;
import ast.expression.nocond.NonConditional;
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
import ast.expression.nocond.atom.trailed.TrailedAtomBuilder;
import ast.expression.nocond.atom.yield.Yield;
import ast.expression.nocond.atom.yield.YieldFrom;
import ast.expression.nocond.atom.yield.YieldValues;
import ast.expression.nocond.bitwise.Shift;
import ast.expression.nocond.bitwise.Xor;
import ast.expression.nocond.logical.*;
import ast.expression.nocond.trailer.*;
import ast.param.*;
import ast.path.DottedPath;
import ast.path.Path;
import ast.path.SimplePath;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;
import gen.PythonParser;
import gen.PythonVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import util.StringHelper;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 19-05-2015
 */
public class AstBuilder {

	private final AstBuilderVisitor visitor;
	private final ParserRuleContext context;

	public AstBuilder(ParserRuleContext context, String filePath) {
		this.visitor = new AstBuilderVisitor(filePath);
		this.context = context;
	}

	public static Module buildErrorModule(String filePath) {
		return new Module(filePath, new LocCounter(filePath).count());
	}

	public Module build() {
		return (Module) this.context.accept(this.visitor);
	}

	private class AstBuilderVisitor implements PythonVisitor<AstNode> {

		private final String filePath;
		private final LocCounter locCounter;

		public AstBuilderVisitor(String filePath) {
			this.filePath = filePath;
			this.locCounter = new LocCounter(this.filePath);
		}

		@Override
		public AstNode visitSingle_input(@NotNull PythonParser.Single_inputContext ctx) {
			//      NEWLINE | simple_stmt | compound_stmt NEWLINE
			if (ctx.simple_stmt() != null) {
				return ctx.simple_stmt().accept(this);
			}
			if (ctx.compound_stmt() != null) {
				return ctx.compound_stmt().accept(this);
			}
			if (ctx.NEWLINE() != null) {
				return null;
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitFile_input(@NotNull PythonParser.File_inputContext ctx) {
			//      ( NEWLINE | stmt )* EOF
			List<ast.statement.Statement> children = new ArrayList<>();
			if (ctx.stmt() != null) {
				ctx.stmt().forEach(e -> children.addAll(((CollectionWrapper<ast.statement.Statement>) e.accept(this)).items));
			}
			return new Module(this.filePath, this.locCounter.count(), children);
		}

		@Override
		public AstNode visitEval_input(@NotNull PythonParser.Eval_inputContext ctx) {
			//      NEWLINE* testlist NEWLINE* EOF
			return ctx.testlist().accept(this);
		}

		@Override
		public AstNode visitDecorator(@NotNull PythonParser.DecoratorContext ctx) {
			//      '@' dotted_name ( '(' arglist? ')' )? NEWLINE
			DottedPath id = (DottedPath) ctx.dotted_name().accept(this);
			ArgList argList = ctx.arglist() == null ? null : (ArgList) ctx.arglist().accept(this);
			return new Decorator(this.getLocInfo(ctx), id, argList);
		}

		@Override
		public AstNode visitDecorators(@NotNull PythonParser.DecoratorsContext ctx) {
			//      decorator+
			if (ctx.decorator() != null) {
				List<Decorator> decorators = ctx.decorator()
						.stream().map(e -> (Decorator) e.accept(this))
						.collect(Collectors.toList());

				return new CollectionWrapper<>(this.getLocInfo(ctx), decorators);
			}
			return new CollectionWrapper<>(this.getLocInfo(ctx), Collections.emptyList());
		}

		@Override
		public AstNode visitDecorated(@NotNull PythonParser.DecoratedContext ctx) {
			//      decorators ( classdef | funcdef | async_funcdef)
			CollectionWrapper<Decorator> decorators = (CollectionWrapper<Decorator>) ctx.decorators().accept(this);
			if (ctx.classdef() != null) {
				ClassDef classDef = (ClassDef) ctx.classdef().accept(this);
				classDef.setDecorators(decorators.getItems());
				return classDef;
			}

			if (ctx.funcdef() != null) {
				Function function = (Function) ctx.funcdef().accept(this);
				function.setDecorators(decorators.getItems());
				return function;
			}

			if (ctx.async_funcdef() != null) {
				Function function = (Function) ctx.async_funcdef().accept(this);
				function.setDecorators(decorators.getItems());
				return function;
			}

			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitName(PythonParser.NameContext ctx) {
			return new Identifier(this.getLocInfo(ctx), ctx.getText());
		}

		@Override
		public AstNode visitAsync_funcdef(PythonParser.Async_funcdefContext ctx) {
			Function f = (Function) ctx.funcdef().accept(this);
			f.markAsAsync();
			return f;
		}

		@Override
		public AstNode visitFuncdef(@NotNull PythonParser.FuncdefContext ctx) {
			//      DEF name parameters ( '->' test )? ':' suite
			Params params = (Params) ctx.parameters().accept(this);
			Expr returnType = ctx.test() == null ? null : (Expr) ctx.test().accept(this);
			Suite suite = this.process(ctx.suite());
			Identifier identifier = (Identifier) ctx.name().accept(this);
			return new Function(this.getLocInfo(ctx), identifier, returnType, suite, params);
		}

		@Override
		public AstNode visitParameters(@NotNull PythonParser.ParametersContext ctx) {
			//      '(' typedargslist ')' | '(' varargslist? ')'
			if (ctx.typedargslist() != null) {
				return ctx.typedargslist().accept(this);
			}
			if (ctx.varargslist() != null) {
				return ctx.varargslist().accept(this);
			}
			return new Params(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitTypedargslist(@NotNull PythonParser.TypedargslistContext ctx) {
			//		tfpdef ( '=' test )? ( ',' tfpdef ( '=' test )? )* ( ',' ( '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		                                                         | '**' tfpdef
			//		                                                         )?
			//		                     )?
			//		| '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef
			Map<ParserRuleContext, PythonParser.TestContext> upcasted = new HashMap<>();
			upcasted.putAll(ctx.regular);
			List<Param> regular = this.getParameters(upcasted);

			Param positional = ctx.positional != null ? (Param) ctx.positional.accept(this) : null;
			Param keyword = ctx.keyword != null? (Param) ctx.keyword.accept(this) : null;
			return new Params(this.getLocInfo(ctx), regular, positional, keyword);
		}

		@Override
		public AstNode visitTfpdef(@NotNull PythonParser.TfpdefContext ctx) {
			//      name ( ':' test )?
			Identifier id = (Identifier) ctx.name().accept(this);
			if (ctx.test() != null) {
				Expr returnType = (Expr) ctx.test().accept(this);
				return new TypedParam(this.getLocInfo(ctx), id, returnType);
			}
			return new UntypedParam(this.getLocInfo(ctx), id);
		}

		@Override
		public AstNode visitVarargslist(@NotNull PythonParser.VarargslistContext ctx) {
			//		vfpdef ( '=' test )? ( ',' vfpdef ( '=' test )? )* ( ',' ( '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		                                                         | '**' vfpdef
			//		                                                         )?
			//		                     )?
			//		| '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			Map<ParserRuleContext, PythonParser.TestContext> upcasted = new HashMap<>();
			upcasted.putAll(ctx.regular);
			List<Param> regular = this.getParameters(upcasted);

			Param positional = ctx.positional != null ? (Param) ctx.positional.accept(this) : null;
			Param keyword = ctx.keyword != null ? (Param) ctx.keyword.accept(this) : null;
			return new Params(this.getLocInfo(ctx), regular, positional, keyword);
		}

		@Override
		public AstNode visitVfpdef(@NotNull PythonParser.VfpdefContext ctx) {
			//      name | '(' vfplist ')'
			if (ctx.name() != null) {
				Identifier id = (Identifier) ctx.name().accept(this);
				return new UntypedParam(this.getLocInfo(ctx), id);
			}
			if (ctx.vfplist() != null) {
				return ctx.vfplist().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitVfplist(PythonParser.VfplistContext ctx) {
			//		vfpdef (',' vfpdef)* (',')?
			List<Param> params = ctx.vfpdef().stream()
					.map(v -> (Param) v.accept(this))
					.collect(Collectors.toList());
			return new ListParam(this.getLocInfo(ctx), params);
		}

		@Override
		public AstNode visitStmt(@NotNull PythonParser.StmtContext ctx) {
			//      simple_stmt | compound_stmt
			if (ctx.simple_stmt() != null) {
				return ctx.simple_stmt().accept(this); //returns a Wrapper
			}
			if (ctx.compound_stmt() != null) {
				//wrap this for consistency
				ast.statement.Statement s = (ast.statement.Statement) ctx.compound_stmt().accept(this);
				return new CollectionWrapper<>(this.getLocInfo(ctx), s);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitSimple_stmt(@NotNull PythonParser.Simple_stmtContext ctx) {
			//      small_stmt ( ';' small_stmt )* ';'? NEWLINE
			List<ast.statement.Statement> statements = ctx.small_stmt().stream()
					.map(e -> (ast.statement.Statement) e.accept(this))
					.collect(Collectors.toList());
			return new CollectionWrapper<>(this.getLocInfo(ctx), statements);
		}

		@Override
		public AstNode visitSmall_stmt(@NotNull PythonParser.Small_stmtContext ctx) {
			//      expr_stmt | print_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt
			//		| global_stmt | nonlocal_stmt | exec_stmt | assert_stmt
			if (ctx.expr_stmt() != null) {
				return ctx.expr_stmt().accept(this);
			}
			if (ctx.print_stmt() != null) {
				return ctx.print_stmt().accept(this);
			}
			if (ctx.del_stmt() != null) {
				return ctx.del_stmt().accept(this);
			}
			if (ctx.pass_stmt() != null) {
				return ctx.pass_stmt().accept(this);
			}
			if (ctx.flow_stmt() != null) {
				return ctx.flow_stmt().accept(this);
			}
			if (ctx.import_stmt() != null) {
				return ctx.import_stmt().accept(this);
			}
			if (ctx.global_stmt() != null) {
				return ctx.global_stmt().accept(this);
			}
			if (ctx.nonlocal_stmt() != null) {
				return ctx.nonlocal_stmt().accept(this);
			}
			if (ctx.exec_stmt() != null) {
				return ctx.exec_stmt().accept(this);
			}
			if (ctx.assert_stmt() != null) {
				return ctx.assert_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitExpr_stmt(@NotNull PythonParser.Expr_stmtContext ctx) {
			//      testlist_star_expr ( augassign ( yield_expr | testlist) | ( '=' ( yield_expr | testlist_star_expr ) )* )
			String operator = "=";
			if (ctx.augassign() != null) {
				Wrapper<String> wrap = (Wrapper<String>) ctx.augassign().accept(this);
				operator = wrap.getItem();
			}

			//single assign (augassign)
			if (ctx.assignYield != null) {
				List<Yield> yieldElements = new ArrayList<>();
				yieldElements.add((Yield) ctx.assignYield.accept(this));

				List<ExprList> exprElements = new ArrayList<>();
				exprElements.add((ExprList) ctx.target.accept(this));

				return new Assign(this.getLocInfo(ctx), operator, exprElements, yieldElements);
			}
			if (ctx.assignTest != null) {
				List<ExprList> exprElements = new ArrayList<>();
				exprElements.add((ExprList) ctx.target.accept(this));
				exprElements.add((ExprList) ctx.assignTest.accept(this));
				return new Assign(this.getLocInfo(ctx), operator, exprElements, Collections.emptyList());
			}

			//no assign
			if (ctx.chainedAssign.size() == 1) {
				return ctx.chainedAssign.get(0).accept(this);
			}

			//chained assign
			List<ExprList> exprElements = ctx.testlist_star_expr().stream()
					.map(a -> (ExprList) a.accept(this))
					.collect(Collectors.toList());
			List<Yield> yieldElements = ctx.yield_expr().stream()
					.map(a -> (Yield) a.accept(this))
					.collect(Collectors.toList());
			return new Assign(this.getLocInfo(ctx), operator, exprElements, yieldElements);
		}


		@Override
		public AstNode visitTestlist_star_expr(@NotNull PythonParser.Testlist_star_exprContext ctx) {
			//      ( test | star_expr ) ( ',' ( test |  star_expr ) )* ','?
			List<Expr> children = new ArrayList<>();
			if (ctx.test() != null) {
				children.addAll(ctx.test().stream()
						.map(e -> (Expr) e.accept(this))
						.collect(Collectors.toList()));
			}
			if (ctx.star_expr() != null) {
				children.addAll(ctx.star_expr().stream()
						.map(e -> (Expr) e.accept(this))
						.collect(Collectors.toList()));
			}
			return new ExprList(this.getLocInfo(ctx), children);
		}

		@Override
		public AstNode visitAugassign(@NotNull PythonParser.AugassignContext ctx) {
			//      '+=' | '-=' | '*=' | '@=' | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '**=' | '//='
			return new Wrapper<>(this.getLocInfo(ctx), ctx.op.getText());
		}

		@Override
		public AstNode visitPrint_stmt(PythonParser.Print_stmtContext ctx) {
			//      PRINT ( ( test (',' test)* ','? )? | '>>' test ( (',' test)+ ','? )?
			List<Expr> expressions = ctx.test().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			return new Print(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitDel_stmt(@NotNull PythonParser.Del_stmtContext ctx) {
			//      DEL exprlist
			ExprList expressions = (ExprList) ctx.exprlist().accept(this);
			return new Delete(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitPass_stmt(@NotNull PythonParser.Pass_stmtContext ctx) {
			//PASS
			return new Pass(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitFlow_stmt(@NotNull PythonParser.Flow_stmtContext ctx) {
			//      break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt
			if (ctx.break_stmt() != null) {
				return ctx.break_stmt().accept(this);
			}
			if (ctx.continue_stmt() != null) {
				return ctx.continue_stmt().accept(this);
			}
			if (ctx.return_stmt() != null) {
				return ctx.return_stmt().accept(this);
			}
			if (ctx.raise_stmt() != null) {
				return ctx.raise_stmt().accept(this);
			}
			if (ctx.yield_stmt() != null) {
				return ctx.yield_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitBreak_stmt(@NotNull PythonParser.Break_stmtContext ctx) {
			//      BREAK
			return new Break(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitContinue_stmt(@NotNull PythonParser.Continue_stmtContext ctx) {
			//      CONTINUE
			return new Continue(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitReturn_stmt(@NotNull PythonParser.Return_stmtContext ctx) {
			//      RETURN testlist?
			if (ctx.testlist() != null) {
				ExprList expressions = (ExprList) ctx.testlist().accept(this);
				return new Return(this.getLocInfo(ctx), expressions);
			}
			return new Return(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitYield_stmt(@NotNull PythonParser.Yield_stmtContext ctx) {
			//      yield_expr
			Yield yield = (Yield) ctx.yield_expr().accept(this);
			return new ast.statement.flow.Yield(this.getLocInfo(ctx), yield);
		}

		@Override
		public AstNode visitRaise_stmt(@NotNull PythonParser.Raise_stmtContext ctx) {
			//      RAISE ( test ( FROM test | ',' test (',' test)? )? )?
			Expr type = ctx.type == null ? null : (Expr) ctx.type.accept(this);
			if (ctx.source != null) {
				Expr source = (Expr) ctx.source.accept(this);
				return new RaiseFrom(this.getLocInfo(ctx), type, source);
			}

			Expr value = ctx.value == null ? null : (Expr) ctx.value.accept(this);
			Expr trace = ctx.trace == null ? null : (Expr) ctx.trace.accept(this);

			return new RaiseEx(this.getLocInfo(ctx), type, value, trace);
		}

		@Override
		public AstNode visitImport_stmt(@NotNull PythonParser.Import_stmtContext ctx) {
			//      import_name | import_from
			if (ctx.import_name() != null) {
				return ctx.import_name().accept(this);
			}
			if (ctx.import_from() != null) {
				return ctx.import_from().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitImport_name(@NotNull PythonParser.Import_nameContext ctx) {
			//      IMPORT dotted_as_names
			CollectionWrapper<Path> wrap = (CollectionWrapper<Path>) ctx.dotted_as_names().accept(this);
			return new ImportPaths(this.getLocInfo(ctx), wrap.getItems());
		}

		@Override
		public AstNode visitImport_from(@NotNull PythonParser.Import_fromContext ctx) {
			//		FROM ( ( '.' | '...' )* dotted_name
			//			 | ('.' | '...')+
			//		     )
			//		IMPORT ( '*'
			//			   | '(' import_as_names ')'
			//			   | import_as_names
			//		       )
			DottedPath module = ctx.dotted_name() == null ? new DottedPath(this.getLocInfo(ctx), new ArrayList<>()) : (DottedPath) ctx.dotted_name().accept(this);
			if (ctx.prefixes != null) {
				module.addPrefixes(ctx.prefixes);
			}

			List<Path> items = new ArrayList<>();

			if (ctx.star != null) {
				Path all = new SimplePath(this.getLocInfo(ctx), ctx.star.getText());
				items.add(all);
				return new ImportFrom(this.getLocInfo(ctx), items, module);
			}

			if (ctx.import_as_names() != null) {
				CollectionWrapper<Path> wrap = (CollectionWrapper<Path>) ctx.import_as_names().accept(this);
				items.addAll(wrap.getItems());
				return new ImportFrom(this.getLocInfo(ctx), items, module);
			}

			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitImport_as_name(@NotNull PythonParser.Import_as_nameContext ctx) {
			//      name ( AS name )?
			SimplePath simplePath = new SimplePath(this.getLocInfo(ctx), ctx.name().get(0).getText());
			if (ctx.name().size() == 2) {
				Identifier alias = (Identifier) ctx.name().get(1).accept(this);
				simplePath.setAlias(alias);
			}
			return simplePath;
		}

		@Override
		public AstNode visitDotted_as_name(@NotNull PythonParser.Dotted_as_nameContext ctx) {
			//      dotted_name ( AS name )?
			DottedPath path = (DottedPath) ctx.dotted_name().accept(this);
			if (ctx.name() != null) {
				Identifier id = (Identifier) ctx.name().accept(this);
				path.setAlias(id);
			}
			return path;
		}

		@Override
		public AstNode visitImport_as_names(@NotNull PythonParser.Import_as_namesContext ctx) {
			//      import_as_name ( ',' import_as_name )* ','?
			List<SimplePath> ids = ctx.import_as_name().stream()
					.map(e -> (SimplePath) e.accept(this))
					.collect(Collectors.toList());
			return new CollectionWrapper<>(this.getLocInfo(ctx), ids);
		}

		@Override
		public AstNode visitDotted_as_names(@NotNull PythonParser.Dotted_as_namesContext ctx) {
			//      dotted_as_name ( ',' dotted_as_name )*
			List<DottedPath> dottedPaths = ctx.dotted_as_name().stream()
					.map(e -> (DottedPath) e.accept(this))
					.collect(Collectors.toList());
			return new CollectionWrapper<>(this.getLocInfo(ctx), dottedPaths);
		}

		@Override
		public AstNode visitDotted_name(@NotNull PythonParser.Dotted_nameContext ctx) {
			//      NAME ( '.' NAME )*
			return new DottedPath(this.getLocInfo(ctx), ctx.names);
		}

		@Override
		public AstNode visitGlobal_stmt(@NotNull PythonParser.Global_stmtContext ctx) {
			//      GLOBAL NAME ( ',' NAME )*
			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			return new Global(this.getLocInfo(ctx), ids);
		}

		@Override
		public AstNode visitNonlocal_stmt(@NotNull PythonParser.Nonlocal_stmtContext ctx) {
			//      NONLOCAL NAME ( ',' NAME )*
			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			return new Nonlocal(this.getLocInfo(ctx), ids);
		}

		@Override
		public AstNode visitExec_stmt(PythonParser.Exec_stmtContext ctx) {
			//      EXEC expr (IN test (',' test)? )?
			Expr target = (Expr) ctx.expr().accept(this);
			Expr vars = ctx.vars == null ? null : (Expr) ctx.vars.accept(this);
			Expr localVars = ctx.localVars == null ? null : (Expr) ctx.localVars.accept(this);
			return new Exec(this.getLocInfo(ctx), target, vars, localVars);
		}

		@Override
		public AstNode visitAssert_stmt(@NotNull PythonParser.Assert_stmtContext ctx) {
			//      ASSERT test ( ',' test )?
			Expr assertion = (Expr) ctx.assertion.accept(this);
			if (ctx.assertionError != null) {
				Expr error = (Expr) ctx.assertionError.accept(this);
				return new Assert(this.getLocInfo(ctx), assertion, error);
			}
			return new Assert(this.getLocInfo(ctx), assertion);
		}

		@Override
		public AstNode visitCompound_stmt(@NotNull PythonParser.Compound_stmtContext ctx) {
			//      if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | funcdef | classdef | decorated | async_stmt
			if (ctx.if_stmt() != null) {
				return ctx.if_stmt().accept(this);
			}
			if (ctx.while_stmt() != null) {
				return ctx.while_stmt().accept(this);
			}
			if (ctx.for_stmt() != null) {
				return ctx.for_stmt().accept(this);
			}
			if (ctx.try_stmt() != null) {
				return ctx.try_stmt().accept(this);
			}
			if (ctx.with_stmt() != null) {
				return ctx.with_stmt().accept(this);
			}
			if (ctx.funcdef() != null) {
				return ctx.funcdef().accept(this);
			}
			if (ctx.classdef() != null) {
				return ctx.classdef().accept(this);
			}
			if (ctx.decorated() != null) {
				return ctx.decorated().accept(this);
			}
			if (ctx.async_stmt() != null) {
				return ctx.async_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitAsync_stmt(PythonParser.Async_stmtContext ctx) {
			//      ASYNC (funcdef | with_stmt | for_stmt)
			if (ctx.funcdef() != null) {
				Function function = (Function) ctx.funcdef().accept(this);
				function.markAsAsync();
				return function;
			}
			else if (ctx.with_stmt() != null) {
				With with = (With) ctx.with_stmt().accept(this);
				with.markAsAsync();
				return with;
			}
			else if (ctx.for_stmt() != null) {
				For forStmt = (For) ctx.for_stmt().accept(this);
				forStmt.markAsAsync();
				return forStmt;
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitIf_stmt(@NotNull PythonParser.If_stmtContext ctx) {
			//      IF test ':' suite ( ELIF test ':' suite )* ( ELSE ':' suite )?
			Expr ifCond = (Expr) ctx.ifTest.accept(this);
			Suite stmts = this.process(ctx.ifSuite);
			Suite elseStmts = this.processOptional(ctx.elseSuite);

			If ifStatement = new If(this.getLocInfo(ctx), ifCond, stmts, elseStmts);

			for (PythonParser.TestContext elifCondition : ctx.elifConditions) {
				PythonParser.SuiteContext elifVal = ctx.elifVals.get(elifCondition);
				ifStatement.addElseIf((Expr) elifCondition.accept(this), this.process(elifVal));
			}

			return ifStatement;
		}

		@Override
		public AstNode visitWhile_stmt(@NotNull PythonParser.While_stmtContext ctx) {
			//      WHILE test ':' suite ( ELSE ':' suite )?
			Expr condition = (Expr) ctx.test().accept(this);
			Suite body = this.process(ctx.body);
			Suite elseBody = this.processOptional(ctx.elseBody);

			return new While(this.getLocInfo(ctx), condition, body, elseBody);
		}

		@Override
		public AstNode visitFor_stmt(@NotNull PythonParser.For_stmtContext ctx) {
			//      FOR exprlist IN testlist ':' suite ( ELSE ':' suite )?
			ExprList iterator = (ExprList) ctx.exprlist().accept(this);
			ExprList source = (ExprList) ctx.testlist().accept(this);
			Suite body = this.process(ctx.body);
			Suite elseBody = this.processOptional(ctx.elseBody);

			return new For(this.getLocInfo(ctx), iterator, source, body, elseBody);
		}

		@Override
		public AstNode visitTry_stmt(@NotNull PythonParser.Try_stmtContext ctx) {
			//		TRY ':' suite ( ( except_clause ':' suite )+
			//		                ( ELSE ':' suite )?
			//		                ( FINALLY ':' suite )?
			//		              | FINALLY ':' suite
			//		              )
			Suite tryBlock = this.process(ctx.tryBlock);
			Suite elseBlock = this.processOptional(ctx.elseBlock);
			Suite finallyBlock = this.processOptional(ctx.finallyBlock);
			Map<Except, Suite> exceptBlocks = new HashMap<>();
			List<Except> exceptions = new ArrayList<>();

			for (PythonParser.Except_clauseContext c : ctx.exceptions) {
				Suite block = this.process(ctx.exceptBlocks.get(c));
				Except except = (Except) c.accept(this);
				exceptions.add(except);
				exceptBlocks.put(except, block);
			}

			return new Try(this.getLocInfo(ctx), tryBlock, exceptions, exceptBlocks, elseBlock, finallyBlock);
		}

		@Override
		public AstNode visitWith_stmt(@NotNull PythonParser.With_stmtContext ctx) {
			//      WITH with_item ( ',' with_item )* ':' suite
			List<WithItem> items = ctx.with_item().stream()
					.map(e -> (WithItem) e.accept(this))
					.collect(Collectors.toList());
			Suite stmts = this.process(ctx.suite());
			return new With(this.getLocInfo(ctx), items, stmts);
		}

		@Override
		public AstNode visitWith_item(@NotNull PythonParser.With_itemContext ctx) {
			//      test ( (AS | NAME) expr )?
			Expr item = (Expr) ctx.test().accept(this);
			Identifier name = ctx.name() == null ? null : (Identifier) ctx.name().accept(this);
			Expr alias = ctx.expr() == null ? null : (Expr) ctx.expr().accept(this);
			return new WithItem(this.getLocInfo(ctx), item, name, alias);
		}

		@Override
		public AstNode visitExcept_clause(@NotNull PythonParser.Except_clauseContext ctx) {
			//      EXCEPT ( test ( ( AS | ',' ) test )? )?
			Expr exception = ctx.type == null ? null : (Expr) ctx.type.accept(this);
			Expr alias = ctx.exName == null ? null : (Expr) ctx.exName.accept(this);
			return new Except(this.getLocInfo(ctx), exception, alias);
		}

		@Override
		public AstNode visitSuite(@NotNull PythonParser.SuiteContext ctx) {
			//      simple_stmt | NEWLINE INDENT stmt+ DEDENT
			if (ctx.simple_stmt() != null) {
				CollectionWrapper<ast.statement.Statement> wrap = (CollectionWrapper<ast.statement.Statement>) ctx.simple_stmt().accept(this);
				return new Suite(this.getLocInfo(ctx), wrap.getItems());
			}
			else if (ctx.stmt() != null) {
				List<CollectionWrapper<ast.statement.Statement>> collectionWrappers = ctx.stmt().stream()
						.map(c -> (CollectionWrapper<ast.statement.Statement>) c.accept(this))
						.collect(Collectors.toList());
				List<ast.statement.Statement> statements = new ArrayList<>();
				collectionWrappers.forEach(e -> statements.addAll(e.getItems()));
				return new Suite(this.getLocInfo(ctx), statements);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitTest(@NotNull PythonParser.TestContext ctx) {
			//      value=or_test ( IF condition=or_test ELSE test )? | lambdef
			if (ctx.lambdef() != null) {
				return ctx.lambdef().accept(this);
			}

			Expr value = (Expr) ctx.value.accept(this);
			if (ctx.condition == null) {
				return value;
			}

			Expr conditionFalseValue = (Expr) ctx.test().accept(this);
			Expr condition = (Expr) ctx.condition.accept(this);
			return new Conditional(this.getLocInfo(ctx), value, condition, conditionFalseValue);
		}

		@Override
		public AstNode visitTest_nocond(@NotNull PythonParser.Test_nocondContext ctx) {
			//      or_test | lambdef_nocond
			if (ctx.or_test() != null) {
				return ctx.or_test().accept(this);
			}
			if (ctx.lambdef_nocond() != null) {
				return ctx.lambdef_nocond().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitLambdef(@NotNull PythonParser.LambdefContext ctx) {
			//      LAMBDA varargslist? ':' test
			Params parameters = ctx.varargslist() == null ? null : (Params) ctx.varargslist().accept(this);
			Expr expr = (Expr) ctx.test().accept(this);
			return new Lambda(this.getLocInfo(ctx), expr, parameters);
		}

		@Override
		public AstNode visitLambdef_nocond(@NotNull PythonParser.Lambdef_nocondContext ctx) {
			//      LAMBDA varargslist? ':' test_nocond
			Params parameters = ctx.varargslist() == null ? null : (Params) ctx.varargslist().accept(this);
			NonConditional expr = (NonConditional) ctx.test_nocond().accept(this);
			return new LambdaNoCond(this.getLocInfo(ctx), expr, parameters);
		}

		@Override
		public AstNode visitOr_test(@NotNull PythonParser.Or_testContext ctx) {
			//      and_test ( OR and_test )*
			List<Expr> children = ctx.and_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());

			if (children.size() == 1) {
				return children.get(0);
			}
			return new Binary(this.getLocInfo(ctx), children, "or");
		}

		@Override
		public AstNode visitAnd_test(@NotNull PythonParser.And_testContext ctx) {
			//      not_test ( AND not_test )*
			List<Expr> children = ctx.not_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());

			if (children.size() == 1) {
				return children.get(0);
			}
			return new Binary(this.getLocInfo(ctx), children, "and");
		}

		@Override
		public AstNode visitNot_test(@NotNull PythonParser.Not_testContext ctx) {
			//      NOT not_test | comparison
			if (ctx.not_test() != null) {
				Expr expr = (Expr) ctx.not_test().accept(this);
				return new Not(this.getLocInfo(ctx), expr);
			}
			if (ctx.comparison() != null) {
				return ctx.comparison().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComparison(@NotNull PythonParser.ComparisonContext ctx) {
			//      star_expr ( comp_op star_expr )*
			List<Expr> operands = ctx.star_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());

			if (operands.size() == 1) {
				return operands.get(0);
			}

			if (ctx.comp_op() != null) {
				List<String> operators = ctx.comp_op().stream()
						.map(c -> ((Wrapper<String>) c.accept(this)).getItem())
						.collect(Collectors.toList());
				return new Comparison(this.getLocInfo(ctx), operands, operators);
			}

			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_op(@NotNull PythonParser.Comp_opContext ctx) {
			//      '<' | '>' | '==' | '>=' | '<=' | '<>' | '!=' | IN | NOT IN | IS | IS NOT
			return new Wrapper<>(this.getLocInfo(ctx), ctx.operator);
		}

		@Override
		public AstNode visitStar_expr(@NotNull PythonParser.Star_exprContext ctx) {
			//      '*'? expr
			return ctx.expr().accept(this);
		}

		@Override
		public AstNode visitExpr(@NotNull PythonParser.ExprContext ctx) {
			//      xor_expr ( '|' xor_expr )*
			List<Expr> operands = new ArrayList<>();
			ctx.xor_expr().forEach(c -> operands.add((Expr) c.accept(this)));
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new ast.expression.nocond.bitwise.Or(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitXor_expr(@NotNull PythonParser.Xor_exprContext ctx) {
			//      and_expr ( '^' and_expr )*
			List<Expr> operands = ctx.and_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new Xor(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitAnd_expr(@NotNull PythonParser.And_exprContext ctx) {
			//      shift_expr ( '&' shift_expr )*
			List<Expr> operands = ctx.shift_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new ast.expression.nocond.bitwise.And(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitShift_expr(@NotNull PythonParser.Shift_exprContext ctx) {
			//      arith_expr ( '<<' arith_expr | '>>' arith_expr )*
			List<Expr> children = ctx.arith_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			if (children.size() == 1) {
				return children.get(0);
			}
			return new Shift(this.getLocInfo(ctx), children, ctx.operators);
		}

		@Override
		public AstNode visitArith_expr(@NotNull PythonParser.Arith_exprContext ctx) {
			//      term ( '+' term | '-' term )*
			List<Expr> operands = ctx.term().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new Nnary(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public AstNode visitTerm(@NotNull PythonParser.TermContext ctx) {
			//      factor ( '*' factor | '/' factor | '%' factor | '//' factor | '@' factor )
			List<Expr> operands = ctx.factor().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());

			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new Nnary(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public AstNode visitFactor(@NotNull PythonParser.FactorContext ctx) {
			//      '+' factor | '-' factor | '~' factor | power
			if (ctx.factor() != null) {
				Expr value = (Expr) ctx.factor().accept(this);
				return new Unary(this.getLocInfo(ctx), value, ctx.op.getText());
			}
			if (ctx.power() != null) {
				return ctx.power().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitPower(@NotNull PythonParser.PowerContext ctx) {
			//      atom trailer* ( '**' factor )?
			Atom base = (Atom) ctx.atom().accept(this);
			List<Trailer> trailers = ctx.trailer() == null ? Collections.emptyList() :
					ctx.trailer().stream()
							.map(e -> (Trailer) e.accept(this))
							.collect(Collectors.toList());

			Atom transformedBase = TrailedAtomBuilder.attachTrailers(this.getLocInfo(ctx), base, trailers);

			if (ctx.factor() == null) {
				return transformedBase;
			}

			Expr exponent = (Expr) ctx.factor().accept(this);
			return new Power(this.getLocInfo(ctx), transformedBase, exponent);
		}

		@Override
		public AstNode visitAtom(@NotNull PythonParser.AtomContext ctx) {
			//		'(' ( yield_expr | testlist_comp )? ')'
			//		| '[' testlist_comp? ']'
			//		| '{' dictorsetmaker? '}'
			//      | '`' testlist '`'
			//		| name
			//      | number | string+ | '...' | NONE | TRUE | FALSE
			if (ctx.yield_expr() != null) {
				return ctx.yield_expr().accept(this);
			}
			if (ctx.testlist_comp() != null) {
				return ctx.testlist_comp().accept(this);
			}
			if (ctx.dictorsetmaker() != null) {
				return ctx.dictorsetmaker().accept(this);
			}
			if (ctx.testlist() != null) {
				ExprList exprList = (ExprList) ctx.testlist().accept(this);
				return new StrConversion(this.getLocInfo(ctx), exprList);
			}
			if (ctx.name() != null) {
				return ctx.name().accept(this);
			}
			if (ctx.number() != null) {
				return ctx.number().accept(this);
			}
			if (ctx.string() != null) {
				List<String> strings = ctx.string().stream()
						.map(e -> ((Str) e.accept(this)).getValue())
						.collect(Collectors.toList());
				return this.getStr(ctx.getStart().getLine(), StringHelper.implode(strings, ""));
			}
			if (ctx.ellipsis != null) {
				return new Ellipsis(this.getLocInfo(ctx));
			}
			if (ctx.NONE() != null) {
				return new None(this.getLocInfo(ctx));
			}
			if (ctx.TRUE() != null) {
				return new Bool(this.getLocInfo(ctx), true);
			}
			if (ctx.FALSE() != null) {
				return new Bool(this.getLocInfo(ctx), false);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		private AstNode getStr(Integer startLine, String str) {
			//this hacky method is here because end line of ctx is not correct so it's necessary to calculate it manually
			Integer endLine = startLine + StringHelper.explode(str, "\n").size() - 1;
			Integer locInfo = this.locCounter.count(startLine, endLine);
			return new Str(locInfo, str);
		}

		@Override
		public AstNode visitTestlist_comp(@NotNull PythonParser.Testlist_compContext ctx) {
			//      (test | star_expr) ( comp_for | ( ',' (test | star_expr) )* ','? )

			if (ctx.vals.size() > 0) {
				if (ctx.comp_for() != null) {
					Expr expression = (Expr) ctx.vals.get(0).accept(this);
					CompFor compFor = (CompFor) ctx.comp_for().accept(this);
					return new CondComprehension(this.getLocInfo(ctx), expression, compFor);
				}
				List<Expr> vals = ctx.vals.stream()
						.map(v -> (Expr) v.accept(this))
						.collect(Collectors.toList());
				return new EnumComprehension(this.getLocInfo(ctx), vals);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitTrailer(@NotNull PythonParser.TrailerContext ctx) {
			//      '(' arglist? ')' | '[' subscriptlist ']' | '.' (NAME | PRINT | EXEC)
			if (ctx.callBracket != null) { //Args
				if (ctx.arglist() != null) {
					return ctx.arglist().accept(this);
				}
				return new ArgList(this.getLocInfo(ctx), Collections.emptyList());
			}
			if (ctx.subscriptlist() != null) {
				return ctx.subscriptlist().accept(this); //SubscriptSliceList
			}
			if (ctx.name() != null) {
				return ctx.name().accept(this); //Identifier
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitSubscriptlist(@NotNull PythonParser.SubscriptlistContext ctx) {
			//      subscript ( ',' subscript )* ','?
			List<SubscriptSliceListElem> values = ctx.subscript().stream()
					.map(e -> (SubscriptSliceListElem) e.accept(this))
					.collect(Collectors.toList());
			return new SubscriptSliceList(this.getLocInfo(ctx), values);
		}

		@Override
		public AstNode visitSubscript(@NotNull PythonParser.SubscriptContext ctx) {
			//      index=test | lowerBound=test? ':' upperBound=test? stride=sliceop?
			if (ctx.index != null) {
				Expr index = (Expr) ctx.index.accept(this);
				return new SubscriptIndex(this.getLocInfo(ctx), index);
			}

			Expr lowerBound = ctx.lowerBound == null ? null : (Expr) ctx.lowerBound.accept(this);
			Expr upperBound = ctx.upperBound == null ? null : (Expr) ctx.upperBound.accept(this);
			Expr stride = ctx.stride == null ? null : (Expr) ctx.stride.accept(this);
			return new SliceBound(this.getLocInfo(ctx), lowerBound, upperBound, stride);
		}

		@Override
		public AstNode visitSliceop(@NotNull PythonParser.SliceopContext ctx) {
			//      ':' test?
			return ctx.test() == null ? null : (Expr) ctx.test().accept(this); //Expr
		}

		@Override
		public AstNode visitExprlist(@NotNull PythonParser.ExprlistContext ctx) {
			//      star_expr ( ',' star_expr )* ','?
			List<Expr> expressions = ctx.star_expr().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			return new ExprList(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitTestlist(@NotNull PythonParser.TestlistContext ctx) {
			//      test ( ',' test )* ','?
			List<Expr> expressions = ctx.test().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			return new ExprList(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitDictorsetmaker(@NotNull PythonParser.DictorsetmakerContext ctx) {
			//		dictVar=test ':' dictExpr=test ( comp_for | ( ',' dictKey=test ':' dictVal=test )* ','? )
			//		| setVar=test ( comp_for | ( ',' setVal=test )* ','? )
			if (ctx.dictVar != null) {
				if (ctx.comp_for() != null) {
					return new DictMaker(this.getLocInfo(ctx), (CompFor) ctx.comp_for().accept(this));
				}
				Map<Expr, Expr> values = ctx.dictValues.keySet().stream()
						.collect(Collectors.toMap(k -> (Expr) k.accept(this), v -> (Expr) v.accept(this)));
				return new DictMaker(this.getLocInfo(ctx), values);
			}

			if (ctx.setVar != null) {
				if (ctx.comp_for() != null) {
					return new SetMaker(this.getLocInfo(ctx), (CompFor) ctx.comp_for().accept(this));
				}
				List<Expr> values = ctx.setValues.stream()
						.map(e -> (Expr) e.accept(this))
						.collect(Collectors.toList());
				return new SetMaker(this.getLocInfo(ctx), values);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitClassdef(@NotNull PythonParser.ClassdefContext ctx) {
			//      CLASS NAME ( '(' arglist? ')' )? ':' suite
			Identifier id = (Identifier) ctx.name().accept(this);
			Suite suite = (Suite) ctx.suite().accept(this);
			if (ctx.arglist() != null) {
				ArgList argList = (ArgList) ctx.arglist().accept(this);
				return new ClassDef(this.getLocInfo(ctx), id, suite, argList.getArguments());
			}
			return new ClassDef(this.getLocInfo(ctx), id, suite, Collections.emptyList());
		}

		@Override
		public AstNode visitArglist(@NotNull PythonParser.ArglistContext ctx) {
			//      argument (',' argument)* ','?
			List<Argument> arguments = ctx.argument().stream()
					.map(e -> (Argument) e.accept(this))
					.collect(Collectors.toList());
			return new ArgList(this.getLocInfo(ctx), arguments);
		}

		@Override
		public AstNode visitArgument(@NotNull PythonParser.ArgumentContext ctx) {
			//      test comp_for? | test '=' test | '**' test | '*' test
			if (ctx.argName != null) {
				Expr value = (Expr) ctx.value.accept(this);
				Identifier id = (Identifier) ctx.argName.accept(this);
				return new DefValArgument(this.getLocInfo(ctx), value, id);
			}
			if (ctx.value != null) {
				Expr value = (Expr) ctx.value.accept(this);
				if (ctx.condition != null) {
					CompFor condition = (CompFor) ctx.condition.accept(this);
					return new CondArgument(this.getLocInfo(ctx), value, condition);
				}
				return new SimpleArgument(this.getLocInfo(ctx), value);
			}
			if (ctx.arg != null) {
				Expr value = (Expr) ctx.arg.accept(this);
				return new Arg(this.getLocInfo(ctx), value);
			}
			if (ctx.kwarg != null) {
				Expr value = (Expr) ctx.kwarg.accept(this);
				return new Kwarg(this.getLocInfo(ctx), value);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_iter(@NotNull PythonParser.Comp_iterContext ctx) {
			//      comp_for | comp_if
			if (ctx.comp_for() != null) {
				return ctx.comp_for().accept(this);
			}
			if (ctx.comp_if() != null) {
				return ctx.comp_if().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_for(@NotNull PythonParser.Comp_forContext ctx) {
			//      FOR exprlist IN or_test ((',' or_test)+ ','?)? comp_iter? |
			//      FOR exprlist IN test_nocond ((',' test_nocond)+ ','?)? comp_iter?

			ExprList targets = (ExprList) ctx.exprlist().accept(this);
			CompIter iter = ctx.comp_iter() == null ? null : (CompIter) ctx.comp_iter().accept(this);
			if (ctx.or_test() != null) {
				List<Expr> source = ctx.or_test().stream()
						.map(s -> (Expr) s.accept(this))
						.collect(Collectors.toList());
				return new CompFor(this.getLocInfo(ctx), iter, targets, source);
			}
			if (ctx.test_nocond() != null) {
				List<Expr> source = ctx.test_nocond().stream()
						.map(s -> (Expr) s.accept(this))
						.collect(Collectors.toList());
				return new CompFor(this.getLocInfo(ctx), iter, targets, source);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_if(@NotNull PythonParser.Comp_ifContext ctx) {
			//      IF test_nocond comp_iter? | IF test comp_iter?
			CompIter iter = ctx.comp_iter() == null ? null : (CompIter) ctx.comp_iter().accept(this);

			if (ctx.test_nocond() != null) {
				Expr expr = (Expr) ctx.test_nocond().accept(this);
				return new CompIf(this.getLocInfo(ctx), iter, expr);
			}

			if (ctx.test() != null) {
				Expr expr = (Expr) ctx.test().accept(this);
				return new CompIf(this.getLocInfo(ctx), iter, expr);
			}

			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitYield_expr(@NotNull PythonParser.Yield_exprContext ctx) {
			//      YIELD yield_arg?
			if (ctx.yield_arg() != null) {
				return ctx.yield_arg().accept(this);
			}
			return new YieldValues(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitYield_arg(@NotNull PythonParser.Yield_argContext ctx) {
			//      FROM test | testlist
			if (ctx.test() != null) {
				Expr from = (Expr) ctx.test().accept(this);
				return new YieldFrom(this.getLocInfo(ctx), from);
			}
			if (ctx.testlist() != null) {
				ExprList list = (ExprList) ctx.testlist().accept(this);
				return new YieldValues(this.getLocInfo(ctx), list);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitString(@NotNull PythonParser.StringContext ctx) {
			//      STRING_LITERAL | BYTES_LITERAL
			if (ctx.STRING_LITERAL() != null) {
				return this.getStr(ctx.getStart().getLine(), ctx.STRING_LITERAL().getText());
			}
			else if (ctx.BYTES_LITERAL() != null) {
				return this.getStr(ctx.getStart().getLine(), ctx.BYTES_LITERAL().getText());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitNumber(@NotNull PythonParser.NumberContext ctx) {
			//      integer | FLOAT_NUMBER | IMAG_NUMBER | LONG
			if (ctx.integer() != null) {
				return ctx.integer().accept(this);
			}
			if (ctx.FLOAT_NUMBER() != null) {
				Double nr = Double.parseDouble(ctx.FLOAT_NUMBER().getText());
				return new Float(this.getLocInfo(ctx), nr);
			}
			if (ctx.IMAG_NUMBER() != null) {
				String s = ctx.IMAG_NUMBER().getText();
				s = s.substring(0, s.length() - 1);
				return new Imaginary(this.getLocInfo(ctx), Double.parseDouble(s));
			}
			if (ctx.LONG() != null) {
				String value = ctx.LONG().getText();
				return new Long(this.getLocInfo(ctx), value);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitInteger(@NotNull PythonParser.IntegerContext ctx) {
			//      DECIMAL_INTEGER | OCT_INTEGER | HEX_INTEGER | BIN_INTEGER
			if (ctx.DECIMAL_INTEGER() != null) {
				BigInteger bi = new BigInteger(ctx.DECIMAL_INTEGER().getText());
				return new Int(this.getLocInfo(ctx), bi);
			}
			else if (ctx.OCT_INTEGER() != null) {
				//strip out the "0O" prefix
				String s = ctx.OCT_INTEGER().getText();
				s = s.substring(2);
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 8));
			}
			else if (ctx.HEX_INTEGER() != null) {
				//strip out the "0X" prefix
				String s = ctx.HEX_INTEGER().getText();
				s = s.substring(2);
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 16));
			}
			else if (ctx.BIN_INTEGER() != null) {
				//strip out the "0B" prefix
				String s = ctx.BIN_INTEGER().getText();
				s = s.substring(2);
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 2));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visit(@NotNull ParseTree parseTree) {
			return null;
		}

		@Override
		public AstNode visitChildren(@NotNull RuleNode ruleNode) {
			return null;
		}

		@Override
		public AstNode visitTerminal(@NotNull TerminalNode terminalNode) {
			return null;
		}

		@Override
		public AstNode visitErrorNode(@NotNull ErrorNode errorNode) {
			return null;
		}

		private Integer getLocInfo(TerminalNode node) {
			Integer lineNr = node.getSymbol().getLine();
			return this.locCounter.count(lineNr, lineNr);
		}

		private Integer getLocInfo(ParserRuleContext ctx) {
			Integer startLine = ctx.getStart().getLine();
			Integer stopLine = ctx.getStop() == null ? startLine : ctx.getStop().getLine();
			return this.locCounter.count(startLine, stopLine);
		}

		private Identifier getIdentifier(TerminalNode name) {
			return new Identifier(this.getLocInfo(name), name.getText());
		}

		private List<Param> getParameters(Map<ParserRuleContext, PythonParser.TestContext> params) {
			Map<Param, Expr> paramMap = this.extractParams(params);
			List<Param> result = new ArrayList<>();
			for (Param p : paramMap.keySet()) {
				this.setParamDefaultValue(p, paramMap.get(p));
				result.add(p);
			}
			return result;
		}

		private void setParamDefaultValue(Param simpleParam, Expr defaultVal) {
			if (defaultVal != null) {
				simpleParam.setDefaultVal(defaultVal);
			}
		}

		private Map<Param, Expr> extractParams(Map<ParserRuleContext, PythonParser.TestContext> params) {
			Map<Param, Expr> parameters = new HashMap<>();
			for (ParserRuleContext key : params.keySet()) {
				Param keyNode = (Param) key.accept(this);
				PythonParser.TestContext value = params.get(key);
				Expr valueNode = value == null ? null : (Expr) value.accept(this);
				parameters.put(keyNode, valueNode);
			}
			return parameters;
		}

		private Suite processOptional(PythonParser.SuiteContext node) {
			if (node == null) {
				return null;
			}
			return this.process(node);
		}

		private Suite process(PythonParser.SuiteContext node) {
			return (Suite) node.accept(this);
		}

		private class Wrapper<T> extends AstNode {
			private final T item;

			public Wrapper(Integer locInfo, T item) {
				super(locInfo);
				this.item = item;
			}

			public T getItem() {
				return this.item;
			}

			@Override
			public <T> T accept(Visitor<T> visitor) {
				throw new IllegalArgumentException("Cannot visit Wrapper");
			}
		}

		private class CollectionWrapper<T> extends AstNode {
			private final List<T> items;

			public CollectionWrapper(Integer locInfo, List<T> items) {
				super(locInfo);
				this.items = items;
			}

			public CollectionWrapper(Integer locInfo, T item) {
				super(locInfo);
				this.items = new ArrayList<>();
				this.items.add(item);
			}

			public List<T> getItems() {
				return this.items;
			}

			@Override
			public <T> T accept(Visitor<T> visitor) {
				throw new IllegalArgumentException("Cannot visit CollectionWrapper");
			}
		}
	}
}