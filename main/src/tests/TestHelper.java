package tests;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import thesis.Class;
import thesis.ClassCollector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 08-07-2015
 */
public class TestHelper {

	public static Module parseFile(String fileName) {
		org.antlr.v4.runtime.CharStream input = null;
		try {
			input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder(context);
		return astBuilder.build();
	}

	public static List<Class> getClasses(String fileName) {
		Module m = parseFile(fileName);
		Map<String, Module> trees = new HashMap<>();
		trees.put(fileName, m);
		ClassCollector collector = new ClassCollector(trees);
		return collector.getClasses().getClasses(fileName);
	}
}
