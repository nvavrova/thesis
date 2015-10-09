// Generated from C:/workspace_intellij/thesis/main/grammar\Python.g4 by ANTLR 4.5.1
package gen;

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PythonParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PythonVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PythonParser#single_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_input(PythonParser.Single_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#file_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_input(PythonParser.File_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#eval_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEval_input(PythonParser.Eval_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#decorator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorator(PythonParser.DecoratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#decorators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorators(PythonParser.DecoratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#decorated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecorated(PythonParser.DecoratedContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(PythonParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#async_funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsync_funcdef(PythonParser.Async_funcdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(PythonParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(PythonParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#typedargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedargslist(PythonParser.TypedargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#tfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTfpdef(PythonParser.TfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#varargslist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarargslist(PythonParser.VarargslistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#vfpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfpdef(PythonParser.VfpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#vfplist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVfplist(PythonParser.VfplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(PythonParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(PythonParser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmall_stmt(PythonParser.Small_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(PythonParser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist_star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_star_expr(PythonParser.Testlist_star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#augassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAugassign(PythonParser.AugassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#print_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stmt(PythonParser.Print_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#del_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel_stmt(PythonParser.Del_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#pass_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPass_stmt(PythonParser.Pass_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#flow_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow_stmt(PythonParser.Flow_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#break_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_stmt(PythonParser.Break_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#continue_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_stmt(PythonParser.Continue_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(PythonParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#yield_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_stmt(PythonParser.Yield_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#raise_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRaise_stmt(PythonParser.Raise_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(PythonParser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_name(PythonParser.Import_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_from(PythonParser.Import_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(PythonParser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_name(PythonParser.Dotted_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(PythonParser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_as_names(PythonParser.Dotted_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dotted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotted_name(PythonParser.Dotted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#global_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_stmt(PythonParser.Global_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#nonlocal_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonlocal_stmt(PythonParser.Nonlocal_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#exec_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_stmt(PythonParser.Exec_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#assert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssert_stmt(PythonParser.Assert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(PythonParser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#async_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsync_stmt(PythonParser.Async_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(PythonParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(PythonParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(PythonParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#try_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTry_stmt(PythonParser.Try_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#with_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_stmt(PythonParser.With_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#with_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith_item(PythonParser.With_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#except_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExcept_clause(PythonParser.Except_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(PythonParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(PythonParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#test_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_nocond(PythonParser.Test_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#lambdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef(PythonParser.LambdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#lambdef_nocond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdef_nocond(PythonParser.Lambdef_nocondContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_test(PythonParser.Or_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_test(PythonParser.And_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#not_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_test(PythonParser.Not_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(PythonParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(PythonParser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#star_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_expr(PythonParser.Star_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PythonParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#xor_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor_expr(PythonParser.Xor_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#and_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(PythonParser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#shift_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_expr(PythonParser.Shift_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#arith_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArith_expr(PythonParser.Arith_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(PythonParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(PythonParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(PythonParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(PythonParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist_comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist_comp(PythonParser.Testlist_compContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#trailer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailer(PythonParser.TrailerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#subscriptlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptlist(PythonParser.SubscriptlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#subscript}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(PythonParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#sliceop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceop(PythonParser.SliceopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#exprlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprlist(PythonParser.ExprlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#testlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestlist(PythonParser.TestlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#dictorsetmaker}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictorsetmaker(PythonParser.DictorsetmakerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(PythonParser.ClassdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(PythonParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(PythonParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_iter(PythonParser.Comp_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_for(PythonParser.Comp_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#comp_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_if(PythonParser.Comp_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#yield_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_expr(PythonParser.Yield_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#yield_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYield_arg(PythonParser.Yield_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(PythonParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PythonParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(PythonParser.IntegerContext ctx);
}