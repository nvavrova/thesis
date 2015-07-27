package tests;

import ast.AstBuilder;
import gen.Python3Lexer;
import gen.Python3Parser;
import model.Class;
import model.Module;
import model.ModelBuilder;
import org.antlr.v4.runtime.ParserRuleContext;
import helpers.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 08-07-2015
 */
public class TestHelper {

	public static ast.Module parseFile(String fileName) {
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

	public static Map<String, Module> getModulesByName(String fileName) {
		Map<String, Module> ms = TestHelper.getModules(fileName);
		Map<String, Module> modules = new HashMap<>();
		ms.values().forEach(m -> modules.put(m.getName(), m));
		return modules;
	}

	public static Map<String, Module> getModules(String fileName) {
		File file = new File(fileName);
		String parent = file.isDirectory() ? file.getAbsolutePath() : file.getParent();

		List<String> filePaths = new ArrayList<>();
		if (file.isDirectory()) {
			filePaths.addAll(FileHelper.getPythonFilePaths(file));
		}
		else {
			filePaths.add(fileName);
		}

		return TestHelper.getModules(parent, filePaths);
	}


	private static Map<String, Module> getModules(String parent, List<String> fileNames) {
		List<ast.Module> trees = new ArrayList<>();
		fileNames.stream().forEach(f -> trees.add(parseFile(f)));
		ModelBuilder collector = new ModelBuilder(parent, trees);
		return collector.getModules();
	}

	public static Map<String, Class> getClasses(String fileName) {
		Map<String, Module> modules = TestHelper.getModules(fileName);

		Map<String, Class> classes = new HashMap<>();
		modules.values().forEach(m -> m.getClasses().forEach(c -> classes.put(c.getName(), c)));
		return classes;
	}
}
