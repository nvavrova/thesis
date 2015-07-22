// Generated from D:/intellij_projects/thesis/main/grammar\Python3.g4 by ANTLR 4.5
package gen;

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Python3Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DEF=1, RETURN=2, RAISE=3, FROM=4, IMPORT=5, AS=6, GLOBAL=7, NONLOCAL=8, 
		ASSERT=9, IF=10, ELIF=11, ELSE=12, WHILE=13, FOR=14, IN=15, TRY=16, FINALLY=17, 
		WITH=18, EXCEPT=19, LAMBDA=20, OR=21, AND=22, NOT=23, IS=24, NONE=25, 
		TRUE=26, FALSE=27, CLASS=28, YIELD=29, DEL=30, PASS=31, CONTINUE=32, BREAK=33, 
		NEWLINE=34, NAME=35, STRING_LITERAL=36, BYTES_LITERAL=37, DECIMAL_INTEGER=38, 
		OCT_INTEGER=39, HEX_INTEGER=40, BIN_INTEGER=41, FLOAT_NUMBER=42, IMAG_NUMBER=43, 
		DOT=44, ELLIPSIS=45, STAR=46, OPEN_PAREN=47, CLOSE_PAREN=48, COMMA=49, 
		COLON=50, SEMI_COLON=51, POWER=52, ASSIGN=53, OPEN_BRACK=54, CLOSE_BRACK=55, 
		OR_OP=56, XOR=57, AND_OP=58, LEFT_SHIFT=59, RIGHT_SHIFT=60, ADD=61, MINUS=62, 
		DIV=63, MOD=64, IDIV=65, NOT_OP=66, OPEN_BRACE=67, CLOSE_BRACE=68, LESS_THAN=69, 
		GREATER_THAN=70, EQUALS=71, GT_EQ=72, LT_EQ=73, NOT_EQ_1=74, NOT_EQ_2=75, 
		AT=76, ARROW=77, ADD_ASSIGN=78, SUB_ASSIGN=79, MULT_ASSIGN=80, AT_ASSIGN=81, 
		DIV_ASSIGN=82, MOD_ASSIGN=83, AND_ASSIGN=84, OR_ASSIGN=85, XOR_ASSIGN=86, 
		LEFT_SHIFT_ASSIGN=87, RIGHT_SHIFT_ASSIGN=88, POWER_ASSIGN=89, IDIV_ASSIGN=90, 
		SKIP=91, COMMENT=92, UNKNOWN_CHAR=93, INDENT=94, DEDENT=95;
	public static final int
		RULE_single_input = 0, RULE_file_input = 1, RULE_eval_input = 2, RULE_decorator = 3, 
		RULE_decorators = 4, RULE_decorated = 5, RULE_funcdef = 6, RULE_parameters = 7, 
		RULE_typedargslist = 8, RULE_tfpdef = 9, RULE_varargslist = 10, RULE_vfpdef = 11, 
		RULE_stmt = 12, RULE_simple_stmt = 13, RULE_small_stmt = 14, RULE_expr_stmt = 15, 
		RULE_testlist_star_expr = 16, RULE_augassign = 17, RULE_del_stmt = 18, 
		RULE_pass_stmt = 19, RULE_flow_stmt = 20, RULE_break_stmt = 21, RULE_continue_stmt = 22, 
		RULE_return_stmt = 23, RULE_yield_stmt = 24, RULE_raise_stmt = 25, RULE_import_stmt = 26, 
		RULE_import_name = 27, RULE_import_from = 28, RULE_import_as_name = 29, 
		RULE_dotted_as_name = 30, RULE_import_as_names = 31, RULE_dotted_as_names = 32, 
		RULE_dotted_name = 33, RULE_global_stmt = 34, RULE_nonlocal_stmt = 35, 
		RULE_assert_stmt = 36, RULE_compound_stmt = 37, RULE_if_stmt = 38, RULE_while_stmt = 39, 
		RULE_for_stmt = 40, RULE_try_stmt = 41, RULE_with_stmt = 42, RULE_with_item = 43, 
		RULE_except_clause = 44, RULE_suite = 45, RULE_test = 46, RULE_test_nocond = 47, 
		RULE_lambdef = 48, RULE_lambdef_nocond = 49, RULE_or_test = 50, RULE_and_test = 51, 
		RULE_not_test = 52, RULE_comparison = 53, RULE_comp_op = 54, RULE_star_expr = 55, 
		RULE_expr = 56, RULE_xor_expr = 57, RULE_and_expr = 58, RULE_shift_expr = 59, 
		RULE_arith_expr = 60, RULE_term = 61, RULE_factor = 62, RULE_power = 63, 
		RULE_atom = 64, RULE_testlist_comp = 65, RULE_trailer = 66, RULE_subscriptlist = 67, 
		RULE_subscript = 68, RULE_sliceop = 69, RULE_exprlist = 70, RULE_testlist = 71, 
		RULE_dictorsetmaker = 72, RULE_classdef = 73, RULE_arglist = 74, RULE_argument = 75, 
		RULE_comp_iter = 76, RULE_comp_for = 77, RULE_comp_if = 78, RULE_yield_expr = 79, 
		RULE_yield_arg = 80, RULE_string = 81, RULE_number = 82, RULE_integer = 83;
	public static final String[] ruleNames = {
		"single_input", "file_input", "eval_input", "decorator", "decorators", 
		"decorated", "funcdef", "parameters", "typedargslist", "tfpdef", "varargslist", 
		"vfpdef", "stmt", "simple_stmt", "small_stmt", "expr_stmt", "testlist_star_expr", 
		"augassign", "del_stmt", "pass_stmt", "flow_stmt", "break_stmt", "continue_stmt", 
		"return_stmt", "yield_stmt", "raise_stmt", "import_stmt", "import_name", 
		"import_from", "import_as_name", "dotted_as_name", "import_as_names", 
		"dotted_as_names", "dotted_name", "global_stmt", "nonlocal_stmt", "assert_stmt", 
		"compound_stmt", "if_stmt", "while_stmt", "for_stmt", "try_stmt", "with_stmt", 
		"with_item", "except_clause", "suite", "test", "test_nocond", "lambdef", 
		"lambdef_nocond", "or_test", "and_test", "not_test", "comparison", "comp_op", 
		"star_expr", "expr", "xor_expr", "and_expr", "shift_expr", "arith_expr", 
		"term", "factor", "power", "atom", "testlist_comp", "trailer", "subscriptlist", 
		"subscript", "sliceop", "exprlist", "testlist", "dictorsetmaker", "classdef", 
		"arglist", "argument", "comp_iter", "comp_for", "comp_if", "yield_expr", 
		"yield_arg", "string", "number", "integer"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'def'", "'return'", "'raise'", "'from'", "'import'", "'as'", "'global'", 
		"'nonlocal'", "'assert'", "'if'", "'elif'", "'else'", "'while'", "'for'", 
		"'in'", "'try'", "'finally'", "'with'", "'except'", "'lambda'", "'or'", 
		"'and'", "'not'", "'is'", "'None'", "'True'", "'False'", "'class'", "'yield'", 
		"'del'", "'pass'", "'continue'", "'break'", null, null, null, null, null, 
		null, null, null, null, null, "'.'", "'...'", "'*'", "'('", "')'", "','", 
		"':'", "';'", "'**'", "'='", "'['", "']'", "'|'", "'^'", "'&'", "'<<'", 
		"'>>'", "'+'", "'-'", "'/'", "'%'", "'//'", "'~'", "'{'", "'}'", "'<'", 
		"'>'", "'=='", "'>='", "'<='", "'<>'", "'!='", "'@'", "'->'", "'+='", 
		"'-='", "'*='", "'@='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", 
		"'>>='", "'**='", "'//='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "DEF", "RETURN", "RAISE", "FROM", "IMPORT", "AS", "GLOBAL", "NONLOCAL", 
		"ASSERT", "IF", "ELIF", "ELSE", "WHILE", "FOR", "IN", "TRY", "FINALLY", 
		"WITH", "EXCEPT", "LAMBDA", "OR", "AND", "NOT", "IS", "NONE", "TRUE", 
		"FALSE", "CLASS", "YIELD", "DEL", "PASS", "CONTINUE", "BREAK", "NEWLINE", 
		"NAME", "STRING_LITERAL", "BYTES_LITERAL", "DECIMAL_INTEGER", "OCT_INTEGER", 
		"HEX_INTEGER", "BIN_INTEGER", "FLOAT_NUMBER", "IMAG_NUMBER", "DOT", "ELLIPSIS", 
		"STAR", "OPEN_PAREN", "CLOSE_PAREN", "COMMA", "COLON", "SEMI_COLON", "POWER", 
		"ASSIGN", "OPEN_BRACK", "CLOSE_BRACK", "OR_OP", "XOR", "AND_OP", "LEFT_SHIFT", 
		"RIGHT_SHIFT", "ADD", "MINUS", "DIV", "MOD", "IDIV", "NOT_OP", "OPEN_BRACE", 
		"CLOSE_BRACE", "LESS_THAN", "GREATER_THAN", "EQUALS", "GT_EQ", "LT_EQ", 
		"NOT_EQ_1", "NOT_EQ_2", "AT", "ARROW", "ADD_ASSIGN", "SUB_ASSIGN", "MULT_ASSIGN", 
		"AT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
		"LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", "POWER_ASSIGN", "IDIV_ASSIGN", 
		"SKIP", "COMMENT", "UNKNOWN_CHAR", "INDENT", "DEDENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Python3.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Python3Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Single_inputContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Single_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_input; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSingle_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_inputContext single_input() throws RecognitionException {
		Single_inputContext _localctx = new Single_inputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_single_input);
		try {
			setState(173);
			switch (_input.LA(1)) {
			case NEWLINE:
				enterOuterAlt(_localctx, 1);
				{
				setState(168); 
				match(NEWLINE);
				}
				break;
			case RETURN:
			case RAISE:
			case FROM:
			case IMPORT:
			case GLOBAL:
			case NONLOCAL:
			case ASSERT:
			case LAMBDA:
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case YIELD:
			case DEL:
			case PASS:
			case CONTINUE:
			case BREAK:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(169); 
				simple_stmt();
				}
				break;
			case DEF:
			case IF:
			case WHILE:
			case FOR:
			case TRY:
			case WITH:
			case CLASS:
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(170); 
				compound_stmt();
				setState(171); 
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class File_inputContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Python3Parser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(Python3Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Python3Parser.NEWLINE, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public File_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_input; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitFile_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final File_inputContext file_input() throws RecognitionException {
		File_inputContext _localctx = new File_inputContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_file_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEF) | (1L << RETURN) | (1L << RAISE) | (1L << FROM) | (1L << IMPORT) | (1L << GLOBAL) | (1L << NONLOCAL) | (1L << ASSERT) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << TRY) | (1L << WITH) | (1L << LAMBDA) | (1L << NOT) | (1L << NONE) | (1L << TRUE) | (1L << FALSE) | (1L << CLASS) | (1L << YIELD) | (1L << DEL) | (1L << PASS) | (1L << CONTINUE) | (1L << BREAK) | (1L << NEWLINE) | (1L << NAME) | (1L << STRING_LITERAL) | (1L << BYTES_LITERAL) | (1L << DECIMAL_INTEGER) | (1L << OCT_INTEGER) | (1L << HEX_INTEGER) | (1L << BIN_INTEGER) | (1L << FLOAT_NUMBER) | (1L << IMAG_NUMBER) | (1L << ELLIPSIS) | (1L << STAR) | (1L << OPEN_PAREN) | (1L << OPEN_BRACK) | (1L << ADD) | (1L << MINUS))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NOT_OP - 66)) | (1L << (OPEN_BRACE - 66)) | (1L << (AT - 66)))) != 0)) {
				{
				setState(177);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(175); 
					match(NEWLINE);
					}
					break;
				case DEF:
				case RETURN:
				case RAISE:
				case FROM:
				case IMPORT:
				case GLOBAL:
				case NONLOCAL:
				case ASSERT:
				case IF:
				case WHILE:
				case FOR:
				case TRY:
				case WITH:
				case LAMBDA:
				case NOT:
				case NONE:
				case TRUE:
				case FALSE:
				case CLASS:
				case YIELD:
				case DEL:
				case PASS:
				case CONTINUE:
				case BREAK:
				case NAME:
				case STRING_LITERAL:
				case BYTES_LITERAL:
				case DECIMAL_INTEGER:
				case OCT_INTEGER:
				case HEX_INTEGER:
				case BIN_INTEGER:
				case FLOAT_NUMBER:
				case IMAG_NUMBER:
				case ELLIPSIS:
				case STAR:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
				case AT:
					{
					setState(176); 
					stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(182); 
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Eval_inputContext extends ParserRuleContext {
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Python3Parser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(Python3Parser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(Python3Parser.NEWLINE, i);
		}
		public Eval_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eval_input; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitEval_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Eval_inputContext eval_input() throws RecognitionException {
		Eval_inputContext _localctx = new Eval_inputContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_eval_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184); 
			testlist();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(185); 
				match(NEWLINE);
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191); 
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratorContext extends ParserRuleContext {
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public DecoratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDecorator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorContext decorator() throws RecognitionException {
		DecoratorContext _localctx = new DecoratorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decorator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); 
			match(AT);
			setState(194); 
			dotted_name();
			setState(200);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(195); 
				match(OPEN_PAREN);
				setState(197);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (POWER - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(196); 
					arglist();
					}
				}

				setState(199); 
				match(CLOSE_PAREN);
				}
			}

			setState(202); 
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratorsContext extends ParserRuleContext {
		public List<DecoratorContext> decorator() {
			return getRuleContexts(DecoratorContext.class);
		}
		public DecoratorContext decorator(int i) {
			return getRuleContext(DecoratorContext.class,i);
		}
		public DecoratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorators; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDecorators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorsContext decorators() throws RecognitionException {
		DecoratorsContext _localctx = new DecoratorsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decorators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(204); 
				decorator();
				}
				}
				setState(207); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==AT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecoratedContext extends ParserRuleContext {
		public DecoratorsContext decorators() {
			return getRuleContext(DecoratorsContext.class,0);
		}
		public ClassdefContext classdef() {
			return getRuleContext(ClassdefContext.class,0);
		}
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public DecoratedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorated; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDecorated(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratedContext decorated() throws RecognitionException {
		DecoratedContext _localctx = new DecoratedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_decorated);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); 
			decorators();
			setState(212);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(210); 
				classdef();
				}
				break;
			case DEF:
				{
				setState(211); 
				funcdef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(Python3Parser.DEF, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitFuncdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214); 
			match(DEF);
			setState(215); 
			match(NAME);
			setState(216); 
			parameters();
			setState(219);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(217); 
				match(ARROW);
				setState(218); 
				test();
				}
			}

			setState(221); 
			match(COLON);
			setState(222); 
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public TypedargslistContext typedargslist() {
			return getRuleContext(TypedargslistContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); 
			match(OPEN_PAREN);
			setState(226);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAME) | (1L << STAR) | (1L << POWER))) != 0)) {
				{
				setState(225); 
				typedargslist();
				}
			}

			setState(228); 
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedargslistContext extends ParserRuleContext {
		public Map<TfpdefContext, TestContext> positional;
		public Map<TfpdefContext, TestContext> args;
		public Map<TfpdefContext, TestContext> kwargs;
		public TfpdefContext a;
		public TestContext aVal;
		public TfpdefContext b;
		public TestContext bVal;
		public TfpdefContext c;
		public TfpdefContext d;
		public TestContext dVal;
		public TfpdefContext e;
		public TfpdefContext f;
		public TfpdefContext g;
		public TfpdefContext h;
		public TestContext hVal;
		public TfpdefContext i;
		public TfpdefContext j;
		public List<TfpdefContext> tfpdef() {
			return getRuleContexts(TfpdefContext.class);
		}
		public TfpdefContext tfpdef(int i) {
			return getRuleContext(TfpdefContext.class,i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TypedargslistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedargslist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTypedargslist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedargslistContext typedargslist() throws RecognitionException {
		TypedargslistContext _localctx = new TypedargslistContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typedargslist);

		     ((TypedargslistContext)_localctx).positional =  new HashMap<>();
		     ((TypedargslistContext)_localctx).args =  new HashMap<>();
		     ((TypedargslistContext)_localctx).kwargs =  new HashMap<>();
		 
		int _la;
		try {
			int _alt;
			setState(317);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(230); 
				((TypedargslistContext)_localctx).a = tfpdef();
				 _localctx.positional.put(((TypedargslistContext)_localctx).a, null); 
				setState(236);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(232); 
					match(ASSIGN);
					setState(233); 
					((TypedargslistContext)_localctx).aVal = test();
					 _localctx.positional.put(((TypedargslistContext)_localctx).a, ((TypedargslistContext)_localctx).aVal); 
					}
				}

				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(238); 
						match(COMMA);
						setState(239); 
						((TypedargslistContext)_localctx).b = tfpdef();
						 _localctx.positional.put(((TypedargslistContext)_localctx).b, null); 
						setState(245);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(241); 
							match(ASSIGN);
							setState(242); 
							((TypedargslistContext)_localctx).bVal = test();
							 _localctx.positional.put(((TypedargslistContext)_localctx).b, ((TypedargslistContext)_localctx).bVal); 
							}
						}

						}
						} 
					}
					setState(251);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(285);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(252); 
					match(COMMA);
					setState(283);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(253); 
						match(STAR);
						setState(255);
						_la = _input.LA(1);
						if (_la==NAME) {
							{
							setState(254); 
							((TypedargslistContext)_localctx).c = tfpdef();
							}
						}

						 _localctx.args.put(((TypedargslistContext)_localctx).c, null); 
						setState(269);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(258); 
								match(COMMA);
								setState(259); 
								((TypedargslistContext)_localctx).d = tfpdef();
								 _localctx.positional.put(((TypedargslistContext)_localctx).d, null); 
								setState(265);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(261); 
									match(ASSIGN);
									setState(262); 
									((TypedargslistContext)_localctx).dVal = test();
									 _localctx.positional.put(((TypedargslistContext)_localctx).d, ((TypedargslistContext)_localctx).dVal); 
									}
								}

								}
								} 
							}
							setState(271);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
						}
						setState(277);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(272); 
							match(COMMA);
							setState(273); 
							match(POWER);
							setState(274); 
							((TypedargslistContext)_localctx).e = tfpdef();
							 _localctx.kwargs.put(((TypedargslistContext)_localctx).e, null); 
							}
						}

						}
						break;
					case POWER:
						{
						setState(279); 
						match(POWER);
						setState(280); 
						((TypedargslistContext)_localctx).f = tfpdef();
						 _localctx.kwargs.put(((TypedargslistContext)_localctx).f, null); 
						}
						break;
					case CLOSE_PAREN:
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
				break;
			case STAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(287); 
				match(STAR);
				setState(289);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(288); 
					((TypedargslistContext)_localctx).g = tfpdef();
					}
				}

				 _localctx.args.put(((TypedargslistContext)_localctx).g, null); 
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(292); 
						match(COMMA);
						setState(293); 
						((TypedargslistContext)_localctx).h = tfpdef();
						 _localctx.positional.put(((TypedargslistContext)_localctx).h, null); 
						setState(299);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(295); 
							match(ASSIGN);
							setState(296); 
							((TypedargslistContext)_localctx).hVal = test();
							 _localctx.positional.put(((TypedargslistContext)_localctx).h, ((TypedargslistContext)_localctx).hVal); 
							}
						}

						}
						} 
					}
					setState(305);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(311);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(306); 
					match(COMMA);
					setState(307); 
					match(POWER);
					setState(308); 
					((TypedargslistContext)_localctx).i = tfpdef();
					 _localctx.kwargs.put(((TypedargslistContext)_localctx).i, null); 
					}
				}

				}
				break;
			case POWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(313); 
				match(POWER);
				setState(314); 
				((TypedargslistContext)_localctx).j = tfpdef();
				 _localctx.kwargs.put(((TypedargslistContext)_localctx).j, null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TfpdefContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TfpdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tfpdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTfpdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TfpdefContext tfpdef() throws RecognitionException {
		TfpdefContext _localctx = new TfpdefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tfpdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319); 
			match(NAME);
			setState(322);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(320); 
				match(COLON);
				setState(321); 
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarargslistContext extends ParserRuleContext {
		public Map<VfpdefContext, TestContext> positional;
		public Map<VfpdefContext, TestContext> args;
		public Map<VfpdefContext, TestContext> kwargs;
		public VfpdefContext a;
		public TestContext aVal;
		public VfpdefContext b;
		public TestContext bVal;
		public VfpdefContext c;
		public VfpdefContext d;
		public TestContext dVal;
		public VfpdefContext e;
		public VfpdefContext f;
		public VfpdefContext g;
		public VfpdefContext h;
		public TestContext hVal;
		public VfpdefContext i;
		public VfpdefContext j;
		public List<VfpdefContext> vfpdef() {
			return getRuleContexts(VfpdefContext.class);
		}
		public VfpdefContext vfpdef(int i) {
			return getRuleContext(VfpdefContext.class,i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public VarargslistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varargslist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitVarargslist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarargslistContext varargslist() throws RecognitionException {
		VarargslistContext _localctx = new VarargslistContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_varargslist);

		    ((VarargslistContext)_localctx).positional =  new HashMap<>();
		    ((VarargslistContext)_localctx).args =  new HashMap<>();
		    ((VarargslistContext)_localctx).kwargs =  new HashMap<>();

		int _la;
		try {
			int _alt;
			setState(411);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(324); 
				((VarargslistContext)_localctx).a = vfpdef();
				 _localctx.positional.put(((VarargslistContext)_localctx).a, null); 
				setState(330);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(326); 
					match(ASSIGN);
					setState(327); 
					((VarargslistContext)_localctx).aVal = test();
					 _localctx.positional.put(((VarargslistContext)_localctx).a, ((VarargslistContext)_localctx).aVal); 
					}
				}

				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(332); 
						match(COMMA);
						setState(333); 
						((VarargslistContext)_localctx).b = vfpdef();
						 _localctx.positional.put(((VarargslistContext)_localctx).b, null); 
						setState(339);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(335); 
							match(ASSIGN);
							setState(336); 
							((VarargslistContext)_localctx).bVal = test();
							 _localctx.positional.put(((VarargslistContext)_localctx).b, ((VarargslistContext)_localctx).bVal); 
							}
						}

						}
						} 
					}
					setState(345);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				setState(379);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(346); 
					match(COMMA);
					setState(377);
					switch (_input.LA(1)) {
					case STAR:
						{
						setState(347); 
						match(STAR);
						setState(349);
						_la = _input.LA(1);
						if (_la==NAME) {
							{
							setState(348); 
							((VarargslistContext)_localctx).c = vfpdef();
							}
						}

						 _localctx.args.put(((VarargslistContext)_localctx).c, null); 
						setState(363);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(352); 
								match(COMMA);
								setState(353); 
								((VarargslistContext)_localctx).d = vfpdef();
								 _localctx.positional.put(((VarargslistContext)_localctx).d, null); 
								setState(359);
								_la = _input.LA(1);
								if (_la==ASSIGN) {
									{
									setState(355); 
									match(ASSIGN);
									setState(356); 
									((VarargslistContext)_localctx).dVal = test();
									 _localctx.positional.put(((VarargslistContext)_localctx).d, ((VarargslistContext)_localctx).dVal); 
									}
								}

								}
								} 
							}
							setState(365);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
						}
						setState(371);
						_la = _input.LA(1);
						if (_la==COMMA) {
							{
							setState(366); 
							match(COMMA);
							setState(367); 
							match(POWER);
							setState(368); 
							((VarargslistContext)_localctx).e = vfpdef();
							 _localctx.kwargs.put(((VarargslistContext)_localctx).e, null); 
							}
						}

						}
						break;
					case POWER:
						{
						setState(373); 
						match(POWER);
						setState(374); 
						((VarargslistContext)_localctx).f = vfpdef();
						 _localctx.kwargs.put(((VarargslistContext)_localctx).f, null); 
						}
						break;
					case COLON:
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
				}

				}
				break;
			case STAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(381); 
				match(STAR);
				setState(383);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(382); 
					((VarargslistContext)_localctx).g = vfpdef();
					}
				}

				 _localctx.args.put(((VarargslistContext)_localctx).g, null); 
				setState(397);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(386); 
						match(COMMA);
						setState(387); 
						((VarargslistContext)_localctx).h = vfpdef();
						 _localctx.positional.put(((VarargslistContext)_localctx).h, null); 
						setState(393);
						_la = _input.LA(1);
						if (_la==ASSIGN) {
							{
							setState(389); 
							match(ASSIGN);
							setState(390); 
							((VarargslistContext)_localctx).hVal = test();
							 _localctx.positional.put(((VarargslistContext)_localctx).h, ((VarargslistContext)_localctx).hVal); 
							}
						}

						}
						} 
					}
					setState(399);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
				}
				setState(405);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(400); 
					match(COMMA);
					setState(401); 
					match(POWER);
					setState(402); 
					((VarargslistContext)_localctx).i = vfpdef();
					 _localctx.kwargs.put(((VarargslistContext)_localctx).i, null); 
					}
				}

				}
				break;
			case POWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(407); 
				match(POWER);
				setState(408); 
				((VarargslistContext)_localctx).j = vfpdef();
				 _localctx.kwargs.put(((VarargslistContext)_localctx).j, null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VfpdefContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public VfpdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vfpdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitVfpdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VfpdefContext vfpdef() throws RecognitionException {
		VfpdefContext _localctx = new VfpdefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_vfpdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413); 
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmt);
		try {
			setState(417);
			switch (_input.LA(1)) {
			case RETURN:
			case RAISE:
			case FROM:
			case IMPORT:
			case GLOBAL:
			case NONLOCAL:
			case ASSERT:
			case LAMBDA:
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case YIELD:
			case DEL:
			case PASS:
			case CONTINUE:
			case BREAK:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(415); 
				simple_stmt();
				}
				break;
			case DEF:
			case IF:
			case WHILE:
			case FOR:
			case TRY:
			case WITH:
			case CLASS:
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(416); 
				compound_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_stmtContext extends ParserRuleContext {
		public List<Small_stmtContext> small_stmt() {
			return getRuleContexts(Small_stmtContext.class);
		}
		public Small_stmtContext small_stmt(int i) {
			return getRuleContext(Small_stmtContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSimple_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_simple_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(419); 
			small_stmt();
			setState(424);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(420); 
					match(SEMI_COLON);
					setState(421); 
					small_stmt();
					}
					} 
				}
				setState(426);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			setState(428);
			_la = _input.LA(1);
			if (_la==SEMI_COLON) {
				{
				setState(427); 
				match(SEMI_COLON);
				}
			}

			setState(430); 
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Small_stmtContext extends ParserRuleContext {
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Del_stmtContext del_stmt() {
			return getRuleContext(Del_stmtContext.class,0);
		}
		public Pass_stmtContext pass_stmt() {
			return getRuleContext(Pass_stmtContext.class,0);
		}
		public Flow_stmtContext flow_stmt() {
			return getRuleContext(Flow_stmtContext.class,0);
		}
		public Import_stmtContext import_stmt() {
			return getRuleContext(Import_stmtContext.class,0);
		}
		public Global_stmtContext global_stmt() {
			return getRuleContext(Global_stmtContext.class,0);
		}
		public Nonlocal_stmtContext nonlocal_stmt() {
			return getRuleContext(Nonlocal_stmtContext.class,0);
		}
		public Assert_stmtContext assert_stmt() {
			return getRuleContext(Assert_stmtContext.class,0);
		}
		public Small_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_small_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSmall_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Small_stmtContext small_stmt() throws RecognitionException {
		Small_stmtContext _localctx = new Small_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_small_stmt);
		try {
			setState(440);
			switch (_input.LA(1)) {
			case LAMBDA:
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(432); 
				expr_stmt();
				}
				break;
			case DEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(433); 
				del_stmt();
				}
				break;
			case PASS:
				enterOuterAlt(_localctx, 3);
				{
				setState(434); 
				pass_stmt();
				}
				break;
			case RETURN:
			case RAISE:
			case YIELD:
			case CONTINUE:
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(435); 
				flow_stmt();
				}
				break;
			case FROM:
			case IMPORT:
				enterOuterAlt(_localctx, 5);
				{
				setState(436); 
				import_stmt();
				}
				break;
			case GLOBAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(437); 
				global_stmt();
				}
				break;
			case NONLOCAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(438); 
				nonlocal_stmt();
				}
				break;
			case ASSERT:
				enterOuterAlt(_localctx, 8);
				{
				setState(439); 
				assert_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_stmtContext extends ParserRuleContext {
		public Testlist_star_exprContext target;
		public Yield_exprContext assignYield;
		public TestlistContext assignTest;
		public Testlist_star_exprContext assignTestStarredList;
		public List<Testlist_star_exprContext> testlist_star_expr() {
			return getRuleContexts(Testlist_star_exprContext.class);
		}
		public Testlist_star_exprContext testlist_star_expr(int i) {
			return getRuleContext(Testlist_star_exprContext.class,i);
		}
		public AugassignContext augassign() {
			return getRuleContext(AugassignContext.class,0);
		}
		public List<Yield_exprContext> yield_expr() {
			return getRuleContexts(Yield_exprContext.class);
		}
		public Yield_exprContext yield_expr(int i) {
			return getRuleContext(Yield_exprContext.class,i);
		}
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitExpr_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442); 
			((Expr_stmtContext)_localctx).target = testlist_star_expr();
			setState(458);
			switch (_input.LA(1)) {
			case ADD_ASSIGN:
			case SUB_ASSIGN:
			case MULT_ASSIGN:
			case AT_ASSIGN:
			case DIV_ASSIGN:
			case MOD_ASSIGN:
			case AND_ASSIGN:
			case OR_ASSIGN:
			case XOR_ASSIGN:
			case LEFT_SHIFT_ASSIGN:
			case RIGHT_SHIFT_ASSIGN:
			case POWER_ASSIGN:
			case IDIV_ASSIGN:
				{
				setState(443); 
				augassign();
				setState(446);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(444); 
					((Expr_stmtContext)_localctx).assignYield = yield_expr();
					}
					break;
				case LAMBDA:
				case NOT:
				case NONE:
				case TRUE:
				case FALSE:
				case NAME:
				case STRING_LITERAL:
				case BYTES_LITERAL:
				case DECIMAL_INTEGER:
				case OCT_INTEGER:
				case HEX_INTEGER:
				case BIN_INTEGER:
				case FLOAT_NUMBER:
				case IMAG_NUMBER:
				case ELLIPSIS:
				case STAR:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
					{
					setState(445); 
					((Expr_stmtContext)_localctx).assignTest = testlist();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case NEWLINE:
			case SEMI_COLON:
			case ASSIGN:
				{
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ASSIGN) {
					{
					{
					setState(448); 
					match(ASSIGN);
					setState(451);
					switch (_input.LA(1)) {
					case YIELD:
						{
						setState(449); 
						((Expr_stmtContext)_localctx).assignYield = yield_expr();
						}
						break;
					case LAMBDA:
					case NOT:
					case NONE:
					case TRUE:
					case FALSE:
					case NAME:
					case STRING_LITERAL:
					case BYTES_LITERAL:
					case DECIMAL_INTEGER:
					case OCT_INTEGER:
					case HEX_INTEGER:
					case BIN_INTEGER:
					case FLOAT_NUMBER:
					case IMAG_NUMBER:
					case ELLIPSIS:
					case STAR:
					case OPEN_PAREN:
					case OPEN_BRACK:
					case ADD:
					case MINUS:
					case NOT_OP:
					case OPEN_BRACE:
						{
						setState(450); 
						((Expr_stmtContext)_localctx).assignTestStarredList = testlist_star_expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					}
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Testlist_star_exprContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public Testlist_star_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist_star_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTestlist_star_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Testlist_star_exprContext testlist_star_expr() throws RecognitionException {
		Testlist_star_exprContext _localctx = new Testlist_star_exprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_testlist_star_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(460); 
				test();
				}
				break;
			case 2:
				{
				setState(461); 
				star_expr();
				}
				break;
			}
			setState(471);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(464); 
					match(COMMA);
					setState(467);
					switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
					case 1:
						{
						setState(465); 
						test();
						}
						break;
					case 2:
						{
						setState(466); 
						star_expr();
						}
						break;
					}
					}
					} 
				}
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(475);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(474); 
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AugassignContext extends ParserRuleContext {
		public Token op;
		public AugassignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_augassign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitAugassign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AugassignContext augassign() throws RecognitionException {
		AugassignContext _localctx = new AugassignContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_augassign);
		try {
			setState(490);
			switch (_input.LA(1)) {
			case ADD_ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(477); 
				((AugassignContext)_localctx).op = match(ADD_ASSIGN);
				}
				break;
			case SUB_ASSIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(478); 
				((AugassignContext)_localctx).op = match(SUB_ASSIGN);
				}
				break;
			case MULT_ASSIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(479); 
				((AugassignContext)_localctx).op = match(MULT_ASSIGN);
				}
				break;
			case AT_ASSIGN:
				enterOuterAlt(_localctx, 4);
				{
				setState(480); 
				((AugassignContext)_localctx).op = match(AT_ASSIGN);
				}
				break;
			case DIV_ASSIGN:
				enterOuterAlt(_localctx, 5);
				{
				setState(481); 
				((AugassignContext)_localctx).op = match(DIV_ASSIGN);
				}
				break;
			case MOD_ASSIGN:
				enterOuterAlt(_localctx, 6);
				{
				setState(482); 
				((AugassignContext)_localctx).op = match(MOD_ASSIGN);
				}
				break;
			case AND_ASSIGN:
				enterOuterAlt(_localctx, 7);
				{
				setState(483); 
				((AugassignContext)_localctx).op = match(AND_ASSIGN);
				}
				break;
			case OR_ASSIGN:
				enterOuterAlt(_localctx, 8);
				{
				setState(484); 
				((AugassignContext)_localctx).op = match(OR_ASSIGN);
				}
				break;
			case XOR_ASSIGN:
				enterOuterAlt(_localctx, 9);
				{
				setState(485); 
				((AugassignContext)_localctx).op = match(XOR_ASSIGN);
				}
				break;
			case LEFT_SHIFT_ASSIGN:
				enterOuterAlt(_localctx, 10);
				{
				setState(486); 
				((AugassignContext)_localctx).op = match(LEFT_SHIFT_ASSIGN);
				}
				break;
			case RIGHT_SHIFT_ASSIGN:
				enterOuterAlt(_localctx, 11);
				{
				setState(487); 
				((AugassignContext)_localctx).op = match(RIGHT_SHIFT_ASSIGN);
				}
				break;
			case POWER_ASSIGN:
				enterOuterAlt(_localctx, 12);
				{
				setState(488); 
				((AugassignContext)_localctx).op = match(POWER_ASSIGN);
				}
				break;
			case IDIV_ASSIGN:
				enterOuterAlt(_localctx, 13);
				{
				setState(489); 
				((AugassignContext)_localctx).op = match(IDIV_ASSIGN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Del_stmtContext extends ParserRuleContext {
		public TerminalNode DEL() { return getToken(Python3Parser.DEL, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public Del_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_del_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDel_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Del_stmtContext del_stmt() throws RecognitionException {
		Del_stmtContext _localctx = new Del_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_del_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492); 
			match(DEL);
			setState(493); 
			exprlist();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pass_stmtContext extends ParserRuleContext {
		public TerminalNode PASS() { return getToken(Python3Parser.PASS, 0); }
		public Pass_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pass_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitPass_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pass_stmtContext pass_stmt() throws RecognitionException {
		Pass_stmtContext _localctx = new Pass_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_pass_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495); 
			match(PASS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Flow_stmtContext extends ParserRuleContext {
		public Break_stmtContext break_stmt() {
			return getRuleContext(Break_stmtContext.class,0);
		}
		public Continue_stmtContext continue_stmt() {
			return getRuleContext(Continue_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Raise_stmtContext raise_stmt() {
			return getRuleContext(Raise_stmtContext.class,0);
		}
		public Yield_stmtContext yield_stmt() {
			return getRuleContext(Yield_stmtContext.class,0);
		}
		public Flow_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitFlow_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Flow_stmtContext flow_stmt() throws RecognitionException {
		Flow_stmtContext _localctx = new Flow_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_flow_stmt);
		try {
			setState(502);
			switch (_input.LA(1)) {
			case BREAK:
				enterOuterAlt(_localctx, 1);
				{
				setState(497); 
				break_stmt();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(498); 
				continue_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(499); 
				return_stmt();
				}
				break;
			case RAISE:
				enterOuterAlt(_localctx, 4);
				{
				setState(500); 
				raise_stmt();
				}
				break;
			case YIELD:
				enterOuterAlt(_localctx, 5);
				{
				setState(501); 
				yield_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Break_stmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(Python3Parser.BREAK, 0); }
		public Break_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitBreak_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Break_stmtContext break_stmt() throws RecognitionException {
		Break_stmtContext _localctx = new Break_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_break_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504); 
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Continue_stmtContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(Python3Parser.CONTINUE, 0); }
		public Continue_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitContinue_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continue_stmtContext continue_stmt() throws RecognitionException {
		Continue_stmtContext _localctx = new Continue_stmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_continue_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506); 
			match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(Python3Parser.RETURN, 0); }
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_return_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508); 
			match(RETURN);
			setState(510);
			_la = _input.LA(1);
			if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
				{
				setState(509); 
				testlist();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Yield_stmtContext extends ParserRuleContext {
		public Yield_exprContext yield_expr() {
			return getRuleContext(Yield_exprContext.class,0);
		}
		public Yield_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitYield_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Yield_stmtContext yield_stmt() throws RecognitionException {
		Yield_stmtContext _localctx = new Yield_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_yield_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512); 
			yield_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Raise_stmtContext extends ParserRuleContext {
		public TestContext except;
		public TestContext source;
		public TerminalNode RAISE() { return getToken(Python3Parser.RAISE, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public Raise_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitRaise_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Raise_stmtContext raise_stmt() throws RecognitionException {
		Raise_stmtContext _localctx = new Raise_stmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_raise_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); 
			match(RAISE);
			setState(520);
			_la = _input.LA(1);
			if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
				{
				setState(515); 
				((Raise_stmtContext)_localctx).except = test();
				setState(518);
				_la = _input.LA(1);
				if (_la==FROM) {
					{
					setState(516); 
					match(FROM);
					setState(517); 
					((Raise_stmtContext)_localctx).source = test();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_stmtContext extends ParserRuleContext {
		public Import_nameContext import_name() {
			return getRuleContext(Import_nameContext.class,0);
		}
		public Import_fromContext import_from() {
			return getRuleContext(Import_fromContext.class,0);
		}
		public Import_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitImport_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_stmtContext import_stmt() throws RecognitionException {
		Import_stmtContext _localctx = new Import_stmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_import_stmt);
		try {
			setState(524);
			switch (_input.LA(1)) {
			case IMPORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(522); 
				import_name();
				}
				break;
			case FROM:
				enterOuterAlt(_localctx, 2);
				{
				setState(523); 
				import_from();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_nameContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(Python3Parser.IMPORT, 0); }
		public Dotted_as_namesContext dotted_as_names() {
			return getRuleContext(Dotted_as_namesContext.class,0);
		}
		public Import_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitImport_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_nameContext import_name() throws RecognitionException {
		Import_nameContext _localctx = new Import_nameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_import_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526); 
			match(IMPORT);
			setState(527); 
			dotted_as_names();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_fromContext extends ParserRuleContext {
		public List<String> prefixes;
		public Token prefix;
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public TerminalNode IMPORT() { return getToken(Python3Parser.IMPORT, 0); }
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public Import_as_namesContext import_as_names() {
			return getRuleContext(Import_as_namesContext.class,0);
		}
		public Import_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_from; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitImport_from(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_fromContext import_from() throws RecognitionException {
		Import_fromContext _localctx = new Import_fromContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_import_from);

		    ((Import_fromContext)_localctx).prefixes =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529); 
			match(FROM);
			setState(544);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(534);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==ELLIPSIS) {
					{
					{
					setState(530);
					((Import_fromContext)_localctx).prefix = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==DOT || _la==ELLIPSIS) ) {
						((Import_fromContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
					}
					consume();
					 _localctx.prefixes.add((((Import_fromContext)_localctx).prefix!=null?((Import_fromContext)_localctx).prefix.getText():null)); 
					}
					}
					setState(536);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(537); 
				dotted_name();
				}
				break;
			case 2:
				{
				setState(540); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(538);
					((Import_fromContext)_localctx).prefix = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==DOT || _la==ELLIPSIS) ) {
						((Import_fromContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
					}
					consume();
					 _localctx.prefixes.add((((Import_fromContext)_localctx).prefix!=null?((Import_fromContext)_localctx).prefix.getText():null)); 
					}
					}
					setState(542); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DOT || _la==ELLIPSIS );
				}
				break;
			}
			setState(546); 
			match(IMPORT);
			setState(553);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(547); 
				match(STAR);
				}
				break;
			case OPEN_PAREN:
				{
				setState(548); 
				match(OPEN_PAREN);
				setState(549); 
				import_as_names();
				setState(550); 
				match(CLOSE_PAREN);
				}
				break;
			case NAME:
				{
				setState(552); 
				import_as_names();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_as_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(Python3Parser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(Python3Parser.NAME, i);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public Import_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitImport_as_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_nameContext import_as_name() throws RecognitionException {
		Import_as_nameContext _localctx = new Import_as_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_import_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(555); 
			match(NAME);
			setState(558);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(556); 
				match(AS);
				setState(557); 
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dotted_as_nameContext extends ParserRuleContext {
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public Dotted_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_as_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDotted_as_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dotted_as_nameContext dotted_as_name() throws RecognitionException {
		Dotted_as_nameContext _localctx = new Dotted_as_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_dotted_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560); 
			dotted_name();
			setState(563);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(561); 
				match(AS);
				setState(562); 
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_as_namesContext extends ParserRuleContext {
		public List<Import_as_nameContext> import_as_name() {
			return getRuleContexts(Import_as_nameContext.class);
		}
		public Import_as_nameContext import_as_name(int i) {
			return getRuleContext(Import_as_nameContext.class,i);
		}
		public Import_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_names; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitImport_as_names(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_namesContext import_as_names() throws RecognitionException {
		Import_as_namesContext _localctx = new Import_as_namesContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_import_as_names);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(565); 
			import_as_name();
			setState(570);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(566); 
					match(COMMA);
					setState(567); 
					import_as_name();
					}
					} 
				}
				setState(572);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			setState(574);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(573); 
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dotted_as_namesContext extends ParserRuleContext {
		public List<Dotted_as_nameContext> dotted_as_name() {
			return getRuleContexts(Dotted_as_nameContext.class);
		}
		public Dotted_as_nameContext dotted_as_name(int i) {
			return getRuleContext(Dotted_as_nameContext.class,i);
		}
		public Dotted_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_as_names; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDotted_as_names(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dotted_as_namesContext dotted_as_names() throws RecognitionException {
		Dotted_as_namesContext _localctx = new Dotted_as_namesContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_dotted_as_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576); 
			dotted_as_name();
			setState(581);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(577); 
				match(COMMA);
				setState(578); 
				dotted_as_name();
				}
				}
				setState(583);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dotted_nameContext extends ParserRuleContext {
		public List<String> names;
		public Token name;
		public Token otherName;
		public List<TerminalNode> NAME() { return getTokens(Python3Parser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(Python3Parser.NAME, i);
		}
		public Dotted_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDotted_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dotted_nameContext dotted_name() throws RecognitionException {
		Dotted_nameContext _localctx = new Dotted_nameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_dotted_name);

		    ((Dotted_nameContext)_localctx).names =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584); 
			((Dotted_nameContext)_localctx).name = match(NAME);
			 _localctx.names.add((((Dotted_nameContext)_localctx).name!=null?((Dotted_nameContext)_localctx).name.getText():null)); 
			setState(591);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(586); 
				match(DOT);
				setState(587); 
				((Dotted_nameContext)_localctx).otherName = match(NAME);
				 _localctx.names.add((((Dotted_nameContext)_localctx).otherName!=null?((Dotted_nameContext)_localctx).otherName.getText():null)); 
				}
				}
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Global_stmtContext extends ParserRuleContext {
		public List<String> names;
		public Token name;
		public Token otherName;
		public TerminalNode GLOBAL() { return getToken(Python3Parser.GLOBAL, 0); }
		public List<TerminalNode> NAME() { return getTokens(Python3Parser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(Python3Parser.NAME, i);
		}
		public Global_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitGlobal_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_stmtContext global_stmt() throws RecognitionException {
		Global_stmtContext _localctx = new Global_stmtContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_global_stmt);

		    ((Global_stmtContext)_localctx).names =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(594); 
			match(GLOBAL);
			setState(595); 
			((Global_stmtContext)_localctx).name = match(NAME);
			 _localctx.names.add((((Global_stmtContext)_localctx).name!=null?((Global_stmtContext)_localctx).name.getText():null)); 
			setState(602);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(597); 
				match(COMMA);
				setState(598); 
				((Global_stmtContext)_localctx).otherName = match(NAME);
				 _localctx.names.add((((Global_stmtContext)_localctx).otherName!=null?((Global_stmtContext)_localctx).otherName.getText():null)); 
				}
				}
				setState(604);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Nonlocal_stmtContext extends ParserRuleContext {
		public List<String> names;
		public Token name;
		public Token otherName;
		public TerminalNode NONLOCAL() { return getToken(Python3Parser.NONLOCAL, 0); }
		public List<TerminalNode> NAME() { return getTokens(Python3Parser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(Python3Parser.NAME, i);
		}
		public Nonlocal_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonlocal_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitNonlocal_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Nonlocal_stmtContext nonlocal_stmt() throws RecognitionException {
		Nonlocal_stmtContext _localctx = new Nonlocal_stmtContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_nonlocal_stmt);

		     ((Nonlocal_stmtContext)_localctx).names =  new ArrayList<>();
		 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605); 
			match(NONLOCAL);
			setState(606); 
			((Nonlocal_stmtContext)_localctx).name = match(NAME);
			 _localctx.names.add((((Nonlocal_stmtContext)_localctx).name!=null?((Nonlocal_stmtContext)_localctx).name.getText():null)); 
			setState(613);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(608); 
				match(COMMA);
				setState(609); 
				((Nonlocal_stmtContext)_localctx).otherName = match(NAME);
				 _localctx.names.add((((Nonlocal_stmtContext)_localctx).otherName!=null?((Nonlocal_stmtContext)_localctx).otherName.getText():null)); 
				}
				}
				setState(615);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assert_stmtContext extends ParserRuleContext {
		public TestContext assertion;
		public TestContext assertionError;
		public TerminalNode ASSERT() { return getToken(Python3Parser.ASSERT, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Assert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assert_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitAssert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assert_stmtContext assert_stmt() throws RecognitionException {
		Assert_stmtContext _localctx = new Assert_stmtContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_assert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); 
			match(ASSERT);
			setState(617); 
			((Assert_stmtContext)_localctx).assertion = test();
			setState(620);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(618); 
				match(COMMA);
				setState(619); 
				((Assert_stmtContext)_localctx).assertionError = test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_stmtContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Try_stmtContext try_stmt() {
			return getRuleContext(Try_stmtContext.class,0);
		}
		public With_stmtContext with_stmt() {
			return getRuleContext(With_stmtContext.class,0);
		}
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public ClassdefContext classdef() {
			return getRuleContext(ClassdefContext.class,0);
		}
		public DecoratedContext decorated() {
			return getRuleContext(DecoratedContext.class,0);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitCompound_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_compound_stmt);
		try {
			setState(630);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(622); 
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(623); 
				while_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(624); 
				for_stmt();
				}
				break;
			case TRY:
				enterOuterAlt(_localctx, 4);
				{
				setState(625); 
				try_stmt();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 5);
				{
				setState(626); 
				with_stmt();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 6);
				{
				setState(627); 
				funcdef();
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 7);
				{
				setState(628); 
				classdef();
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 8);
				{
				setState(629); 
				decorated();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public Map<TestContext, SuiteContext> elifVals;
		public List<TestContext> elifConditions;
		public TestContext ifTest;
		public SuiteContext ifSuite;
		public TestContext elifTest;
		public SuiteContext elifSuite;
		public SuiteContext elseSuite;
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(Python3Parser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(Python3Parser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_if_stmt);

		    ((If_stmtContext)_localctx).elifVals =  new HashMap<TestContext, SuiteContext>();
		    ((If_stmtContext)_localctx).elifConditions =  new ArrayList<TestContext>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(632); 
			match(IF);
			setState(633); 
			((If_stmtContext)_localctx).ifTest = test();
			setState(634); 
			match(COLON);
			setState(635); 
			((If_stmtContext)_localctx).ifSuite = suite();
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(636); 
				match(ELIF);
				setState(637); 
				((If_stmtContext)_localctx).elifTest = test();
				setState(638); 
				match(COLON);
				setState(639); 
				((If_stmtContext)_localctx).elifSuite = suite();
				 _localctx.elifVals.put(((If_stmtContext)_localctx).elifTest, ((If_stmtContext)_localctx).elifSuite); _localctx.elifConditions.add(((If_stmtContext)_localctx).elifTest); 
				}
				}
				setState(646);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(650);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(647); 
				match(ELSE);
				setState(648); 
				match(COLON);
				setState(649); 
				((If_stmtContext)_localctx).elseSuite = suite();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_stmtContext extends ParserRuleContext {
		public SuiteContext body;
		public SuiteContext elseBody;
		public TerminalNode WHILE() { return getToken(Python3Parser.WHILE, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitWhile_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_while_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652); 
			match(WHILE);
			setState(653); 
			test();
			setState(654); 
			match(COLON);
			setState(655); 
			((While_stmtContext)_localctx).body = suite();
			setState(659);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(656); 
				match(ELSE);
				setState(657); 
				match(COLON);
				setState(658); 
				((While_stmtContext)_localctx).elseBody = suite();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_stmtContext extends ParserRuleContext {
		public SuiteContext body;
		public SuiteContext elseBody;
		public TerminalNode FOR() { return getToken(Python3Parser.FOR, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_for_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(661); 
			match(FOR);
			setState(662); 
			exprlist();
			setState(663); 
			match(IN);
			setState(664); 
			testlist();
			setState(665); 
			match(COLON);
			setState(666); 
			((For_stmtContext)_localctx).body = suite();
			setState(670);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(667); 
				match(ELSE);
				setState(668); 
				match(COLON);
				setState(669); 
				((For_stmtContext)_localctx).elseBody = suite();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Try_stmtContext extends ParserRuleContext {
		public Map<Except_clauseContext, SuiteContext> exceptBlocks;
		public List<Except_clauseContext> exceptions;
		public SuiteContext tryBlock;
		public Except_clauseContext exKey;
		public SuiteContext exVal;
		public SuiteContext elseBlock;
		public SuiteContext finallyBlock;
		public TerminalNode TRY() { return getToken(Python3Parser.TRY, 0); }
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public TerminalNode FINALLY() { return getToken(Python3Parser.FINALLY, 0); }
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public List<Except_clauseContext> except_clause() {
			return getRuleContexts(Except_clauseContext.class);
		}
		public Except_clauseContext except_clause(int i) {
			return getRuleContext(Except_clauseContext.class,i);
		}
		public Try_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_try_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTry_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Try_stmtContext try_stmt() throws RecognitionException {
		Try_stmtContext _localctx = new Try_stmtContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_try_stmt);

		    ((Try_stmtContext)_localctx).exceptBlocks =  new HashMap<>();
		    ((Try_stmtContext)_localctx).exceptions =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672); 
			match(TRY);
			setState(673); 
			match(COLON);
			setState(674); 
			((Try_stmtContext)_localctx).tryBlock = suite();
			setState(697);
			switch (_input.LA(1)) {
			case EXCEPT:
				{
				setState(680); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(675); 
					((Try_stmtContext)_localctx).exKey = except_clause();
					setState(676); 
					match(COLON);
					setState(677); 
					((Try_stmtContext)_localctx).exVal = suite();
					 _localctx.exceptions.add(((Try_stmtContext)_localctx).exKey); _localctx.exceptBlocks.put(((Try_stmtContext)_localctx).exKey, ((Try_stmtContext)_localctx).exVal); 
					}
					}
					setState(682); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==EXCEPT );
				setState(687);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(684); 
					match(ELSE);
					setState(685); 
					match(COLON);
					setState(686); 
					((Try_stmtContext)_localctx).elseBlock = suite();
					}
				}

				setState(692);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(689); 
					match(FINALLY);
					setState(690); 
					match(COLON);
					setState(691); 
					((Try_stmtContext)_localctx).finallyBlock = suite();
					}
				}

				}
				break;
			case FINALLY:
				{
				setState(694); 
				match(FINALLY);
				setState(695); 
				match(COLON);
				setState(696); 
				((Try_stmtContext)_localctx).finallyBlock = suite();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class With_stmtContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(Python3Parser.WITH, 0); }
		public List<With_itemContext> with_item() {
			return getRuleContexts(With_itemContext.class);
		}
		public With_itemContext with_item(int i) {
			return getRuleContext(With_itemContext.class,i);
		}
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public With_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitWith_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final With_stmtContext with_stmt() throws RecognitionException {
		With_stmtContext _localctx = new With_stmtContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_with_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(699); 
			match(WITH);
			setState(700); 
			with_item();
			setState(705);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(701); 
				match(COMMA);
				setState(702); 
				with_item();
				}
				}
				setState(707);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(708); 
			match(COLON);
			setState(709); 
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class With_itemContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public With_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with_item; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitWith_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final With_itemContext with_item() throws RecognitionException {
		With_itemContext _localctx = new With_itemContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_with_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711); 
			test();
			setState(714);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(712); 
				match(AS);
				setState(713); 
				expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Except_clauseContext extends ParserRuleContext {
		public TerminalNode EXCEPT() { return getToken(Python3Parser.EXCEPT, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode AS() { return getToken(Python3Parser.AS, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public Except_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_except_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitExcept_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Except_clauseContext except_clause() throws RecognitionException {
		Except_clauseContext _localctx = new Except_clauseContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_except_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716); 
			match(EXCEPT);
			setState(722);
			_la = _input.LA(1);
			if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
				{
				setState(717); 
				test();
				setState(720);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(718); 
					match(AS);
					setState(719); 
					match(NAME);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(Python3Parser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(Python3Parser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(Python3Parser.DEDENT, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_suite);
		int _la;
		try {
			setState(734);
			switch (_input.LA(1)) {
			case RETURN:
			case RAISE:
			case FROM:
			case IMPORT:
			case GLOBAL:
			case NONLOCAL:
			case ASSERT:
			case LAMBDA:
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case YIELD:
			case DEL:
			case PASS:
			case CONTINUE:
			case BREAK:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(724); 
				simple_stmt();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(725); 
				match(NEWLINE);
				setState(726); 
				match(INDENT);
				setState(728); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(727); 
					stmt();
					}
					}
					setState(730); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEF) | (1L << RETURN) | (1L << RAISE) | (1L << FROM) | (1L << IMPORT) | (1L << GLOBAL) | (1L << NONLOCAL) | (1L << ASSERT) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << TRY) | (1L << WITH) | (1L << LAMBDA) | (1L << NOT) | (1L << NONE) | (1L << TRUE) | (1L << FALSE) | (1L << CLASS) | (1L << YIELD) | (1L << DEL) | (1L << PASS) | (1L << CONTINUE) | (1L << BREAK) | (1L << NAME) | (1L << STRING_LITERAL) | (1L << BYTES_LITERAL) | (1L << DECIMAL_INTEGER) | (1L << OCT_INTEGER) | (1L << HEX_INTEGER) | (1L << BIN_INTEGER) | (1L << FLOAT_NUMBER) | (1L << IMAG_NUMBER) | (1L << ELLIPSIS) | (1L << STAR) | (1L << OPEN_PAREN) | (1L << OPEN_BRACK) | (1L << ADD) | (1L << MINUS))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (NOT_OP - 66)) | (1L << (OPEN_BRACE - 66)) | (1L << (AT - 66)))) != 0) );
				setState(732); 
				match(DEDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestContext extends ParserRuleContext {
		public Or_testContext value;
		public Or_testContext condition;
		public List<Or_testContext> or_test() {
			return getRuleContexts(Or_testContext.class);
		}
		public Or_testContext or_test(int i) {
			return getRuleContext(Or_testContext.class,i);
		}
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public TerminalNode ELSE() { return getToken(Python3Parser.ELSE, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public LambdefContext lambdef() {
			return getRuleContext(LambdefContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_test);
		int _la;
		try {
			setState(745);
			switch (_input.LA(1)) {
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(736); 
				((TestContext)_localctx).value = or_test();
				setState(742);
				_la = _input.LA(1);
				if (_la==IF) {
					{
					setState(737); 
					match(IF);
					setState(738); 
					((TestContext)_localctx).condition = or_test();
					setState(739); 
					match(ELSE);
					setState(740); 
					test();
					}
				}

				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(744); 
				lambdef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Test_nocondContext extends ParserRuleContext {
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public Lambdef_nocondContext lambdef_nocond() {
			return getRuleContext(Lambdef_nocondContext.class,0);
		}
		public Test_nocondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_nocond; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTest_nocond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_nocondContext test_nocond() throws RecognitionException {
		Test_nocondContext _localctx = new Test_nocondContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_test_nocond);
		try {
			setState(749);
			switch (_input.LA(1)) {
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(747); 
				or_test();
				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(748); 
				lambdef_nocond();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdefContext extends ParserRuleContext {
		public TerminalNode LAMBDA() { return getToken(Python3Parser.LAMBDA, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public VarargslistContext varargslist() {
			return getRuleContext(VarargslistContext.class,0);
		}
		public LambdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitLambdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdefContext lambdef() throws RecognitionException {
		LambdefContext _localctx = new LambdefContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_lambdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751); 
			match(LAMBDA);
			setState(753);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAME) | (1L << STAR) | (1L << POWER))) != 0)) {
				{
				setState(752); 
				varargslist();
				}
			}

			setState(755); 
			match(COLON);
			setState(756); 
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Lambdef_nocondContext extends ParserRuleContext {
		public TerminalNode LAMBDA() { return getToken(Python3Parser.LAMBDA, 0); }
		public Test_nocondContext test_nocond() {
			return getRuleContext(Test_nocondContext.class,0);
		}
		public VarargslistContext varargslist() {
			return getRuleContext(VarargslistContext.class,0);
		}
		public Lambdef_nocondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdef_nocond; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitLambdef_nocond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lambdef_nocondContext lambdef_nocond() throws RecognitionException {
		Lambdef_nocondContext _localctx = new Lambdef_nocondContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_lambdef_nocond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(758); 
			match(LAMBDA);
			setState(760);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAME) | (1L << STAR) | (1L << POWER))) != 0)) {
				{
				setState(759); 
				varargslist();
				}
			}

			setState(762); 
			match(COLON);
			setState(763); 
			test_nocond();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Or_testContext extends ParserRuleContext {
		public List<String> operators;
		public List<And_testContext> operands;
		public And_testContext left;
		public Token op;
		public And_testContext right;
		public List<And_testContext> and_test() {
			return getRuleContexts(And_testContext.class);
		}
		public And_testContext and_test(int i) {
			return getRuleContext(And_testContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(Python3Parser.OR); }
		public TerminalNode OR(int i) {
			return getToken(Python3Parser.OR, i);
		}
		public Or_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_test; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitOr_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_testContext or_test() throws RecognitionException {
		Or_testContext _localctx = new Or_testContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_or_test);

		    ((Or_testContext)_localctx).operators =  new ArrayList<>();
		    ((Or_testContext)_localctx).operands =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765); 
			((Or_testContext)_localctx).left = and_test();
			 _localctx.operands.add(((Or_testContext)_localctx).left); 
			setState(773);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(767); 
				((Or_testContext)_localctx).op = match(OR);
				setState(768); 
				((Or_testContext)_localctx).right = and_test();
				 _localctx.operators.add((((Or_testContext)_localctx).op!=null?((Or_testContext)_localctx).op.getText():null)); _localctx.operands.add(((Or_testContext)_localctx).right); 
				}
				}
				setState(775);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class And_testContext extends ParserRuleContext {
		public List<String> operators;
		public List<Not_testContext> operands;
		public Not_testContext left;
		public Token op;
		public Not_testContext right;
		public List<Not_testContext> not_test() {
			return getRuleContexts(Not_testContext.class);
		}
		public Not_testContext not_test(int i) {
			return getRuleContext(Not_testContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(Python3Parser.AND); }
		public TerminalNode AND(int i) {
			return getToken(Python3Parser.AND, i);
		}
		public And_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_test; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitAnd_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_testContext and_test() throws RecognitionException {
		And_testContext _localctx = new And_testContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_and_test);

		    ((And_testContext)_localctx).operators =  new ArrayList<>();
		    ((And_testContext)_localctx).operands =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(776); 
			((And_testContext)_localctx).left = not_test();
			 _localctx.operands.add(((And_testContext)_localctx).left); 
			setState(784);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(778); 
				((And_testContext)_localctx).op = match(AND);
				setState(779); 
				((And_testContext)_localctx).right = not_test();
				 _localctx.operators.add((((And_testContext)_localctx).op!=null?((And_testContext)_localctx).op.getText():null)); _localctx.operands.add(((And_testContext)_localctx).right); 
				}
				}
				setState(786);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Not_testContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(Python3Parser.NOT, 0); }
		public Not_testContext not_test() {
			return getRuleContext(Not_testContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public Not_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_test; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitNot_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_testContext not_test() throws RecognitionException {
		Not_testContext _localctx = new Not_testContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_not_test);
		try {
			setState(790);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(787); 
				match(NOT);
				setState(788); 
				not_test();
				}
				break;
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(789); 
				comparison();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public List<Comp_opContext> operators;
		public List<Star_exprContext> operands;
		public Star_exprContext left;
		public Comp_opContext op;
		public Star_exprContext right;
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public List<Comp_opContext> comp_op() {
			return getRuleContexts(Comp_opContext.class);
		}
		public Comp_opContext comp_op(int i) {
			return getRuleContext(Comp_opContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_comparison);

		    ((ComparisonContext)_localctx).operators =  new ArrayList<>();
		    ((ComparisonContext)_localctx).operands =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792); 
			((ComparisonContext)_localctx).left = star_expr();
			 _localctx.operands.add(((ComparisonContext)_localctx).left); 
			setState(800);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 15)) & ~0x3f) == 0 && ((1L << (_la - 15)) & ((1L << (IN - 15)) | (1L << (NOT - 15)) | (1L << (IS - 15)) | (1L << (LESS_THAN - 15)) | (1L << (GREATER_THAN - 15)) | (1L << (EQUALS - 15)) | (1L << (GT_EQ - 15)) | (1L << (LT_EQ - 15)) | (1L << (NOT_EQ_1 - 15)) | (1L << (NOT_EQ_2 - 15)))) != 0)) {
				{
				{
				setState(794); 
				((ComparisonContext)_localctx).op = comp_op();
				setState(795); 
				((ComparisonContext)_localctx).right = star_expr();
				 _localctx.operators.add(((ComparisonContext)_localctx).op); _localctx.operands.add(((ComparisonContext)_localctx).right); 
				}
				}
				setState(802);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comp_opContext extends ParserRuleContext {
		public String operator;
		public Token op;
		public Token neg;
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public TerminalNode NOT() { return getToken(Python3Parser.NOT, 0); }
		public TerminalNode IS() { return getToken(Python3Parser.IS, 0); }
		public Comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_op; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitComp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_comp_op);

		    ((Comp_opContext)_localctx).operator =  "";

		try {
			setState(827);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(803); 
				((Comp_opContext)_localctx).op = match(LESS_THAN);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(805); 
				((Comp_opContext)_localctx).op = match(GREATER_THAN);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(807); 
				((Comp_opContext)_localctx).op = match(EQUALS);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(809); 
				((Comp_opContext)_localctx).op = match(GT_EQ);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(811); 
				((Comp_opContext)_localctx).op = match(LT_EQ);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(813); 
				((Comp_opContext)_localctx).op = match(NOT_EQ_1);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(815); 
				((Comp_opContext)_localctx).op = match(NOT_EQ_2);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(817); 
				((Comp_opContext)_localctx).op = match(IN);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(819); 
				((Comp_opContext)_localctx).neg = match(NOT);
				setState(820); 
				((Comp_opContext)_localctx).op = match(IN);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).neg!=null?((Comp_opContext)_localctx).neg.getText():null) + " " + (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(822); 
				((Comp_opContext)_localctx).op = match(IS);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null); 
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(824); 
				((Comp_opContext)_localctx).op = match(IS);
				setState(825); 
				((Comp_opContext)_localctx).neg = match(NOT);
				 ((Comp_opContext)_localctx).operator =  (((Comp_opContext)_localctx).op!=null?((Comp_opContext)_localctx).op.getText():null) + " " + (((Comp_opContext)_localctx).neg!=null?((Comp_opContext)_localctx).neg.getText():null); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Star_exprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Star_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitStar_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Star_exprContext star_expr() throws RecognitionException {
		Star_exprContext _localctx = new Star_exprContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_star_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(830);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(829); 
				match(STAR);
				}
			}

			setState(832); 
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<Xor_exprContext> xor_expr() {
			return getRuleContexts(Xor_exprContext.class);
		}
		public Xor_exprContext xor_expr(int i) {
			return getRuleContext(Xor_exprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(834); 
			xor_expr();
			setState(839);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OP) {
				{
				{
				setState(835); 
				match(OR_OP);
				setState(836); 
				xor_expr();
				}
				}
				setState(841);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Xor_exprContext extends ParserRuleContext {
		public List<And_exprContext> and_expr() {
			return getRuleContexts(And_exprContext.class);
		}
		public And_exprContext and_expr(int i) {
			return getRuleContext(And_exprContext.class,i);
		}
		public Xor_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xor_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitXor_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Xor_exprContext xor_expr() throws RecognitionException {
		Xor_exprContext _localctx = new Xor_exprContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_xor_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(842); 
			and_expr();
			setState(847);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(843); 
				match(XOR);
				setState(844); 
				and_expr();
				}
				}
				setState(849);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class And_exprContext extends ParserRuleContext {
		public List<Shift_exprContext> shift_expr() {
			return getRuleContexts(Shift_exprContext.class);
		}
		public Shift_exprContext shift_expr(int i) {
			return getRuleContext(Shift_exprContext.class,i);
		}
		public And_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitAnd_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_exprContext and_expr() throws RecognitionException {
		And_exprContext _localctx = new And_exprContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_and_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(850); 
			shift_expr();
			setState(855);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND_OP) {
				{
				{
				setState(851); 
				match(AND_OP);
				setState(852); 
				shift_expr();
				}
				}
				setState(857);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shift_exprContext extends ParserRuleContext {
		public List<String> operators;
		public Token op;
		public List<Arith_exprContext> arith_expr() {
			return getRuleContexts(Arith_exprContext.class);
		}
		public Arith_exprContext arith_expr(int i) {
			return getRuleContext(Arith_exprContext.class,i);
		}
		public Shift_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shift_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitShift_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shift_exprContext shift_expr() throws RecognitionException {
		Shift_exprContext _localctx = new Shift_exprContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_shift_expr);

		    ((Shift_exprContext)_localctx).operators =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(858); 
			arith_expr();
			setState(869);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LEFT_SHIFT || _la==RIGHT_SHIFT) {
				{
				setState(867);
				switch (_input.LA(1)) {
				case LEFT_SHIFT:
					{
					setState(859); 
					((Shift_exprContext)_localctx).op = match(LEFT_SHIFT);
					setState(860); 
					arith_expr();
					 _localctx.operators.add((((Shift_exprContext)_localctx).op!=null?((Shift_exprContext)_localctx).op.getText():null)); 
					}
					break;
				case RIGHT_SHIFT:
					{
					setState(863); 
					((Shift_exprContext)_localctx).op = match(RIGHT_SHIFT);
					setState(864); 
					arith_expr();
					 _localctx.operators.add((((Shift_exprContext)_localctx).op!=null?((Shift_exprContext)_localctx).op.getText():null)); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(871);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arith_exprContext extends ParserRuleContext {
		public List<String> operators;
		public Token op;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Arith_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitArith_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arith_exprContext arith_expr() throws RecognitionException {
		Arith_exprContext _localctx = new Arith_exprContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_arith_expr);

		    ((Arith_exprContext)_localctx).operators =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(872); 
			term();
			setState(883);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==MINUS) {
				{
				setState(881);
				switch (_input.LA(1)) {
				case ADD:
					{
					setState(873); 
					((Arith_exprContext)_localctx).op = match(ADD);
					setState(874); 
					term();
					 _localctx.operators.add((((Arith_exprContext)_localctx).op!=null?((Arith_exprContext)_localctx).op.getText():null)); 
					}
					break;
				case MINUS:
					{
					setState(877); 
					((Arith_exprContext)_localctx).op = match(MINUS);
					setState(878); 
					term();
					 _localctx.operators.add((((Arith_exprContext)_localctx).op!=null?((Arith_exprContext)_localctx).op.getText():null)); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(885);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public List<String> operators;
		public Token op;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_term);

		    ((TermContext)_localctx).operators =  new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(886); 
			factor();
			setState(909);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (STAR - 46)) | (1L << (DIV - 46)) | (1L << (MOD - 46)) | (1L << (IDIV - 46)) | (1L << (AT - 46)))) != 0)) {
				{
				setState(907);
				switch (_input.LA(1)) {
				case STAR:
					{
					setState(887); 
					((TermContext)_localctx).op = match(STAR);
					setState(888); 
					factor();
					 _localctx.operators.add((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
					}
					break;
				case DIV:
					{
					setState(891); 
					((TermContext)_localctx).op = match(DIV);
					setState(892); 
					factor();
					 _localctx.operators.add((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
					}
					break;
				case MOD:
					{
					setState(895); 
					((TermContext)_localctx).op = match(MOD);
					setState(896); 
					factor();
					 _localctx.operators.add((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
					}
					break;
				case IDIV:
					{
					setState(899); 
					((TermContext)_localctx).op = match(IDIV);
					setState(900); 
					factor();
					 _localctx.operators.add((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
					}
					break;
				case AT:
					{
					setState(903); 
					((TermContext)_localctx).op = match(AT);
					setState(904); 
					factor();
					 _localctx.operators.add((((TermContext)_localctx).op!=null?((TermContext)_localctx).op.getText():null)); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(911);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Token op;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public PowerContext power() {
			return getRuleContext(PowerContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_factor);
		try {
			setState(919);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(912); 
				((FactorContext)_localctx).op = match(ADD);
				setState(913); 
				factor();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(914); 
				((FactorContext)_localctx).op = match(MINUS);
				setState(915); 
				factor();
				}
				break;
			case NOT_OP:
				enterOuterAlt(_localctx, 3);
				{
				setState(916); 
				((FactorContext)_localctx).op = match(NOT_OP);
				setState(917); 
				factor();
				}
				break;
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 4);
				{
				setState(918); 
				power();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PowerContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TrailerContext> trailer() {
			return getRuleContexts(TrailerContext.class);
		}
		public TrailerContext trailer(int i) {
			return getRuleContext(TrailerContext.class,i);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public PowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_power);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(921); 
			atom();
			setState(925);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOT) | (1L << OPEN_PAREN) | (1L << OPEN_BRACK))) != 0)) {
				{
				{
				setState(922); 
				trailer();
				}
				}
				setState(927);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(930);
			_la = _input.LA(1);
			if (_la==POWER) {
				{
				setState(928); 
				match(POWER);
				setState(929); 
				factor();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Token ellipsis;
		public Yield_exprContext yield_expr() {
			return getRuleContext(Yield_exprContext.class,0);
		}
		public Testlist_compContext testlist_comp() {
			return getRuleContext(Testlist_compContext.class,0);
		}
		public DictorsetmakerContext dictorsetmaker() {
			return getRuleContext(DictorsetmakerContext.class,0);
		}
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public TerminalNode NONE() { return getToken(Python3Parser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(Python3Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Python3Parser.FALSE, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_atom);
		int _la;
		try {
			setState(959);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(932); 
				match(OPEN_PAREN);
				setState(935);
				switch (_input.LA(1)) {
				case YIELD:
					{
					setState(933); 
					yield_expr();
					}
					break;
				case LAMBDA:
				case NOT:
				case NONE:
				case TRUE:
				case FALSE:
				case NAME:
				case STRING_LITERAL:
				case BYTES_LITERAL:
				case DECIMAL_INTEGER:
				case OCT_INTEGER:
				case HEX_INTEGER:
				case BIN_INTEGER:
				case FLOAT_NUMBER:
				case IMAG_NUMBER:
				case ELLIPSIS:
				case STAR:
				case OPEN_PAREN:
				case OPEN_BRACK:
				case ADD:
				case MINUS:
				case NOT_OP:
				case OPEN_BRACE:
					{
					setState(934); 
					testlist_comp();
					}
					break;
				case CLOSE_PAREN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(937); 
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(938); 
				match(OPEN_BRACK);
				setState(940);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(939); 
					testlist_comp();
					}
				}

				setState(942); 
				match(CLOSE_BRACK);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(943); 
				match(OPEN_BRACE);
				setState(945);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(944); 
					dictorsetmaker();
					}
				}

				setState(947); 
				match(CLOSE_BRACE);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(948); 
				match(NAME);
				}
				break;
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(949); 
				number();
				}
				break;
			case STRING_LITERAL:
			case BYTES_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(951); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(950); 
					string();
					}
					}
					setState(953); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING_LITERAL || _la==BYTES_LITERAL );
				}
				break;
			case ELLIPSIS:
				enterOuterAlt(_localctx, 7);
				{
				setState(955); 
				((AtomContext)_localctx).ellipsis = match(ELLIPSIS);
				}
				break;
			case NONE:
				enterOuterAlt(_localctx, 8);
				{
				setState(956); 
				match(NONE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 9);
				{
				setState(957); 
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 10);
				{
				setState(958); 
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Testlist_compContext extends ParserRuleContext {
		public List<TestContext> values;
		public TestContext initial;
		public TestContext val;
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public Testlist_compContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist_comp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTestlist_comp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Testlist_compContext testlist_comp() throws RecognitionException {
		Testlist_compContext _localctx = new Testlist_compContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_testlist_comp);

		    ((Testlist_compContext)_localctx).values =  new ArrayList<>();

		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(961); 
			((Testlist_compContext)_localctx).initial = test();
			 _localctx.values.add(((Testlist_compContext)_localctx).initial); 
			setState(976);
			switch (_input.LA(1)) {
			case FOR:
				{
				setState(963); 
				comp_for();
				}
				break;
			case CLOSE_PAREN:
			case COMMA:
			case CLOSE_BRACK:
				{
				setState(970);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(964); 
						match(COMMA);
						setState(965); 
						((Testlist_compContext)_localctx).val = test();
						 _localctx.values.add(((Testlist_compContext)_localctx).val); 
						}
						} 
					}
					setState(972);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
				}
				setState(974);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(973); 
					match(COMMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrailerContext extends ParserRuleContext {
		public Token callBracket;
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public SubscriptlistContext subscriptlist() {
			return getRuleContext(SubscriptlistContext.class,0);
		}
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public TrailerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trailer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTrailer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrailerContext trailer() throws RecognitionException {
		TrailerContext _localctx = new TrailerContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_trailer);
		int _la;
		try {
			setState(989);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(978); 
				((TrailerContext)_localctx).callBracket = match(OPEN_PAREN);
				setState(980);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (POWER - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(979); 
					arglist();
					}
				}

				setState(982); 
				match(CLOSE_PAREN);
				}
				break;
			case OPEN_BRACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(983); 
				match(OPEN_BRACK);
				setState(984); 
				subscriptlist();
				setState(985); 
				match(CLOSE_BRACK);
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(987); 
				match(DOT);
				setState(988); 
				match(NAME);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubscriptlistContext extends ParserRuleContext {
		public List<SubscriptContext> subscript() {
			return getRuleContexts(SubscriptContext.class);
		}
		public SubscriptContext subscript(int i) {
			return getRuleContext(SubscriptContext.class,i);
		}
		public SubscriptlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscriptlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSubscriptlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubscriptlistContext subscriptlist() throws RecognitionException {
		SubscriptlistContext _localctx = new SubscriptlistContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_subscriptlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(991); 
			subscript();
			setState(996);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(992); 
					match(COMMA);
					setState(993); 
					subscript();
					}
					} 
				}
				setState(998);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(1000);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(999); 
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubscriptContext extends ParserRuleContext {
		public TestContext index;
		public TestContext lowerBound;
		public TestContext upperBound;
		public SliceopContext stride;
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public SliceopContext sliceop() {
			return getRuleContext(SliceopContext.class,0);
		}
		public SubscriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscript; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSubscript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubscriptContext subscript() throws RecognitionException {
		SubscriptContext _localctx = new SubscriptContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_subscript);
		int _la;
		try {
			setState(1013);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1002); 
				((SubscriptContext)_localctx).index = test();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1004);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(1003); 
					((SubscriptContext)_localctx).lowerBound = test();
					}
				}

				setState(1006); 
				match(COLON);
				setState(1008);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(1007); 
					((SubscriptContext)_localctx).upperBound = test();
					}
				}

				setState(1011);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1010); 
					((SubscriptContext)_localctx).stride = sliceop();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SliceopContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public SliceopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliceop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitSliceop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SliceopContext sliceop() throws RecognitionException {
		SliceopContext _localctx = new SliceopContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_sliceop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015); 
			match(COLON);
			setState(1017);
			_la = _input.LA(1);
			if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
				{
				setState(1016); 
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprlistContext extends ParserRuleContext {
		public List<Star_exprContext> star_expr() {
			return getRuleContexts(Star_exprContext.class);
		}
		public Star_exprContext star_expr(int i) {
			return getRuleContext(Star_exprContext.class,i);
		}
		public ExprlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitExprlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprlistContext exprlist() throws RecognitionException {
		ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_exprlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1019); 
			star_expr();
			setState(1024);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1020); 
					match(COMMA);
					setState(1021); 
					star_expr();
					}
					} 
				}
				setState(1026);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			}
			setState(1028);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1027); 
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestlistContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public TestlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitTestlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestlistContext testlist() throws RecognitionException {
		TestlistContext _localctx = new TestlistContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_testlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1030); 
			test();
			setState(1035);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,127,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1031); 
					match(COMMA);
					setState(1032); 
					test();
					}
					} 
				}
				setState(1037);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,127,_ctx);
			}
			setState(1039);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1038); 
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DictorsetmakerContext extends ParserRuleContext {
		public List<TestContext> setValues;
		public Map<TestContext, TestContext> dictValues;
		public TestContext dictVar;
		public TestContext dictExpr;
		public TestContext dictKey;
		public TestContext dictVal;
		public TestContext setVar;
		public TestContext setVal;
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public DictorsetmakerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictorsetmaker; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitDictorsetmaker(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DictorsetmakerContext dictorsetmaker() throws RecognitionException {
		DictorsetmakerContext _localctx = new DictorsetmakerContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_dictorsetmaker);

		    ((DictorsetmakerContext)_localctx).dictValues =  new HashMap<>();
		    ((DictorsetmakerContext)_localctx).setValues =  new ArrayList<>();

		int _la;
		try {
			int _alt;
			setState(1077);
			switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1041); 
				((DictorsetmakerContext)_localctx).dictVar = test();
				setState(1042); 
				match(COLON);
				setState(1043); 
				((DictorsetmakerContext)_localctx).dictExpr = test();
				setState(1059);
				switch (_input.LA(1)) {
				case FOR:
					{
					setState(1044); 
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1053);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,129,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1045); 
							match(COMMA);
							setState(1046); 
							((DictorsetmakerContext)_localctx).dictKey = test();
							setState(1047); 
							match(COLON);
							setState(1048); 
							((DictorsetmakerContext)_localctx).dictVal = test();
							 _localctx.dictValues.put(((DictorsetmakerContext)_localctx).dictKey, ((DictorsetmakerContext)_localctx).dictVal); 
							}
							} 
						}
						setState(1055);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,129,_ctx);
					}
					setState(1057);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1056); 
						match(COMMA);
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1061); 
				((DictorsetmakerContext)_localctx).setVar = test();
				setState(1075);
				switch (_input.LA(1)) {
				case FOR:
					{
					setState(1062); 
					comp_for();
					}
					break;
				case COMMA:
				case CLOSE_BRACE:
					{
					setState(1069);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1063); 
							match(COMMA);
							setState(1064); 
							((DictorsetmakerContext)_localctx).setVal = test();
							 _localctx.setValues.add(((DictorsetmakerContext)_localctx).setVal); 
							}
							} 
						}
						setState(1071);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,132,_ctx);
					}
					setState(1073);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(1072); 
						match(COMMA);
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassdefContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(Python3Parser.CLASS, 0); }
		public TerminalNode NAME() { return getToken(Python3Parser.NAME, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public ClassdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitClassdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassdefContext classdef() throws RecognitionException {
		ClassdefContext _localctx = new ClassdefContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_classdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1079); 
			match(CLASS);
			setState(1080); 
			match(NAME);
			setState(1086);
			_la = _input.LA(1);
			if (_la==OPEN_PAREN) {
				{
				setState(1081); 
				match(OPEN_PAREN);
				setState(1083);
				_la = _input.LA(1);
				if (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (LAMBDA - 20)) | (1L << (NOT - 20)) | (1L << (NONE - 20)) | (1L << (TRUE - 20)) | (1L << (FALSE - 20)) | (1L << (NAME - 20)) | (1L << (STRING_LITERAL - 20)) | (1L << (BYTES_LITERAL - 20)) | (1L << (DECIMAL_INTEGER - 20)) | (1L << (OCT_INTEGER - 20)) | (1L << (HEX_INTEGER - 20)) | (1L << (BIN_INTEGER - 20)) | (1L << (FLOAT_NUMBER - 20)) | (1L << (IMAG_NUMBER - 20)) | (1L << (ELLIPSIS - 20)) | (1L << (STAR - 20)) | (1L << (OPEN_PAREN - 20)) | (1L << (POWER - 20)) | (1L << (OPEN_BRACK - 20)) | (1L << (ADD - 20)) | (1L << (MINUS - 20)) | (1L << (NOT_OP - 20)) | (1L << (OPEN_BRACE - 20)))) != 0)) {
					{
					setState(1082); 
					arglist();
					}
				}

				setState(1085); 
				match(CLOSE_PAREN);
				}
			}

			setState(1088); 
			match(COLON);
			setState(1089); 
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArglistContext extends ParserRuleContext {
		public List<ArgumentContext> positionalArgs;
		public ArgumentContext optArg;
		public ArgumentContext arg;
		public TestContext args;
		public ArgumentContext optArg2;
		public TestContext kwargs;
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_arglist);

		    ((ArglistContext)_localctx).positionalArgs =  new ArrayList<>();

		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1097);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1091); 
					((ArglistContext)_localctx).optArg = argument();
					setState(1092); 
					match(COMMA);
					 _localctx.positionalArgs.add(((ArglistContext)_localctx).optArg); 
					}
					} 
				}
				setState(1099);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
			}
			setState(1123);
			switch ( getInterpreter().adaptivePredict(_input,142,_ctx) ) {
			case 1:
				{
				setState(1100); 
				((ArglistContext)_localctx).arg = argument();
				 _localctx.positionalArgs.add(((ArglistContext)_localctx).arg); 
				setState(1103);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1102); 
					match(COMMA);
					}
				}

				}
				break;
			case 2:
				{
				setState(1105); 
				match(STAR);
				setState(1106); 
				((ArglistContext)_localctx).args = test();
				setState(1113);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,140,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1107); 
						match(COMMA);
						setState(1108); 
						((ArglistContext)_localctx).optArg2 = argument();
						 _localctx.positionalArgs.add(((ArglistContext)_localctx).optArg2); 
						}
						} 
					}
					setState(1115);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,140,_ctx);
				}
				setState(1119);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1116); 
					match(COMMA);
					setState(1117); 
					match(POWER);
					setState(1118); 
					((ArglistContext)_localctx).kwargs = test();
					}
				}

				}
				break;
			case 3:
				{
				setState(1121); 
				match(POWER);
				setState(1122); 
				((ArglistContext)_localctx).kwargs = test();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public TestContext value;
		public Comp_forContext condition;
		public TestContext name;
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_argument);
		int _la;
		try {
			setState(1133);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1125); 
				((ArgumentContext)_localctx).value = test();
				setState(1127);
				_la = _input.LA(1);
				if (_la==FOR) {
					{
					setState(1126); 
					((ArgumentContext)_localctx).condition = comp_for();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1129); 
				((ArgumentContext)_localctx).name = test();
				setState(1130); 
				match(ASSIGN);
				setState(1131); 
				((ArgumentContext)_localctx).value = test();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comp_iterContext extends ParserRuleContext {
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public Comp_ifContext comp_if() {
			return getRuleContext(Comp_ifContext.class,0);
		}
		public Comp_iterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_iter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitComp_iter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_iterContext comp_iter() throws RecognitionException {
		Comp_iterContext _localctx = new Comp_iterContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_comp_iter);
		try {
			setState(1137);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(1135); 
				comp_for();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(1136); 
				comp_if();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comp_forContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(Python3Parser.FOR, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode IN() { return getToken(Python3Parser.IN, 0); }
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public Comp_iterContext comp_iter() {
			return getRuleContext(Comp_iterContext.class,0);
		}
		public Comp_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_for; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitComp_for(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_forContext comp_for() throws RecognitionException {
		Comp_forContext _localctx = new Comp_forContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_comp_for);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1139); 
			match(FOR);
			setState(1140); 
			exprlist();
			setState(1141); 
			match(IN);
			setState(1142); 
			or_test();
			setState(1144);
			_la = _input.LA(1);
			if (_la==IF || _la==FOR) {
				{
				setState(1143); 
				comp_iter();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comp_ifContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Python3Parser.IF, 0); }
		public Test_nocondContext test_nocond() {
			return getRuleContext(Test_nocondContext.class,0);
		}
		public Comp_iterContext comp_iter() {
			return getRuleContext(Comp_iterContext.class,0);
		}
		public Comp_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_if; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitComp_if(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_ifContext comp_if() throws RecognitionException {
		Comp_ifContext _localctx = new Comp_ifContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_comp_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1146); 
			match(IF);
			setState(1147); 
			test_nocond();
			setState(1149);
			_la = _input.LA(1);
			if (_la==IF || _la==FOR) {
				{
				setState(1148); 
				comp_iter();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Yield_exprContext extends ParserRuleContext {
		public TerminalNode YIELD() { return getToken(Python3Parser.YIELD, 0); }
		public Yield_argContext yield_arg() {
			return getRuleContext(Yield_argContext.class,0);
		}
		public Yield_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitYield_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Yield_exprContext yield_expr() throws RecognitionException {
		Yield_exprContext _localctx = new Yield_exprContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_yield_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1151); 
			match(YIELD);
			setState(1153);
			_la = _input.LA(1);
			if (((((_la - 4)) & ~0x3f) == 0 && ((1L << (_la - 4)) & ((1L << (FROM - 4)) | (1L << (LAMBDA - 4)) | (1L << (NOT - 4)) | (1L << (NONE - 4)) | (1L << (TRUE - 4)) | (1L << (FALSE - 4)) | (1L << (NAME - 4)) | (1L << (STRING_LITERAL - 4)) | (1L << (BYTES_LITERAL - 4)) | (1L << (DECIMAL_INTEGER - 4)) | (1L << (OCT_INTEGER - 4)) | (1L << (HEX_INTEGER - 4)) | (1L << (BIN_INTEGER - 4)) | (1L << (FLOAT_NUMBER - 4)) | (1L << (IMAG_NUMBER - 4)) | (1L << (ELLIPSIS - 4)) | (1L << (STAR - 4)) | (1L << (OPEN_PAREN - 4)) | (1L << (OPEN_BRACK - 4)) | (1L << (ADD - 4)) | (1L << (MINUS - 4)) | (1L << (NOT_OP - 4)) | (1L << (OPEN_BRACE - 4)))) != 0)) {
				{
				setState(1152); 
				yield_arg();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Yield_argContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(Python3Parser.FROM, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TestlistContext testlist() {
			return getRuleContext(TestlistContext.class,0);
		}
		public Yield_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yield_arg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitYield_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Yield_argContext yield_arg() throws RecognitionException {
		Yield_argContext _localctx = new Yield_argContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_yield_arg);
		try {
			setState(1158);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1155); 
				match(FROM);
				setState(1156); 
				test();
				}
				break;
			case LAMBDA:
			case NOT:
			case NONE:
			case TRUE:
			case FALSE:
			case NAME:
			case STRING_LITERAL:
			case BYTES_LITERAL:
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
			case FLOAT_NUMBER:
			case IMAG_NUMBER:
			case ELLIPSIS:
			case STAR:
			case OPEN_PAREN:
			case OPEN_BRACK:
			case ADD:
			case MINUS:
			case NOT_OP:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1157); 
				testlist();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(Python3Parser.STRING_LITERAL, 0); }
		public TerminalNode BYTES_LITERAL() { return getToken(Python3Parser.BYTES_LITERAL, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1160);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==BYTES_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode FLOAT_NUMBER() { return getToken(Python3Parser.FLOAT_NUMBER, 0); }
		public TerminalNode IMAG_NUMBER() { return getToken(Python3Parser.IMAG_NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_number);
		try {
			setState(1165);
			switch (_input.LA(1)) {
			case DECIMAL_INTEGER:
			case OCT_INTEGER:
			case HEX_INTEGER:
			case BIN_INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1162); 
				integer();
				}
				break;
			case FLOAT_NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1163); 
				match(FLOAT_NUMBER);
				}
				break;
			case IMAG_NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(1164); 
				match(IMAG_NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode DECIMAL_INTEGER() { return getToken(Python3Parser.DECIMAL_INTEGER, 0); }
		public TerminalNode OCT_INTEGER() { return getToken(Python3Parser.OCT_INTEGER, 0); }
		public TerminalNode HEX_INTEGER() { return getToken(Python3Parser.HEX_INTEGER, 0); }
		public TerminalNode BIN_INTEGER() { return getToken(Python3Parser.BIN_INTEGER, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Python3Visitor ) return ((Python3Visitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1167);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_INTEGER) | (1L << OCT_INTEGER) | (1L << HEX_INTEGER) | (1L << BIN_INTEGER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3a\u0494\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\3\2\3\2\3\2\3\2\3\2\5\2\u00b0\n\2\3\3\3\3\7\3\u00b4\n\3\f\3\16"+
		"\3\u00b7\13\3\3\3\3\3\3\4\3\4\7\4\u00bd\n\4\f\4\16\4\u00c0\13\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\5\5\u00c8\n\5\3\5\5\5\u00cb\n\5\3\5\3\5\3\6\6\6\u00d0"+
		"\n\6\r\6\16\6\u00d1\3\7\3\7\3\7\5\7\u00d7\n\7\3\b\3\b\3\b\3\b\3\b\5\b"+
		"\u00de\n\b\3\b\3\b\3\b\3\t\3\t\5\t\u00e5\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\5\n\u00ef\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f8\n\n\7\n\u00fa"+
		"\n\n\f\n\16\n\u00fd\13\n\3\n\3\n\3\n\5\n\u0102\n\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u010c\n\n\7\n\u010e\n\n\f\n\16\n\u0111\13\n\3\n\3\n\3"+
		"\n\3\n\3\n\5\n\u0118\n\n\3\n\3\n\3\n\3\n\5\n\u011e\n\n\5\n\u0120\n\n\3"+
		"\n\3\n\5\n\u0124\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u012e\n\n\7\n"+
		"\u0130\n\n\f\n\16\n\u0133\13\n\3\n\3\n\3\n\3\n\3\n\5\n\u013a\n\n\3\n\3"+
		"\n\3\n\3\n\5\n\u0140\n\n\3\13\3\13\3\13\5\13\u0145\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u014d\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0156\n\f\7\f"+
		"\u0158\n\f\f\f\16\f\u015b\13\f\3\f\3\f\3\f\5\f\u0160\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u016a\n\f\7\f\u016c\n\f\f\f\16\f\u016f\13\f\3\f"+
		"\3\f\3\f\3\f\3\f\5\f\u0176\n\f\3\f\3\f\3\f\3\f\5\f\u017c\n\f\5\f\u017e"+
		"\n\f\3\f\3\f\5\f\u0182\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u018c\n"+
		"\f\7\f\u018e\n\f\f\f\16\f\u0191\13\f\3\f\3\f\3\f\3\f\3\f\5\f\u0198\n\f"+
		"\3\f\3\f\3\f\3\f\5\f\u019e\n\f\3\r\3\r\3\16\3\16\5\16\u01a4\n\16\3\17"+
		"\3\17\3\17\7\17\u01a9\n\17\f\17\16\17\u01ac\13\17\3\17\5\17\u01af\n\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u01bb\n\20\3\21"+
		"\3\21\3\21\3\21\5\21\u01c1\n\21\3\21\3\21\3\21\5\21\u01c6\n\21\7\21\u01c8"+
		"\n\21\f\21\16\21\u01cb\13\21\5\21\u01cd\n\21\3\22\3\22\5\22\u01d1\n\22"+
		"\3\22\3\22\3\22\5\22\u01d6\n\22\7\22\u01d8\n\22\f\22\16\22\u01db\13\22"+
		"\3\22\5\22\u01de\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u01ed\n\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\5\26\u01f9\n\26\3\27\3\27\3\30\3\30\3\31\3\31\5\31\u0201\n"+
		"\31\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u0209\n\33\5\33\u020b\n\33\3\34"+
		"\3\34\5\34\u020f\n\34\3\35\3\35\3\35\3\36\3\36\3\36\7\36\u0217\n\36\f"+
		"\36\16\36\u021a\13\36\3\36\3\36\3\36\6\36\u021f\n\36\r\36\16\36\u0220"+
		"\5\36\u0223\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u022c\n\36\3"+
		"\37\3\37\3\37\5\37\u0231\n\37\3 \3 \3 \5 \u0236\n \3!\3!\3!\7!\u023b\n"+
		"!\f!\16!\u023e\13!\3!\5!\u0241\n!\3\"\3\"\3\"\7\"\u0246\n\"\f\"\16\"\u0249"+
		"\13\"\3#\3#\3#\3#\3#\7#\u0250\n#\f#\16#\u0253\13#\3$\3$\3$\3$\3$\3$\7"+
		"$\u025b\n$\f$\16$\u025e\13$\3%\3%\3%\3%\3%\3%\7%\u0266\n%\f%\16%\u0269"+
		"\13%\3&\3&\3&\3&\5&\u026f\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0279"+
		"\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\7(\u0285\n(\f(\16(\u0288\13(\3(\3("+
		"\3(\5(\u028d\n(\3)\3)\3)\3)\3)\3)\3)\5)\u0296\n)\3*\3*\3*\3*\3*\3*\3*"+
		"\3*\3*\5*\u02a1\n*\3+\3+\3+\3+\3+\3+\3+\3+\6+\u02ab\n+\r+\16+\u02ac\3"+
		"+\3+\3+\5+\u02b2\n+\3+\3+\3+\5+\u02b7\n+\3+\3+\3+\5+\u02bc\n+\3,\3,\3"+
		",\3,\7,\u02c2\n,\f,\16,\u02c5\13,\3,\3,\3,\3-\3-\3-\5-\u02cd\n-\3.\3."+
		"\3.\3.\5.\u02d3\n.\5.\u02d5\n.\3/\3/\3/\3/\6/\u02db\n/\r/\16/\u02dc\3"+
		"/\3/\5/\u02e1\n/\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u02e9\n\60\3\60\5"+
		"\60\u02ec\n\60\3\61\3\61\5\61\u02f0\n\61\3\62\3\62\5\62\u02f4\n\62\3\62"+
		"\3\62\3\62\3\63\3\63\5\63\u02fb\n\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\7\64\u0306\n\64\f\64\16\64\u0309\13\64\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\7\65\u0311\n\65\f\65\16\65\u0314\13\65\3\66\3\66\3\66\5\66"+
		"\u0319\n\66\3\67\3\67\3\67\3\67\3\67\3\67\7\67\u0321\n\67\f\67\16\67\u0324"+
		"\13\67\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38"+
		"\38\38\38\58\u033e\n8\39\59\u0341\n9\39\39\3:\3:\3:\7:\u0348\n:\f:\16"+
		":\u034b\13:\3;\3;\3;\7;\u0350\n;\f;\16;\u0353\13;\3<\3<\3<\7<\u0358\n"+
		"<\f<\16<\u035b\13<\3=\3=\3=\3=\3=\3=\3=\3=\3=\7=\u0366\n=\f=\16=\u0369"+
		"\13=\3>\3>\3>\3>\3>\3>\3>\3>\3>\7>\u0374\n>\f>\16>\u0377\13>\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\7?\u038e\n?\f?"+
		"\16?\u0391\13?\3@\3@\3@\3@\3@\3@\3@\5@\u039a\n@\3A\3A\7A\u039e\nA\fA\16"+
		"A\u03a1\13A\3A\3A\5A\u03a5\nA\3B\3B\3B\5B\u03aa\nB\3B\3B\3B\5B\u03af\n"+
		"B\3B\3B\3B\5B\u03b4\nB\3B\3B\3B\3B\6B\u03ba\nB\rB\16B\u03bb\3B\3B\3B\3"+
		"B\5B\u03c2\nB\3C\3C\3C\3C\3C\3C\3C\7C\u03cb\nC\fC\16C\u03ce\13C\3C\5C"+
		"\u03d1\nC\5C\u03d3\nC\3D\3D\5D\u03d7\nD\3D\3D\3D\3D\3D\3D\3D\5D\u03e0"+
		"\nD\3E\3E\3E\7E\u03e5\nE\fE\16E\u03e8\13E\3E\5E\u03eb\nE\3F\3F\5F\u03ef"+
		"\nF\3F\3F\5F\u03f3\nF\3F\5F\u03f6\nF\5F\u03f8\nF\3G\3G\5G\u03fc\nG\3H"+
		"\3H\3H\7H\u0401\nH\fH\16H\u0404\13H\3H\5H\u0407\nH\3I\3I\3I\7I\u040c\n"+
		"I\fI\16I\u040f\13I\3I\5I\u0412\nI\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\7J\u041e"+
		"\nJ\fJ\16J\u0421\13J\3J\5J\u0424\nJ\5J\u0426\nJ\3J\3J\3J\3J\3J\3J\7J\u042e"+
		"\nJ\fJ\16J\u0431\13J\3J\5J\u0434\nJ\5J\u0436\nJ\5J\u0438\nJ\3K\3K\3K\3"+
		"K\5K\u043e\nK\3K\5K\u0441\nK\3K\3K\3K\3L\3L\3L\3L\7L\u044a\nL\fL\16L\u044d"+
		"\13L\3L\3L\3L\5L\u0452\nL\3L\3L\3L\3L\3L\3L\7L\u045a\nL\fL\16L\u045d\13"+
		"L\3L\3L\3L\5L\u0462\nL\3L\3L\5L\u0466\nL\3M\3M\5M\u046a\nM\3M\3M\3M\3"+
		"M\5M\u0470\nM\3N\3N\5N\u0474\nN\3O\3O\3O\3O\3O\5O\u047b\nO\3P\3P\3P\5"+
		"P\u0480\nP\3Q\3Q\5Q\u0484\nQ\3R\3R\3R\5R\u0489\nR\3S\3S\3T\3T\3T\5T\u0490"+
		"\nT\3U\3U\3U\2\2V\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\u00a4\u00a6\u00a8\2\5\3\2./\3\2&\'\3\2(+\u0510\2\u00af\3\2\2\2"+
		"\4\u00b5\3\2\2\2\6\u00ba\3\2\2\2\b\u00c3\3\2\2\2\n\u00cf\3\2\2\2\f\u00d3"+
		"\3\2\2\2\16\u00d8\3\2\2\2\20\u00e2\3\2\2\2\22\u013f\3\2\2\2\24\u0141\3"+
		"\2\2\2\26\u019d\3\2\2\2\30\u019f\3\2\2\2\32\u01a3\3\2\2\2\34\u01a5\3\2"+
		"\2\2\36\u01ba\3\2\2\2 \u01bc\3\2\2\2\"\u01d0\3\2\2\2$\u01ec\3\2\2\2&\u01ee"+
		"\3\2\2\2(\u01f1\3\2\2\2*\u01f8\3\2\2\2,\u01fa\3\2\2\2.\u01fc\3\2\2\2\60"+
		"\u01fe\3\2\2\2\62\u0202\3\2\2\2\64\u0204\3\2\2\2\66\u020e\3\2\2\28\u0210"+
		"\3\2\2\2:\u0213\3\2\2\2<\u022d\3\2\2\2>\u0232\3\2\2\2@\u0237\3\2\2\2B"+
		"\u0242\3\2\2\2D\u024a\3\2\2\2F\u0254\3\2\2\2H\u025f\3\2\2\2J\u026a\3\2"+
		"\2\2L\u0278\3\2\2\2N\u027a\3\2\2\2P\u028e\3\2\2\2R\u0297\3\2\2\2T\u02a2"+
		"\3\2\2\2V\u02bd\3\2\2\2X\u02c9\3\2\2\2Z\u02ce\3\2\2\2\\\u02e0\3\2\2\2"+
		"^\u02eb\3\2\2\2`\u02ef\3\2\2\2b\u02f1\3\2\2\2d\u02f8\3\2\2\2f\u02ff\3"+
		"\2\2\2h\u030a\3\2\2\2j\u0318\3\2\2\2l\u031a\3\2\2\2n\u033d\3\2\2\2p\u0340"+
		"\3\2\2\2r\u0344\3\2\2\2t\u034c\3\2\2\2v\u0354\3\2\2\2x\u035c\3\2\2\2z"+
		"\u036a\3\2\2\2|\u0378\3\2\2\2~\u0399\3\2\2\2\u0080\u039b\3\2\2\2\u0082"+
		"\u03c1\3\2\2\2\u0084\u03c3\3\2\2\2\u0086\u03df\3\2\2\2\u0088\u03e1\3\2"+
		"\2\2\u008a\u03f7\3\2\2\2\u008c\u03f9\3\2\2\2\u008e\u03fd\3\2\2\2\u0090"+
		"\u0408\3\2\2\2\u0092\u0437\3\2\2\2\u0094\u0439\3\2\2\2\u0096\u044b\3\2"+
		"\2\2\u0098\u046f\3\2\2\2\u009a\u0473\3\2\2\2\u009c\u0475\3\2\2\2\u009e"+
		"\u047c\3\2\2\2\u00a0\u0481\3\2\2\2\u00a2\u0488\3\2\2\2\u00a4\u048a\3\2"+
		"\2\2\u00a6\u048f\3\2\2\2\u00a8\u0491\3\2\2\2\u00aa\u00b0\7$\2\2\u00ab"+
		"\u00b0\5\34\17\2\u00ac\u00ad\5L\'\2\u00ad\u00ae\7$\2\2\u00ae\u00b0\3\2"+
		"\2\2\u00af\u00aa\3\2\2\2\u00af\u00ab\3\2\2\2\u00af\u00ac\3\2\2\2\u00b0"+
		"\3\3\2\2\2\u00b1\u00b4\7$\2\2\u00b2\u00b4\5\32\16\2\u00b3\u00b1\3\2\2"+
		"\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6"+
		"\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7\2\2\3\u00b9"+
		"\5\3\2\2\2\u00ba\u00be\5\u0090I\2\u00bb\u00bd\7$\2\2\u00bc\u00bb\3\2\2"+
		"\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1"+
		"\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\2\2\3\u00c2\7\3\2\2\2\u00c3"+
		"\u00c4\7N\2\2\u00c4\u00ca\5D#\2\u00c5\u00c7\7\61\2\2\u00c6\u00c8\5\u0096"+
		"L\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00cb\7\62\2\2\u00ca\u00c5\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3"+
		"\2\2\2\u00cc\u00cd\7$\2\2\u00cd\t\3\2\2\2\u00ce\u00d0\5\b\5\2\u00cf\u00ce"+
		"\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\13\3\2\2\2\u00d3\u00d6\5\n\6\2\u00d4\u00d7\5\u0094K\2\u00d5\u00d7\5\16"+
		"\b\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7\r\3\2\2\2\u00d8\u00d9"+
		"\7\3\2\2\u00d9\u00da\7%\2\2\u00da\u00dd\5\20\t\2\u00db\u00dc\7O\2\2\u00dc"+
		"\u00de\5^\60\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00e0\7\64\2\2\u00e0\u00e1\5\\/\2\u00e1\17\3\2\2\2\u00e2\u00e4"+
		"\7\61\2\2\u00e3\u00e5\5\22\n\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00e7\7\62\2\2\u00e7\21\3\2\2\2\u00e8\u00e9"+
		"\5\24\13\2\u00e9\u00ee\b\n\1\2\u00ea\u00eb\7\67\2\2\u00eb\u00ec\5^\60"+
		"\2\u00ec\u00ed\b\n\1\2\u00ed\u00ef\3\2\2\2\u00ee\u00ea\3\2\2\2\u00ee\u00ef"+
		"\3\2\2\2\u00ef\u00fb\3\2\2\2\u00f0\u00f1\7\63\2\2\u00f1\u00f2\5\24\13"+
		"\2\u00f2\u00f7\b\n\1\2\u00f3\u00f4\7\67\2\2\u00f4\u00f5\5^\60\2\u00f5"+
		"\u00f6\b\n\1\2\u00f6\u00f8\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f0\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb"+
		"\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u011f\3\2\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fe\u011d\7\63\2\2\u00ff\u0101\7\60\2\2\u0100\u0102\5\24\13\2\u0101"+
		"\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u010f\b\n"+
		"\1\2\u0104\u0105\7\63\2\2\u0105\u0106\5\24\13\2\u0106\u010b\b\n\1\2\u0107"+
		"\u0108\7\67\2\2\u0108\u0109\5^\60\2\u0109\u010a\b\n\1\2\u010a\u010c\3"+
		"\2\2\2\u010b\u0107\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2\2\2\u010d"+
		"\u0104\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0117\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\63\2\2\u0113"+
		"\u0114\7\66\2\2\u0114\u0115\5\24\13\2\u0115\u0116\b\n\1\2\u0116\u0118"+
		"\3\2\2\2\u0117\u0112\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011e\3\2\2\2\u0119"+
		"\u011a\7\66\2\2\u011a\u011b\5\24\13\2\u011b\u011c\b\n\1\2\u011c\u011e"+
		"\3\2\2\2\u011d\u00ff\3\2\2\2\u011d\u0119\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u0120\3\2\2\2\u011f\u00fe\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0140\3\2"+
		"\2\2\u0121\u0123\7\60\2\2\u0122\u0124\5\24\13\2\u0123\u0122\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0131\b\n\1\2\u0126\u0127\7\63"+
		"\2\2\u0127\u0128\5\24\13\2\u0128\u012d\b\n\1\2\u0129\u012a\7\67\2\2\u012a"+
		"\u012b\5^\60\2\u012b\u012c\b\n\1\2\u012c\u012e\3\2\2\2\u012d\u0129\3\2"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u0126\3\2\2\2\u0130"+
		"\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0139\3\2"+
		"\2\2\u0133\u0131\3\2\2\2\u0134\u0135\7\63\2\2\u0135\u0136\7\66\2\2\u0136"+
		"\u0137\5\24\13\2\u0137\u0138\b\n\1\2\u0138\u013a\3\2\2\2\u0139\u0134\3"+
		"\2\2\2\u0139\u013a\3\2\2\2\u013a\u0140\3\2\2\2\u013b\u013c\7\66\2\2\u013c"+
		"\u013d\5\24\13\2\u013d\u013e\b\n\1\2\u013e\u0140\3\2\2\2\u013f\u00e8\3"+
		"\2\2\2\u013f\u0121\3\2\2\2\u013f\u013b\3\2\2\2\u0140\23\3\2\2\2\u0141"+
		"\u0144\7%\2\2\u0142\u0143\7\64\2\2\u0143\u0145\5^\60\2\u0144\u0142\3\2"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\25\3\2\2\2\u0146\u0147\5\30\r\2\u0147\u014c"+
		"\b\f\1\2\u0148\u0149\7\67\2\2\u0149\u014a\5^\60\2\u014a\u014b\b\f\1\2"+
		"\u014b\u014d\3\2\2\2\u014c\u0148\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u0159"+
		"\3\2\2\2\u014e\u014f\7\63\2\2\u014f\u0150\5\30\r\2\u0150\u0155\b\f\1\2"+
		"\u0151\u0152\7\67\2\2\u0152\u0153\5^\60\2\u0153\u0154\b\f\1\2\u0154\u0156"+
		"\3\2\2\2\u0155\u0151\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157"+
		"\u014e\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\u017d\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u017b\7\63\2\2\u015d"+
		"\u015f\7\60\2\2\u015e\u0160\5\30\r\2\u015f\u015e\3\2\2\2\u015f\u0160\3"+
		"\2\2\2\u0160\u0161\3\2\2\2\u0161\u016d\b\f\1\2\u0162\u0163\7\63\2\2\u0163"+
		"\u0164\5\30\r\2\u0164\u0169\b\f\1\2\u0165\u0166\7\67\2\2\u0166\u0167\5"+
		"^\60\2\u0167\u0168\b\f\1\2\u0168\u016a\3\2\2\2\u0169\u0165\3\2\2\2\u0169"+
		"\u016a\3\2\2\2\u016a\u016c\3\2\2\2\u016b\u0162\3\2\2\2\u016c\u016f\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0175\3\2\2\2\u016f"+
		"\u016d\3\2\2\2\u0170\u0171\7\63\2\2\u0171\u0172\7\66\2\2\u0172\u0173\5"+
		"\30\r\2\u0173\u0174\b\f\1\2\u0174\u0176\3\2\2\2\u0175\u0170\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u017c\3\2\2\2\u0177\u0178\7\66\2\2\u0178\u0179\5"+
		"\30\r\2\u0179\u017a\b\f\1\2\u017a\u017c\3\2\2\2\u017b\u015d\3\2\2\2\u017b"+
		"\u0177\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u015c\3\2"+
		"\2\2\u017d\u017e\3\2\2\2\u017e\u019e\3\2\2\2\u017f\u0181\7\60\2\2\u0180"+
		"\u0182\5\30\r\2\u0181\u0180\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\3"+
		"\2\2\2\u0183\u018f\b\f\1\2\u0184\u0185\7\63\2\2\u0185\u0186\5\30\r\2\u0186"+
		"\u018b\b\f\1\2\u0187\u0188\7\67\2\2\u0188\u0189\5^\60\2\u0189\u018a\b"+
		"\f\1\2\u018a\u018c\3\2\2\2\u018b\u0187\3\2\2\2\u018b\u018c\3\2\2\2\u018c"+
		"\u018e\3\2\2\2\u018d\u0184\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2"+
		"\2\2\u018f\u0190\3\2\2\2\u0190\u0197\3\2\2\2\u0191\u018f\3\2\2\2\u0192"+
		"\u0193\7\63\2\2\u0193\u0194\7\66\2\2\u0194\u0195\5\30\r\2\u0195\u0196"+
		"\b\f\1\2\u0196\u0198\3\2\2\2\u0197\u0192\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u019e\3\2\2\2\u0199\u019a\7\66\2\2\u019a\u019b\5\30\r\2\u019b\u019c\b"+
		"\f\1\2\u019c\u019e\3\2\2\2\u019d\u0146\3\2\2\2\u019d\u017f\3\2\2\2\u019d"+
		"\u0199\3\2\2\2\u019e\27\3\2\2\2\u019f\u01a0\7%\2\2\u01a0\31\3\2\2\2\u01a1"+
		"\u01a4\5\34\17\2\u01a2\u01a4\5L\'\2\u01a3\u01a1\3\2\2\2\u01a3\u01a2\3"+
		"\2\2\2\u01a4\33\3\2\2\2\u01a5\u01aa\5\36\20\2\u01a6\u01a7\7\65\2\2\u01a7"+
		"\u01a9\5\36\20\2\u01a8\u01a6\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa\u01a8\3"+
		"\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad"+
		"\u01af\7\65\2\2\u01ae\u01ad\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01b0\3"+
		"\2\2\2\u01b0\u01b1\7$\2\2\u01b1\35\3\2\2\2\u01b2\u01bb\5 \21\2\u01b3\u01bb"+
		"\5&\24\2\u01b4\u01bb\5(\25\2\u01b5\u01bb\5*\26\2\u01b6\u01bb\5\66\34\2"+
		"\u01b7\u01bb\5F$\2\u01b8\u01bb\5H%\2\u01b9\u01bb\5J&\2\u01ba\u01b2\3\2"+
		"\2\2\u01ba\u01b3\3\2\2\2\u01ba\u01b4\3\2\2\2\u01ba\u01b5\3\2\2\2\u01ba"+
		"\u01b6\3\2\2\2\u01ba\u01b7\3\2\2\2\u01ba\u01b8\3\2\2\2\u01ba\u01b9\3\2"+
		"\2\2\u01bb\37\3\2\2\2\u01bc\u01cc\5\"\22\2\u01bd\u01c0\5$\23\2\u01be\u01c1"+
		"\5\u00a0Q\2\u01bf\u01c1\5\u0090I\2\u01c0\u01be\3\2\2\2\u01c0\u01bf\3\2"+
		"\2\2\u01c1\u01cd\3\2\2\2\u01c2\u01c5\7\67\2\2\u01c3\u01c6\5\u00a0Q\2\u01c4"+
		"\u01c6\5\"\22\2\u01c5\u01c3\3\2\2\2\u01c5\u01c4\3\2\2\2\u01c6\u01c8\3"+
		"\2\2\2\u01c7\u01c2\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9"+
		"\u01ca\3\2\2\2\u01ca\u01cd\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01bd\3\2"+
		"\2\2\u01cc\u01c9\3\2\2\2\u01cd!\3\2\2\2\u01ce\u01d1\5^\60\2\u01cf\u01d1"+
		"\5p9\2\u01d0\u01ce\3\2\2\2\u01d0\u01cf\3\2\2\2\u01d1\u01d9\3\2\2\2\u01d2"+
		"\u01d5\7\63\2\2\u01d3\u01d6\5^\60\2\u01d4\u01d6\5p9\2\u01d5\u01d3\3\2"+
		"\2\2\u01d5\u01d4\3\2\2\2\u01d6\u01d8\3\2\2\2\u01d7\u01d2\3\2\2\2\u01d8"+
		"\u01db\3\2\2\2\u01d9\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01dd\3\2"+
		"\2\2\u01db\u01d9\3\2\2\2\u01dc\u01de\7\63\2\2\u01dd\u01dc\3\2\2\2\u01dd"+
		"\u01de\3\2\2\2\u01de#\3\2\2\2\u01df\u01ed\7P\2\2\u01e0\u01ed\7Q\2\2\u01e1"+
		"\u01ed\7R\2\2\u01e2\u01ed\7S\2\2\u01e3\u01ed\7T\2\2\u01e4\u01ed\7U\2\2"+
		"\u01e5\u01ed\7V\2\2\u01e6\u01ed\7W\2\2\u01e7\u01ed\7X\2\2\u01e8\u01ed"+
		"\7Y\2\2\u01e9\u01ed\7Z\2\2\u01ea\u01ed\7[\2\2\u01eb\u01ed\7\\\2\2\u01ec"+
		"\u01df\3\2\2\2\u01ec\u01e0\3\2\2\2\u01ec\u01e1\3\2\2\2\u01ec\u01e2\3\2"+
		"\2\2\u01ec\u01e3\3\2\2\2\u01ec\u01e4\3\2\2\2\u01ec\u01e5\3\2\2\2\u01ec"+
		"\u01e6\3\2\2\2\u01ec\u01e7\3\2\2\2\u01ec\u01e8\3\2\2\2\u01ec\u01e9\3\2"+
		"\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01eb\3\2\2\2\u01ed%\3\2\2\2\u01ee\u01ef"+
		"\7 \2\2\u01ef\u01f0\5\u008eH\2\u01f0\'\3\2\2\2\u01f1\u01f2\7!\2\2\u01f2"+
		")\3\2\2\2\u01f3\u01f9\5,\27\2\u01f4\u01f9\5.\30\2\u01f5\u01f9\5\60\31"+
		"\2\u01f6\u01f9\5\64\33\2\u01f7\u01f9\5\62\32\2\u01f8\u01f3\3\2\2\2\u01f8"+
		"\u01f4\3\2\2\2\u01f8\u01f5\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f7\3\2"+
		"\2\2\u01f9+\3\2\2\2\u01fa\u01fb\7#\2\2\u01fb-\3\2\2\2\u01fc\u01fd\7\""+
		"\2\2\u01fd/\3\2\2\2\u01fe\u0200\7\4\2\2\u01ff\u0201\5\u0090I\2\u0200\u01ff"+
		"\3\2\2\2\u0200\u0201\3\2\2\2\u0201\61\3\2\2\2\u0202\u0203\5\u00a0Q\2\u0203"+
		"\63\3\2\2\2\u0204\u020a\7\5\2\2\u0205\u0208\5^\60\2\u0206\u0207\7\6\2"+
		"\2\u0207\u0209\5^\60\2\u0208\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020b"+
		"\3\2\2\2\u020a\u0205\3\2\2\2\u020a\u020b\3\2\2\2\u020b\65\3\2\2\2\u020c"+
		"\u020f\58\35\2\u020d\u020f\5:\36\2\u020e\u020c\3\2\2\2\u020e\u020d\3\2"+
		"\2\2\u020f\67\3\2\2\2\u0210\u0211\7\7\2\2\u0211\u0212\5B\"\2\u02129\3"+
		"\2\2\2\u0213\u0222\7\6\2\2\u0214\u0215\t\2\2\2\u0215\u0217\b\36\1\2\u0216"+
		"\u0214\3\2\2\2\u0217\u021a\3\2\2\2\u0218\u0216\3\2\2\2\u0218\u0219\3\2"+
		"\2\2\u0219\u021b\3\2\2\2\u021a\u0218\3\2\2\2\u021b\u0223\5D#\2\u021c\u021d"+
		"\t\2\2\2\u021d\u021f\b\36\1\2\u021e\u021c\3\2\2\2\u021f\u0220\3\2\2\2"+
		"\u0220\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u0223\3\2\2\2\u0222\u0218"+
		"\3\2\2\2\u0222\u021e\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u022b\7\7\2\2\u0225"+
		"\u022c\7\60\2\2\u0226\u0227\7\61\2\2\u0227\u0228\5@!\2\u0228\u0229\7\62"+
		"\2\2\u0229\u022c\3\2\2\2\u022a\u022c\5@!\2\u022b\u0225\3\2\2\2\u022b\u0226"+
		"\3\2\2\2\u022b\u022a\3\2\2\2\u022c;\3\2\2\2\u022d\u0230\7%\2\2\u022e\u022f"+
		"\7\b\2\2\u022f\u0231\7%\2\2\u0230\u022e\3\2\2\2\u0230\u0231\3\2\2\2\u0231"+
		"=\3\2\2\2\u0232\u0235\5D#\2\u0233\u0234\7\b\2\2\u0234\u0236\7%\2\2\u0235"+
		"\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236?\3\2\2\2\u0237\u023c\5<\37\2"+
		"\u0238\u0239\7\63\2\2\u0239\u023b\5<\37\2\u023a\u0238\3\2\2\2\u023b\u023e"+
		"\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u0240\3\2\2\2\u023e"+
		"\u023c\3\2\2\2\u023f\u0241\7\63\2\2\u0240\u023f\3\2\2\2\u0240\u0241\3"+
		"\2\2\2\u0241A\3\2\2\2\u0242\u0247\5> \2\u0243\u0244\7\63\2\2\u0244\u0246"+
		"\5> \2\u0245\u0243\3\2\2\2\u0246\u0249\3\2\2\2\u0247\u0245\3\2\2\2\u0247"+
		"\u0248\3\2\2\2\u0248C\3\2\2\2\u0249\u0247\3\2\2\2\u024a\u024b\7%\2\2\u024b"+
		"\u0251\b#\1\2\u024c\u024d\7.\2\2\u024d\u024e\7%\2\2\u024e\u0250\b#\1\2"+
		"\u024f\u024c\3\2\2\2\u0250\u0253\3\2\2\2\u0251\u024f\3\2\2\2\u0251\u0252"+
		"\3\2\2\2\u0252E\3\2\2\2\u0253\u0251\3\2\2\2\u0254\u0255\7\t\2\2\u0255"+
		"\u0256\7%\2\2\u0256\u025c\b$\1\2\u0257\u0258\7\63\2\2\u0258\u0259\7%\2"+
		"\2\u0259\u025b\b$\1\2\u025a\u0257\3\2\2\2\u025b\u025e\3\2\2\2\u025c\u025a"+
		"\3\2\2\2\u025c\u025d\3\2\2\2\u025dG\3\2\2\2\u025e\u025c\3\2\2\2\u025f"+
		"\u0260\7\n\2\2\u0260\u0261\7%\2\2\u0261\u0267\b%\1\2\u0262\u0263\7\63"+
		"\2\2\u0263\u0264\7%\2\2\u0264\u0266\b%\1\2\u0265\u0262\3\2\2\2\u0266\u0269"+
		"\3\2\2\2\u0267\u0265\3\2\2\2\u0267\u0268\3\2\2\2\u0268I\3\2\2\2\u0269"+
		"\u0267\3\2\2\2\u026a\u026b\7\13\2\2\u026b\u026e\5^\60\2\u026c\u026d\7"+
		"\63\2\2\u026d\u026f\5^\60\2\u026e\u026c\3\2\2\2\u026e\u026f\3\2\2\2\u026f"+
		"K\3\2\2\2\u0270\u0279\5N(\2\u0271\u0279\5P)\2\u0272\u0279\5R*\2\u0273"+
		"\u0279\5T+\2\u0274\u0279\5V,\2\u0275\u0279\5\16\b\2\u0276\u0279\5\u0094"+
		"K\2\u0277\u0279\5\f\7\2\u0278\u0270\3\2\2\2\u0278\u0271\3\2\2\2\u0278"+
		"\u0272\3\2\2\2\u0278\u0273\3\2\2\2\u0278\u0274\3\2\2\2\u0278\u0275\3\2"+
		"\2\2\u0278\u0276\3\2\2\2\u0278\u0277\3\2\2\2\u0279M\3\2\2\2\u027a\u027b"+
		"\7\f\2\2\u027b\u027c\5^\60\2\u027c\u027d\7\64\2\2\u027d\u0286\5\\/\2\u027e"+
		"\u027f\7\r\2\2\u027f\u0280\5^\60\2\u0280\u0281\7\64\2\2\u0281\u0282\5"+
		"\\/\2\u0282\u0283\b(\1\2\u0283\u0285\3\2\2\2\u0284\u027e\3\2\2\2\u0285"+
		"\u0288\3\2\2\2\u0286\u0284\3\2\2\2\u0286\u0287\3\2\2\2\u0287\u028c\3\2"+
		"\2\2\u0288\u0286\3\2\2\2\u0289\u028a\7\16\2\2\u028a\u028b\7\64\2\2\u028b"+
		"\u028d\5\\/\2\u028c\u0289\3\2\2\2\u028c\u028d\3\2\2\2\u028dO\3\2\2\2\u028e"+
		"\u028f\7\17\2\2\u028f\u0290\5^\60\2\u0290\u0291\7\64\2\2\u0291\u0295\5"+
		"\\/\2\u0292\u0293\7\16\2\2\u0293\u0294\7\64\2\2\u0294\u0296\5\\/\2\u0295"+
		"\u0292\3\2\2\2\u0295\u0296\3\2\2\2\u0296Q\3\2\2\2\u0297\u0298\7\20\2\2"+
		"\u0298\u0299\5\u008eH\2\u0299\u029a\7\21\2\2\u029a\u029b\5\u0090I\2\u029b"+
		"\u029c\7\64\2\2\u029c\u02a0\5\\/\2\u029d\u029e\7\16\2\2\u029e\u029f\7"+
		"\64\2\2\u029f\u02a1\5\\/\2\u02a0\u029d\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1"+
		"S\3\2\2\2\u02a2\u02a3\7\22\2\2\u02a3\u02a4\7\64\2\2\u02a4\u02bb\5\\/\2"+
		"\u02a5\u02a6\5Z.\2\u02a6\u02a7\7\64\2\2\u02a7\u02a8\5\\/\2\u02a8\u02a9"+
		"\b+\1\2\u02a9\u02ab\3\2\2\2\u02aa\u02a5\3\2\2\2\u02ab\u02ac\3\2\2\2\u02ac"+
		"\u02aa\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02b1\3\2\2\2\u02ae\u02af\7\16"+
		"\2\2\u02af\u02b0\7\64\2\2\u02b0\u02b2\5\\/\2\u02b1\u02ae\3\2\2\2\u02b1"+
		"\u02b2\3\2\2\2\u02b2\u02b6\3\2\2\2\u02b3\u02b4\7\23\2\2\u02b4\u02b5\7"+
		"\64\2\2\u02b5\u02b7\5\\/\2\u02b6\u02b3\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7"+
		"\u02bc\3\2\2\2\u02b8\u02b9\7\23\2\2\u02b9\u02ba\7\64\2\2\u02ba\u02bc\5"+
		"\\/\2\u02bb\u02aa\3\2\2\2\u02bb\u02b8\3\2\2\2\u02bcU\3\2\2\2\u02bd\u02be"+
		"\7\24\2\2\u02be\u02c3\5X-\2\u02bf\u02c0\7\63\2\2\u02c0\u02c2\5X-\2\u02c1"+
		"\u02bf\3\2\2\2\u02c2\u02c5\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c3\u02c4\3\2"+
		"\2\2\u02c4\u02c6\3\2\2\2\u02c5\u02c3\3\2\2\2\u02c6\u02c7\7\64\2\2\u02c7"+
		"\u02c8\5\\/\2\u02c8W\3\2\2\2\u02c9\u02cc\5^\60\2\u02ca\u02cb\7\b\2\2\u02cb"+
		"\u02cd\5r:\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cdY\3\2\2\2\u02ce"+
		"\u02d4\7\25\2\2\u02cf\u02d2\5^\60\2\u02d0\u02d1\7\b\2\2\u02d1\u02d3\7"+
		"%\2\2\u02d2\u02d0\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d5\3\2\2\2\u02d4"+
		"\u02cf\3\2\2\2\u02d4\u02d5\3\2\2\2\u02d5[\3\2\2\2\u02d6\u02e1\5\34\17"+
		"\2\u02d7\u02d8\7$\2\2\u02d8\u02da\7`\2\2\u02d9\u02db\5\32\16\2\u02da\u02d9"+
		"\3\2\2\2\u02db\u02dc\3\2\2\2\u02dc\u02da\3\2\2\2\u02dc\u02dd\3\2\2\2\u02dd"+
		"\u02de\3\2\2\2\u02de\u02df\7a\2\2\u02df\u02e1\3\2\2\2\u02e0\u02d6\3\2"+
		"\2\2\u02e0\u02d7\3\2\2\2\u02e1]\3\2\2\2\u02e2\u02e8\5f\64\2\u02e3\u02e4"+
		"\7\f\2\2\u02e4\u02e5\5f\64\2\u02e5\u02e6\7\16\2\2\u02e6\u02e7\5^\60\2"+
		"\u02e7\u02e9\3\2\2\2\u02e8\u02e3\3\2\2\2\u02e8\u02e9\3\2\2\2\u02e9\u02ec"+
		"\3\2\2\2\u02ea\u02ec\5b\62\2\u02eb\u02e2\3\2\2\2\u02eb\u02ea\3\2\2\2\u02ec"+
		"_\3\2\2\2\u02ed\u02f0\5f\64\2\u02ee\u02f0\5d\63\2\u02ef\u02ed\3\2\2\2"+
		"\u02ef\u02ee\3\2\2\2\u02f0a\3\2\2\2\u02f1\u02f3\7\26\2\2\u02f2\u02f4\5"+
		"\26\f\2\u02f3\u02f2\3\2\2\2\u02f3\u02f4\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5"+
		"\u02f6\7\64\2\2\u02f6\u02f7\5^\60\2\u02f7c\3\2\2\2\u02f8\u02fa\7\26\2"+
		"\2\u02f9\u02fb\5\26\f\2\u02fa\u02f9\3\2\2\2\u02fa\u02fb\3\2\2\2\u02fb"+
		"\u02fc\3\2\2\2\u02fc\u02fd\7\64\2\2\u02fd\u02fe\5`\61\2\u02fee\3\2\2\2"+
		"\u02ff\u0300\5h\65\2\u0300\u0307\b\64\1\2\u0301\u0302\7\27\2\2\u0302\u0303"+
		"\5h\65\2\u0303\u0304\b\64\1\2\u0304\u0306\3\2\2\2\u0305\u0301\3\2\2\2"+
		"\u0306\u0309\3\2\2\2\u0307\u0305\3\2\2\2\u0307\u0308\3\2\2\2\u0308g\3"+
		"\2\2\2\u0309\u0307\3\2\2\2\u030a\u030b\5j\66\2\u030b\u0312\b\65\1\2\u030c"+
		"\u030d\7\30\2\2\u030d\u030e\5j\66\2\u030e\u030f\b\65\1\2\u030f\u0311\3"+
		"\2\2\2\u0310\u030c\3\2\2\2\u0311\u0314\3\2\2\2\u0312\u0310\3\2\2\2\u0312"+
		"\u0313\3\2\2\2\u0313i\3\2\2\2\u0314\u0312\3\2\2\2\u0315\u0316\7\31\2\2"+
		"\u0316\u0319\5j\66\2\u0317\u0319\5l\67\2\u0318\u0315\3\2\2\2\u0318\u0317"+
		"\3\2\2\2\u0319k\3\2\2\2\u031a\u031b\5p9\2\u031b\u0322\b\67\1\2\u031c\u031d"+
		"\5n8\2\u031d\u031e\5p9\2\u031e\u031f\b\67\1\2\u031f\u0321\3\2\2\2\u0320"+
		"\u031c\3\2\2\2\u0321\u0324\3\2\2\2\u0322\u0320\3\2\2\2\u0322\u0323\3\2"+
		"\2\2\u0323m\3\2\2\2\u0324\u0322\3\2\2\2\u0325\u0326\7G\2\2\u0326\u033e"+
		"\b8\1\2\u0327\u0328\7H\2\2\u0328\u033e\b8\1\2\u0329\u032a\7I\2\2\u032a"+
		"\u033e\b8\1\2\u032b\u032c\7J\2\2\u032c\u033e\b8\1\2\u032d\u032e\7K\2\2"+
		"\u032e\u033e\b8\1\2\u032f\u0330\7L\2\2\u0330\u033e\b8\1\2\u0331\u0332"+
		"\7M\2\2\u0332\u033e\b8\1\2\u0333\u0334\7\21\2\2\u0334\u033e\b8\1\2\u0335"+
		"\u0336\7\31\2\2\u0336\u0337\7\21\2\2\u0337\u033e\b8\1\2\u0338\u0339\7"+
		"\32\2\2\u0339\u033e\b8\1\2\u033a\u033b\7\32\2\2\u033b\u033c\7\31\2\2\u033c"+
		"\u033e\b8\1\2\u033d\u0325\3\2\2\2\u033d\u0327\3\2\2\2\u033d\u0329\3\2"+
		"\2\2\u033d\u032b\3\2\2\2\u033d\u032d\3\2\2\2\u033d\u032f\3\2\2\2\u033d"+
		"\u0331\3\2\2\2\u033d\u0333\3\2\2\2\u033d\u0335\3\2\2\2\u033d\u0338\3\2"+
		"\2\2\u033d\u033a\3\2\2\2\u033eo\3\2\2\2\u033f\u0341\7\60\2\2\u0340\u033f"+
		"\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0342\3\2\2\2\u0342\u0343\5r:\2\u0343"+
		"q\3\2\2\2\u0344\u0349\5t;\2\u0345\u0346\7:\2\2\u0346\u0348\5t;\2\u0347"+
		"\u0345\3\2\2\2\u0348\u034b\3\2\2\2\u0349\u0347\3\2\2\2\u0349\u034a\3\2"+
		"\2\2\u034as\3\2\2\2\u034b\u0349\3\2\2\2\u034c\u0351\5v<\2\u034d\u034e"+
		"\7;\2\2\u034e\u0350\5v<\2\u034f\u034d\3\2\2\2\u0350\u0353\3\2\2\2\u0351"+
		"\u034f\3\2\2\2\u0351\u0352\3\2\2\2\u0352u\3\2\2\2\u0353\u0351\3\2\2\2"+
		"\u0354\u0359\5x=\2\u0355\u0356\7<\2\2\u0356\u0358\5x=\2\u0357\u0355\3"+
		"\2\2\2\u0358\u035b\3\2\2\2\u0359\u0357\3\2\2\2\u0359\u035a\3\2\2\2\u035a"+
		"w\3\2\2\2\u035b\u0359\3\2\2\2\u035c\u0367\5z>\2\u035d\u035e\7=\2\2\u035e"+
		"\u035f\5z>\2\u035f\u0360\b=\1\2\u0360\u0366\3\2\2\2\u0361\u0362\7>\2\2"+
		"\u0362\u0363\5z>\2\u0363\u0364\b=\1\2\u0364\u0366\3\2\2\2\u0365\u035d"+
		"\3\2\2\2\u0365\u0361\3\2\2\2\u0366\u0369\3\2\2\2\u0367\u0365\3\2\2\2\u0367"+
		"\u0368\3\2\2\2\u0368y\3\2\2\2\u0369\u0367\3\2\2\2\u036a\u0375\5|?\2\u036b"+
		"\u036c\7?\2\2\u036c\u036d\5|?\2\u036d\u036e\b>\1\2\u036e\u0374\3\2\2\2"+
		"\u036f\u0370\7@\2\2\u0370\u0371\5|?\2\u0371\u0372\b>\1\2\u0372\u0374\3"+
		"\2\2\2\u0373\u036b\3\2\2\2\u0373\u036f\3\2\2\2\u0374\u0377\3\2\2\2\u0375"+
		"\u0373\3\2\2\2\u0375\u0376\3\2\2\2\u0376{\3\2\2\2\u0377\u0375\3\2\2\2"+
		"\u0378\u038f\5~@\2\u0379\u037a\7\60\2\2\u037a\u037b\5~@\2\u037b\u037c"+
		"\b?\1\2\u037c\u038e\3\2\2\2\u037d\u037e\7A\2\2\u037e\u037f\5~@\2\u037f"+
		"\u0380\b?\1\2\u0380\u038e\3\2\2\2\u0381\u0382\7B\2\2\u0382\u0383\5~@\2"+
		"\u0383\u0384\b?\1\2\u0384\u038e\3\2\2\2\u0385\u0386\7C\2\2\u0386\u0387"+
		"\5~@\2\u0387\u0388\b?\1\2\u0388\u038e\3\2\2\2\u0389\u038a\7N\2\2\u038a"+
		"\u038b\5~@\2\u038b\u038c\b?\1\2\u038c\u038e\3\2\2\2\u038d\u0379\3\2\2"+
		"\2\u038d\u037d\3\2\2\2\u038d\u0381\3\2\2\2\u038d\u0385\3\2\2\2\u038d\u0389"+
		"\3\2\2\2\u038e\u0391\3\2\2\2\u038f\u038d\3\2\2\2\u038f\u0390\3\2\2\2\u0390"+
		"}\3\2\2\2\u0391\u038f\3\2\2\2\u0392\u0393\7?\2\2\u0393\u039a\5~@\2\u0394"+
		"\u0395\7@\2\2\u0395\u039a\5~@\2\u0396\u0397\7D\2\2\u0397\u039a\5~@\2\u0398"+
		"\u039a\5\u0080A\2\u0399\u0392\3\2\2\2\u0399\u0394\3\2\2\2\u0399\u0396"+
		"\3\2\2\2\u0399\u0398\3\2\2\2\u039a\177\3\2\2\2\u039b\u039f\5\u0082B\2"+
		"\u039c\u039e\5\u0086D\2\u039d\u039c\3\2\2\2\u039e\u03a1\3\2\2\2\u039f"+
		"\u039d\3\2\2\2\u039f\u03a0\3\2\2\2\u03a0\u03a4\3\2\2\2\u03a1\u039f\3\2"+
		"\2\2\u03a2\u03a3\7\66\2\2\u03a3\u03a5\5~@\2\u03a4\u03a2\3\2\2\2\u03a4"+
		"\u03a5\3\2\2\2\u03a5\u0081\3\2\2\2\u03a6\u03a9\7\61\2\2\u03a7\u03aa\5"+
		"\u00a0Q\2\u03a8\u03aa\5\u0084C\2\u03a9\u03a7\3\2\2\2\u03a9\u03a8\3\2\2"+
		"\2\u03a9\u03aa\3\2\2\2\u03aa\u03ab\3\2\2\2\u03ab\u03c2\7\62\2\2\u03ac"+
		"\u03ae\78\2\2\u03ad\u03af\5\u0084C\2\u03ae\u03ad\3\2\2\2\u03ae\u03af\3"+
		"\2\2\2\u03af\u03b0\3\2\2\2\u03b0\u03c2\79\2\2\u03b1\u03b3\7E\2\2\u03b2"+
		"\u03b4\5\u0092J\2\u03b3\u03b2\3\2\2\2\u03b3\u03b4\3\2\2\2\u03b4\u03b5"+
		"\3\2\2\2\u03b5\u03c2\7F\2\2\u03b6\u03c2\7%\2\2\u03b7\u03c2\5\u00a6T\2"+
		"\u03b8\u03ba\5\u00a4S\2\u03b9\u03b8\3\2\2\2\u03ba\u03bb\3\2\2\2\u03bb"+
		"\u03b9\3\2\2\2\u03bb\u03bc\3\2\2\2\u03bc\u03c2\3\2\2\2\u03bd\u03c2\7/"+
		"\2\2\u03be\u03c2\7\33\2\2\u03bf\u03c2\7\34\2\2\u03c0\u03c2\7\35\2\2\u03c1"+
		"\u03a6\3\2\2\2\u03c1\u03ac\3\2\2\2\u03c1\u03b1\3\2\2\2\u03c1\u03b6\3\2"+
		"\2\2\u03c1\u03b7\3\2\2\2\u03c1\u03b9\3\2\2\2\u03c1\u03bd\3\2\2\2\u03c1"+
		"\u03be\3\2\2\2\u03c1\u03bf\3\2\2\2\u03c1\u03c0\3\2\2\2\u03c2\u0083\3\2"+
		"\2\2\u03c3\u03c4\5^\60\2\u03c4\u03d2\bC\1\2\u03c5\u03d3\5\u009cO\2\u03c6"+
		"\u03c7\7\63\2\2\u03c7\u03c8\5^\60\2\u03c8\u03c9\bC\1\2\u03c9\u03cb\3\2"+
		"\2\2\u03ca\u03c6\3\2\2\2\u03cb\u03ce\3\2\2\2\u03cc\u03ca\3\2\2\2\u03cc"+
		"\u03cd\3\2\2\2\u03cd\u03d0\3\2\2\2\u03ce\u03cc\3\2\2\2\u03cf\u03d1\7\63"+
		"\2\2\u03d0\u03cf\3\2\2\2\u03d0\u03d1\3\2\2\2\u03d1\u03d3\3\2\2\2\u03d2"+
		"\u03c5\3\2\2\2\u03d2\u03cc\3\2\2\2\u03d3\u0085\3\2\2\2\u03d4\u03d6\7\61"+
		"\2\2\u03d5\u03d7\5\u0096L\2\u03d6\u03d5\3\2\2\2\u03d6\u03d7\3\2\2\2\u03d7"+
		"\u03d8\3\2\2\2\u03d8\u03e0\7\62\2\2\u03d9\u03da\78\2\2\u03da\u03db\5\u0088"+
		"E\2\u03db\u03dc\79\2\2\u03dc\u03e0\3\2\2\2\u03dd\u03de\7.\2\2\u03de\u03e0"+
		"\7%\2\2\u03df\u03d4\3\2\2\2\u03df\u03d9\3\2\2\2\u03df\u03dd\3\2\2\2\u03e0"+
		"\u0087\3\2\2\2\u03e1\u03e6\5\u008aF\2\u03e2\u03e3\7\63\2\2\u03e3\u03e5"+
		"\5\u008aF\2\u03e4\u03e2\3\2\2\2\u03e5\u03e8\3\2\2\2\u03e6\u03e4\3\2\2"+
		"\2\u03e6\u03e7\3\2\2\2\u03e7\u03ea\3\2\2\2\u03e8\u03e6\3\2\2\2\u03e9\u03eb"+
		"\7\63\2\2\u03ea\u03e9\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb\u0089\3\2\2\2"+
		"\u03ec\u03f8\5^\60\2\u03ed\u03ef\5^\60\2\u03ee\u03ed\3\2\2\2\u03ee\u03ef"+
		"\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0\u03f2\7\64\2\2\u03f1\u03f3\5^\60\2"+
		"\u03f2\u03f1\3\2\2\2\u03f2\u03f3\3\2\2\2\u03f3\u03f5\3\2\2\2\u03f4\u03f6"+
		"\5\u008cG\2\u03f5\u03f4\3\2\2\2\u03f5\u03f6\3\2\2\2\u03f6\u03f8\3\2\2"+
		"\2\u03f7\u03ec\3\2\2\2\u03f7\u03ee\3\2\2\2\u03f8\u008b\3\2\2\2\u03f9\u03fb"+
		"\7\64\2\2\u03fa\u03fc\5^\60\2\u03fb\u03fa\3\2\2\2\u03fb\u03fc\3\2\2\2"+
		"\u03fc\u008d\3\2\2\2\u03fd\u0402\5p9\2\u03fe\u03ff\7\63\2\2\u03ff\u0401"+
		"\5p9\2\u0400\u03fe\3\2\2\2\u0401\u0404\3\2\2\2\u0402\u0400\3\2\2\2\u0402"+
		"\u0403\3\2\2\2\u0403\u0406\3\2\2\2\u0404\u0402\3\2\2\2\u0405\u0407\7\63"+
		"\2\2\u0406\u0405\3\2\2\2\u0406\u0407\3\2\2\2\u0407\u008f\3\2\2\2\u0408"+
		"\u040d\5^\60\2\u0409\u040a\7\63\2\2\u040a\u040c\5^\60\2\u040b\u0409\3"+
		"\2\2\2\u040c\u040f\3\2\2\2\u040d\u040b\3\2\2\2\u040d\u040e\3\2\2\2\u040e"+
		"\u0411\3\2\2\2\u040f\u040d\3\2\2\2\u0410\u0412\7\63\2\2\u0411\u0410\3"+
		"\2\2\2\u0411\u0412\3\2\2\2\u0412\u0091\3\2\2\2\u0413\u0414\5^\60\2\u0414"+
		"\u0415\7\64\2\2\u0415\u0425\5^\60\2\u0416\u0426\5\u009cO\2\u0417\u0418"+
		"\7\63\2\2\u0418\u0419\5^\60\2\u0419\u041a\7\64\2\2\u041a\u041b\5^\60\2"+
		"\u041b\u041c\bJ\1\2\u041c\u041e\3\2\2\2\u041d\u0417\3\2\2\2\u041e\u0421"+
		"\3\2\2\2\u041f\u041d\3\2\2\2\u041f\u0420\3\2\2\2\u0420\u0423\3\2\2\2\u0421"+
		"\u041f\3\2\2\2\u0422\u0424\7\63\2\2\u0423\u0422\3\2\2\2\u0423\u0424\3"+
		"\2\2\2\u0424\u0426\3\2\2\2\u0425\u0416\3\2\2\2\u0425\u041f\3\2\2\2\u0426"+
		"\u0438\3\2\2\2\u0427\u0435\5^\60\2\u0428\u0436\5\u009cO\2\u0429\u042a"+
		"\7\63\2\2\u042a\u042b\5^\60\2\u042b\u042c\bJ\1\2\u042c\u042e\3\2\2\2\u042d"+
		"\u0429\3\2\2\2\u042e\u0431\3\2\2\2\u042f\u042d\3\2\2\2\u042f\u0430\3\2"+
		"\2\2\u0430\u0433\3\2\2\2\u0431\u042f\3\2\2\2\u0432\u0434\7\63\2\2\u0433"+
		"\u0432\3\2\2\2\u0433\u0434\3\2\2\2\u0434\u0436\3\2\2\2\u0435\u0428\3\2"+
		"\2\2\u0435\u042f\3\2\2\2\u0436\u0438\3\2\2\2\u0437\u0413\3\2\2\2\u0437"+
		"\u0427\3\2\2\2\u0438\u0093\3\2\2\2\u0439\u043a\7\36\2\2\u043a\u0440\7"+
		"%\2\2\u043b\u043d\7\61\2\2\u043c\u043e\5\u0096L\2\u043d\u043c\3\2\2\2"+
		"\u043d\u043e\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u0441\7\62\2\2\u0440\u043b"+
		"\3\2\2\2\u0440\u0441\3\2\2\2\u0441\u0442\3\2\2\2\u0442\u0443\7\64\2\2"+
		"\u0443\u0444\5\\/\2\u0444\u0095\3\2\2\2\u0445\u0446\5\u0098M\2\u0446\u0447"+
		"\7\63\2\2\u0447\u0448\bL\1\2\u0448\u044a\3\2\2\2\u0449\u0445\3\2\2\2\u044a"+
		"\u044d\3\2\2\2\u044b\u0449\3\2\2\2\u044b\u044c\3\2\2\2\u044c\u0465\3\2"+
		"\2\2\u044d\u044b\3\2\2\2\u044e\u044f\5\u0098M\2\u044f\u0451\bL\1\2\u0450"+
		"\u0452\7\63\2\2\u0451\u0450\3\2\2\2\u0451\u0452\3\2\2\2\u0452\u0466\3"+
		"\2\2\2\u0453\u0454\7\60\2\2\u0454\u045b\5^\60\2\u0455\u0456\7\63\2\2\u0456"+
		"\u0457\5\u0098M\2\u0457\u0458\bL\1\2\u0458\u045a\3\2\2\2\u0459\u0455\3"+
		"\2\2\2\u045a\u045d\3\2\2\2\u045b\u0459\3\2\2\2\u045b\u045c\3\2\2\2\u045c"+
		"\u0461\3\2\2\2\u045d\u045b\3\2\2\2\u045e\u045f\7\63\2\2\u045f\u0460\7"+
		"\66\2\2\u0460\u0462\5^\60\2\u0461\u045e\3\2\2\2\u0461\u0462\3\2\2\2\u0462"+
		"\u0466\3\2\2\2\u0463\u0464\7\66\2\2\u0464\u0466\5^\60\2\u0465\u044e\3"+
		"\2\2\2\u0465\u0453\3\2\2\2\u0465\u0463\3\2\2\2\u0466\u0097\3\2\2\2\u0467"+
		"\u0469\5^\60\2\u0468\u046a\5\u009cO\2\u0469\u0468\3\2\2\2\u0469\u046a"+
		"\3\2\2\2\u046a\u0470\3\2\2\2\u046b\u046c\5^\60\2\u046c\u046d\7\67\2\2"+
		"\u046d\u046e\5^\60\2\u046e\u0470\3\2\2\2\u046f\u0467\3\2\2\2\u046f\u046b"+
		"\3\2\2\2\u0470\u0099\3\2\2\2\u0471\u0474\5\u009cO\2\u0472\u0474\5\u009e"+
		"P\2\u0473\u0471\3\2\2\2\u0473\u0472\3\2\2\2\u0474\u009b\3\2\2\2\u0475"+
		"\u0476\7\20\2\2\u0476\u0477\5\u008eH\2\u0477\u0478\7\21\2\2\u0478\u047a"+
		"\5f\64\2\u0479\u047b\5\u009aN\2\u047a\u0479\3\2\2\2\u047a\u047b\3\2\2"+
		"\2\u047b\u009d\3\2\2\2\u047c\u047d\7\f\2\2\u047d\u047f\5`\61\2\u047e\u0480"+
		"\5\u009aN\2\u047f\u047e\3\2\2\2\u047f\u0480\3\2\2\2\u0480\u009f\3\2\2"+
		"\2\u0481\u0483\7\37\2\2\u0482\u0484\5\u00a2R\2\u0483\u0482\3\2\2\2\u0483"+
		"\u0484\3\2\2\2\u0484\u00a1\3\2\2\2\u0485\u0486\7\6\2\2\u0486\u0489\5^"+
		"\60\2\u0487\u0489\5\u0090I\2\u0488\u0485\3\2\2\2\u0488\u0487\3\2\2\2\u0489"+
		"\u00a3\3\2\2\2\u048a\u048b\t\3\2\2\u048b\u00a5\3\2\2\2\u048c\u0490\5\u00a8"+
		"U\2\u048d\u0490\7,\2\2\u048e\u0490\7-\2\2\u048f\u048c\3\2\2\2\u048f\u048d"+
		"\3\2\2\2\u048f\u048e\3\2\2\2\u0490\u00a7\3\2\2\2\u0491\u0492\t\4\2\2\u0492"+
		"\u00a9\3\2\2\2\u0099\u00af\u00b3\u00b5\u00be\u00c7\u00ca\u00d1\u00d6\u00dd"+
		"\u00e4\u00ee\u00f7\u00fb\u0101\u010b\u010f\u0117\u011d\u011f\u0123\u012d"+
		"\u0131\u0139\u013f\u0144\u014c\u0155\u0159\u015f\u0169\u016d\u0175\u017b"+
		"\u017d\u0181\u018b\u018f\u0197\u019d\u01a3\u01aa\u01ae\u01ba\u01c0\u01c5"+
		"\u01c9\u01cc\u01d0\u01d5\u01d9\u01dd\u01ec\u01f8\u0200\u0208\u020a\u020e"+
		"\u0218\u0220\u0222\u022b\u0230\u0235\u023c\u0240\u0247\u0251\u025c\u0267"+
		"\u026e\u0278\u0286\u028c\u0295\u02a0\u02ac\u02b1\u02b6\u02bb\u02c3\u02cc"+
		"\u02d2\u02d4\u02dc\u02e0\u02e8\u02eb\u02ef\u02f3\u02fa\u0307\u0312\u0318"+
		"\u0322\u033d\u0340\u0349\u0351\u0359\u0365\u0367\u0373\u0375\u038d\u038f"+
		"\u0399\u039f\u03a4\u03a9\u03ae\u03b3\u03bb\u03c1\u03cc\u03d0\u03d2\u03d6"+
		"\u03df\u03e6\u03ea\u03ee\u03f2\u03f5\u03f7\u03fb\u0402\u0406\u040d\u0411"+
		"\u041f\u0423\u0425\u042f\u0433\u0435\u0437\u043d\u0440\u044b\u0451\u045b"+
		"\u0461\u0465\u0469\u046f\u0473\u047a\u047f\u0483\u0488\u048f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}