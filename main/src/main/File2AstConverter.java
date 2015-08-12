package main;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
import helpers.StringHelper;
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
			try {
				Module tree = File2AstConverter.parse(filePath);
				trees.put(filePath, tree);
				if (tree.getErrors().size() > 0) {
					throw new Exception(StringHelper.implode(tree.getErrors(), "\n"));
				}
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace(System.err);
//				throw e;
			}
		}
		return trees;
	}

	private static Module parse(String fileName) throws Exception {
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
			m.addError("Parse Exception in " + fileName + ": \n" + StringHelper.getStackTraceString(ex));

			return m;
		}
	}
}
