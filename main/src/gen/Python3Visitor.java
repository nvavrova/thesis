// Generated from D:/intellij_projects/thesis/main/grammar\Python3.g4 by ANTLR 4.5
package gen;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Python3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Python3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Python3Parser#single_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_input(@NotNull Python3Parser.Single_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(@NotNull Python3Parser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#eval_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_input(@NotNull Python3Parser.Eval_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(@NotNull Python3Parser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#decorators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorators(@NotNull Python3Parser.DecoratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#decorated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorated(@NotNull Python3Parser.DecoratedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(@NotNull Python3Parser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(@NotNull Python3Parser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#tfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTfpdef(@NotNull Python3Parser.TfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#varargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargslist(@NotNull Python3Parser.VarargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#vfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfpdef(@NotNull Python3Parser.VfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(@NotNull Python3Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(@NotNull Python3Parser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugassign(@NotNull Python3Parser.AugassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#del_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#pass_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#break_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#continue_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#yield_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#raise_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#import_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_name(@NotNull Python3Parser.Import_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_from(@NotNull Python3Parser.Import_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#assert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#try_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#with_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_stmt(@NotNull Python3Parser.With_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#with_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_item(@NotNull Python3Parser.With_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#except_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(@NotNull Python3Parser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(@NotNull Python3Parser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#test_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#lambdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef(@NotNull Python3Parser.LambdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#lambdef_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_test(@NotNull Python3Parser.Or_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_test(@NotNull Python3Parser.And_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_test(@NotNull Python3Parser.Not_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(@NotNull Python3Parser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(@NotNull Python3Parser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull Python3Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#xor_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#and_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#shift_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_expr(@NotNull Python3Parser.Shift_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#arith_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull Python3Parser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(@NotNull Python3Parser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(@NotNull Python3Parser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(@NotNull Python3Parser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailer(@NotNull Python3Parser.TrailerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(@NotNull Python3Parser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#sliceop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceop(@NotNull Python3Parser.SliceopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprlist(@NotNull Python3Parser.ExprlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist(@NotNull Python3Parser.TestlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#dictorsetmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(@NotNull Python3Parser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(@NotNull Python3Parser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(@NotNull Python3Parser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comp_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_iter(@NotNull Python3Parser.Comp_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_for(@NotNull Python3Parser.Comp_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#comp_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_if(@NotNull Python3Parser.Comp_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#yield_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_expr(@NotNull Python3Parser.Yield_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#yield_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_arg(@NotNull Python3Parser.Yield_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull Python3Parser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull Python3Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python3Parser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(@NotNull Python3Parser.IntegerContext ctx);
}