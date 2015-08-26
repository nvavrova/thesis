// Generated from D:/intellij_projects/thesis/main/grammar\Python.g4 by ANTLR 4.5.1
package gen;

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PythonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, DEF=3, RETURN=4, RAISE=5, FROM=6, IMPORT=7, AS=8, GLOBAL=9, 
		NONLOCAL=10, EXEC=11, ASSERT=12, IF=13, ELIF=14, ELSE=15, WHILE=16, FOR=17, 
		IN=18, TRY=19, FINALLY=20, WITH=21, EXCEPT=22, LAMBDA=23, OR=24, AND=25, 
		NOT=26, IS=27, NONE=28, TRUE=29, FALSE=30, CLASS=31, YIELD=32, DEL=33, 
		PASS=34, CONTINUE=35, BREAK=36, NEWLINE=37, NAME=38, STRING_LITERAL=39, 
		BYTES_LITERAL=40, DECIMAL_INTEGER=41, OCT_INTEGER=42, HEX_INTEGER=43, 
		BIN_INTEGER=44, FLOAT_NUMBER=45, IMAG_NUMBER=46, DOT=47, ELLIPSIS=48, 
		STAR=49, OPEN_PAREN=50, CLOSE_PAREN=51, COMMA=52, COLON=53, SEMI_COLON=54, 
		POWER=55, ASSIGN=56, OPEN_BRACK=57, CLOSE_BRACK=58, OR_OP=59, XOR=60, 
		AND_OP=61, LEFT_SHIFT=62, RIGHT_SHIFT=63, ADD=64, MINUS=65, DIV=66, MOD=67, 
		IDIV=68, NOT_OP=69, OPEN_BRACE=70, CLOSE_BRACE=71, LESS_THAN=72, GREATER_THAN=73, 
		EQUALS=74, GT_EQ=75, LT_EQ=76, NOT_EQ_1=77, NOT_EQ_2=78, AT=79, ARROW=80, 
		ADD_ASSIGN=81, SUB_ASSIGN=82, MULT_ASSIGN=83, AT_ASSIGN=84, DIV_ASSIGN=85, 
		MOD_ASSIGN=86, AND_ASSIGN=87, OR_ASSIGN=88, XOR_ASSIGN=89, LEFT_SHIFT_ASSIGN=90, 
		RIGHT_SHIFT_ASSIGN=91, POWER_ASSIGN=92, IDIV_ASSIGN=93, SKIP=94, COMMENT=95, 
		UNKNOWN_CHAR=96;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "DEF", "RETURN", "RAISE", "FROM", "IMPORT", "AS", "GLOBAL", 
		"NONLOCAL", "EXEC", "ASSERT", "IF", "ELIF", "ELSE", "WHILE", "FOR", "IN", 
		"TRY", "FINALLY", "WITH", "EXCEPT", "LAMBDA", "OR", "AND", "NOT", "IS", 
		"NONE", "TRUE", "FALSE", "CLASS", "YIELD", "DEL", "PASS", "CONTINUE", 
		"BREAK", "NEWLINE", "NAME", "STRING_LITERAL", "BYTES_LITERAL", "DECIMAL_INTEGER", 
		"OCT_INTEGER", "HEX_INTEGER", "BIN_INTEGER", "FLOAT_NUMBER", "IMAG_NUMBER", 
		"DOT", "ELLIPSIS", "STAR", "OPEN_PAREN", "CLOSE_PAREN", "COMMA", "COLON", 
		"SEMI_COLON", "POWER", "ASSIGN", "OPEN_BRACK", "CLOSE_BRACK", "OR_OP", 
		"XOR", "AND_OP", "LEFT_SHIFT", "RIGHT_SHIFT", "ADD", "MINUS", "DIV", "MOD", 
		"IDIV", "NOT_OP", "OPEN_BRACE", "CLOSE_BRACE", "LESS_THAN", "GREATER_THAN", 
		"EQUALS", "GT_EQ", "LT_EQ", "NOT_EQ_1", "NOT_EQ_2", "AT", "ARROW", "ADD_ASSIGN", 
		"SUB_ASSIGN", "MULT_ASSIGN", "AT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", 
		"AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", 
		"POWER_ASSIGN", "IDIV_ASSIGN", "SKIP", "COMMENT", "UNKNOWN_CHAR", "SHORT_STRING", 
		"LONG_STRING", "LONG_STRING_ITEM", "LONG_STRING_CHAR", "STRING_ESCAPE_SEQ", 
		"NON_ZERO_DIGIT", "DIGIT", "OCT_DIGIT", "HEX_DIGIT", "BIN_DIGIT", "POINT_FLOAT", 
		"EXPONENT_FLOAT", "INT_PART", "FRACTION", "EXPONENT", "SHORT_BYTES", "LONG_BYTES", 
		"LONG_BYTES_ITEM", "SHORT_BYTES_CHAR_NO_SINGLE_QUOTE", "SHORT_BYTES_CHAR_NO_DOUBLE_QUOTE", 
		"LONG_BYTES_CHAR", "BYTES_ESCAPE_SEQ", "SPACES", "LINE_JOINING", "ID_START", 
		"ID_CONTINUE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'print'", "'`'", "'def'", "'return'", "'raise'", "'from'", "'import'", 
		"'as'", "'global'", "'nonlocal'", "'exec'", "'assert'", "'if'", "'elif'", 
		"'else'", "'while'", "'for'", "'in'", "'try'", "'finally'", "'with'", 
		"'except'", "'lambda'", "'or'", "'and'", "'not'", "'is'", "'None'", "'True'", 
		"'False'", "'class'", "'yield'", "'del'", "'pass'", "'continue'", "'break'", 
		null, null, null, null, null, null, null, null, null, null, "'.'", "'...'", 
		"'*'", "'('", "')'", "','", "':'", "';'", "'**'", "'='", "'['", "']'", 
		"'|'", "'^'", "'&'", "'<<'", "'>>'", "'+'", "'-'", "'/'", "'%'", "'//'", 
		"'~'", "'{'", "'}'", "'<'", "'>'", "'=='", "'>='", "'<='", "'<>'", "'!='", 
		"'@'", "'->'", "'+='", "'-='", "'*='", "'@='", "'/='", "'%='", "'&='", 
		"'|='", "'^='", "'<<='", "'>>='", "'**='", "'//='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "DEF", "RETURN", "RAISE", "FROM", "IMPORT", "AS", "GLOBAL", 
		"NONLOCAL", "EXEC", "ASSERT", "IF", "ELIF", "ELSE", "WHILE", "FOR", "IN", 
		"TRY", "FINALLY", "WITH", "EXCEPT", "LAMBDA", "OR", "AND", "NOT", "IS", 
		"NONE", "TRUE", "FALSE", "CLASS", "YIELD", "DEL", "PASS", "CONTINUE", 
		"BREAK", "NEWLINE", "NAME", "STRING_LITERAL", "BYTES_LITERAL", "DECIMAL_INTEGER", 
		"OCT_INTEGER", "HEX_INTEGER", "BIN_INTEGER", "FLOAT_NUMBER", "IMAG_NUMBER", 
		"DOT", "ELLIPSIS", "STAR", "OPEN_PAREN", "CLOSE_PAREN", "COMMA", "COLON", 
		"SEMI_COLON", "POWER", "ASSIGN", "OPEN_BRACK", "CLOSE_BRACK", "OR_OP", 
		"XOR", "AND_OP", "LEFT_SHIFT", "RIGHT_SHIFT", "ADD", "MINUS", "DIV", "MOD", 
		"IDIV", "NOT_OP", "OPEN_BRACE", "CLOSE_BRACE", "LESS_THAN", "GREATER_THAN", 
		"EQUALS", "GT_EQ", "LT_EQ", "NOT_EQ_1", "NOT_EQ_2", "AT", "ARROW", "ADD_ASSIGN", 
		"SUB_ASSIGN", "MULT_ASSIGN", "AT_ASSIGN", "DIV_ASSIGN", "MOD_ASSIGN", 
		"AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "LEFT_SHIFT_ASSIGN", "RIGHT_SHIFT_ASSIGN", 
		"POWER_ASSIGN", "IDIV_ASSIGN", "SKIP", "COMMENT", "UNKNOWN_CHAR"
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

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}



	  // A queue where extra tokens are pushed on (see the NEWLINE lexer rule).
	  private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();

	  // The stack that keeps track of the indentation level.
	  private java.util.Stack<Integer> indents = new java.util.Stack<>();

	  // The amount of opened braces, brackets and parenthesis.
	  private int opened = 0;

	  // The most recently produced token.
	  private Token lastToken = null;

	  @Override
	  public void emit(Token t) {
	    super.setToken(t);
	    tokens.offer(t);
	  }

	  @Override
	  public Token nextToken() {

	    // Check if the end-of-file is ahead and there are still some DEDENTS expected.
	    if (_input.LA(1) == EOF && !this.indents.isEmpty()) {

	      // Remove any trailing EOF tokens from our buffer.
	      for (int i = tokens.size() - 1; i >= 0; i--) {
	        if (tokens.get(i).getType() == EOF) {
	          tokens.remove(i);
	        }
	      }

	      // First emit an extra line break that serves as the end of the statement.
	      this.emit(commonToken(PythonParser.NEWLINE, "\n"));

	      // Now emit as much DEDENT tokens as needed.
	      while (!indents.isEmpty()) {
	        this.emit(createDedent());
	        indents.pop();
	      }

	      // Put the EOF back on the token stream.
	      this.emit(commonToken(PythonParser.EOF, "<EOF>"));
	    }

	    Token next = super.nextToken();

	    if (next.getChannel() == Token.DEFAULT_CHANNEL) {
	      // Keep track of the last token on the default channel.
	      this.lastToken = next;
	    }

	    return tokens.isEmpty() ? next : tokens.poll();
	  }

	  private Token createDedent() {
	    CommonToken dedent = commonToken(PythonParser.DEDENT, "");
	    dedent.setLine(this.lastToken.getLine());
	    return dedent;
	  }

	  private CommonToken commonToken(int type, String text) {
	    int stop = this.getCharIndex() - 1;
	    int start = text.isEmpty() ? stop : stop - text.length() + 1;
	    return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
	  }

	  // Calculates the indentation of the provided spaces, taking the
	  // following rules into account:
	  //
	  // "Tabs are replaced (from left to right) by one to eight spaces
	  //  such that the total number of characters up to and including
	  //  the replacement is a multiple of eight [...]"
	  //
	  //  -- https://docs.python.org/3.1/reference/lexical_analysis.html#indentation
	  static int getIndentationCount(String spaces) {

	    int count = 0;

	    for (char ch : spaces.toCharArray()) {
	      switch (ch) {
	        case '\t':
	          count += 8 - (count % 8);
	          break;
	        default:
	          // A normal space char.
	          count++;
	      }
	    }

	    return count;
	  }

	  boolean atStartOfInput() {
	    return super.getCharPositionInLine() == 0 && super.getLine() == 1;
	  }


	public PythonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Python.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 36:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			OPEN_PAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			CLOSE_PAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 56:
			OPEN_BRACK_action((RuleContext)_localctx, actionIndex);
			break;
		case 57:
			CLOSE_BRACK_action((RuleContext)_localctx, actionIndex);
			break;
		case 69:
			OPEN_BRACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 70:
			CLOSE_BRACE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			     String newLine = getText().replaceAll("[^\r\n]+", "");
			     String spaces = getText().replaceAll("[\r\n]+", "");
			     int next = _input.LA(1);

			     if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
			       // If we're inside a list or on a blank line, ignore all indents,
			       // dedents and line breaks.
			       skip();
			     }
			     else {
			       emit(commonToken(NEWLINE, newLine));

			       int indent = getIndentationCount(spaces);
			       int previous = indents.isEmpty() ? 0 : indents.peek();

			       if (indent == previous) {
			         // skip indents of the same size as the present indent-size
			         skip();
			       }
			       else if (indent > previous) {
			         indents.push(indent);
			         emit(commonToken(PythonParser.INDENT, spaces));
			       }
			       else {
			         // Possibly emit more than 1 DEDENT token.
			         while(!indents.isEmpty() && indents.peek() > indent) {
			           this.emit(createDedent());
			           indents.pop();
			         }
			       }
			     }
			   
			break;
		}
	}
	private void OPEN_PAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			opened++;
			break;
		}
	}
	private void CLOSE_PAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			opened--;
			break;
		}
	}
	private void OPEN_BRACK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			opened++;
			break;
		}
	}
	private void CLOSE_BRACK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			opened--;
			break;
		}
	}
	private void OPEN_BRACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			opened++;
			break;
		}
	}
	private void CLOSE_BRACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			opened--;
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 36:
			return NEWLINE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean NEWLINE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return atStartOfInput();
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2b\u035c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!"+
		"\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3%\3&\3&\3&\5&\u01ba\n&\3&\3&\5&\u01be\n&\3&\5&\u01c1"+
		"\n&\5&\u01c3\n&\3&\3&\3\'\3\'\7\'\u01c9\n\'\f\'\16\'\u01cc\13\'\3(\5("+
		"\u01cf\n(\3(\5(\u01d2\n(\3(\3(\5(\u01d6\n(\3)\3)\5)\u01da\n)\3)\3)\5)"+
		"\u01de\n)\3*\3*\7*\u01e2\n*\f*\16*\u01e5\13*\3*\6*\u01e8\n*\r*\16*\u01e9"+
		"\5*\u01ec\n*\3+\3+\3+\6+\u01f1\n+\r+\16+\u01f2\3,\3,\3,\6,\u01f8\n,\r"+
		",\16,\u01f9\3-\3-\3-\6-\u01ff\n-\r-\16-\u0200\3.\3.\5.\u0205\n.\3/\3/"+
		"\5/\u0209\n/\3/\3/\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3\63\3\63\3"+
		"\63\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\38\39\39\3:\3:"+
		"\3:\3;\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3?\3@\3@\3@\3A\3A\3B\3B\3C\3C\3D"+
		"\3D\3E\3E\3E\3F\3F\3G\3G\3G\3H\3H\3H\3I\3I\3J\3J\3K\3K\3K\3L\3L\3L\3M"+
		"\3M\3M\3N\3N\3N\3O\3O\3O\3P\3P\3Q\3Q\3Q\3R\3R\3R\3S\3S\3S\3T\3T\3T\3U"+
		"\3U\3U\3V\3V\3V\3W\3W\3W\3X\3X\3X\3Y\3Y\3Y\3Z\3Z\3Z\3[\3[\3[\3[\3\\\3"+
		"\\\3\\\3\\\3]\3]\3]\3]\3^\3^\3^\3^\3_\3_\3_\5_\u0291\n_\3_\3_\3`\3`\7"+
		"`\u0297\n`\f`\16`\u029a\13`\3a\3a\3b\3b\3b\3b\7b\u02a2\nb\fb\16b\u02a5"+
		"\13b\3b\3b\3b\3b\3b\7b\u02ac\nb\fb\16b\u02af\13b\3b\5b\u02b2\nb\3c\3c"+
		"\3c\3c\3c\7c\u02b9\nc\fc\16c\u02bc\13c\3c\3c\3c\3c\3c\3c\3c\3c\7c\u02c6"+
		"\nc\fc\16c\u02c9\13c\3c\3c\3c\5c\u02ce\nc\3d\3d\5d\u02d2\nd\3e\3e\3f\3"+
		"f\3f\3g\3g\3h\3h\3i\3i\3j\3j\3k\3k\3l\5l\u02e4\nl\3l\3l\3l\3l\5l\u02ea"+
		"\nl\3m\3m\5m\u02ee\nm\3m\3m\3n\6n\u02f3\nn\rn\16n\u02f4\3o\3o\6o\u02f9"+
		"\no\ro\16o\u02fa\3p\3p\5p\u02ff\np\3p\6p\u0302\np\rp\16p\u0303\3q\3q\3"+
		"q\7q\u0309\nq\fq\16q\u030c\13q\3q\3q\3q\3q\7q\u0312\nq\fq\16q\u0315\13"+
		"q\3q\5q\u0318\nq\3r\3r\3r\3r\3r\7r\u031f\nr\fr\16r\u0322\13r\3r\3r\3r"+
		"\3r\3r\3r\3r\3r\7r\u032c\nr\fr\16r\u032f\13r\3r\3r\3r\5r\u0334\nr\3s\3"+
		"s\5s\u0338\ns\3t\5t\u033b\nt\3u\5u\u033e\nu\3v\5v\u0341\nv\3w\3w\3w\3"+
		"x\6x\u0347\nx\rx\16x\u0348\3y\3y\5y\u034d\ny\3y\5y\u0350\ny\3y\3y\5y\u0354"+
		"\ny\3z\5z\u0357\nz\3{\3{\5{\u035b\n{\6\u02ba\u02c7\u0320\u032d\2|\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s"+
		";u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008f"+
		"I\u0091J\u0093K\u0095L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3"+
		"S\u00a5T\u00a7U\u00a9V\u00abW\u00adX\u00afY\u00b1Z\u00b3[\u00b5\\\u00b7"+
		"]\u00b9^\u00bb_\u00bd`\u00bfa\u00c1b\u00c3\2\u00c5\2\u00c7\2\u00c9\2\u00cb"+
		"\2\u00cd\2\u00cf\2\u00d1\2\u00d3\2\u00d5\2\u00d7\2\u00d9\2\u00db\2\u00dd"+
		"\2\u00df\2\u00e1\2\u00e3\2\u00e5\2\u00e7\2\u00e9\2\u00eb\2\u00ed\2\u00ef"+
		"\2\u00f1\2\u00f3\2\u00f5\2\3\2\32\4\2WWww\4\2TTtt\4\2DDdd\4\2QQqq\4\2"+
		"ZZzz\4\2LLll\4\2\f\f\17\17\6\2\f\f\17\17))^^\6\2\f\f\17\17$$^^\3\2^^\3"+
		"\2\63;\3\2\62;\3\2\629\5\2\62;CHch\3\2\62\63\4\2GGgg\4\2--//\7\2\2\13"+
		"\r\16\20(*]_\u0081\7\2\2\13\r\16\20#%]_\u0081\4\2\2]_\u0081\3\2\2\u0081"+
		"\4\2\13\13\"\"\u0129\2C\\aac|\u00ac\u00ac\u00b7\u00b7\u00bc\u00bc\u00c2"+
		"\u00d8\u00da\u00f8\u00fa\u0243\u0252\u02c3\u02c8\u02d3\u02e2\u02e6\u02f0"+
		"\u02f0\u037c\u037c\u0388\u0388\u038a\u038c\u038e\u038e\u0390\u03a3\u03a5"+
		"\u03d0\u03d2\u03f7\u03f9\u0483\u048c\u04d0\u04d2\u04fb\u0502\u0511\u0533"+
		"\u0558\u055b\u055b\u0563\u0589\u05d2\u05ec\u05f2\u05f4\u0623\u063c\u0642"+
		"\u064c\u0670\u0671\u0673\u06d5\u06d7\u06d7\u06e7\u06e8\u06f0\u06f1\u06fc"+
		"\u06fe\u0701\u0701\u0712\u0712\u0714\u0731\u074f\u076f\u0782\u07a7\u07b3"+
		"\u07b3\u0906\u093b\u093f\u093f\u0952\u0952\u095a\u0963\u097f\u097f\u0987"+
		"\u098e\u0991\u0992\u0995\u09aa\u09ac\u09b2\u09b4\u09b4\u09b8\u09bb\u09bf"+
		"\u09bf\u09d0\u09d0\u09de\u09df\u09e1\u09e3\u09f2\u09f3\u0a07\u0a0c\u0a11"+
		"\u0a12\u0a15\u0a2a\u0a2c\u0a32\u0a34\u0a35\u0a37\u0a38\u0a3a\u0a3b\u0a5b"+
		"\u0a5e\u0a60\u0a60\u0a74\u0a76\u0a87\u0a8f\u0a91\u0a93\u0a95\u0aaa\u0aac"+
		"\u0ab2\u0ab4\u0ab5\u0ab7\u0abb\u0abf\u0abf\u0ad2\u0ad2\u0ae2\u0ae3\u0b07"+
		"\u0b0e\u0b11\u0b12\u0b15\u0b2a\u0b2c\u0b32\u0b34\u0b35\u0b37\u0b3b\u0b3f"+
		"\u0b3f\u0b5e\u0b5f\u0b61\u0b63\u0b73\u0b73\u0b85\u0b85\u0b87\u0b8c\u0b90"+
		"\u0b92\u0b94\u0b97\u0b9b\u0b9c\u0b9e\u0b9e\u0ba0\u0ba1\u0ba5\u0ba6\u0baa"+
		"\u0bac\u0bb0\u0bbb\u0c07\u0c0e\u0c10\u0c12\u0c14\u0c2a\u0c2c\u0c35\u0c37"+
		"\u0c3b\u0c62\u0c63\u0c87\u0c8e\u0c90\u0c92\u0c94\u0caa\u0cac\u0cb5\u0cb7"+
		"\u0cbb\u0cbf\u0cbf\u0ce0\u0ce0\u0ce2\u0ce3\u0d07\u0d0e\u0d10\u0d12\u0d14"+
		"\u0d2a\u0d2c\u0d3b\u0d62\u0d63\u0d87\u0d98\u0d9c\u0db3\u0db5\u0dbd\u0dbf"+
		"\u0dbf\u0dc2\u0dc8\u0e03\u0e32\u0e34\u0e35\u0e42\u0e48\u0e83\u0e84\u0e86"+
		"\u0e86\u0e89\u0e8a\u0e8c\u0e8c\u0e8f\u0e8f\u0e96\u0e99\u0e9b\u0ea1\u0ea3"+
		"\u0ea5\u0ea7\u0ea7\u0ea9\u0ea9\u0eac\u0ead\u0eaf\u0eb2\u0eb4\u0eb5\u0ebf"+
		"\u0ebf\u0ec2\u0ec6\u0ec8\u0ec8\u0ede\u0edf\u0f02\u0f02\u0f42\u0f49\u0f4b"+
		"\u0f6c\u0f8a\u0f8d\u1002\u1023\u1025\u1029\u102b\u102c\u1052\u1057\u10a2"+
		"\u10c7\u10d2\u10fc\u10fe\u10fe\u1102\u115b\u1161\u11a4\u11aa\u11fb\u1202"+
		"\u124a\u124c\u124f\u1252\u1258\u125a\u125a\u125c\u125f\u1262\u128a\u128c"+
		"\u128f\u1292\u12b2\u12b4\u12b7\u12ba\u12c0\u12c2\u12c2\u12c4\u12c7\u12ca"+
		"\u12d8\u12da\u1312\u1314\u1317\u131a\u135c\u1382\u1391\u13a2\u13f6\u1403"+
		"\u166e\u1671\u1678\u1683\u169c\u16a2\u16ec\u16f0\u16f2\u1702\u170e\u1710"+
		"\u1713\u1722\u1733\u1742\u1753\u1762\u176e\u1770\u1772\u1782\u17b5\u17d9"+
		"\u17d9\u17de\u17de\u1822\u1879\u1882\u18aa\u1902\u191e\u1952\u196f\u1972"+
		"\u1976\u1982\u19ab\u19c3\u19c9\u1a02\u1a18\u1d02\u1dc1\u1e02\u1e9d\u1ea2"+
		"\u1efb\u1f02\u1f17\u1f1a\u1f1f\u1f22\u1f47\u1f4a\u1f4f\u1f52\u1f59\u1f5b"+
		"\u1f5b\u1f5d\u1f5d\u1f5f\u1f5f\u1f61\u1f7f\u1f82\u1fb6\u1fb8\u1fbe\u1fc0"+
		"\u1fc0\u1fc4\u1fc6\u1fc8\u1fce\u1fd2\u1fd5\u1fd8\u1fdd\u1fe2\u1fee\u1ff4"+
		"\u1ff6\u1ff8\u1ffe\u2073\u2073\u2081\u2081\u2092\u2096\u2104\u2104\u2109"+
		"\u2109\u210c\u2115\u2117\u2117\u211a\u211f\u2126\u2126\u2128\u2128\u212a"+
		"\u212a\u212c\u2133\u2135\u213b\u213e\u2141\u2147\u214b\u2162\u2185\u2c02"+
		"\u2c30\u2c32\u2c60\u2c82\u2ce6\u2d02\u2d27\u2d32\u2d67\u2d71\u2d71\u2d82"+
		"\u2d98\u2da2\u2da8\u2daa\u2db0\u2db2\u2db8\u2dba\u2dc0\u2dc2\u2dc8\u2dca"+
		"\u2dd0\u2dd2\u2dd8\u2dda\u2de0\u3007\u3009\u3023\u302b\u3033\u3037\u303a"+
		"\u303e\u3043\u3098\u309d\u30a1\u30a3\u30fc\u30fe\u3101\u3107\u312e\u3133"+
		"\u3190\u31a2\u31b9\u31f2\u3201\u3402\u4db7\u4e02\u9fbd\ua002\ua48e\ua802"+
		"\ua803\ua805\ua807\ua809\ua80c\ua80e\ua824\uac02\ud7a5\uf902\ufa2f\ufa32"+
		"\ufa6c\ufa72\ufadb\ufb02\ufb08\ufb15\ufb19\ufb1f\ufb1f\ufb21\ufb2a\ufb2c"+
		"\ufb38\ufb3a\ufb3e\ufb40\ufb40\ufb42\ufb43\ufb45\ufb46\ufb48\ufbb3\ufbd5"+
		"\ufd3f\ufd52\ufd91\ufd94\ufdc9\ufdf2\ufdfd\ufe72\ufe76\ufe78\ufefe\uff23"+
		"\uff3c\uff43\uff5c\uff68\uffc0\uffc4\uffc9\uffcc\uffd1\uffd4\uffd9\uffdc"+
		"\uffde\u0096\2\62;\u0302\u0371\u0485\u0488\u0593\u05bb\u05bd\u05bf\u05c1"+
		"\u05c1\u05c3\u05c4\u05c6\u05c7\u05c9\u05c9\u0612\u0617\u064d\u0660\u0662"+
		"\u066b\u0672\u0672\u06d8\u06de\u06e1\u06e6\u06e9\u06ea\u06ec\u06ef\u06f2"+
		"\u06fb\u0713\u0713\u0732\u074c\u07a8\u07b2\u0903\u0905\u093e\u093e\u0940"+
		"\u094f\u0953\u0956\u0964\u0965\u0968\u0971\u0983\u0985\u09be\u09be\u09c0"+
		"\u09c6\u09c9\u09ca\u09cd\u09cf\u09d9\u09d9\u09e4\u09e5\u09e8\u09f1\u0a03"+
		"\u0a05\u0a3e\u0a3e\u0a40\u0a44\u0a49\u0a4a\u0a4d\u0a4f\u0a68\u0a73\u0a83"+
		"\u0a85\u0abe\u0abe\u0ac0\u0ac7\u0ac9\u0acb\u0acd\u0acf\u0ae4\u0ae5\u0ae8"+
		"\u0af1\u0b03\u0b05\u0b3e\u0b3e\u0b40\u0b45\u0b49\u0b4a\u0b4d\u0b4f\u0b58"+
		"\u0b59\u0b68\u0b71\u0b84\u0b84\u0bc0\u0bc4\u0bc8\u0bca\u0bcc\u0bcf\u0bd9"+
		"\u0bd9\u0be8\u0bf1\u0c03\u0c05\u0c40\u0c46\u0c48\u0c4a\u0c4c\u0c4f\u0c57"+
		"\u0c58\u0c68\u0c71\u0c84\u0c85\u0cbe\u0cbe\u0cc0\u0cc6\u0cc8\u0cca\u0ccc"+
		"\u0ccf\u0cd7\u0cd8\u0ce8\u0cf1\u0d04\u0d05\u0d40\u0d45\u0d48\u0d4a\u0d4c"+
		"\u0d4f\u0d59\u0d59\u0d68\u0d71\u0d84\u0d85\u0dcc\u0dcc\u0dd1\u0dd6\u0dd8"+
		"\u0dd8\u0dda\u0de1\u0df4\u0df5\u0e33\u0e33\u0e36\u0e3c\u0e49\u0e50\u0e52"+
		"\u0e5b\u0eb3\u0eb3\u0eb6\u0ebb\u0ebd\u0ebe\u0eca\u0ecf\u0ed2\u0edb\u0f1a"+
		"\u0f1b\u0f22\u0f2b\u0f37\u0f37\u0f39\u0f39\u0f3b\u0f3b\u0f40\u0f41\u0f73"+
		"\u0f86\u0f88\u0f89\u0f92\u0f99\u0f9b\u0fbe\u0fc8\u0fc8\u102e\u1034\u1038"+
		"\u103b\u1042\u104b\u1058\u105b\u1361\u1361\u136b\u1373\u1714\u1716\u1734"+
		"\u1736\u1754\u1755\u1774\u1775\u17b8\u17d5\u17df\u17df\u17e2\u17eb\u180d"+
		"\u180f\u1812\u181b\u18ab\u18ab\u1922\u192d\u1932\u193d\u1948\u1951\u19b2"+
		"\u19c2\u19ca\u19cb\u19d2\u19db\u1a19\u1a1d\u1dc2\u1dc5\u2041\u2042\u2056"+
		"\u2056\u20d2\u20de\u20e3\u20e3\u20e7\u20ed\u302c\u3031\u309b\u309c\ua804"+
		"\ua804\ua808\ua808\ua80d\ua80d\ua825\ua829\ufb20\ufb20\ufe02\ufe11\ufe22"+
		"\ufe25\ufe35\ufe36\ufe4f\ufe51\uff12\uff1b\uff41\uff41\u0376\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3"+
		"\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2"+
		"\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2"+
		"}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2"+
		"\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2"+
		"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1"+
		"\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2"+
		"\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3"+
		"\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2"+
		"\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\3\u00f7\3\2\2\2\5\u00fd"+
		"\3\2\2\2\7\u00ff\3\2\2\2\t\u0103\3\2\2\2\13\u010a\3\2\2\2\r\u0110\3\2"+
		"\2\2\17\u0115\3\2\2\2\21\u011c\3\2\2\2\23\u011f\3\2\2\2\25\u0126\3\2\2"+
		"\2\27\u012f\3\2\2\2\31\u0134\3\2\2\2\33\u013b\3\2\2\2\35\u013e\3\2\2\2"+
		"\37\u0143\3\2\2\2!\u0148\3\2\2\2#\u014e\3\2\2\2%\u0152\3\2\2\2\'\u0155"+
		"\3\2\2\2)\u0159\3\2\2\2+\u0161\3\2\2\2-\u0166\3\2\2\2/\u016d\3\2\2\2\61"+
		"\u0174\3\2\2\2\63\u0177\3\2\2\2\65\u017b\3\2\2\2\67\u017f\3\2\2\29\u0182"+
		"\3\2\2\2;\u0187\3\2\2\2=\u018c\3\2\2\2?\u0192\3\2\2\2A\u0198\3\2\2\2C"+
		"\u019e\3\2\2\2E\u01a2\3\2\2\2G\u01a7\3\2\2\2I\u01b0\3\2\2\2K\u01c2\3\2"+
		"\2\2M\u01c6\3\2\2\2O\u01ce\3\2\2\2Q\u01d7\3\2\2\2S\u01eb\3\2\2\2U\u01ed"+
		"\3\2\2\2W\u01f4\3\2\2\2Y\u01fb\3\2\2\2[\u0204\3\2\2\2]\u0208\3\2\2\2_"+
		"\u020c\3\2\2\2a\u020e\3\2\2\2c\u0212\3\2\2\2e\u0214\3\2\2\2g\u0217\3\2"+
		"\2\2i\u021a\3\2\2\2k\u021c\3\2\2\2m\u021e\3\2\2\2o\u0220\3\2\2\2q\u0223"+
		"\3\2\2\2s\u0225\3\2\2\2u\u0228\3\2\2\2w\u022b\3\2\2\2y\u022d\3\2\2\2{"+
		"\u022f\3\2\2\2}\u0231\3\2\2\2\177\u0234\3\2\2\2\u0081\u0237\3\2\2\2\u0083"+
		"\u0239\3\2\2\2\u0085\u023b\3\2\2\2\u0087\u023d\3\2\2\2\u0089\u023f\3\2"+
		"\2\2\u008b\u0242\3\2\2\2\u008d\u0244\3\2\2\2\u008f\u0247\3\2\2\2\u0091"+
		"\u024a\3\2\2\2\u0093\u024c\3\2\2\2\u0095\u024e\3\2\2\2\u0097\u0251\3\2"+
		"\2\2\u0099\u0254\3\2\2\2\u009b\u0257\3\2\2\2\u009d\u025a\3\2\2\2\u009f"+
		"\u025d\3\2\2\2\u00a1\u025f\3\2\2\2\u00a3\u0262\3\2\2\2\u00a5\u0265\3\2"+
		"\2\2\u00a7\u0268\3\2\2\2\u00a9\u026b\3\2\2\2\u00ab\u026e\3\2\2\2\u00ad"+
		"\u0271\3\2\2\2\u00af\u0274\3\2\2\2\u00b1\u0277\3\2\2\2\u00b3\u027a\3\2"+
		"\2\2\u00b5\u027d\3\2\2\2\u00b7\u0281\3\2\2\2\u00b9\u0285\3\2\2\2\u00bb"+
		"\u0289\3\2\2\2\u00bd\u0290\3\2\2\2\u00bf\u0294\3\2\2\2\u00c1\u029b\3\2"+
		"\2\2\u00c3\u02b1\3\2\2\2\u00c5\u02cd\3\2\2\2\u00c7\u02d1\3\2\2\2\u00c9"+
		"\u02d3\3\2\2\2\u00cb\u02d5\3\2\2\2\u00cd\u02d8\3\2\2\2\u00cf\u02da\3\2"+
		"\2\2\u00d1\u02dc\3\2\2\2\u00d3\u02de\3\2\2\2\u00d5\u02e0\3\2\2\2\u00d7"+
		"\u02e9\3\2\2\2\u00d9\u02ed\3\2\2\2\u00db\u02f2\3\2\2\2\u00dd\u02f6\3\2"+
		"\2\2\u00df\u02fc\3\2\2\2\u00e1\u0317\3\2\2\2\u00e3\u0333\3\2\2\2\u00e5"+
		"\u0337\3\2\2\2\u00e7\u033a\3\2\2\2\u00e9\u033d\3\2\2\2\u00eb\u0340\3\2"+
		"\2\2\u00ed\u0342\3\2\2\2\u00ef\u0346\3\2\2\2\u00f1\u034a\3\2\2\2\u00f3"+
		"\u0356\3\2\2\2\u00f5\u035a\3\2\2\2\u00f7\u00f8\7r\2\2\u00f8\u00f9\7t\2"+
		"\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7v\2\2\u00fc\4\3"+
		"\2\2\2\u00fd\u00fe\7b\2\2\u00fe\6\3\2\2\2\u00ff\u0100\7f\2\2\u0100\u0101"+
		"\7g\2\2\u0101\u0102\7h\2\2\u0102\b\3\2\2\2\u0103\u0104\7t\2\2\u0104\u0105"+
		"\7g\2\2\u0105\u0106\7v\2\2\u0106\u0107\7w\2\2\u0107\u0108\7t\2\2\u0108"+
		"\u0109\7p\2\2\u0109\n\3\2\2\2\u010a\u010b\7t\2\2\u010b\u010c\7c\2\2\u010c"+
		"\u010d\7k\2\2\u010d\u010e\7u\2\2\u010e\u010f\7g\2\2\u010f\f\3\2\2\2\u0110"+
		"\u0111\7h\2\2\u0111\u0112\7t\2\2\u0112\u0113\7q\2\2\u0113\u0114\7o\2\2"+
		"\u0114\16\3\2\2\2\u0115\u0116\7k\2\2\u0116\u0117\7o\2\2\u0117\u0118\7"+
		"r\2\2\u0118\u0119\7q\2\2\u0119\u011a\7t\2\2\u011a\u011b\7v\2\2\u011b\20"+
		"\3\2\2\2\u011c\u011d\7c\2\2\u011d\u011e\7u\2\2\u011e\22\3\2\2\2\u011f"+
		"\u0120\7i\2\2\u0120\u0121\7n\2\2\u0121\u0122\7q\2\2\u0122\u0123\7d\2\2"+
		"\u0123\u0124\7c\2\2\u0124\u0125\7n\2\2\u0125\24\3\2\2\2\u0126\u0127\7"+
		"p\2\2\u0127\u0128\7q\2\2\u0128\u0129\7p\2\2\u0129\u012a\7n\2\2\u012a\u012b"+
		"\7q\2\2\u012b\u012c\7e\2\2\u012c\u012d\7c\2\2\u012d\u012e\7n\2\2\u012e"+
		"\26\3\2\2\2\u012f\u0130\7g\2\2\u0130\u0131\7z\2\2\u0131\u0132\7g\2\2\u0132"+
		"\u0133\7e\2\2\u0133\30\3\2\2\2\u0134\u0135\7c\2\2\u0135\u0136\7u\2\2\u0136"+
		"\u0137\7u\2\2\u0137\u0138\7g\2\2\u0138\u0139\7t\2\2\u0139\u013a\7v\2\2"+
		"\u013a\32\3\2\2\2\u013b\u013c\7k\2\2\u013c\u013d\7h\2\2\u013d\34\3\2\2"+
		"\2\u013e\u013f\7g\2\2\u013f\u0140\7n\2\2\u0140\u0141\7k\2\2\u0141\u0142"+
		"\7h\2\2\u0142\36\3\2\2\2\u0143\u0144\7g\2\2\u0144\u0145\7n\2\2\u0145\u0146"+
		"\7u\2\2\u0146\u0147\7g\2\2\u0147 \3\2\2\2\u0148\u0149\7y\2\2\u0149\u014a"+
		"\7j\2\2\u014a\u014b\7k\2\2\u014b\u014c\7n\2\2\u014c\u014d\7g\2\2\u014d"+
		"\"\3\2\2\2\u014e\u014f\7h\2\2\u014f\u0150\7q\2\2\u0150\u0151\7t\2\2\u0151"+
		"$\3\2\2\2\u0152\u0153\7k\2\2\u0153\u0154\7p\2\2\u0154&\3\2\2\2\u0155\u0156"+
		"\7v\2\2\u0156\u0157\7t\2\2\u0157\u0158\7{\2\2\u0158(\3\2\2\2\u0159\u015a"+
		"\7h\2\2\u015a\u015b\7k\2\2\u015b\u015c\7p\2\2\u015c\u015d\7c\2\2\u015d"+
		"\u015e\7n\2\2\u015e\u015f\7n\2\2\u015f\u0160\7{\2\2\u0160*\3\2\2\2\u0161"+
		"\u0162\7y\2\2\u0162\u0163\7k\2\2\u0163\u0164\7v\2\2\u0164\u0165\7j\2\2"+
		"\u0165,\3\2\2\2\u0166\u0167\7g\2\2\u0167\u0168\7z\2\2\u0168\u0169\7e\2"+
		"\2\u0169\u016a\7g\2\2\u016a\u016b\7r\2\2\u016b\u016c\7v\2\2\u016c.\3\2"+
		"\2\2\u016d\u016e\7n\2\2\u016e\u016f\7c\2\2\u016f\u0170\7o\2\2\u0170\u0171"+
		"\7d\2\2\u0171\u0172\7f\2\2\u0172\u0173\7c\2\2\u0173\60\3\2\2\2\u0174\u0175"+
		"\7q\2\2\u0175\u0176\7t\2\2\u0176\62\3\2\2\2\u0177\u0178\7c\2\2\u0178\u0179"+
		"\7p\2\2\u0179\u017a\7f\2\2\u017a\64\3\2\2\2\u017b\u017c\7p\2\2\u017c\u017d"+
		"\7q\2\2\u017d\u017e\7v\2\2\u017e\66\3\2\2\2\u017f\u0180\7k\2\2\u0180\u0181"+
		"\7u\2\2\u01818\3\2\2\2\u0182\u0183\7P\2\2\u0183\u0184\7q\2\2\u0184\u0185"+
		"\7p\2\2\u0185\u0186\7g\2\2\u0186:\3\2\2\2\u0187\u0188\7V\2\2\u0188\u0189"+
		"\7t\2\2\u0189\u018a\7w\2\2\u018a\u018b\7g\2\2\u018b<\3\2\2\2\u018c\u018d"+
		"\7H\2\2\u018d\u018e\7c\2\2\u018e\u018f\7n\2\2\u018f\u0190\7u\2\2\u0190"+
		"\u0191\7g\2\2\u0191>\3\2\2\2\u0192\u0193\7e\2\2\u0193\u0194\7n\2\2\u0194"+
		"\u0195\7c\2\2\u0195\u0196\7u\2\2\u0196\u0197\7u\2\2\u0197@\3\2\2\2\u0198"+
		"\u0199\7{\2\2\u0199\u019a\7k\2\2\u019a\u019b\7g\2\2\u019b\u019c\7n\2\2"+
		"\u019c\u019d\7f\2\2\u019dB\3\2\2\2\u019e\u019f\7f\2\2\u019f\u01a0\7g\2"+
		"\2\u01a0\u01a1\7n\2\2\u01a1D\3\2\2\2\u01a2\u01a3\7r\2\2\u01a3\u01a4\7"+
		"c\2\2\u01a4\u01a5\7u\2\2\u01a5\u01a6\7u\2\2\u01a6F\3\2\2\2\u01a7\u01a8"+
		"\7e\2\2\u01a8\u01a9\7q\2\2\u01a9\u01aa\7p\2\2\u01aa\u01ab\7v\2\2\u01ab"+
		"\u01ac\7k\2\2\u01ac\u01ad\7p\2\2\u01ad\u01ae\7w\2\2\u01ae\u01af\7g\2\2"+
		"\u01afH\3\2\2\2\u01b0\u01b1\7d\2\2\u01b1\u01b2\7t\2\2\u01b2\u01b3\7g\2"+
		"\2\u01b3\u01b4\7c\2\2\u01b4\u01b5\7m\2\2\u01b5J\3\2\2\2\u01b6\u01b7\6"+
		"&\2\2\u01b7\u01c3\5\u00efx\2\u01b8\u01ba\7\17\2\2\u01b9\u01b8\3\2\2\2"+
		"\u01b9\u01ba\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01be\7\f\2\2\u01bc\u01be"+
		"\7\17\2\2\u01bd\u01b9\3\2\2\2\u01bd\u01bc\3\2\2\2\u01be\u01c0\3\2\2\2"+
		"\u01bf\u01c1\5\u00efx\2\u01c0\u01bf\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1"+
		"\u01c3\3\2\2\2\u01c2\u01b6\3\2\2\2\u01c2\u01bd\3\2\2\2\u01c3\u01c4\3\2"+
		"\2\2\u01c4\u01c5\b&\2\2\u01c5L\3\2\2\2\u01c6\u01ca\5\u00f3z\2\u01c7\u01c9"+
		"\5\u00f5{\2\u01c8\u01c7\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01c8\3\2\2"+
		"\2\u01ca\u01cb\3\2\2\2\u01cbN\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01cf"+
		"\t\2\2\2\u01ce\u01cd\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d1\3\2\2\2\u01d0"+
		"\u01d2\t\3\2\2\u01d1\u01d0\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d5\3\2"+
		"\2\2\u01d3\u01d6\5\u00c3b\2\u01d4\u01d6\5\u00c5c\2\u01d5\u01d3\3\2\2\2"+
		"\u01d5\u01d4\3\2\2\2\u01d6P\3\2\2\2\u01d7\u01d9\t\4\2\2\u01d8\u01da\t"+
		"\3\2\2\u01d9\u01d8\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01dd\3\2\2\2\u01db"+
		"\u01de\5\u00e1q\2\u01dc\u01de\5\u00e3r\2\u01dd\u01db\3\2\2\2\u01dd\u01dc"+
		"\3\2\2\2\u01deR\3\2\2\2\u01df\u01e3\5\u00cdg\2\u01e0\u01e2\5\u00cfh\2"+
		"\u01e1\u01e0\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3\u01e4"+
		"\3\2\2\2\u01e4\u01ec\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6\u01e8\7\62\2\2"+
		"\u01e7\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea"+
		"\3\2\2\2\u01ea\u01ec\3\2\2\2\u01eb\u01df\3\2\2\2\u01eb\u01e7\3\2\2\2\u01ec"+
		"T\3\2\2\2\u01ed\u01ee\7\62\2\2\u01ee\u01f0\t\5\2\2\u01ef\u01f1\5\u00d1"+
		"i\2\u01f0\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2"+
		"\u01f3\3\2\2\2\u01f3V\3\2\2\2\u01f4\u01f5\7\62\2\2\u01f5\u01f7\t\6\2\2"+
		"\u01f6\u01f8\5\u00d3j\2\u01f7\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9"+
		"\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01faX\3\2\2\2\u01fb\u01fc\7\62\2\2"+
		"\u01fc\u01fe\t\4\2\2\u01fd\u01ff\5\u00d5k\2\u01fe\u01fd\3\2\2\2\u01ff"+
		"\u0200\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201Z\3\2\2\2"+
		"\u0202\u0205\5\u00d7l\2\u0203\u0205\5\u00d9m\2\u0204\u0202\3\2\2\2\u0204"+
		"\u0203\3\2\2\2\u0205\\\3\2\2\2\u0206\u0209\5[.\2\u0207\u0209\5\u00dbn"+
		"\2\u0208\u0206\3\2\2\2\u0208\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u020b"+
		"\t\7\2\2\u020b^\3\2\2\2\u020c\u020d\7\60\2\2\u020d`\3\2\2\2\u020e\u020f"+
		"\7\60\2\2\u020f\u0210\7\60\2\2\u0210\u0211\7\60\2\2\u0211b\3\2\2\2\u0212"+
		"\u0213\7,\2\2\u0213d\3\2\2\2\u0214\u0215\7*\2\2\u0215\u0216\b\63\3\2\u0216"+
		"f\3\2\2\2\u0217\u0218\7+\2\2\u0218\u0219\b\64\4\2\u0219h\3\2\2\2\u021a"+
		"\u021b\7.\2\2\u021bj\3\2\2\2\u021c\u021d\7<\2\2\u021dl\3\2\2\2\u021e\u021f"+
		"\7=\2\2\u021fn\3\2\2\2\u0220\u0221\7,\2\2\u0221\u0222\7,\2\2\u0222p\3"+
		"\2\2\2\u0223\u0224\7?\2\2\u0224r\3\2\2\2\u0225\u0226\7]\2\2\u0226\u0227"+
		"\b:\5\2\u0227t\3\2\2\2\u0228\u0229\7_\2\2\u0229\u022a\b;\6\2\u022av\3"+
		"\2\2\2\u022b\u022c\7~\2\2\u022cx\3\2\2\2\u022d\u022e\7`\2\2\u022ez\3\2"+
		"\2\2\u022f\u0230\7(\2\2\u0230|\3\2\2\2\u0231\u0232\7>\2\2\u0232\u0233"+
		"\7>\2\2\u0233~\3\2\2\2\u0234\u0235\7@\2\2\u0235\u0236\7@\2\2\u0236\u0080"+
		"\3\2\2\2\u0237\u0238\7-\2\2\u0238\u0082\3\2\2\2\u0239\u023a\7/\2\2\u023a"+
		"\u0084\3\2\2\2\u023b\u023c\7\61\2\2\u023c\u0086\3\2\2\2\u023d\u023e\7"+
		"\'\2\2\u023e\u0088\3\2\2\2\u023f\u0240\7\61\2\2\u0240\u0241\7\61\2\2\u0241"+
		"\u008a\3\2\2\2\u0242\u0243\7\u0080\2\2\u0243\u008c\3\2\2\2\u0244\u0245"+
		"\7}\2\2\u0245\u0246\bG\7\2\u0246\u008e\3\2\2\2\u0247\u0248\7\177\2\2\u0248"+
		"\u0249\bH\b\2\u0249\u0090\3\2\2\2\u024a\u024b\7>\2\2\u024b\u0092\3\2\2"+
		"\2\u024c\u024d\7@\2\2\u024d\u0094\3\2\2\2\u024e\u024f\7?\2\2\u024f\u0250"+
		"\7?\2\2\u0250\u0096\3\2\2\2\u0251\u0252\7@\2\2\u0252\u0253\7?\2\2\u0253"+
		"\u0098\3\2\2\2\u0254\u0255\7>\2\2\u0255\u0256\7?\2\2\u0256\u009a\3\2\2"+
		"\2\u0257\u0258\7>\2\2\u0258\u0259\7@\2\2\u0259\u009c\3\2\2\2\u025a\u025b"+
		"\7#\2\2\u025b\u025c\7?\2\2\u025c\u009e\3\2\2\2\u025d\u025e\7B\2\2\u025e"+
		"\u00a0\3\2\2\2\u025f\u0260\7/\2\2\u0260\u0261\7@\2\2\u0261\u00a2\3\2\2"+
		"\2\u0262\u0263\7-\2\2\u0263\u0264\7?\2\2\u0264\u00a4\3\2\2\2\u0265\u0266"+
		"\7/\2\2\u0266\u0267\7?\2\2\u0267\u00a6\3\2\2\2\u0268\u0269\7,\2\2\u0269"+
		"\u026a\7?\2\2\u026a\u00a8\3\2\2\2\u026b\u026c\7B\2\2\u026c\u026d\7?\2"+
		"\2\u026d\u00aa\3\2\2\2\u026e\u026f\7\61\2\2\u026f\u0270\7?\2\2\u0270\u00ac"+
		"\3\2\2\2\u0271\u0272\7\'\2\2\u0272\u0273\7?\2\2\u0273\u00ae\3\2\2\2\u0274"+
		"\u0275\7(\2\2\u0275\u0276\7?\2\2\u0276\u00b0\3\2\2\2\u0277\u0278\7~\2"+
		"\2\u0278\u0279\7?\2\2\u0279\u00b2\3\2\2\2\u027a\u027b\7`\2\2\u027b\u027c"+
		"\7?\2\2\u027c\u00b4\3\2\2\2\u027d\u027e\7>\2\2\u027e\u027f\7>\2\2\u027f"+
		"\u0280\7?\2\2\u0280\u00b6\3\2\2\2\u0281\u0282\7@\2\2\u0282\u0283\7@\2"+
		"\2\u0283\u0284\7?\2\2\u0284\u00b8\3\2\2\2\u0285\u0286\7,\2\2\u0286\u0287"+
		"\7,\2\2\u0287\u0288\7?\2\2\u0288\u00ba\3\2\2\2\u0289\u028a\7\61\2\2\u028a"+
		"\u028b\7\61\2\2\u028b\u028c\7?\2\2\u028c\u00bc\3\2\2\2\u028d\u0291\5\u00ef"+
		"x\2\u028e\u0291\5\u00bf`\2\u028f\u0291\5\u00f1y\2\u0290\u028d\3\2\2\2"+
		"\u0290\u028e\3\2\2\2\u0290\u028f\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0293"+
		"\b_\t\2\u0293\u00be\3\2\2\2\u0294\u0298\7%\2\2\u0295\u0297\n\b\2\2\u0296"+
		"\u0295\3\2\2\2\u0297\u029a\3\2\2\2\u0298\u0296\3\2\2\2\u0298\u0299\3\2"+
		"\2\2\u0299\u00c0\3\2\2\2\u029a\u0298\3\2\2\2\u029b\u029c\13\2\2\2\u029c"+
		"\u00c2\3\2\2\2\u029d\u02a3\7)\2\2\u029e\u02a2\5\u00cbf\2\u029f\u02a2\5"+
		"\u00f1y\2\u02a0\u02a2\n\t\2\2\u02a1\u029e\3\2\2\2\u02a1\u029f\3\2\2\2"+
		"\u02a1\u02a0\3\2\2\2\u02a2\u02a5\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a3\u02a4"+
		"\3\2\2\2\u02a4\u02a6\3\2\2\2\u02a5\u02a3\3\2\2\2\u02a6\u02b2\7)\2\2\u02a7"+
		"\u02ad\7$\2\2\u02a8\u02ac\5\u00cbf\2\u02a9\u02ac\5\u00f1y\2\u02aa\u02ac"+
		"\n\n\2\2\u02ab\u02a8\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ab\u02aa\3\2\2\2\u02ac"+
		"\u02af\3\2\2\2\u02ad\u02ab\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\u02b0\3\2"+
		"\2\2\u02af\u02ad\3\2\2\2\u02b0\u02b2\7$\2\2\u02b1\u029d\3\2\2\2\u02b1"+
		"\u02a7\3\2\2\2\u02b2\u00c4\3\2\2\2\u02b3\u02b4\7)\2\2\u02b4\u02b5\7)\2"+
		"\2\u02b5\u02b6\7)\2\2\u02b6\u02ba\3\2\2\2\u02b7\u02b9\5\u00c7d\2\u02b8"+
		"\u02b7\3\2\2\2\u02b9\u02bc\3\2\2\2\u02ba\u02bb\3\2\2\2\u02ba\u02b8\3\2"+
		"\2\2\u02bb\u02bd\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bd\u02be\7)\2\2\u02be"+
		"\u02bf\7)\2\2\u02bf\u02ce\7)\2\2\u02c0\u02c1\7$\2\2\u02c1\u02c2\7$\2\2"+
		"\u02c2\u02c3\7$\2\2\u02c3\u02c7\3\2\2\2\u02c4\u02c6\5\u00c7d\2\u02c5\u02c4"+
		"\3\2\2\2\u02c6\u02c9\3\2\2\2\u02c7\u02c8\3\2\2\2\u02c7\u02c5\3\2\2\2\u02c8"+
		"\u02ca\3\2\2\2\u02c9\u02c7\3\2\2\2\u02ca\u02cb\7$\2\2\u02cb\u02cc\7$\2"+
		"\2\u02cc\u02ce\7$\2\2\u02cd\u02b3\3\2\2\2\u02cd\u02c0\3\2\2\2\u02ce\u00c6"+
		"\3\2\2\2\u02cf\u02d2\5\u00c9e\2\u02d0\u02d2\5\u00cbf\2\u02d1\u02cf\3\2"+
		"\2\2\u02d1\u02d0\3\2\2\2\u02d2\u00c8\3\2\2\2\u02d3\u02d4\n\13\2\2\u02d4"+
		"\u00ca\3\2\2\2\u02d5\u02d6\7^\2\2\u02d6\u02d7\13\2\2\2\u02d7\u00cc\3\2"+
		"\2\2\u02d8\u02d9\t\f\2\2\u02d9\u00ce\3\2\2\2\u02da\u02db\t\r\2\2\u02db"+
		"\u00d0\3\2\2\2\u02dc\u02dd\t\16\2\2\u02dd\u00d2\3\2\2\2\u02de\u02df\t"+
		"\17\2\2\u02df\u00d4\3\2\2\2\u02e0\u02e1\t\20\2\2\u02e1\u00d6\3\2\2\2\u02e2"+
		"\u02e4\5\u00dbn\2\u02e3\u02e2\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02e5"+
		"\3\2\2\2\u02e5\u02ea\5\u00ddo\2\u02e6\u02e7\5\u00dbn\2\u02e7\u02e8\7\60"+
		"\2\2\u02e8\u02ea\3\2\2\2\u02e9\u02e3\3\2\2\2\u02e9\u02e6\3\2\2\2\u02ea"+
		"\u00d8\3\2\2\2\u02eb\u02ee\5\u00dbn\2\u02ec\u02ee\5\u00d7l\2\u02ed\u02eb"+
		"\3\2\2\2\u02ed\u02ec\3\2\2\2\u02ee\u02ef\3\2\2\2\u02ef\u02f0\5\u00dfp"+
		"\2\u02f0\u00da\3\2\2\2\u02f1\u02f3\5\u00cfh\2\u02f2\u02f1\3\2\2\2\u02f3"+
		"\u02f4\3\2\2\2\u02f4\u02f2\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5\u00dc\3\2"+
		"\2\2\u02f6\u02f8\7\60\2\2\u02f7\u02f9\5\u00cfh\2\u02f8\u02f7\3\2\2\2\u02f9"+
		"\u02fa\3\2\2\2\u02fa\u02f8\3\2\2\2\u02fa\u02fb\3\2\2\2\u02fb\u00de\3\2"+
		"\2\2\u02fc\u02fe\t\21\2\2\u02fd\u02ff\t\22\2\2\u02fe\u02fd\3\2\2\2\u02fe"+
		"\u02ff\3\2\2\2\u02ff\u0301\3\2\2\2\u0300\u0302\5\u00cfh\2\u0301\u0300"+
		"\3\2\2\2\u0302\u0303\3\2\2\2\u0303\u0301\3\2\2\2\u0303\u0304\3\2\2\2\u0304"+
		"\u00e0\3\2\2\2\u0305\u030a\7)\2\2\u0306\u0309\5\u00e7t\2\u0307\u0309\5"+
		"\u00edw\2\u0308\u0306\3\2\2\2\u0308\u0307\3\2\2\2\u0309\u030c\3\2\2\2"+
		"\u030a\u0308\3\2\2\2\u030a\u030b\3\2\2\2\u030b\u030d\3\2\2\2\u030c\u030a"+
		"\3\2\2\2\u030d\u0318\7)\2\2\u030e\u0313\7$\2\2\u030f\u0312\5\u00e9u\2"+
		"\u0310\u0312\5\u00edw\2\u0311\u030f\3\2\2\2\u0311\u0310\3\2\2\2\u0312"+
		"\u0315\3\2\2\2\u0313\u0311\3\2\2\2\u0313\u0314\3\2\2\2\u0314\u0316\3\2"+
		"\2\2\u0315\u0313\3\2\2\2\u0316\u0318\7$\2\2\u0317\u0305\3\2\2\2\u0317"+
		"\u030e\3\2\2\2\u0318\u00e2\3\2\2\2\u0319\u031a\7)\2\2\u031a\u031b\7)\2"+
		"\2\u031b\u031c\7)\2\2\u031c\u0320\3\2\2\2\u031d\u031f\5\u00e5s\2\u031e"+
		"\u031d\3\2\2\2\u031f\u0322\3\2\2\2\u0320\u0321\3\2\2\2\u0320\u031e\3\2"+
		"\2\2\u0321\u0323\3\2\2\2\u0322\u0320\3\2\2\2\u0323\u0324\7)\2\2\u0324"+
		"\u0325\7)\2\2\u0325\u0334\7)\2\2\u0326\u0327\7$\2\2\u0327\u0328\7$\2\2"+
		"\u0328\u0329\7$\2\2\u0329\u032d\3\2\2\2\u032a\u032c\5\u00e5s\2\u032b\u032a"+
		"\3\2\2\2\u032c\u032f\3\2\2\2\u032d\u032e\3\2\2\2\u032d\u032b\3\2\2\2\u032e"+
		"\u0330\3\2\2\2\u032f\u032d\3\2\2\2\u0330\u0331\7$\2\2\u0331\u0332\7$\2"+
		"\2\u0332\u0334\7$\2\2\u0333\u0319\3\2\2\2\u0333\u0326\3\2\2\2\u0334\u00e4"+
		"\3\2\2\2\u0335\u0338\5\u00ebv\2\u0336\u0338\5\u00edw\2\u0337\u0335\3\2"+
		"\2\2\u0337\u0336\3\2\2\2\u0338\u00e6\3\2\2\2\u0339\u033b\t\23\2\2\u033a"+
		"\u0339\3\2\2\2\u033b\u00e8\3\2\2\2\u033c\u033e\t\24\2\2\u033d\u033c\3"+
		"\2\2\2\u033e\u00ea\3\2\2\2\u033f\u0341\t\25\2\2\u0340\u033f\3\2\2\2\u0341"+
		"\u00ec\3\2\2\2\u0342\u0343\7^\2\2\u0343\u0344\t\26\2\2\u0344\u00ee\3\2"+
		"\2\2\u0345\u0347\t\27\2\2\u0346\u0345\3\2\2\2\u0347\u0348\3\2\2\2\u0348"+
		"\u0346\3\2\2\2\u0348\u0349\3\2\2\2\u0349\u00f0\3\2\2\2\u034a\u034c\7^"+
		"\2\2\u034b\u034d\5\u00efx\2\u034c\u034b\3\2\2\2\u034c\u034d\3\2\2\2\u034d"+
		"\u0353\3\2\2\2\u034e\u0350\7\17\2\2\u034f\u034e\3\2\2\2\u034f\u0350\3"+
		"\2\2\2\u0350\u0351\3\2\2\2\u0351\u0354\7\f\2\2\u0352\u0354\7\17\2\2\u0353"+
		"\u034f\3\2\2\2\u0353\u0352\3\2\2\2\u0354\u00f2\3\2\2\2\u0355\u0357\t\30"+
		"\2\2\u0356\u0355\3\2\2\2\u0357\u00f4\3\2\2\2\u0358\u035b\5\u00f3z\2\u0359"+
		"\u035b\t\31\2\2\u035a\u0358\3\2\2\2\u035a\u0359\3\2\2\2\u035b\u00f6\3"+
		"\2\2\29\2\u01b9\u01bd\u01c0\u01c2\u01ca\u01ce\u01d1\u01d5\u01d9\u01dd"+
		"\u01e3\u01e9\u01eb\u01f2\u01f9\u0200\u0204\u0208\u0290\u0298\u02a1\u02a3"+
		"\u02ab\u02ad\u02b1\u02ba\u02c7\u02cd\u02d1\u02e3\u02e9\u02ed\u02f4\u02fa"+
		"\u02fe\u0303\u0308\u030a\u0311\u0313\u0317\u0320\u032d\u0333\u0337\u033a"+
		"\u033d\u0340\u0348\u034c\u034f\u0353\u0356\u035a\n\3&\2\3\63\3\3\64\4"+
		"\3:\5\3;\6\3G\7\3H\b\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}