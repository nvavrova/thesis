// Generated from D:/intellij_projects/thesis/main/grammar\Python2.g4 by ANTLR 4.5.1
package gen;

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Python2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Python2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Python2Parser#single_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_input(Python2Parser.Single_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(Python2Parser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#eval_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_input(Python2Parser.Eval_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#decorators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorators(Python2Parser.DecoratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(Python2Parser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(Python2Parser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(Python2Parser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#varargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargslist(Python2Parser.VarargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#vfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfpdef(Python2Parser.VfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#vfplist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfplist(Python2Parser.VfplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(Python2Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(Python2Parser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmall_stmt(Python2Parser.Small_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(Python2Parser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugassign(Python2Parser.AugassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#print_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stmt(Python2Parser.Print_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#del_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel_stmt(Python2Parser.Del_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#pass_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPass_stmt(Python2Parser.Pass_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow_stmt(Python2Parser.Flow_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#break_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_stmt(Python2Parser.Break_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#continue_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_stmt(Python2Parser.Continue_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(Python2Parser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#yield_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_stmt(Python2Parser.Yield_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#raise_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_stmt(Python2Parser.Raise_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(Python2Parser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#import_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_name(Python2Parser.Import_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_from(Python2Parser.Import_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(Python2Parser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_name(Python2Parser.Dotted_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(Python2Parser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_names(Python2Parser.Dotted_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_name(Python2Parser.Dotted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_stmt(Python2Parser.Global_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#exec_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_stmt(Python2Parser.Exec_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#assert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssert_stmt(Python2Parser.Assert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(Python2Parser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(Python2Parser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(Python2Parser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(Python2Parser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#try_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTry_stmt(Python2Parser.Try_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#with_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_stmt(Python2Parser.With_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(Python2Parser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(Python2Parser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_test(Python2Parser.Or_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_test(Python2Parser.And_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_test(Python2Parser.Not_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(Python2Parser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(Python2Parser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Python2Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#xor_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor_expr(Python2Parser.Xor_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#and_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(Python2Parser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#shift_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_expr(Python2Parser.Shift_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#arith_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith_expr(Python2Parser.Arith_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(Python2Parser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(Python2Parser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(Python2Parser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(Python2Parser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#listmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListmaker(Python2Parser.ListmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_comp(Python2Parser.Testlist_compContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#lambdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef(Python2Parser.LambdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailer(Python2Parser.TrailerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptlist(Python2Parser.SubscriptlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(Python2Parser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#sliceop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceop(Python2Parser.SliceopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprlist(Python2Parser.ExprlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist(Python2Parser.TestlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#dictmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictmaker(Python2Parser.DictmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(Python2Parser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(Python2Parser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(Python2Parser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#list_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_iter(Python2Parser.List_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#list_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_for(Python2Parser.List_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#list_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_if(Python2Parser.List_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#comp_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_iter(Python2Parser.Comp_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_for(Python2Parser.Comp_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#comp_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_if(Python2Parser.Comp_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#yield_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_expr(Python2Parser.Yield_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(Python2Parser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(Python2Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Python2Parser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(Python2Parser.IntegerContext ctx);
}