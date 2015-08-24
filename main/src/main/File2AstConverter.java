package main;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
import util.StringHelper;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 05-08-2015
 */
public class File2AstConverter {

	public static Map<String, Module> getTrees(List<String> filePaths) {
		Map<String, Module> trees = new HashMap<>();
		for (String filePath : filePaths) {
			Module tree = File2AstConverter.parse(filePath);
			trees.put(filePath, tree);
		}
		return trees;
	}

	private static Module parse(String fileName) {
		try {
			org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
			Python3Lexer lexer = new Python3Lexer(input);

			org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
			Python3Parser parser = new Python3Parser(tokens);
			parser.setErrorHandler(new BailErrorStrategy());

			ParserRuleContext context = parser.file_input();
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
}
