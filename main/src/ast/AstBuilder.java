package ast;

import ast.expression.*;
import ast.expression.arithmetic.Arithmetic;
import ast.expression.atom.*;
import ast.expression.bitwise.Xor;
import ast.expression.logical.And;
import ast.expression.logical.Logical;
import ast.expression.logical.Not;
import ast.expression.logical.Or;
import ast.expression.maker.DictMaker;
import ast.expression.maker.SetMaker;
import ast.expression.primary.Subscript;
import ast.expression.primary.SubscriptIndex;
import ast.expression.primary.SubscriptSlice;
import ast.expression.unary.Invert;
import ast.expression.unary.Minus;
import ast.expression.unary.Plus;
import ast.statement.*;
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
import thesis.Py3TreeVisitor;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 19-05-2015
 */
public class AstBuilder {

	private final AstBuilderVisitor visitor;
	private final ParserRuleContext context;

	public AstBuilder(ParserRuleContext context) {
		this.visitor = new AstBuilderVisitor();
		this.context = context;
	}

	public Py3Node build() {
		return this.context.accept(this.visitor);
	}

	private class AstBuilderVisitor implements Python3Visitor<Py3Node> {

		public AstBuilderVisitor() {

		}

		private int indent = 0;

		@Override
		public Py3Node visitSingle_input(@NotNull Python3Parser.Single_inputContext ctx) {
			indent++;
			print("visitSingle_input");
			indent--;
			//NEWLINE | simple_stmt | compound_stmt NEWLINE
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
		public Py3Node visitFile_input(@NotNull Python3Parser.File_inputContext ctx) {
			indent++;
			print("visitFile_input");
			//( NEWLINE | stmt )* EOF
			if (ctx.stmt() != null) {
				List<Py3Node> children = new ArrayList<>();
				ctx.stmt().forEach(e -> children.addAll(((CollectionWrapper<Statement>) e.accept(this)).items));
				indent--;
				return new Module(this.getLocInfo(ctx), children);
			}
			if (ctx.NEWLINE() != null) {
				indent--;
				return new Module(this.getLocInfo(ctx), Collections.emptyList());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitEval_input(@NotNull Python3Parser.Eval_inputContext ctx) {
			indent++;
			print("visitEval_input");
			//testlist NEWLINE* EOF
			indent--;
			return ctx.testlist().accept(this);
		}

		@Override
		public Py3Node visitDecorator(@NotNull Python3Parser.DecoratorContext ctx) {
			//TODO
			indent++;
			print("visitDecorator");
			//'@' dotted_name ( '(' arglist? ')' )? NEWLINE
			ctx.dotted_name().accept(this);
			if (ctx.arglist() != null) {
				ctx.arglist().accept(this);
			}
			indent--;
			return new Decorator(this.getLocInfo(ctx), ctx.dotted_name().getText());
		}

		@Override
		public Py3Node visitDecorators(@NotNull Python3Parser.DecoratorsContext ctx) {
			indent++;
			print("visitDecorators");
			//decorator+
			if (ctx.decorator() != null) {
				List<Decorator> decorators = ctx.decorator()
						.stream().map(e -> (Decorator) e.accept(this))
						.collect(Collectors.toList());

				indent--;
				return new CollectionWrapper<>(this.getLocInfo(ctx), decorators);
			}
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), Collections.emptyList());
		}

		@Override
		public Py3Node visitDecorated(@NotNull Python3Parser.DecoratedContext ctx) {
			indent++;
			print("visitDecorated");
			//decorators ( classdef | funcdef )

			CollectionWrapper<Decorator> decorators = (CollectionWrapper<Decorator>)ctx.decorators().accept(this);

			if (ctx.classdef() != null) {
				ClassDef cd = (ClassDef) ctx.classdef().accept(this);
				indent--;
				return new DecoratedClass(this.getLocInfo(ctx), decorators.getItems(), cd.getName(), cd.getBody());
			}

			if (ctx.funcdef() != null) {
				Function f = (Function) ctx.funcdef().accept(this);
				indent--;
				return new DecoratedFunction(this.getLocInfo(ctx), decorators.getItems(), f.getName(), f.getBody());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitFuncdef(@NotNull Python3Parser.FuncdefContext ctx) {
			indent++;
			print("visitFuncdef");
			//DEF NAME parameters ( '->' test )? ':' suite
			//TODO: params
			ctx.parameters().accept(this);
			Expr returnType = ctx.test() == null ? null : (Expr) ctx.test().accept(this);
			CollectionWrapper<Statement> wrap = this.getResultWrapper(ctx.suite());
			indent--;
			return new Function(this.getLocInfo(ctx), new Identifier(this.getLocInfo(ctx.NAME()), ctx.NAME().getText()), wrap.getItems());
		}

		@Override
		public Py3Node visitParameters(@NotNull Python3Parser.ParametersContext ctx) {
			indent++;
			print("visitParameters");
			//'(' typedargslist? ')'
			if (ctx.typedargslist() != null) {
				indent--;
				return ctx.typedargslist().accept(this);
			}
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), Collections.emptyList());
		}

		@Override
		public Py3Node visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx) {
			//TODO
			indent++;
			print("visitTypedargslist");
			//		tfpdef ( '=' test )? ( ',' tfpdef ( '=' test )? )* ( ',' ( '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef
			//		)?
			//		)?
			//		| '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef

			Map<Py3Node, Py3Node> arguments = new HashMap<>();
			for (Python3Parser.TfpdefContext key : ctx.args.keySet()) {
				Py3Node keyNode = key.accept(this);
				Python3Parser.TestContext value = ctx.args.get(key);
				arguments.put(keyNode, value == null ? null : value.accept(this));
			}

			indent--;
			return null;
		}

