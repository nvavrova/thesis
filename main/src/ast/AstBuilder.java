package ast;

import ast.expression.Comparison;
import ast.expression.Expr;
import ast.expression.Not;
import ast.expression.Shift;
import ast.expression.atom.Comment;
import ast.expression.atom.Imaginary;
import ast.expression.atom.Int;
import ast.expression.atom.Str;
import ast.expression.bitwise.Xor;
import gen.Python3Parser;
import gen.Python3Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

		private int indent = 0;

		@Override
		public Py3Node visitSingle_input(@NotNull Python3Parser.Single_inputContext ctx) {
			indent++;
			print("visitSingle_input");
			indent--;
			//NEWLINE | simple_stmt | compound_stmt NEWLINE
			if (ctx.simple_stmt() != null) {
				return ctx.compound_stmt().accept(this);
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
				for (Python3Parser.StmtContext stmt : ctx.stmt()) {
					stmt.accept(this);
				}
			}
			indent--;
			return null;
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
			indent++;
			print("visitDecorator");
			//'@' dotted_name ( '(' arglist? ')' )? NEWLINE
			ctx.dotted_name().accept(this);
			if (ctx.arglist() != null) {
				ctx.arglist().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDecorators(@NotNull Python3Parser.DecoratorsContext ctx) {
			indent++;
			print("visitDecorators");
			//decorator+
			if (ctx.decorator() != null) {
				for (Python3Parser.DecoratorContext c : ctx.decorator()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDecorated(@NotNull Python3Parser.DecoratedContext ctx) {
			indent++;
			print("visitDecorated");
			//decorators ( classdef | funcdef )
			ctx.decorators().accept(this);
			if (ctx.classdef() != null) {
				ctx.classdef().accept(this);
			}
			if (ctx.funcdef() != null) {
				ctx.funcdef().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitFuncdef(@NotNull Python3Parser.FuncdefContext ctx) {
			indent++;
			print("visitFuncdef");
			//DEF NAME parameters ( '->' test )? ':' suite
			ctx.parameters().accept(this);
			if (ctx.test() != null) {
				ctx.test().accept(this);
			}
			ctx.suite().accept(this);
			indent--;
			return null;
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
			return null;
		}

		@Override
		public Py3Node visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx) {
			indent++;
			print("visitTypedargslist");
			//		tfpdef ( '=' test )? ( ',' tfpdef ( '=' test )? )* ( ',' ( '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef
			//		)?
			//		)?
			//		| '*' tfpdef? ( ',' tfpdef ( '=' test )? )* ( ',' '**' tfpdef )?
			//		| '**' tfpdef
			if (ctx.tfpdef() != null) {
				for (Python3Parser.TfpdefContext tfpdef : ctx.tfpdef()) {
					tfpdef.accept(this);
				}
			}
			if (ctx.test() != null) {
				for (Python3Parser.TestContext c : ctx.test()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTfpdef(@NotNull Python3Parser.TfpdefContext ctx) {
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
			indent++;
			print("visitVarargslist");
			//		vfpdef ( '=' test )? ( ',' vfpdef ( '=' test )? )* ( ',' ( '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			//		)?
			//		)?
			//		| '*' vfpdef? ( ',' vfpdef ( '=' test )? )* ( ',' '**' vfpdef )?
			//		| '**' vfpdef
			if (ctx.vfpdef() != null) {
				for (Python3Parser.VfpdefContext vfpdef : ctx.vfpdef()) {
					vfpdef.accept(this);
				}
			}
			if (ctx.test() != null) {
				for (Python3Parser.TestContext c : ctx.test()) {
					c.accept(this);
				}
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
			return null;
		}

		@Override
		public Py3Node visitStmt(@NotNull Python3Parser.StmtContext ctx) {
			indent++;
			print("visitStmt " + ctx.getText());
			//simple_stmt | compound_stmt
			if (ctx.simple_stmt() != null) {
				indent--;
				return ctx.simple_stmt().accept(this);
			}
			if (ctx.compound_stmt() != null) {
				indent--;
				return ctx.compound_stmt().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx) {
			indent++;
			print("visitSimple_stmt");
			//small_stmt ( ';' small_stmt )* ';'? NEWLINE
			for (Python3Parser.Small_stmtContext stmt : ctx.small_stmt()) {
				stmt.accept(this);
			}
			indent--;
			return null;
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
			indent++;
			print("visitExpr_stmt");
			//testlist_star_expr ( augassign ( yield_expr | testlist) | ( '=' ( yield_expr| testlist_star_expr ) )* )
			if (ctx.testlist_star_expr() != null) {
				for (Python3Parser.Testlist_star_exprContext e : ctx.testlist_star_expr()) {
					e.accept(this);
				}
			}
			if (ctx.augassign() != null) {
				ctx.augassign().accept(this);
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
			if (ctx.test() != null) {
				for (Python3Parser.TestContext testContext : ctx.test()) {
					testContext.accept(this);
				}
			}
			if (ctx.star_expr() != null) {
				for (Python3Parser.Star_exprContext e : ctx.star_expr()) {
					e.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitAugassign(@NotNull Python3Parser.AugassignContext ctx) {
			indent++;
			print("visitAugassign");
			//'+=' | '-=' | '*=' | '@=' // PEP 465 | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '**=' | '//='
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx) {
			indent++;
			print("visitDel_stmt");
			//DEL exprlist
			indent--;
			return ctx.exprlist().accept(this);
		}

		@Override
		public Py3Node visitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx) {
			indent++;
			print("visitPass_stmt");
			//PASS
			indent--;
			return null;
		}

		@Override
		public Py3Node visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx) {
			indent++;
			print("visitFlow_stmt");
			indent--;
			//break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt
			if (ctx.break_stmt() != null) {
				indent--;
				return ctx.break_stmt().accept(this);
			}
			if (ctx.continue_stmt() != null) {
				indent--;
				return ctx.continue_stmt().accept(this);
			}
			indent--;
			if (ctx.return_stmt() != null) {
				indent--;
				return ctx.return_stmt().accept(this);
			}
			indent--;
			if (ctx.return_stmt() != null) {
				indent--;
				return ctx.return_stmt().accept(this);
			}
			if (ctx.yield_stmt() != null) {
				indent--;
				return ctx.yield_stmt().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
			indent++;
			print("visitBreak_stmt");
			//BREAK
			indent--;
			return null;
		}

		@Override
		public Py3Node visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx) {
			indent++;
			print("visitContinue_stmt");
			//CONTINUE
			indent--;
			return null;
		}

		@Override
		public Py3Node visitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx) {
			indent++;
			print("visitReturn_stmt");
			//RETURN testlist?
			if (ctx.testlist() != null) {
				indent--;
				return ctx.testlist().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx) {
			indent++;
			print("visitYield_stmt");
			//yield_expr
			indent--;
			return ctx.yield_expr().accept(this);
		}

		@Override
		public Py3Node visitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx) {
			indent++;
			print("visitRaise_stmt");
			//RAISE ( test ( FROM test )? )?
			if (ctx.test() != null) {
				for (Python3Parser.TestContext c : ctx.test()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
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
			indent++;
			print("visitImport_name");
			//IMPORT dotted_as_names
			indent--;
			return ctx.dotted_as_names().accept(this);
		}

		@Override
		public Py3Node visitImport_from(@NotNull Python3Parser.Import_fromContext ctx) {
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
			indent++;
			print("visitImport_as_name");
			//NAME ( AS NAME )?
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx) {
			indent++;
			print("visitDotted_as_name");
			//dotted_name ( AS NAME )?
			indent--;
			return ctx.dotted_name().accept(this);
		}

		@Override
		public Py3Node visitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx) {
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
			indent++;
			print("visitDotted_name");
			//NAME ( '.' NAME )*
			indent--;
			return null;
		}

		@Override
		public Py3Node visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
			indent++;
			print("visitGlobal_stmt");
			//GLOBAL NAME ( ',' NAME )*
			indent--;
			return null;
		}

		@Override
		public Py3Node visitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx) {
			indent++;
			print("visitNonlocal_stmt");
			//NONLOCAL NAME ( ',' NAME )*
			indent--;
			return null;
		}

		@Override
		public Py3Node visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx) {
			indent++;
			print("visitAssert_stmt");
			//ASSERT test ( ',' test )?
			for (Python3Parser.TestContext c : ctx.test()) {
				c.accept(this);
			}
			indent--;
			return null;
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
			for (Python3Parser.TestContext c : ctx.test()) {
				c.accept(this);
			}
			for (Python3Parser.SuiteContext c : ctx.suite()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx) {
			indent++;
			print("visitWhile_stmt");
			//WHILE test ':' suite ( ELSE ':' suite )?
			ctx.test().accept(this);
			for (Python3Parser.SuiteContext c : ctx.suite()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx) {
			indent++;
			print("visitFor_stmt");
			//FOR exprlist IN testlist ':' suite ( ELSE ':' suite )?
			ctx.exprlist().accept(this);
			ctx.testlist().accept(this);
			for (Python3Parser.SuiteContext c : ctx.suite()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx) {
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
			indent++;
			print("visitWith_stmt");
			//WITH with_item ( ',' with_item )* ':' suite
			for (Python3Parser.With_itemContext c : ctx.with_item()) {
				c.accept(this);
			}
			ctx.suite().accept(this);
			indent--;
			return null;
		}

		@Override
		public Py3Node visitWith_item(@NotNull Python3Parser.With_itemContext ctx) {
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
				indent--;
				return ctx.simple_stmt().accept(this);
			}
			if (ctx.stmt() != null) {
				for (Python3Parser.StmtContext stmt : ctx.stmt()) {
					stmt.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTest(@NotNull Python3Parser.TestContext ctx) {
			indent++;
			print("visitTest");
			//or_test ( IF or_test ELSE test )?  | lambdef
			if (ctx.or_test() != null) {
				for (Python3Parser.Or_testContext orTestContext : ctx.or_test()) {
					orTestContext.accept(this);
				}
				if (ctx.test() != null) {
					ctx.test().accept(this);
				}
			}
			if (ctx.lambdef() != null) {
				indent--;
				return ctx.lambdef().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx) {
			indent++;
			print("visitTest_nocond");
			//or_test | lambdef_nocond
			if (ctx.or_test() != null) {
				indent--;
				return ctx.or_test().accept(this);
			}
			if (ctx.lambdef_nocond() != null) {
				indent--;
				return ctx.lambdef_nocond().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
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
			if (ctx.and_test() != null) {
				for (Python3Parser.And_testContext c : ctx.and_test()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
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
			//TODO: divide to left and right shift?

			//arith_expr ( '<<' arith_expr | '>>' arith_expr )*
			List<Expr> children = ctx.arith_expr().stream()
					.map(c -> (Expr) c.accept(this))
					.collect(Collectors.toList());
			indent--;
			if (children.size() == 1) {
				return children.get(0);
			}
			return new Shift(this.getLocInfo(ctx), children);
		}

		@Override
		public Py3Node visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx) {
			indent++;
			print("visitArith_expr");
			//term ( '+' term | '-' term )*
			for (Python3Parser.TermContext c : ctx.term()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTerm(@NotNull Python3Parser.TermContext ctx) {
			indent++;
			print("visitTerm");
			//factor ( '*' factor | '/' factor | '%' factor | '//' factor | '@' factor // PEP 465 )
			for (Python3Parser.FactorContext c : ctx.factor()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitFactor(@NotNull Python3Parser.FactorContext ctx) {
			indent++;
			print("visitFactor");
			//'+' factor | '-' factor | '~' factor | power
			if (ctx.factor() != null) {
				indent--;
				return ctx.factor().accept(this);
			}
			if (ctx.power() != null) {
				indent--;
				return ctx.power().accept(this);
			}
			indent--;
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
			//		'(' ( yield_expr | testlist_comp )? ')'
			//				| '[' testlist_comp? ']'
			//				| '{' dictorsetmaker? '}'
			//				| NAME
			//				| number
			//				| string+
			//				| '...'
			//				| NONE
			//				| TRUE
			//				| FALSE
			if (ctx.yield_expr() != null) {
				indent--;
				return ctx.yield_expr().accept(this);
			}
			if (ctx.testlist_comp() != null) {
				indent--;
				return ctx.testlist_comp().accept(this);
			}
			if (ctx.dictorsetmaker() != null) {
				indent--;
				return ctx.dictorsetmaker().accept(this);
			}
			if (ctx.number() != null) {
				indent--;
				return ctx.number().accept(this);
			}
			if (ctx.string() != null) {
				for (Python3Parser.StringContext c : ctx.string()) {
					c.accept(this);
				}
				indent--;
				return null;
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx) {
			indent++;
			print("visitTestlist_comp");
			//test ( comp_for | ( ',' test )* ','? )
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
		public Py3Node visitTrailer(@NotNull Python3Parser.TrailerContext ctx) {
			indent++;
			print("visitTrailer");
			//'(' arglist? ')' | '[' subscriptlist ']' | '.' NAME
			if (ctx.arglist() != null) {
				ctx.arglist().accept(this);
			}
			if (ctx.subscriptlist() != null) {
				ctx.subscriptlist().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx) {
			indent++;
			print("visitSubscriptlist");
			//subscript ( ',' subscript )* ','?
			for (Python3Parser.SubscriptContext c : ctx.subscript()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitSubscript(@NotNull Python3Parser.SubscriptContext ctx) {
			indent++;
			print("visitSubscript");
			// test | test? ':' test? sliceop?
			if (ctx.test() != null) {
				for (Python3Parser.TestContext c : ctx.test()) {
					c.accept(this);
				}
			}
			if (ctx.sliceop() != null) {
				ctx.sliceop().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitSliceop(@NotNull Python3Parser.SliceopContext ctx) {
			indent++;
			print("visitSliceop");
			//':' test?
			if (ctx.test() != null) {
				indent--;
				return ctx.test().accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitExprlist(@NotNull Python3Parser.ExprlistContext ctx) {
			indent++;
			print("visitExprlist");
			//star_expr ( ',' star_expr )* ','?
			for (Python3Parser.Star_exprContext c : ctx.star_expr()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTestlist(@NotNull Python3Parser.TestlistContext ctx) {
			indent++;
			print("visitTestlist");
			//test ( ',' test )* ','?
			for (Python3Parser.TestContext c : ctx.test()) {
				c.accept(this);
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx) {
			indent++;
			print("visitDictorsetmaker");
			//		test ':' test ( comp_for | ( ',' test ':' test )* ','? )
			//		| test ( comp_for | ( ',' test )* ','? )
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
		public Py3Node visitClassdef(@NotNull Python3Parser.ClassdefContext ctx) {
			indent++;
			List<Py3Node> args = new ArrayList<>();
			if (ctx.arglist() != null) {
				args.add(ctx.arglist().accept(this));
			}
			ctx.suite().accept(this);
			indent--;
			LocInfo li = new LocInfo(ctx.getStart().getLine(), ctx.getStop().getLine());
			indent--;
			return new ClassDef(li, ctx.NAME().toString(), args);
		}

		@Override
		public Py3Node visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
			indent++;
			print("visitArglist");
			//		( argument ',' )* ( argument ','? | '*' test ( ',' argument )* ( ',' '**' test )? | '**' test )
			if (ctx.argument() != null) {
				for (Python3Parser.ArgumentContext c : ctx.argument()) {
					c.accept(this);
				}
			}
			if (ctx.test() != null) {
				for (Python3Parser.TestContext c : ctx.test()) {
					c.accept(this);
				}
			}
			indent--;
			return null;
		}

		@Override
		public Py3Node visitArgument(@NotNull Python3Parser.ArgumentContext ctx) {
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
			return null;
		}

		@Override
		public Py3Node visitYield_arg(@NotNull Python3Parser.Yield_argContext ctx) {
			indent++;
			print("visitYield_arg");
			//FROM test | testlist
			if (ctx.test() != null) {
				indent--;
				return ctx.test().accept(this);
			}
			if (ctx.testlist() != null) {
				indent--;
				return ctx.testlist().accept(this);
			}
			indent--;
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitString(@NotNull Python3Parser.StringContext ctx) {
			indent++;
			print("visitString");
			//string_literal | bytes_literal
			indent--;
			if (ctx.string_literal() != null) {
				return ctx.string_literal().accept(this);
			}
			else if (ctx.bytes_literal() != null) {
				return ctx.bytes_literal().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitNumber(@NotNull Python3Parser.NumberContext ctx) {
			indent++;
			print("visitNumber");
			//integer | FLOAT_NUMBER | IMAG_NUMBER
			indent--;
			if (ctx.integer() != null) {
				return ctx.integer().accept(this);
			}
			else if (ctx.FLOAT_NUMBER() != null) {
				Double nr = Double.parseDouble(ctx.FLOAT_NUMBER().getText());
				return new ast.expression.atom.Float(this.getLocInfo(ctx), nr);
			}
			else if (ctx.IMAG_NUMBER() != null) {
				//strip out "j" at the end
				String s = ctx.IMAG_NUMBER().getText();
				Double nr = Double.parseDouble(s.substring(0, s.length() - 1));
				return new Imaginary(this.getLocInfo(ctx), nr);
			}
			return null;
		}

		@Override
		public Py3Node visitInteger(@NotNull Python3Parser.IntegerContext ctx) {
			indent++;
			print("visitInteger");
			//DECIMAL_INTEGER | oct_integer | hex_integer | bin_integer
			indent--;
			if (ctx.DECIMAL_INTEGER() != null) {
				BigInteger bi = new BigInteger(ctx.DECIMAL_INTEGER().getText());
				return new Int(this.getLocInfo(ctx), bi);
			}
			else if (ctx.oct_integer() != null) {
				return ctx.oct_integer().accept(this);
			}
			else if (ctx.hex_integer() != null) {
				return ctx.hex_integer().accept(this);
			}
			else if (ctx.bin_integer() != null) {
				return ctx.bin_integer().accept(this);
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitString_literal(@NotNull Python3Parser.String_literalContext ctx) {
			//STRING_LITERAL_PREFIX value=( SHORT_STRING | LONG_STRING )
			print("visitString_literal");
			if (ctx.SHORT_STRING() != null) {
				return new Str(this.getLocInfo(ctx), ctx.SHORT_STRING().getText());
			}
			else if (ctx.LONG_STRING() != null) {
				return new Str(this.getLocInfo(ctx), ctx.LONG_STRING().getText());
			}
			throw new IllegalArgumentException("Unknown context");
		}

		@Override
		public Py3Node visitBytes_literal(@NotNull Python3Parser.Bytes_literalContext ctx) {
			//BYTES_LITERAL_PREFIX value=( SHORT_BYTES | LONG_BYTES )
			print("visitBytes_literal");
			//TODO
//			if (ctx.SHORT_BYTES() != null) {
//				return new Str(this.getLocInfo(ctx), );
//			}
//			else if (ctx.LONG_BYTES() != null) {
//				return new Str(this.getLocInfo(ctx), );
//			}
//			throw new IllegalArgumentException("Unknown context");
			return null;
		}

		@Override
		public Py3Node visitOct_integer(@NotNull Python3Parser.Oct_integerContext ctx) {
			//OCT_INTEGER_PREFIX OCT_INTEGER_UNPREFIXED
			print("visitOct_integer");
			return new Int(this.getLocInfo(ctx), new BigInteger(ctx.value.getText(), 8));
		}

		@Override
		public Py3Node visitHex_integer(@NotNull Python3Parser.Hex_integerContext ctx) {
			//HEX_INTEGER_PREFIX HEX_INTEGER_UNPREFIXED
			print("visitHex_integer");
			return new Int(this.getLocInfo(ctx), new BigInteger(ctx.value.getText(), 16));
		}

		@Override
		public Py3Node visitBin_integer(@NotNull Python3Parser.Bin_integerContext ctx) {
			//BIN_INTEGER_PREFIX BIN_INTEGER_UNPREFIXED
			print("visitBin_integer");
			return new Int(this.getLocInfo(ctx), new BigInteger(ctx.value.getText(), 2));
		}

		public Py3Node visitComment(@NotNull Python3Parser.CommentContext ctx) {
			print("visitComment");
			return new Comment(this.getLocInfo(ctx), ctx.COMMENT().getText());
		}

		@Override
		public Py3Node visit(@NotNull ParseTree parseTree) {
			indent++;
			print("visit");
			//TODO
			indent--;
			return null;
		}

		@Override
		public Py3Node visitChildren(@NotNull RuleNode ruleNode) {
			indent++;
			print("visitChildren");
			//TODO
			indent--;
			return null;
		}

		@Override
		public Py3Node visitTerminal(@NotNull TerminalNode terminalNode) {
			indent++;
			print("visitTerminal");
			//TODO
			indent--;
			return null;
		}

		@Override
		public Py3Node visitErrorNode(@NotNull ErrorNode errorNode) {
			indent++;
			print("visitErrorNode");
			//TODO
			indent--;
			return null;
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
	}
}