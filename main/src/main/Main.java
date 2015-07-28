package main;

import ast.AstBuilder;
import ast.Module;
import db.DataHandler;
import gen.Python3Lexer;
import gen.Python3Parser;
import helpers.FileHelper;
import model.Class;
import model.ModelBuilder;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		File folder = new File(args[0]);
		List<String> filePaths = FileHelper.getPythonFilePaths(folder);

		List<Module> trees = getTrees(filePaths);

		ModelBuilder collector = new ModelBuilder(folder.getAbsolutePath(), trees);
		Map<String, model.Module> modules = collector.getModules();

		Set<Class> classes = new HashSet<>();

		for (String fileName : modules.keySet()) {
			model.Module module = modules.get(fileName);
			classes.addAll(module.getClasses());

			Collection<Class> pyClasses = module.getClasses();
			for (Class pyClass : pyClasses) {
				Map<String, Boolean> antipatterns = new HashMap<>();

				antipatterns.put("BLOB", pyClass.isBlob());
				antipatterns.put("FUNCTIONAL DECOMPOSITION", pyClass.isFunctionalDecomposition());
				antipatterns.put("SPAGHETTI CODE", pyClass.isSpaghettiCode());
				antipatterns.put("SWISS ARMY KNIFE", pyClass.isSwissArmyKnife());

				antipatterns.keySet().stream()
						.filter(name -> antipatterns.get(name))
						.forEach(name -> {
							printAntipattern(name, fileName, pyClass.getName());
						});
			}
		}
		DataHandler dataHandler = new DataHandler(folder.getName(), classes);
		dataHandler.save();
	}

	private static void printAntipattern(String antipatternName, String fileName, String className) {
		System.out.println("FOUND " + antipatternName + ": \n\tfile name: " + fileName + "\n\tclass: " + className);
	}

	public static List<Module> getTrees(List<String> filePaths) {
		List<Module> trees = new ArrayList<>();
		long startTime = System.nanoTime();
		for (String filePath : filePaths) {
			try {
				Module tree = parse(filePath);
				trees.add(tree);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000000;
		System.out.println("Duration: " + duration + "s");
		return trees;
	}

	public static Module parse(String fileName) throws Exception {
		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder(context, fileName);
		return astBuilder.build();
	}
}