		@Override
		public Py3Node visitTfpdef(@NotNull Python3Parser.TfpdefContext ctx) {
			//TODO
			indent++;
			print("visitTfpdef");
			//NAME ( ':' test )?
			if (ctx.test() != null) {
				indent--;
				return ctx.test().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitVarargslist(@NotNull Python3Parser.VarargslistContext ctx) {
			//TODO
			indent++;
			print("visitVarargslist");
			//		vfpdef ( '=' test )? ( ',' vfpdef ( '=' test )? )* ( ',' ( '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			//		)?
			//		)?
			//		| '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			Map<Py3Node, Py3Node> arguments = new HashMap<>();
			for (Python3Parser.VfpdefContext key : ctx.args.keySet()) {
				Py3Node keyNode = key.accept(this);
				Python3Parser.TestContext value = ctx.args.get(key);
				arguments.put(keyNode, value == null ? null : value.accept(this));
			}

			indent--;
			return null;
		}

		@Override
		public Py3Node visitVfpdef(@NotNull Python3Parser.VfpdefContext ctx) {
			indent++;
			print("visitVfpdef");
			//NAME
			indent--;
			return new Identifier(this.getLocInfo(ctx), ctx.NAME().getText());
		}

		@Override
		public Py3Node visitStmt(@NotNull Python3Parser.StmtContext ctx) {
			indent++;
			print("visitStmt " + ctx.getText());
			indent--;
			//simple_stmt | compound_stmt
			if (ctx.simple_stmt() != null) {
				return ctx.simple_stmt().accept(this); //returns a Wrapper
			}
			if (ctx.compound_stmt() != null) {
				//wrap this for consistency
				Statement s = (Statement) ctx.compound_stmt().accept(this);
				return new CollectionWrapper<>(this.getLocInfo(ctx), s);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx) {
			indent++;
			print("visitSimple_stmt");
			indent--;
			//small_stmt ( ';' small_stmt )* ';'? NEWLINE
			return new CollectionWrapper<>(this.getLocInfo(ctx),
					ctx.small_stmt().stream()
							.map(e -> (Statement) e.accept(this))
							.collect(Collectors.toList()));
		}

		@Override
		public Py3Node visitSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx) {
			indent++;
			print("visitSmall_stmt");
			// expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | nonlocal_stmt | assert_stmt
			if (ctx.expr_stmt() != null) {
				indent--;
				return ctx.expr_stmt().accept(this);
			}
			if (ctx.del_stmt() != null) {
				indent--;
				return ctx.del_stmt().accept(this);
			}
			if (ctx.pass_stmt() != null) {
				indent--;
				return ctx.pass_stmt().accept(this);
			}
			if (ctx.flow_stmt() != null) {
				indent--;
				return ctx.flow_stmt().accept(this);
			}
			if (ctx.import_stmt() != null) {
				indent--;
				return ctx.import_stmt().accept(this);
			}
			if (ctx.global_stmt() != null) {
				indent--;
				return ctx.global_stmt().accept(this);
			}
			if (ctx.nonlocal_stmt() != null) {
				indent--;
				return ctx.nonlocal_stmt().accept(this);
			}
			if (ctx.assert_stmt() != null) {
				indent--;
				return ctx.assert_stmt().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx) {
			//TODO
			indent++;
			print("visitExpr_stmt");
			//testlist_star_expr ( augassign ( yield_expr | testlist) | ( '=' ( yield_expr | testlist_star_expr ) )* )
			if (ctx.testlist_star_expr() != null) {
				for (Python3Parser.Testlist_star_exprContext e : ctx.testlist_star_expr()) {
					CollectionWrapper<Expr> wrap = (CollectionWrapper<Expr>) e.accept(this);
				}
			}
			if (ctx.augassign() != null) {
				Wrapper<String> operatorWrapper = (Wrapper<String>) ctx.augassign().accept(this);
				String operator = operatorWrapper.getItem();
			}
			if (ctx.yield_expr() != null) {
				for (Python3Parser.Yield_exprContext e : ctx.yield_expr()) {
					e.accept(this);
				}
			}
			if (ctx.testlist() != null) {
				ctx.testlist().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx) {
			indent++;
			print("visitTestlist_star_expr");
			//( test | star_expr ) ( ',' ( test |  star_expr ) )* ','?
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
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitAugassign(@NotNull Python3Parser.AugassignContext ctx) {
			indent++;
			print("visitAugassign");
			indent--;
			//'+=' | '-=' | '*=' | '@=' // PEP 465 | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '**=' | '//='
			//TODO: strip quotes etc.
			return new Wrapper<>(this.getLocInfo(ctx), ctx.op.getText());
		}

		@Override
		public Py3Node visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx) {
			indent++;
			print("visitDel_stmt");
			//DEL exprlist
			CollectionWrapper<Expr> expressions = (CollectionWrapper<Expr>)ctx.exprlist().accept(this);
			indent--;
			return new Delete(this.getLocInfo(ctx), expressions.getItems());
		}

		@Override
		public Py3Node visitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx) {
			indent++;
			print("visitPass_stmt");
			indent--;
			//PASS
			return new Pass(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx) {
			indent++;
			print("visitFlow_stmt");
			//break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt
			if (ctx.break_stmt() != null) {
				indent--;
				return ctx.break_stmt().accept(this);
			}
			if (ctx.continue_stmt() != null) {
				indent--;
				return ctx.continue_stmt().accept(this);
			}
			if (ctx.return_stmt() != null) {
				indent--;
				return ctx.return_stmt().accept(this);
			}
			if (ctx.raise_stmt() != null) {
				indent--;
				return ctx.raise_stmt().accept(this);
			}
			if (ctx.yield_stmt() != null) {
				indent--;
				return ctx.yield_stmt().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
			indent++;
			print("visitBreak_stmt");
			indent--;
			//BREAK
			return new Break(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx) {
			indent++;
			print("visitContinue_stmt");
			indent--;
			//CONTINUE
			return new Continue(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx) {
			indent++;
			print("visitReturn_stmt");
			//RETURN testlist?
			if (ctx.testlist() != null) {
				CollectionWrapper<Expr> expressions = (CollectionWrapper<Expr>) ctx.testlist().accept(this);
				indent--;
				return new Return(this.getLocInfo(ctx), expressions.getItems());
			}
			indent--;
			return new Return(this.getLocInfo(ctx), Collections.emptyList());
		}

		@Override
		public Py3Node visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx) {
			//yield_expr
			return ctx.yield_expr().accept(this);
		}

		@Override
		public Py3Node visitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx) {
			indent++;
			print("visitRaise_stmt");
			//RAISE ( test ( FROM test )? )?
			Expr type = (Expr) ctx.exception.accept(this);
			Expr source = (Expr) ctx.source.accept(this);
			indent--;
			return new Raise(this.getLocInfo(ctx), type, source);
		}

		@Override
		public Py3Node visitImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx) {
			indent++;
			print("visitImport_stmt");
			//import_name | import_from
			if (ctx.import_name() != null) {
				indent--;
				return ctx.import_name().accept(this);
			}
			if (ctx.import_from() != null) {
				indent--;
				return ctx.import_from().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitImport_name(@NotNull Python3Parser.Import_nameContext ctx) {
			//TODO
			indent++;
			print("visitImport_name");
			//IMPORT dotted_as_names
//			return ctx.dotted_as_names().accept(this);
			indent--;
			return new Import(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitImport_from(@NotNull Python3Parser.Import_fromContext ctx) {
			//TODO
			indent++;
			print("visitImport_from");
			//		FROM ( ( '.' | '...' )* dotted_name
			//						| ('.' | '...')+
			//		)
			//		IMPORT ( '*'
			//				| '(' import_as_names ')'
			//				| import_as_names
			//		)
			if (ctx.dotted_name() != null) {
				indent--;
				return ctx.dotted_name().accept(this);
			}
			if (ctx.import_as_names() != null) {
				indent--;
				return ctx.import_as_names().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx) {
			//TODO
			indent++;
			print("visitImport_as_name");
			//NAME ( AS NAME )?
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx) {
			//TODO
			indent++;
			print("visitDotted_as_name");
			//dotted_name ( AS NAME )?
			indent--;
			return ctx.dotted_name().accept(this);
		}

		@Override
		public Py3Node visitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx) {
			//TODO
			indent++;
			print("visitImport_as_names");
			//import_as_name ( ',' import_as_name )* ','?
			for (Python3Parser.Import_as_nameContext c : ctx.import_as_name()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx) {
			//TODO
			indent++;
			print("visitDotted_as_names");
			//dotted_as_name ( ',' dotted_as_name )*
			for (Python3Parser.Dotted_as_nameContext c : ctx.dotted_as_name()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx) {
			//TODO
			indent++;
			print("visitDotted_name");
			//NAME ( '.' NAME )*
			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			indent--;
			return null;
		}

		@Override
		public Py3Node visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
			indent++;
			print("visitGlobal_stmt");
			//GLOBAL NAME ( ',' NAME )*
			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			indent--;
			return new Global(this.getLocInfo(ctx), ids);
		}

		@Override
		public Py3Node visitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx) {
			indent++;
			print("visitNonlocal_stmt");
			//: NONLOCAL NAME ( ',' otherName = NAME )*
			List<Identifier> ids = ctx.names.stream()
					.map(e -> new Identifier(this.getLocInfo(ctx), e))
					.collect(Collectors.toList());
			indent--;
			return new Nonlocal(this.getLocInfo(ctx), ids);
		}

		@Override
		public Py3Node visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx) {
			indent++;
			print("visitAssert_stmt");
			//ASSERT test ( ',' test )?
			Expr assertion = (Expr) ctx.assertion.accept(this);
			if (ctx.assertionError != null) {
				Expr error = (Expr) ctx.assertionError.accept(this);
				indent--;
				return new Assert(this.getLocInfo(ctx), assertion, error);
			}
			indent--;
			return new Assert(this.getLocInfo(ctx), assertion);
		}

		@Override
		public Py3Node visitCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx) {
			indent++;
			print("visitCompound_stmt " + ctx.getText());
			//if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | funcdef | classdef | decorated
			if (ctx.if_stmt() != null) {
				indent--;
				return ctx.if_stmt().accept(this);
			}
			if (ctx.while_stmt() != null) {
				indent--;
				return ctx.while_stmt().accept(this);
			}
			if (ctx.for_stmt() != null) {
				indent--;
				return ctx.for_stmt().accept(this);
			}
			if (ctx.try_stmt() != null) {
				indent--;
				return ctx.try_stmt().accept(this);
			}
			if (ctx.with_stmt() != null) {
				indent--;
				return ctx.with_stmt().accept(this);
			}
			if (ctx.funcdef() != null) {
				indent--;
				return ctx.funcdef().accept(this);
			}
			if (ctx.classdef() != null) {
				indent--;
				return ctx.classdef().accept(this);
			}
			if (ctx.decorated() != null) {
				indent--;
				return ctx.decorated().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx) {
			indent++;
			print("visitIf_stmt");
			//IF test ':' suite ( ELIF test ':' suite )* ( ELSE ':' suite )?

			Expr ifCond = (Expr) ctx.ifTest.accept(this);
			List<Statement> stmts = this.process(ctx.ifSuite);
			List<Statement> elseStmts = this.processOptional(ctx.elseSuite);

			If ifStatement = new If(this.getLocInfo(ctx), ifCond, stmts, elseStmts);

			for (Python3Parser.TestContext elifCondition : ctx.elifConditions) {
				Python3Parser.SuiteContext elifVal = ctx.elifVals.get(elifCondition);
				ifStatement.addElseIf((Expr) elifCondition.accept(this), this.process(elifVal));
			}

			indent--;
			return ifStatement;
		}

		@Override
		public Py3Node visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx) {
			indent++;
			print("visitWhile_stmt");
			//WHILE test ':' suite ( ELSE ':' suite )?

			Expr condition = (Expr) ctx.test().accept(this);
			List<Statement> body = this.process(ctx.body);
			List<Statement> elseBody = this.processOptional(ctx.elseBody);

			indent--;
			return new While(this.getLocInfo(ctx), condition, body, elseBody);
		}

		@Override
		public Py3Node visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx) {
			indent++;
			print("visitFor_stmt");
			//FOR exprlist IN testlist ':' suite ( ELSE ':' suite )?

			//TODO: add exprlist and testlist
			ctx.exprlist().accept(this);
			ctx.testlist().accept(this);

			indent--;
			return new For(this.getLocInfo(ctx), this.process(ctx.body), this.processOptional(ctx.elseBody));
		}

		@Override
		public Py3Node visitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx) {
			//TODO
			indent++;
			print("visitTry_stmt");
			//		TRY ':' suite ( ( except_clause ':' suite )+
			//		( ELSE ':' suite )?
			//		( FINALLY ':' suite )?
			//		| FINALLY ':' suite
			//		)
			for (Python3Parser.SuiteContext c : ctx.suite()) {
				c.accept(this);
			}
			if (ctx.except_clause() != null) {
				for (Python3Parser.Except_clauseContext c : ctx.except_clause()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitWith_stmt(@NotNull Python3Parser.With_stmtContext ctx) {
			//TODO
			indent++;
			print("visitWith_stmt");
			//WITH with_item ( ',' with_item )* ':' suite
			for (Python3Parser.With_itemContext c : ctx.with_item()) {
				c.accept(this);
			}
			List<Statement> stmts = this.process(ctx.suite());
			indent--;
			return null;
		}

		@Override
		public Py3Node visitWith_item(@NotNull Python3Parser.With_itemContext ctx) {
			//TODO
			indent++;
			print("visitWith_item");
			//test ( AS expr )?
			ctx.test().accept(this);
			if (ctx.expr() != null) {
				ctx.expr().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx) {
			//TODO
			indent++;
			print("visitExcept_clause");
			//EXCEPT ( test ( AS NAME )? )?
			if (ctx.test() != null) {
				indent--;
				return ctx.test().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitSuite(@NotNull Python3Parser.SuiteContext ctx) {
			indent++;
			print("visitSuite");
			//simple_stmt | NEWLINE INDENT stmt+ DEDENT
			if (ctx.simple_stmt() != null) {
				return ctx.simple_stmt().accept(this); //returns a Wrapper
			}
			else if (ctx.stmt() != null) {
				List<CollectionWrapper<Statement>> collectionWrappers = ctx.stmt().stream()
						.map(c -> (CollectionWrapper<Statement>) c.accept(this))
						.collect(Collectors.toList());
				List<Statement> statements = new ArrayList<>();
				collectionWrappers.forEach(e -> statements.addAll(e.getItems()));
				indent--;
				return new CollectionWrapper<>(this.getLocInfo(ctx), statements);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitTest(@NotNull Python3Parser.TestContext ctx) {
			indent++;
			print("visitTest");
//			value=or_test ( IF condition=or_test ELSE test )? | lambdef
			indent--;
			if (ctx.lambdef() != null) {
				return ctx.lambdef().accept(this);
			}

			Logical value = (Logical) ctx.value.accept(this);
			if (ctx.condition == null) {
				return new Conditional(this.getLocInfo(ctx), value);
			}

			Expr conditionFalseValue = (Expr) ctx.test().accept(this);
			Logical condition = (Logical) ctx.condition.accept(this);
			return new Conditional(this.getLocInfo(ctx), value, condition, conditionFalseValue);
		}

		@Override
		public Py3Node visitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx) {
			indent++;
			print("visitTest_nocond");
			//or_test | lambdef_nocond
			indent--;
			if (ctx.or_test() != null) {
				return ctx.or_test().accept(this);
			}
			if (ctx.lambdef_nocond() != null) {
				return ctx.lambdef_nocond().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
			//TODO
			indent++;
			print("visitLambdef");
			//LAMBDA varargslist? ':' test
			if (ctx.varargslist() != null) {
				ctx.varargslist().accept(this);
			}
			ctx.test().accept(this);
			indent--;
			return null;
		}

		@Override
		public Py3Node visitLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx) {
			//TODO
			indent++;
			print("visitLambdef_nocond");
			//LAMBDA varargslist? ':' test_nocond
			if (ctx.varargslist() != null) {
				ctx.varargslist().accept(this);
			}
			ctx.test_nocond().accept(this);
			indent--;
			return null;
		}

		@Override
		public Py3Node visitOr_test(@NotNull Python3Parser.Or_testContext ctx) {
			indent++;
			print("visitOr_test");
			//and_test ( OR and_test )*
			List<Expr> children = ctx.and_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;

			if (children.size() == 1) {
				return children.get(0);
			}
			return new Or(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitAnd_test(@NotNull Python3Parser.And_testContext ctx) {
			indent++;
			print("visitAnd_test");
			//not_test ( AND not_test )*

			List<Expr> children = ctx.not_test().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;

			if (children.size() == 1) {
				return children.get(0);
			}
			return new And(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitNot_test(@NotNull Python3Parser.Not_testContext ctx) {
			indent++;
			print("visitNot_test");
			indent--;
			// NOT not_test | comparison
			if (ctx.not_test() != null) {
				return new Not(this.getLocInfo(ctx), (Expr) ctx.not_test().accept(this));
			}
			if (ctx.comparison() != null) {
				return new Not(this.getLocInfo(ctx), (Expr) ctx.comparison().accept(this));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitComparison(@NotNull Python3Parser.ComparisonContext ctx) {
			indent++;
			print("visitComparison");
			//star_expr ( comp_op star_expr )*
			List<Expr> operands = ctx.star_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			if (ctx.comp_op() != null) {
				List<Expr> operators = ctx.comp_op().stream()
						.map(c -> (Expr) c.accept(this))
						.collect(Collectors.toList());
				indent--;
				return new Comparison(this.getLocInfo(ctx), operands); //TODO: add operators
			}
			indent--;
			if (operands.size() == 1) {
				return operands.get(0);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitComp_op(@NotNull Python3Parser.Comp_opContext ctx) {
			//TODO: figure out how to store this, enum?
			indent++;
			print("visitComp_op " + ctx.getText());
			//'<' | '>' | '==' | '>=' | '<=' | '<>' | '!=' | IN | NOT IN | IS | IS NOT
			indent--;
			return null;
		}

		@Override
		public Py3Node visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx) {
			indent++;
			print("visitStar_expr " + ctx.getText());
			//'*'? expr
			indent--;
			//TODO: is this ok or should it be wrapped in something extra?
			return ctx.expr().accept(this);
		}

		@Override
		public Py3Node visitExpr(@NotNull Python3Parser.ExprContext ctx) {
			indent++;
			print("visitExpr");
			//xor_expr ( '|' xor_expr )*
			List<Py3Node> children = new ArrayList<>();
			ctx.xor_expr().forEach(c -> children.add(c.accept(this)));
			indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return null;
		}

		@Override
		public Py3Node visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx) {
			indent++;
			print("visitXor_expr");
			//and_expr ( '^' and_expr )*
			List<Expr> children = ctx.and_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return new Xor(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx) {
			indent++;
			print("visitAnd_expr");
			//shift_expr ( '&' shift_expr )*
			List<Expr> children = ctx.shift_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return new ast.expression.bitwise.And(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitShift_expr(@NotNull Python3Parser.Shift_exprContext ctx) {
			indent++;
			print("visitShift_expr");
			//arith_expr ( '<<' arith_expr | '>>' arith_expr )*
			List<Expr> children = ctx.arith_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return new Shift(this.getLocInfo(ctx), children, ctx.operators);
		}

		@Override
		public Py3Node visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx) {
			indent++;
			print("visitArith_expr");
			//term ( '+' term | '-' term )*
			List<Expr> operands = ctx.term().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			indent--;
			return new Arithmetic(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public Py3Node visitTerm(@NotNull Python3Parser.TermContext ctx) {
			indent++;
			print("visitTerm");
			//factor ( '*' factor | '/' factor | '%' factor | '//' factor | '@' factor // PEP 465 )
			List<Expr> operands = ctx.factor().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			indent--;
			return new Arithmetic(this.getLocInfo(ctx), operands, ctx.operators);
		}

		@Override
		public Py3Node visitFactor(@NotNull Python3Parser.FactorContext ctx) {
			indent++;
			print("visitFactor");
			indent--;
			//'+' factor | '-' factor | '~' factor | power
			if (ctx.factor() != null) {
				if (ctx.op.getText().equals("+")) {
					return new Plus(this.getLocInfo(ctx), (Expr) ctx.power().accept(this));
				}
				if (ctx.op.getText().equals("-")) {
					new Minus(this.getLocInfo(ctx), (Expr) ctx.power().accept(this));
				}
				if (ctx.op.getText().equals("~")) {
					new Invert(this.getLocInfo(ctx), (Expr) ctx.power().accept(this));
				}
				throw new IllegalArgumentException("Unknown context");
			}
			if (ctx.power() != null) {
				return ctx.power().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitPower(@NotNull Python3Parser.PowerContext ctx) {
			indent++;
			print("visitPower");
			//atom trailer* ( '**' factor )?
			ctx.atom().accept(this);
			if (ctx.trailer() != null) {
				for (Python3Parser.TrailerContext c : ctx.trailer()) {
					c.accept(this);
				}
			}
			if (ctx.factor() != null) {
				ctx.factor().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitAtom(@NotNull Python3Parser.AtomContext ctx) {
			indent++;
			print("visitAtom");
//			'(' ( yield_expr | testlist_comp )? ')'
//			| '[' testlist_comp? ']'
//			| '{' dictorsetmaker? '}'
//			| NAME | number | string+ | '...' | NONE | TRUE | FALSE
			indent--;
			if (ctx.yield_expr() != null) {
				return ctx.yield_expr().accept(this);
			}
			if (ctx.testlist_comp() != null) {
				return ctx.testlist_comp().accept(this);
			}
			if (ctx.dictorsetmaker() != null) {
				return ctx.dictorsetmaker().accept(this);
			}
			if (ctx.NAME() != null) {
				return new Identifier(this.getLocInfo(ctx.NAME()), ctx.NAME().getText());
			}
			if (ctx.number() != null) {
				return ctx.number().accept(this);
			}
			if (ctx.string() != null) {
				//TODO: filter out the start characters, quotes, etc.
				String str = "";
				for (Python3Parser.StringContext s : ctx.string()) {
					str += s.getText();
				}
				return new Str(this.getLocInfo(ctx), str);
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

		@Override
		public Py3Node visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx) {
			indent++;
			print("visitTestlist_comp");
			//test ( comp_for | ( ',' test )* ','? )
			ctx.test().get(0).accept(this);
			if (ctx.comp_for() != null) {
				ctx.comp_for().accept(this);
				//TODO
			}
			if (ctx.values.size() > 0) {
				List<Py3Node> vals = new ArrayList<>();
				ctx.values.forEach(e -> vals.add(e.accept(this)));
				//TODO
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTrailer(@NotNull Python3Parser.TrailerContext ctx) {
			indent++;
			print("visitTrailer");
			//'(' arglist? ')' | '[' subscriptlist ']' | '.' NAME
			if (ctx.arglist() != null) {
				ctx.arglist().accept(this);
			}
			if (ctx.subscriptlist() != null) {
				CollectionWrapper<Subscript> wrap = (CollectionWrapper<Subscript>) ctx.subscriptlist().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx) {
			indent++;
			print("visitSubscriptlist");
			//subscript ( ',' subscript )* ','?
			List<Subscript> subscripts = ctx.subscript().stream()
					.map(e -> (Subscript) e.accept(this))
					.collect(Collectors.toList());
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), subscripts);
		}

		@Override
		public Py3Node visitSubscript(@NotNull Python3Parser.SubscriptContext ctx) {
			indent++;
			print("visitSubscript");
			// index=test | start=test? colon=':' end=test? step=sliceop?
			if (ctx.index != null) {
				indent--;
				return new SubscriptIndex(this.getLocInfo(ctx), (Expr) ctx.index.accept(this));
			}
			if (ctx.colon != null) {
				Expr startIndex = ctx.lowerBound == null ? null : (Expr) ctx.lowerBound.accept(this);
				Expr endIndex = ctx.upperBound == null ? null : (Expr) ctx.upperBound.accept(this);
				Expr step = ctx.stride == null ? null : (Expr) ctx.stride.accept(this);
				indent--;
				return new SubscriptSlice(this.getLocInfo(ctx), startIndex, endIndex, step);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitSliceop(@NotNull Python3Parser.SliceopContext ctx) {
			indent++;
			print("visitSliceop");
			indent--;
			//':' test?
			if (ctx.test() != null) {
				return new Slice(this.getLocInfo(ctx), (Expr) ctx.test().accept(this));
			}
			return new Slice(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitExprlist(@NotNull Python3Parser.ExprlistContext ctx) {
			indent++;
			print("visitExprlist");
			//star_expr ( ',' star_expr )* ','?
			List<Expr> expressions = ctx.star_expr().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), expressions);
		}

		@Override
		public Py3Node visitTestlist(@NotNull Python3Parser.TestlistContext ctx) {
			indent++;
			print("visitTestlist");
			//test ( ',' test )* ','?

			List<Expr> expressions = ctx.test().stream()
					.map(e -> (Expr) e.accept(this))
					.collect(Collectors.toList());
			indent--;
			return new CollectionWrapper<>(this.getLocInfo(ctx), expressions);
		}

		@Override
		public Py3Node visitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx) {
			indent++;
			print("visitDictorsetmaker");
			indent--;
//			dictVar=test ':' dictExpr=test ( comp_for | ( ',' dictKey=test ':' dictVal=test )* ','? )
//			| setVar=test ( comp_for | ( ',' setVal=test )* ','? )

			if (ctx.dictVar != null) {
				if (ctx.comp_for() != null) {
					//TODO
					return null;
				}
				Map<Expr, Expr> values = ctx.dictValues.keySet().stream()
						.collect(Collectors.toMap(k -> (Expr) k.accept(this), k -> (Expr) ctx.dictValues.get(k).accept(this)));
				return new DictMaker(this.getLocInfo(ctx), values);
			}

			if (ctx.setVar != null) {
				if (ctx.comp_for() != null) {
					//TODO
					return null;
				}
				List<Expr> values = ctx.setValues.stream()
						.map(e -> (Expr) e.accept(this))
						.collect(Collectors.toList());
				return new SetMaker(this.getLocInfo(ctx), values);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitClassdef(@NotNull Python3Parser.ClassdefContext ctx) {
			indent++;
			print("visitClassdef");
			//TODO: fix args
			List<Py3Node> args = new ArrayList<>();
			if (ctx.arglist() != null) {
				args.add(ctx.arglist().accept(this));
			}
			ctx.suite().accept(this);
			indent--;
			return new ClassDef(this.getLocInfo(ctx), ctx.NAME().toString(), args);
		}

		@Override
		public Py3Node visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
			//TODO
			indent++;
			print("visitArglist");
			//( argument ',' )* ( argument ','? | '*' test ( ',' argument )* ( ',' '**' test )? | '**' test )

			ctx.params.stream();
			if (ctx.args != null ) {

			}
			if (ctx.kwargs != null) {

			}

			indent--;
			return null;
		}

		@Override
		public Py3Node visitArgument(@NotNull Python3Parser.ArgumentContext ctx) {
			//TODO
			indent++;
			print("visitArgument");
			//test comp_for? | test '=' test
			for (Python3Parser.TestContext c : ctx.test()) {
				c.accept(this);
			}
			if (ctx.comp_for() != null) {
				ctx.comp_for().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitComp_iter(@NotNull Python3Parser.Comp_iterContext ctx) {
			//TODO
			indent++;
			print("visitComp_iter");
			//comp_for | comp_if
			if (ctx.comp_for() != null) {
				indent--;
				return ctx.comp_for().accept(this);
			}
			if (ctx.comp_if() != null) {
				indent--;
				return ctx.comp_if().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitComp_for(@NotNull Python3Parser.Comp_forContext ctx) {
			//TODO
			indent++;
			print("visitComp_for");
			//FOR exprlist IN or_test comp_iter?
			ctx.exprlist().accept(this);
			ctx.or_test().accept(this);
			if (ctx.comp_iter() != null) {
				ctx.comp_iter().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitComp_if(@NotNull Python3Parser.Comp_ifContext ctx) {
			//TODO
			indent++;
			print("visitComp_if");
			//IF test_nocond comp_iter?
			ctx.test_nocond().accept(this);
			if (ctx.comp_iter() != null) {
				ctx.comp_iter().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitYield_expr(@NotNull Python3Parser.Yield_exprContext ctx) {
			indent++;
			print("visitYield_expr");
			//: YIELD yield_arg?
			if (ctx.yield_arg() != null) {
				indent--;
				return ctx.yield_arg().accept(this);
			}
			indent--;
			return new Yield(this.getLocInfo(ctx));
		}

		@Override
		public Py3Node visitYield_arg(@NotNull Python3Parser.Yield_argContext ctx) {
			indent++;
			print("visitYield_arg");
			//FROM test | testlist
			if (ctx.test() != null) {
				indent--;
				return new YieldFrom(this.getLocInfo(ctx), (Expr) ctx.test().accept(this));
			}
			if (ctx.testlist() != null) {
				CollectionWrapper<Expr> wrap = (CollectionWrapper<Expr>) ctx.testlist().accept(this);
				indent--;
				return new Yield(this.getLocInfo(ctx), wrap.getItems());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitString(@NotNull Python3Parser.StringContext ctx) {
			indent++;
			print("visitString");
			indent--;
			//STRING_LITERAL | BYTES_LITERAL
			//TODO: strip out prefix + quotes?
			if (ctx.STRING_LITERAL() != null) {
				return new Str(this.getLocInfo(ctx), ctx.STRING_LITERAL().getText());
			}
			else if (ctx.BYTES_LITERAL() != null) {
				//TODO: fix bytes into Bytes[] mb? or translate to normal string?
				return new Str(this.getLocInfo(ctx), ctx.BYTES_LITERAL().getText());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitNumber(@NotNull Python3Parser.NumberContext ctx) {
			indent++;
			print("visitNumber");
			//integer | FLOAT_NUMBER | IMAG_NUMBER
			if (ctx.integer() != null) {
				indent--;
				return ctx.integer().accept(this);
			}
			else if (ctx.FLOAT_NUMBER() != null) {
				Double nr = Double.parseDouble(ctx.FLOAT_NUMBER().getText());
				indent--;
				return new ast.expression.atom.Float(this.getLocInfo(ctx), nr);
			}
			else if (ctx.IMAG_NUMBER() != null) {
				//strip out the "j" at the end
				String s = ctx.IMAG_NUMBER().getText();
				s = s.substring(0, s.length() - 1);
				indent--;
				return new Imaginary(this.getLocInfo(ctx), Double.parseDouble(s));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitInteger(@NotNull Python3Parser.IntegerContext ctx) {
			indent++;
			print("visitInteger");
			//DECIMAL_INTEGER | OCT_INTEGER | HEX_INTEGER | BIN_INTEGER
			if (ctx.DECIMAL_INTEGER() != null) {
				BigInteger bi = new BigInteger(ctx.DECIMAL_INTEGER().getText());
				indent--;
				return new Int(this.getLocInfo(ctx), bi);
			}
			else if (ctx.OCT_INTEGER() != null) {
				//strip out the "0O" prefix
				String s = ctx.OCT_INTEGER().getText();
				s = s.substring(2);
				indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 8));
			}
			else if (ctx.HEX_INTEGER() != null) {
				//strip out the "0X" prefix
				String s = ctx.HEX_INTEGER().getText();
				s = s.substring(2);
				indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 16));
			}
			else if (ctx.BIN_INTEGER() != null) {
				//strip out the "0B" prefix
				String s = ctx.HEX_INTEGER().getText();
				s = s.substring(2);
				indent--;
				return new Int(this.getLocInfo(ctx), new BigInteger(s, 2));
			}
			throw new IllegalArgumentException("Unknown context");
		}

		public Py3Node visitComment(@NotNull Python3Parser.CommentContext ctx) {
			indent++;
			print("visitComment");
			indent--;
			return new Comment(this.getLocInfo(ctx), ctx.COMMENT().getText());
		}

		@Override
		public Py3Node visit(@NotNull ParseTree parseTree) {
			indent++;
			print("visit");
			indent--;
			//TODO
			return null;
		}

		@Override
		public Py3Node visitChildren(@NotNull RuleNode ruleNode) {
			indent++;
			print("visitChildren");
			indent--;
			//TODO
			return null;
		}

		@Override
		public Py3Node visitTerminal(@NotNull TerminalNode terminalNode) {
			indent++;
			print("visitTerminal");
			indent--;
			//TODO
			return null;
		}

		@Override
		public Py3Node visitErrorNode(@NotNull ErrorNode errorNode) {
			indent++;
			print("visitErrorNode");
			indent--;
			//TODO
			return null;
		}

		private LocInfo getLocInfo (TerminalNode node) {
			//TODO: check this
			return new LocInfo(node.getSymbol().getStartIndex(), node.getSymbol().getStopIndex());
		}

		private LocInfo getLocInfo(ParserRuleContext ctx) {
			return new LocInfo(ctx.getStart().getLine(), ctx.getStop().getLine());
		}

		private void print(String s) {
			for (int i = 0; i < indent; i++) {
				System.out.print("\t");
			}
			System.out.println(s);
		}

		private List<Statement> processOptional(Python3Parser.SuiteContext node) {
			if (node == null) {
				return Collections.emptyList();
			}
			return process(node);
		}

		private List<Statement> process(Python3Parser.SuiteContext node) {
			return this.getResultWrapper(node).getItems();
		}

		private CollectionWrapper<Statement> getResultWrapper(Python3Parser.SuiteContext node) {
			return (CollectionWrapper<Statement>) node.accept(this);
		}

		private class Wrapper<T> extends Py3Node {
			private final T item;

			public Wrapper(LocInfo locInfo, T item) {
				super(locInfo);
				this.item = item;
			}

			public T getItem() {
				return item;
			}

			@Override
			public <T> T accept(Py3TreeVisitor<T> visitor) {
				throw new IllegalArgumentException("Cannot visit Wrapper");
			}
		}

		private class CollectionWrapper<T> extends Py3Node {
			private final List<T> items;

			public CollectionWrapper(LocInfo locInfo, List<T> items) {
				super(locInfo);
				this.items = items;
			}

			public CollectionWrapper(LocInfo locInfo, T item) {
				super(locInfo);
				this.items = new ArrayList<>();
				this.items.add(item);
			}

			public List<T> getItems() {
				return items;
			}

			@Override
			public <T> T accept(Py3TreeVisitor<T> visitor) {
				throw new IllegalArgumentException("Cannot visit CollectionWrapper");
			}
		}
	}
}