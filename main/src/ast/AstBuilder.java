package ast;

import ast.arg.Arg;
import ast.arg.ArgCond;
import ast.arg.Kwarg;
import ast.arg.SimpleArg;
import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.bitwise.Xor;
import ast.expression.comprehension.*;
import ast.expression.logical.And;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.primary.atom.*;
import ast.expression.primary.atom.Float;
import ast.expression.primary.atom.trailed.TrailedAtomBuilder;
import ast.expression.primary.trailer.*;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.param.Params;
import ast.param.SimpleParam;
import ast.param.TypedParam;
import ast.path.DottedPath;
import ast.path.Path;
import ast.path.SimplePath;
import ast.statement.Statement;
import ast.statement.compound.*;
import ast.statement.flow.*;
import ast.statement.simple.*;
import gen.Python3Parser;
import gen.Python3Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import thesis.LocCounter;
import thesis.StringHelper;

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

	public Module build() {
		return (Module) this.context.accept(this.visitor);
	}

	private class AstBuilderVisitor implements Python3Visitor<AstNode> {

		private final String filePath;
		private final LocCounter locCounter;

		public AstBuilderVisitor(String filePath) {
			this.filePath = filePath;
			this.locCounter = new LocCounter(this.filePath);
		}

		private int indent = 0;

		@Override
		public AstNode visitSingle_input(@NotNull Python3Parser.Single_inputContext ctx) {
			this.indent++;
			this.print("visitSingle_input");
			this.indent--;
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
		public AstNode visitFile_input(@NotNull Python3Parser.File_inputContext ctx) {
			this.indent++;
			this.print("visitFile_input");
			//      ( NEWLINE | stmt )* EOF
			if (ctx.stmt() != null) {
				List<Statement> children = new ArrayList<>();
				ctx.stmt().forEach(e -> children.addAll(((CollectionWrapper<Statement>) e.accept(this)).items));
				this.indent--;
				return new Module(this.filePath, this.locCounter.count(), children);
			}
			return new Module(this.filePath, this.locCounter.count(), Collections.emptyList());
		}

		@Override
		public AstNode visitEval_input(@NotNull Python3Parser.Eval_inputContext ctx) {
			this.indent++;
			this.print("visitEval_input");
			//      testlist NEWLINE* EOF

			this.indent--;
			return ctx.testlist().accept(this);
		}

		@Override
		public AstNode visitDecorator(@NotNull Python3Parser.DecoratorContext ctx) {
			this.indent++;
			this.print("visitDecorator");
			//      '@' dotted_name ( '(' arglist? ')' )? NEWLINE

			DottedPath id = (DottedPath) ctx.dotted_name().accept(this);
			ArgList argList = ctx.arglist() == null ? null : (ArgList) ctx.arglist().accept(this);
			this.indent--;
			return new Decorator(this.getLocInfo(ctx), id, argList);
		}

		@Override
		public AstNode visitDecorators(@NotNull Python3Parser.DecoratorsContext ctx) {
			this.indent++;
			this.print("visitDecorators");
			//      decorator+

			if (ctx.decorator() != null) {
				List<Decorator> decorators = ctx.decorator()
						.stream().map(e -> (Decorator) e.accept(this))
						.collect(Collectors.toList());

				this.indent--;
				return new CollectionWrapper<>(this.getLocInfo(ctx), decorators);
			}
			this.indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), Collections.emptyList());
		}

		@Override
		public AstNode visitDecorated(@NotNull Python3Parser.DecoratedContext ctx) {
			this.indent++;
			this.print("visitDecorated");
			//      decorators ( classdef | funcdef )

			CollectionWrapper<Decorator> decorators = (CollectionWrapper<Decorator>) ctx.decorators().accept(this);
			if (ctx.classdef() != null) {
				ClassDef classDef = (ClassDef) ctx.classdef().accept(this);
				classDef.setDecorators(decorators.getItems());
				this.indent--;
				return classDef;
			}

			if (ctx.funcdef() != null) {
				Function function = (Function) ctx.funcdef().accept(this);
				function.setDecorators(decorators.getItems());
				this.indent--;
				return function;
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitFuncdef(@NotNull Python3Parser.FuncdefContext ctx) {
			this.indent++;
			this.print("visitFuncdef");
			//      DEF NAME parameters ( '->' test )? ':' suite

			Params params = (Params) ctx.parameters().accept(this);
			Expr returnType = ctx.test() == null ? null : (Expr) ctx.test().accept(this);
			Suite suite = this.process(ctx.suite());
			this.indent--;
			return new Function(this.getLocInfo(ctx), this.getIdentifier(ctx.NAME()), returnType, suite, params);
		}

		@Override
		public AstNode visitParameters(@NotNull Python3Parser.ParametersContext ctx) {
			this.indent++;
			this.print("visitParameters");
			//      '(' typedargslist? ')'

			if (ctx.typedargslist() != null) {
				this.indent--;
				return ctx.typedargslist().accept(this);
			}
			this.indent--;
			return new Params(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx) {
			this.indent++;
			this.print("visitTypedargslist");
			//		tfpdef ( '=' test )? ( ',' tfpdef ( '=' test )? )* ( ',' ( '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef
			//		)?
			//		)?
			//		| '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef

			List<SimpleParam> positional = this.getTfpParams(ctx.positional);
			List<SimpleParam> args = this.getTfpParams(ctx.args);
			List<SimpleParam> kwargs = this.getTfpParams(ctx.kwargs);
			this.indent--;
			return new Params(this.getLocInfo(ctx), positional, args, kwargs);
		}

		@Override
		public AstNode visitTfpdef(@NotNull Python3Parser.TfpdefContext ctx) {
			this.indent++;
			this.print("visitTfpdef");
			//      NAME ( ':' test )?

			Identifier id = this.getIdentifier(ctx.NAME());
			if (ctx.test() != null) {
				Expr returnType = (Expr) ctx.test().accept(this);
				this.indent--;
				return new TypedParam(this.getLocInfo(ctx), id, returnType);
			}
			this.indent--;
			return new SimpleParam(this.getLocInfo(ctx), id);
		}

		@Override
		public AstNode visitVarargslist(@NotNull Python3Parser.VarargslistContext ctx) {
			this.indent++;
			this.print("visitVarargslist");
			//		vfpdef ( '=' test )? ( ',' vfpdef ( '=' test )? )* ( ',' ( '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			//		)?
			//		)?
			//		| '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef

			List<SimpleParam> positional = this.getVfpParams(ctx.positional);
			List<SimpleParam> args = this.getVfpParams(ctx.args);
			List<SimpleParam> kwargs = this.getVfpParams(ctx.kwargs);
			this.indent--;
			return new Params(this.getLocInfo(ctx), positional, args, kwargs);
		}

		@Override
		public AstNode visitVfpdef(@NotNull Python3Parser.VfpdefContext ctx) {
			this.indent++;
			this.print("visitVfpdef");
			//      NAME

			Identifier id = this.getIdentifier(ctx.NAME());
			this.indent--;
			return new SimpleParam(this.getLocInfo(ctx), id);
		}

		@Override
		public AstNode visitStmt(@NotNull Python3Parser.StmtContext ctx) {
			this.indent++;
			this.print("visitStmt " + ctx.getText());
			//      simple_stmt | compound_stmt

			if (ctx.simple_stmt() != null) {
				this.indent--;
				return ctx.simple_stmt().accept(this); //returns a Wrapper
			}
			if (ctx.compound_stmt() != null) {
				//wrap this for consistency
				Statement s = (Statement) ctx.compound_stmt().accept(this);
				this.indent--;
				return new CollectionWrapper<>(this.getLocInfo(ctx), s);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx) {
			this.indent++;
			this.print("visitSimple_stmt");
			//      small_stmt ( ';' small_stmt )* ';'? NEWLINE

			List<Statement> statements = ctx.small_stmt().stream()
					.map(e -> (Statement) e.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), statements);
		}

		@Override
		public AstNode visitSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx) {
			this.indent++;
			this.print("visitSmall_stmt");
			this.indent--;
			//      expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | nonlocal_stmt | assert_stmt

			if (ctx.expr_stmt() != null) {
				return ctx.expr_stmt().accept(this);
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
			if (ctx.assert_stmt() != null) {
				return ctx.assert_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx) {
			this.indent++;
			this.print("visitExpr_stmt");
			//      testlist_star_expr ( augassign ( yield_expr | testlist) | ( '=' ( yield_expr | testlist_star_expr ) )* )

			ExprList targets = (ExprList) ctx.target.accept(this);

			String operator = "=";
			if (ctx.augassign() != null) {
				Wrapper<String> wrap = (Wrapper<String>) ctx.augassign().accept(this);
				operator = wrap.getItem();
			}

			if (ctx.assignYield != null) {
				Yield yield = (Yield) ctx.assignYield.accept(this);
				this.indent--;
				return new AssignYield(this.getLocInfo(ctx), operator, targets, yield);
			}
			if (ctx.assignTest != null) {
				ExprList source = (ExprList) ctx.assignTest.accept(this);
				this.indent--;
				return new AssignExpr(this.getLocInfo(ctx), operator, targets, source);
			}
			if (ctx.assignTestStarredList != null) {
				ExprList source = (ExprList) ctx.assignTestStarredList.accept(this);
				this.indent--;
				return new AssignExpr(this.getLocInfo(ctx), operator, targets, source);
			}

			this.indent--;
			return targets;
		}


		@Override
		public AstNode visitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx) {
			this.indent++;
			this.print("visitTestlist_star_expr");
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
			this.indent--;
			return new ExprList(this.getLocInfo(ctx), children);
		}

		@Override
		public AstNode visitAugassign(@NotNull Python3Parser.AugassignContext ctx) {
			this.indent++;
			this.print("visitAugassign");
			this.indent--;
			//      '+=' | '-=' | '*=' | '@=' // PEP 465 | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '**=' | '//='

			return new Wrapper<>(this.getLocInfo(ctx), ctx.op.getText());
		}

		@Override
		public AstNode visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx) {
			this.indent++;
			this.print("visitDel_stmt");
			//      DEL exprlist

			ExprList expressions = (ExprList) ctx.exprlist().accept(this);
			this.indent--;
			return new Delete(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx) {
			this.indent++;
			this.print("visitPass_stmt");
			this.indent--;
			//PASS

			return new Pass(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx) {
			this.indent++;
			this.print("visitFlow_stmt");
			//      break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt

			if (ctx.break_stmt() != null) {
				this.indent--;
				return ctx.break_stmt().accept(this);
			}
			if (ctx.continue_stmt() != null) {
				this.indent--;
				return ctx.continue_stmt().accept(this);
			}
			if (ctx.return_stmt() != null) {
				this.indent--;
				return ctx.return_stmt().accept(this);
			}
			if (ctx.raise_stmt() != null) {
				this.indent--;
				return ctx.raise_stmt().accept(this);
			}
			if (ctx.yield_stmt() != null) {
				this.indent--;
				return ctx.yield_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
			this.indent++;
			this.print("visitBreak_stmt");
			this.indent--;
			//      BREAK

			return new Break(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx) {
			this.indent++;
			this.print("visitContinue_stmt");
			this.indent--;
			//      CONTINUE

			return new Continue(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx) {
			this.indent++;
			this.print("visitReturn_stmt");
			//      RETURN testlist?

			if (ctx.testlist() != null) {
				ExprList expressions = (ExprList) ctx.testlist().accept(this);
				this.indent--;
				return new Return(this.getLocInfo(ctx), expressions);
			}
			this.indent--;
			return new Return(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx) {
			//      yield_expr

			return ctx.yield_expr().accept(this);
		}

		@Override
		public AstNode visitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx) {
			this.indent++;
			this.print("visitRaise_stmt");
			//      RAISE ( test ( FROM test )? )?

			Expr type = ctx.except == null ? null : (Expr) ctx.except.accept(this);
			Expr source = ctx.source == null ? null : (Expr) ctx.source.accept(this);
			this.indent--;
			return new Raise(this.getLocInfo(ctx), type, source);
		}

		@Override
		public AstNode visitImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx) {
			this.indent++;
			this.print("visitImport_stmt");
			//      import_name | import_from

			if (ctx.import_name() != null) {
				this.indent--;
				return ctx.import_name().accept(this);
			}
			if (ctx.import_from() != null) {
				this.indent--;
				return ctx.import_from().accept(this);
			}
			this.indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitImport_name(@NotNull Python3Parser.Import_nameContext ctx) {
			this.indent++;
			this.print("visitImport_name");
			//      IMPORT dotted_as_names

			CollectionWrapper<Path> wrap = (CollectionWrapper<Path>) ctx.dotted_as_names().accept(this);
			this.indent--;
			return new SimpleImport(this.getLocInfo(ctx), wrap.getItems());
		}

		@Override
		public AstNode visitImport_from(@NotNull Python3Parser.Import_fromContext ctx) {
			this.indent++;
			this.print("visitImport_from");
			//		FROM ( ( '.' | '...' )* dotted_name
			//						| ('.' | '...')+
			//		)
			//		IMPORT ( '*'
			//				| '(' import_as_names ')'
			//				| import_as_names
			//		)
			DottedPath module = ctx.dotted_name() == null ? new DottedPath(this.getLocInfo(ctx), new ArrayList<>()) : (DottedPath) ctx.dotted_name().accept(this);
			if (ctx.prefixes != null) {
				module.addPrefixes(ctx.prefixes);
			}

			List<Path> items = new ArrayList<>();
			if (ctx.import_as_names() != null) {
				CollectionWrapper<Path> wrap = (CollectionWrapper<Path>) ctx.import_as_names().accept(this);
				items.addAll(wrap.getItems());
			}

			this.indent--;
			return new ImportFrom(this.getLocInfo(ctx), items, module);
		}

		@Override
		public AstNode visitImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx) {
			this.indent++;
			this.print("visitImport_as_name");
			//      NAME ( AS NAME )?

			SimplePath simplePath = new SimplePath(this.getLocInfo(ctx), ctx.NAME().get(0).getText());
			if (ctx.NAME().size() == 2) {
				simplePath.setAlias(this.getIdentifier(ctx.NAME().get(1)));
			}
			this.indent--;
			return simplePath;
		}

		@Override
		public AstNode visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx) {
			this.indent++;
			this.print("visitDotted_as_name");
			//      dotted_name ( AS NAME )?

			DottedPath path = (DottedPath) ctx.dotted_name().accept(this);
			if (ctx.NAME() != null) {
				Identifier id = this.getIdentifier(ctx.NAME());
				path.setAlias(id);
			}
			this.indent--;
			return path;
		}

		@Override
		public AstNode visitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx) {
			this.indent++;
			this.print("visitImport_as_names");
			//      import_as_name ( ',' import_as_name )* ','?

			List<SimplePath> ids = ctx.import_as_name().stream()
					.map(e -> (SimplePath) e.accept(this))
					.collect(Collectors.toList());
			CollectionWrapper<SimplePath> wrap = new CollectionWrapper<>(this.getLocInfo(ctx), ids);
			this.indent--;
			return wrap;
		}

		@Override
		public AstNode visitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx) {
			this.indent++;
			this.print("visitDotted_as_names");
			//      dotted_as_name ( ',' dotted_as_name )*

			List<DottedPath> dottedPaths = ctx.dotted_as_name().stream()
					.map(e -> (DottedPath) e.accept(this))
					.collect(Collectors.toList());
			CollectionWrapper<DottedPath> wrap = new CollectionWrapper<>(this.getLocInfo(ctx), dottedPaths);
			this.indent--;
			return wrap;
		}

		@Override
		public AstNode visitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx) {
			this.indent++;
			this.print("visitDotted_name");
			this.indent--;
			//      NAME ( '.' NAME )*

			return new DottedPath(this.getLocInfo(ctx), ctx.names);
		}

		@Override
		public AstNode visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
			this.indent++;
			this.print("visitGlobal_stmt");
			//      GLOBAL NAME ( ',' NAME )*

			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			this.indent--;
			return new Global(this.getLocInfo(ctx), ids);
		}

		@Override
		public AstNode visitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx) {
			this.indent++;
			this.print("visitNonlocal_stmt");
			//      NONLOCAL NAME ( ',' otherName = NAME )*

			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			this.indent--;
			return new Nonlocal(this.getLocInfo(ctx), ids);
		}

		@Override
		public AstNode visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx) {
			this.indent++;
			this.print("visitAssert_stmt");
			//      ASSERT test ( ',' test )?

			Expr assertion = (Expr) ctx.assertion.accept(this);
			if (ctx.assertionError != null) {
				Expr error = (Expr) ctx.assertionError.accept(this);
				this.indent--;
				return new Assert(this.getLocInfo(ctx), assertion, error);
			}
			this.indent--;
			return new Assert(this.getLocInfo(ctx), assertion);
		}

		@Override
		public AstNode visitCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx) {
			this.indent++;
			this.print("visitCompound_stmt " + ctx.getText());
			//      if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | funcdef | classdef | decorated

			if (ctx.if_stmt() != null) {
				this.indent--;
				return ctx.if_stmt().accept(this);
			}
			if (ctx.while_stmt() != null) {
				this.indent--;
				return ctx.while_stmt().accept(this);
			}
			if (ctx.for_stmt() != null) {
				this.indent--;
				return ctx.for_stmt().accept(this);
			}
			if (ctx.try_stmt() != null) {
				this.indent--;
				return ctx.try_stmt().accept(this);
			}
			if (ctx.with_stmt() != null) {
				this.indent--;
				return ctx.with_stmt().accept(this);
			}
			if (ctx.funcdef() != null) {
				this.indent--;
				return ctx.funcdef().accept(this);
			}
			if (ctx.classdef() != null) {
				this.indent--;
				return ctx.classdef().accept(this);
			}
			if (ctx.decorated() != null) {
				this.indent--;
				return ctx.decorated().accept(this);
			}
			this.indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx) {
			this.indent++;
			this.print("visitIf_stmt");
			//      IF test ':' suite ( ELIF test ':' suite )* ( ELSE ':' suite )?

			Expr ifCond = (Expr) ctx.ifTest.accept(this);
			Suite stmts = this.process(ctx.ifSuite);
			Suite elseStmts = this.processOptional(ctx.elseSuite);

			If ifStatement = new If(this.getLocInfo(ctx), ifCond, stmts, elseStmts);

			for (Python3Parser.TestContext elifCondition : ctx.elifConditions) {
				Python3Parser.SuiteContext elifVal = ctx.elifVals.get(elifCondition);
				ifStatement.addElseIf((Expr) elifCondition.accept(this), this.process(elifVal));
			}

			this.indent--;
			return ifStatement;
		}

		@Override
		public AstNode visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx) {
			this.indent++;
			this.print("visitWhile_stmt");
			//      WHILE test ':' suite ( ELSE ':' suite )?

			Expr condition = (Expr) ctx.test().accept(this);
			Suite body = this.process(ctx.body);
			Suite elseBody = this.processOptional(ctx.elseBody);

			this.indent--;
			return new While(this.getLocInfo(ctx), condition, body, elseBody);
		}

		@Override
		public AstNode visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx) {
			this.indent++;
			this.print("visitFor_stmt");
			//      FOR exprlist IN testlist ':' suite ( ELSE ':' suite )?

			ExprList iterator = (ExprList) ctx.exprlist().accept(this);
			ExprList source = (ExprList) ctx.testlist().accept(this);
			Suite body = this.process(ctx.body);
			Suite elseBody = this.processOptional(ctx.elseBody);

			this.indent--;
			return new For(this.getLocInfo(ctx), iterator, source, body, elseBody);
		}

		@Override
		public AstNode visitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx) {
			this.indent++;
			this.print("visitTry_stmt");
			//		TRY ':' suite ( ( except_clause ':' suite )+
			//		( ELSE ':' suite )?
			//		( FINALLY ':' suite )?
			//		| FINALLY ':' suite
			//		)

			Suite tryBlock = this.process(ctx.tryBlock);
			Suite elseBlock = this.processOptional(ctx.elseBlock);
			Suite finallyBlock = this.processOptional(ctx.finallyBlock);
			Map<Except, Suite> exceptBlocks = new HashMap<>();
			List<Except> exceptions = new ArrayList<>();

			for (Python3Parser.Except_clauseContext c : ctx.exceptions) {
				Suite block = this.process(ctx.exceptBlocks.get(c));
				Except except = (Except) c.accept(this);
				exceptions.add(except);
				exceptBlocks.put(except, block);
			}

			this.indent--;
			return new Try(this.getLocInfo(ctx), tryBlock, exceptions, exceptBlocks, elseBlock, finallyBlock);
		}

		@Override
		public AstNode visitWith_stmt(@NotNull Python3Parser.With_stmtContext ctx) {
			this.indent++;
			this.print("visitWith_stmt");
			//      WITH with_item ( ',' with_item )* ':' suite

			List<WithItem> items = ctx.with_item().stream()
					.map(e -> (WithItem) e.accept(this))
					.collect(Collectors.toList());
			Suite stmts = this.process(ctx.suite());
			this.indent--;
			return new With(this.getLocInfo(ctx), items, stmts);
		}

		@Override
		public AstNode visitWith_item(@NotNull Python3Parser.With_itemContext ctx) {
			this.indent++;
			this.print("visitWith_item");
			//      test ( AS expr )?

			Expr item = (Expr) ctx.test().accept(this);
			Expr alias = ctx.expr() == null ? null : (Expr) ctx.expr().accept(this);
			this.indent--;
			return new WithItem(this.getLocInfo(ctx), item, alias);
		}

		@Override
		public AstNode visitExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx) {
			this.indent++;
			this.print("visitExcept_clause");
			//      EXCEPT ( test ( AS NAME )? )?

			Expr exception = ctx.test() == null ? null : (Expr) ctx.test().accept(this);
			String alias = ctx.NAME() == null ? null : ctx.NAME().getText();
			this.indent--;
			return new Except(this.getLocInfo(ctx), exception, alias);
		}

		@Override
		public AstNode visitSuite(@NotNull Python3Parser.SuiteContext ctx) {
			this.indent++;
			this.print("visitSuite");
			//      simple_stmt | NEWLINE INDENT stmt+ DEDENT

			if (ctx.simple_stmt() != null) {
				CollectionWrapper<Statement> wrap = (CollectionWrapper<Statement>) ctx.simple_stmt().accept(this);
				return new Suite(this.getLocInfo(ctx), wrap.getItems());
			}
			else if (ctx.stmt() != null) {
				List<CollectionWrapper<Statement>> collectionWrappers = ctx.stmt().stream()
						.map(c -> (CollectionWrapper<Statement>) c.accept(this))
						.collect(Collectors.toList());
				List<Statement> statements = new ArrayList<>();
				collectionWrappers.forEach(e -> statements.addAll(e.getItems()));
				this.indent--;
				return new Suite(this.getLocInfo(ctx), statements);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitTest(@NotNull Python3Parser.TestContext ctx) {
			this.indent++;
			this.print("visitTest");
			//      value=or_test ( IF condition=or_test ELSE test )? | lambdef

			if (ctx.lambdef() != null) {
				this.indent--;
				return ctx.lambdef().accept(this);
			}

			if (ctx.value == null) {
				System.out.println("");
			}
			Expr value = (Expr) ctx.value.accept(this);
			if (ctx.condition == null) {
				this.indent--;
				return value;
			}

			Expr conditionFalseValue = (Expr) ctx.test().accept(this);
			Expr condition = (Expr) ctx.condition.accept(this);
			this.indent--;
			return new Conditional(this.getLocInfo(ctx), value, condition, conditionFalseValue);
		}

		@Override
		public AstNode visitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx) {
			this.indent++;
			this.print("visitTest_nocond");
			//      or_test | lambdef_nocond

			this.indent--;
			if (ctx.or_test() != null) {
				//TODO: This needs to return something of type ExprNoCond! Figure out what it should be!
				return ctx.or_test().accept(this);
			}
			if (ctx.lambdef_nocond() != null) {
				return ctx.lambdef_nocond().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
			this.indent++;
			this.print("visitLambdef");
			//      LAMBDA varargslist? ':' test

			Params parameters = ctx.varargslist() == null ? null : (Params) ctx.varargslist().accept(this);
			Expr expr = (Expr) ctx.test().accept(this);
			this.indent--;
			return new Lambda(this.getLocInfo(ctx), expr, parameters);
		}

		@Override
		public AstNode visitLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx) {
			this.indent++;
			this.print("visitLambdef_nocond");
			//      LAMBDA varargslist? ':' test_nocond

			Params parameters = ctx.varargslist() == null ? null : (Params) ctx.varargslist().accept(this);
			ExprNoCond expr = (ExprNoCond) ctx.test_nocond().accept(this);
			this.indent--;
			return new LambdaNoCond(this.getLocInfo(ctx), expr, parameters);
		}

		@Override
		public AstNode visitOr_test(@NotNull Python3Parser.Or_testContext ctx) {
			this.indent++;
			this.print("visitOr_test");
			//      and_test ( OR and_test )*

			List<Expr> children = ctx.and_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			this.indent--;

			if (children.size() == 1) {
				return children.get(0);
			}
			return new Or(this.getLocInfo(ctx), children);
		}

		@Override
		public AstNode visitAnd_test(@NotNull Python3Parser.And_testContext ctx) {
			this.indent++;
			this.print("visitAnd_test");
			//      not_test ( AND not_test )*


			List<Expr> children = ctx.not_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			this.indent--;

			if (children.size() == 1) {
				return children.get(0);
			}
			return new And(this.getLocInfo(ctx), children);
		}

		@Override
		public AstNode visitNot_test(@NotNull Python3Parser.Not_testContext ctx) {
			this.indent++;
			this.print("visitNot_test");
			//      NOT not_test | comparison

			if (ctx.not_test() != null) {
				Expr expr = (Expr) ctx.not_test().accept(this);
				this.indent--;
				return new Not(this.getLocInfo(ctx), expr);
			}
			if (ctx.comparison() != null) {
				this.indent--;
				return ctx.comparison().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComparison(@NotNull Python3Parser.ComparisonContext ctx) {
			this.indent++;
			this.print("visitComparison");
			//      star_expr ( comp_op star_expr )*

			List<Expr> operands = ctx.star_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());

			if (operands.size() == 1) {
				this.indent--;
				return operands.get(0);
			}

			if (ctx.comp_op() != null) {
				List<String> operators = ctx.comp_op().stream()
						.map(c -> ((Wrapper<String>) c.accept(this)).getItem())
						.collect(Collectors.toList());
				this.indent--;
				return new Comparison(this.getLocInfo(ctx), operands, operators);
			}

			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_op(@NotNull Python3Parser.Comp_opContext ctx) {
			this.indent++;
			this.print("visitComp_op " + ctx.getText());
			this.indent--;
			//      '<' | '>' | '==' | '>=' | '<=' | '<>' | '!=' | IN | NOT IN | IS | IS NOT

			return new Wrapper<>(this.getLocInfo(ctx), ctx.operator);
		}

		@Override
		public AstNode visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx) {
			this.indent++;
			this.print("visitStar_expr " + ctx.getText());
			this.indent--;
			//      '*'? expr

			return ctx.expr().accept(this);
		}

		@Override
		public AstNode visitExpr(@NotNull Python3Parser.ExprContext ctx) {
			this.indent++;
			this.print("visitExpr");
			//      xor_expr ( '|' xor_expr )*

			List<Expr> operands = new ArrayList<>();
			ctx.xor_expr().forEach(c -> operands.add((Expr) c.accept(this)));
			this.indent--;
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new ast.expression.bitwise.Or(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx) {
			this.indent++;
			this.print("visitXor_expr");
			//      and_expr ( '^' and_expr )*

			List<Expr> operands = ctx.and_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new Xor(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx) {
			this.indent++;
			this.print("visitAnd_expr");
			//      shift_expr ( '&' shift_expr )*

			List<Expr> operands = ctx.shift_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			if (operands.size() == 1) {
				return operands.get(0);
			}
			return new ast.expression.bitwise.And(this.getLocInfo(ctx), operands);
		}

		@Override
		public AstNode visitShift_expr(@NotNull Python3Parser.Shift_exprContext ctx) {
			this.indent++;
			this.print("visitShift_expr");
			//      arith_expr ( '<<' arith_expr | '>>' arith_expr )*

			List<Expr> children = ctx.arith_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return new Shift(this.getLocInfo(ctx), children, ctx.operators);
		}

		@Override
		public AstNode visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx) {
			this.indent++;
			this.print("visitArith_expr");
			//      term ( '+' term | '-' term )*

			List<Expr> operands = ctx.term().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			if (operands.size() == 1) {
				this.indent--;
				return operands.get(0);
			}
			this.indent--;
			return new Arithmetic(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public AstNode visitTerm(@NotNull Python3Parser.TermContext ctx) {
			this.indent++;
			this.print("visitTerm");
			//      factor ( '*' factor | '/' factor | '%' factor | '//' factor | '@' factor // PEP 465 )

			List<Expr> operands = ctx.factor().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());

			if (operands.size() == 1) {
				this.indent--;
				return operands.get(0);
			}
			this.indent--;
			return new Arithmetic(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public AstNode visitFactor(@NotNull Python3Parser.FactorContext ctx) {
			this.indent++;
			this.print("visitFactor");
			//      '+' factor | '-' factor | '~' factor | power

			if (ctx.factor() != null) {
				if (ctx.op.getText().equals("+")) {
					Expr value = (Expr) ctx.factor().accept(this);
					this.indent--;
					return new Plus(this.getLocInfo(ctx), value);
				}
				if (ctx.op.getText().equals("-")) {
					Expr value = (Expr) ctx.factor().accept(this);
					this.indent--;
					return new Minus(this.getLocInfo(ctx), value);
				}
				if (ctx.op.getText().equals("~")) {
					Expr value = (Expr) ctx.factor().accept(this);
					this.indent--;
					return new Invert(this.getLocInfo(ctx), value);
				}
				throw new IllegalArgumentException("Unknown context");
			}
			if (ctx.power() != null) {
				this.indent--;
				return ctx.power().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitPower(@NotNull Python3Parser.PowerContext ctx) {
			this.indent++;
			this.print("visitPower");
			//      atom trailer* ( '**' factor )?

			Atom base = (Atom) ctx.atom().accept(this);
			List<Trailer> trailers = ctx.trailer() == null ? Collections.emptyList() :
					ctx.trailer().stream()
							.map(e -> (Trailer) e.accept(this))
							.collect(Collectors.toList());

			Atom transformedBase = TrailedAtomBuilder.attachTrailers(this.getLocInfo(ctx), base, trailers);

			if (ctx.factor() == null) {
				this.indent--;
				return transformedBase;
			}

			Expr exponent = (Expr) ctx.factor().accept(this);
			this.indent--;
			return new Power(this.getLocInfo(ctx), transformedBase, exponent);
		}

		@Override
		public AstNode visitAtom(@NotNull Python3Parser.AtomContext ctx) {
			this.indent++;
			this.print("visitAtom");
			//		'(' ( yield_expr | testlist_comp )? ')'
			//		| '[' testlist_comp? ']'
			//		| '{' dictorsetmaker? '}'
			//		| NAME | number | string+ | '...' | NONE | TRUE | FALSE

			this.indent--;
			if (ctx.yield_expr() != null) {
				return ctx.yield_expr().accept(this);
			}
			if (ctx.testlist_comp() != null) {
				//TODO: make a distinction between a dictionary and a tuple?
				return ctx.testlist_comp().accept(this);
			}
			if (ctx.dictorsetmaker() != null) {
				return ctx.dictorsetmaker().accept(this);
			}
			if (ctx.NAME() != null) {
				return this.getIdentifier(ctx.NAME());
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

		private AstNode getStr(java.lang.Integer startLine, String str) {
			//this hacky method is here because end line of ctx is not correct so it's necessary to calculate it manually
			java.lang.Integer endLine = startLine + StringHelper.explode(str, "\n").size() - 1;
			Integer locInfo = this.locCounter.count(startLine, endLine);
			return new Str(locInfo, str);
		}

		@Override
		public AstNode visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx) {
			this.indent++;
			this.print("visitTestlist_comp");
			//      test ( comp_for | ( ',' test )* ','? )

			if (ctx.comp_for() != null) {
				Expr expression = (Expr) ctx.initial.accept(this);
				CompFor compFor = (CompFor) ctx.comp_for().accept(this);
				this.indent--;
				return new CondComprehension(this.getLocInfo(ctx), expression, compFor);
			}
			if (ctx.values.size() > 0) {
				List<Expr> values = ctx.values.stream().map(e -> (Expr) e.accept(this)).collect(Collectors.toList());
				this.indent--;
				return new EnumComprehension(this.getLocInfo(ctx), values);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitTrailer(@NotNull Python3Parser.TrailerContext ctx) {
			this.indent++;
			this.print("visitTrailer");
			//      '(' arglist? ')' | '[' subscriptlist ']' | '.' NAME

			if (ctx.callBracket != null) { //Args
				if (ctx.arglist() != null) {
					return ctx.arglist().accept(this);
				}
				return new ArgList(this.getLocInfo(ctx));
			}
			if (ctx.subscriptlist() != null) {
				return ctx.subscriptlist().accept(this); //SubscriptSliceList
			}
			if (ctx.NAME() != null) {
				return this.getIdentifier(ctx.NAME()); //Identifier
			}
			this.indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx) {
			this.indent++;
			this.print("visitSubscriptlist");
			//      subscript ( ',' subscript )* ','?

			List<SubscriptSliceListElem> values = ctx.subscript().stream()
					.map(e -> (SubscriptSliceListElem) e.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			return new SubscriptSliceList(this.getLocInfo(ctx), values);
		}

		@Override
		public AstNode visitSubscript(@NotNull Python3Parser.SubscriptContext ctx) {
			this.indent++;
			this.print("visitSubscript");
			//      index=test | lowerBound=test? ':' upperBound=test? stride=sliceop?

			if (ctx.index != null) {
				Expr index = (Expr) ctx.index.accept(this);
				this.indent--;
				return new SubscriptIndex(this.getLocInfo(ctx), index);
			}

			Expr lowerBound = ctx.lowerBound == null ? null : (Expr) ctx.lowerBound.accept(this);
			Expr upperBound = ctx.upperBound == null ? null : (Expr) ctx.upperBound.accept(this);
			Expr stride = ctx.stride == null ? null : (Expr) ctx.stride.accept(this);
			this.indent--;
			return new SliceBound(this.getLocInfo(ctx), lowerBound, upperBound, stride);
		}

		@Override
		public AstNode visitSliceop(@NotNull Python3Parser.SliceopContext ctx) {
			this.indent++;
			this.print("visitSliceop");
			//      ':' test?
			Expr expression = ctx.test() == null ? null : (Expr) ctx.test().accept(this);
			this.indent--;
			return expression; //Expr
		}

		@Override
		public AstNode visitExprlist(@NotNull Python3Parser.ExprlistContext ctx) {
			this.indent++;
			this.print("visitExprlist");
			//      star_expr ( ',' star_expr )* ','?
			List<Expr> expressions = ctx.star_expr().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			return new ExprList(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitTestlist(@NotNull Python3Parser.TestlistContext ctx) {
			this.indent++;
			this.print("visitTestlist");
			//      test ( ',' test )* ','?

			List<Expr> expressions = ctx.test().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			this.indent--;
			return new ExprList(this.getLocInfo(ctx), expressions);
		}

		@Override
		public AstNode visitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx) {
			this.indent++;
			this.print("visitDictorsetmaker");
			this.indent--;
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
		public AstNode visitClassdef(@NotNull Python3Parser.ClassdefContext ctx) {
			this.indent++;
			this.print("visitClassdef");
			//      CLASS NAME ( '(' arglist? ')' )? ':' suite

			Identifier id = this.getIdentifier(ctx.NAME());
			Suite suite = (Suite) ctx.suite().accept(this);
			if (ctx.arglist() != null) {
				ArgList argList = (ArgList) ctx.arglist().accept(this);
				List<SimpleArg> args = argList.getPositional().stream()
						.map(a -> (SimpleArg) a)
						.collect(Collectors.toList());
				this.indent--;
				return new ClassDef(this.getLocInfo(ctx), id, suite, args);
			}
			this.indent--;
			return new ClassDef(this.getLocInfo(ctx), id, suite, Collections.emptyList());
		}

		@Override
		public AstNode visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
			this.indent++;
			this.print("visitArglist");
			//      ( argument ',' )* ( argument ','? | '*' test ( ',' argument )* ( ',' '**' test )? | '**' test )

			List<Arg> positional = ctx.positionalArgs.stream()
					.map(e -> (Arg) e.accept(this))
					.collect(Collectors.toList());
			Expr args = ctx.args == null ? null : (Expr) ctx.args.accept(this);
			Expr kwargs = ctx.kwargs == null ? null : (Expr) ctx.kwargs.accept(this);
			this.indent--;
			return new ArgList(this.getLocInfo(ctx), positional, args, kwargs);
		}

		@Override
		public AstNode visitArgument(@NotNull Python3Parser.ArgumentContext ctx) {
			this.indent++;
			this.print("visitArgument");
			//      test comp_for? | test '=' test

			if (ctx.name != null) {
				Expr value = (Expr) ctx.value.accept(this);
				//TODO: either translate this to Identifier somehow or change the param for Kwarg to Expr
				//Identifier id = (Identifier) ctx.name.accept(this);
				this.indent--;
				return new Kwarg(this.getLocInfo(ctx), value, null);
			}
			if (ctx.value != null) {
				Expr value = (Expr) ctx.value.accept(this);
				if (ctx.condition != null) {
					CompFor condition = (CompFor) ctx.condition.accept(this);
					this.indent--;
					return new ArgCond(this.getLocInfo(ctx), value, condition);
				}
				this.indent--;
				return new SimpleArg(this.getLocInfo(ctx), value);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_iter(@NotNull Python3Parser.Comp_iterContext ctx) {
			this.indent++;
			this.print("visitComp_iter");
			//      comp_for | comp_if

			if (ctx.comp_for() != null) {
				this.indent--;
				return ctx.comp_for().accept(this);
			}
			if (ctx.comp_if() != null) {
				this.indent--;
				return ctx.comp_if().accept(this);
			}
			this.indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitComp_for(@NotNull Python3Parser.Comp_forContext ctx) {
			this.indent++;
			this.print("visitComp_for");
			//      FOR exprlist IN or_test comp_iter?

			ExprList targets = (ExprList) ctx.exprlist().accept(this);
			Expr source = (Expr) ctx.or_test().accept(this);
			CompIter iter = ctx.comp_iter() == null ? null : (CompIter) ctx.comp_iter().accept(this);
			this.indent--;
			return new CompFor(this.getLocInfo(ctx), iter, targets, source);
		}

		@Override
		public AstNode visitComp_if(@NotNull Python3Parser.Comp_ifContext ctx) {
			this.indent++;
			this.print("visitComp_if");
			//      IF test_nocond comp_iter?

			ExprNoCond expr = (ExprNoCond) ctx.test_nocond().accept(this);
			CompIter iter = ctx.comp_iter() == null ? null : (CompIter) ctx.comp_iter().accept(this);
			this.indent--;
			return new CompIf(this.getLocInfo(ctx), iter, expr);
		}

		@Override
		public AstNode visitYield_expr(@NotNull Python3Parser.Yield_exprContext ctx) {
			this.indent++;
			this.print("visitYield_expr");
			//      YIELD yield_arg?

			if (ctx.yield_arg() != null) {
				this.indent--;
				return ctx.yield_arg().accept(this);
			}
			this.indent--;
			return new YieldValues(this.getLocInfo(ctx));
		}

		@Override
		public AstNode visitYield_arg(@NotNull Python3Parser.Yield_argContext ctx) {
			this.indent++;
			this.print("visitYield_arg");
			//      FROM test | testlist

			if (ctx.test() != null) {
				Expr from = (Expr) ctx.test().accept(this);
				this.indent--;
				return new YieldFrom(this.getLocInfo(ctx), from);
			}
			if (ctx.testlist() != null) {
				ExprList list = (ExprList) ctx.testlist().accept(this);
				this.indent--;
				return new YieldValues(this.getLocInfo(ctx), list);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitString(@NotNull Python3Parser.StringContext ctx) {
			this.indent++;
			this.print("visitString");
			this.indent--;
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
		public AstNode visitNumber(@NotNull Python3Parser.NumberContext ctx) {
			this.indent++;
			this.print("visitNumber");
			//      integer | FLOAT_NUMBER | IMAG_NUMBER

			if (ctx.integer() != null) {
				this.indent--;
				return ctx.integer().accept(this);
			}
			else if (ctx.FLOAT_NUMBER() != null) {
				Double nr = Double.parseDouble(ctx.FLOAT_NUMBER().getText());
				this.indent--;
				return new Float(this.getLocInfo(ctx), nr);
			}
			else if (ctx.IMAG_NUMBER() != null) {
				//strip out the "j" at the end
				String s = ctx.IMAG_NUMBER().getText();
				s = s.substring(0, s.length() - 1);
				this.indent--;
				return new Imaginary(this.getLocInfo(ctx), Double.parseDouble(s));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visitInteger(@NotNull Python3Parser.IntegerContext ctx) {
			this.indent++;
			this.print("visitInteger");
			//      DECIMAL_INTEGER | OCT_INTEGER | HEX_INTEGER | BIN_INTEGER

			if (ctx.DECIMAL_INTEGER() != null) {
				BigInteger bi = new BigInteger(ctx.DECIMAL_INTEGER().getText());
				this.indent--;
				return new Int(this.getLocInfo(ctx), bi);
			}
			else if (ctx.OCT_INTEGER() != null) {
				//strip out the "0O" prefix
				String s = ctx.OCT_INTEGER().getText();
				s = s.substring(2);
				this.indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 8));
			}
			else if (ctx.HEX_INTEGER() != null) {
				//strip out the "0X" prefix
				String s = ctx.HEX_INTEGER().getText();
				s = s.substring(2);
				this.indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 16));
			}
			else if (ctx.BIN_INTEGER() != null) {
				//strip out the "0B" prefix
				String s = ctx.HEX_INTEGER().getText();
				s = s.substring(2);
				this.indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 2));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public AstNode visit(@NotNull ParseTree parseTree) {
			this.indent++;
			this.print("visit");
			this.indent--;
			return null;
		}

		@Override
		public AstNode visitChildren(@NotNull RuleNode ruleNode) {
			this.indent++;
			this.print("visitChildren");
			this.indent--;
			return null;
		}

		@Override
		public AstNode visitTerminal(@NotNull TerminalNode terminalNode) {
			this.indent++;
			this.print("visitTerminal");
			this.indent--;
			return null;
		}

		@Override
		public AstNode visitErrorNode(@NotNull ErrorNode errorNode) {
			this.indent++;
			this.print("visitErrorNode");
			this.indent--;
			return null;
		}

		private java.lang.Integer getLocInfo(TerminalNode node) {
			java.lang.Integer lineNr = node.getSymbol().getLine();
			return this.locCounter.count(lineNr, lineNr);
		}

		private java.lang.Integer getLocInfo(ParserRuleContext ctx) {
			java.lang.Integer startLine = ctx.getStart().getLine();
			if (ctx.getStop() == null) {
				return this.locCounter.count(startLine, startLine);
			}
			return this.locCounter.count(startLine, ctx.getStop().getLine());
		}

		private Identifier getIdentifier(TerminalNode name) {
			return new Identifier(this.getLocInfo(name), name.getText());
		}

		private List<SimpleParam> getVfpParams(Map<Python3Parser.VfpdefContext, Python3Parser.TestContext> params) {
			Map<ParserRuleContext, Python3Parser.TestContext> upcasted = new HashMap<>();
			upcasted.putAll(params);
			return this.getParameters(upcasted);
		}

		private List<SimpleParam> getTfpParams(Map<Python3Parser.TfpdefContext, Python3Parser.TestContext> params) {
			Map<ParserRuleContext, Python3Parser.TestContext> upcasted = new HashMap<>();
			upcasted.putAll(params);
			return this.getParameters(upcasted);
		}

		private List<SimpleParam> getParameters(Map<ParserRuleContext, Python3Parser.TestContext> params) {
			Map<SimpleParam, Expr> paramMap = this.extractParams(params);
			List<SimpleParam> result = new ArrayList<>();
			for (SimpleParam p : paramMap.keySet()) {
				this.setParamDefaultValue(p, paramMap.get(p));
				result.add(p);
			}
			return result;
		}

		private void setParamDefaultValue(SimpleParam simpleParam, Expr defaultVal) {
			if (defaultVal != null) {
				simpleParam.setDefaultVal(defaultVal);
			}
		}

		private Map<SimpleParam, Expr> extractParams(Map<ParserRuleContext, Python3Parser.TestContext> params) {
			Map<SimpleParam, Expr> parameters = new HashMap<>();
			for (ParserRuleContext key : params.keySet()) {
				SimpleParam keyNode = (SimpleParam) key.accept(this);
				Python3Parser.TestContext value = params.get(key);
				Expr valueNode = value == null ? null : (Expr) value.accept(this);
				parameters.put(keyNode, valueNode);
			}
			return parameters;
		}

		private void print(String s) {
//			for (int i = 0; i < this.indent; i++) {
//				System.out.print("\t");
//			}
//			System.out.println(s);
		}

		private Suite processOptional(Python3Parser.SuiteContext node) {
			if (node == null) {
				return null;//new Suite(this.getLocInfo(node), Collections.emptyList());
			}
			return this.process(node);
		}

		private Suite process(Python3Parser.SuiteContext node) {
			return (Suite) node.accept(this);
		}

		private CollectionWrapper<Statement> getResultWrapper(Python3Parser.SuiteContext node) {
			return (CollectionWrapper<Statement>) node.accept(this);
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