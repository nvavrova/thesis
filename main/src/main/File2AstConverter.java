package main;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
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
			}
			catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return trees;
	}

	private static Module parse(String fileName) throws Exception {
		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder(context, fileName);
		return astBuilder.build();
	}
}
