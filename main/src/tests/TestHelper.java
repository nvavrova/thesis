package tests;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
import model.Class;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		AstBuilder astBuilder = new AstBuilder(context, fileName);
		return astBuilder.build();
	}

	public static List<Class> getClasses(String fileName) {
		Module m = parseFile(fileName);
		List<Module> trees = new ArrayList<>();
		trees.add(m);
//		ModelBuilder collector = new ModelBuilder(trees);
//		return collector.getModules().getClasses(fileName);
		//TODO
		return null;
	}
}
