package process;

import ast.AstBuilder;
import ast.Module;
import gen.PythonLexer;
import gen.PythonParser;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import util.FileOpener;
import util.StringHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 05-08-2015
 */
public class File2Tree {

	public static Map<String, Module> getAsts(List<String> filePaths) {
		Map<String, Module> trees = new HashMap<>();
		for (String filePath : filePaths) {
			Module tree = File2Tree.parseAndBuildAst(filePath);
			trees.put(filePath, tree);
		}
		return trees;
	}

	public static ParserRuleContext getParseTree(String fileName) throws Exception {
		FileOpener fo = new FileOpener(fileName);
		String contents = fo.getTrimmedContents();

		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(contents);
		PythonLexer lexer = new PythonLexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		PythonParser parser = new PythonParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());

		try {
			ParserRuleContext context = parser.file_input();
			File2Tree.unlink(lexer, parser);
			return context;
		}
		catch (Exception ex) {
			File2Tree.unlink(lexer, parser);
			throw ex;
		}
	}

	private static Module parseAndBuildAst(String fileName) {
		try {
			ParserRuleContext context = File2Tree.getParseTree(fileName);
			AstBuilder astBuilder = new AstBuilder(context, fileName);
			return astBuilder.build();
		}
		catch (Exception ex) {
			Module m = AstBuilder.buildErrorModule(fileName);
			String errorMsg = "Parse Exception in " + fileName + ": \n" + StringHelper.getStackTraceString(ex);
			m.addError(errorMsg);

			System.err.println(errorMsg);
			return m;
		}
	}

	private static void unlink(PythonLexer lexer, PythonParser parser) {
		ATNSimulator simulator = parser.getInterpreter();
		simulator.clearDFA();
		lexer.setInterpreter(new LexerATNSimulator(lexer, lexer.getATN(), lexer.getInterpreter().decisionToDFA, new PredictionContextCache()));
		parser.setInterpreter(new ParserATNSimulator(parser, parser.getATN(), parser.getInterpreter().decisionToDFA, new PredictionContextCache()));
	}
}
