package thesis;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
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
		Map<Integer, Integer> methods = new HashMap<>();
		Map<Integer, Integer> vars = new HashMap<>();
		for (String fileName : modules.keySet()) {
			model.Module module = modules.get(fileName);
			Collection<Class> pyClasses = module.getClasses();
			for (Class pyClass : pyClasses) {
				Map<String, Boolean> antipatterns = new HashMap<>();

				if (!methods.containsKey(pyClass.getAmountOfMethods())) {
					methods.put(pyClass.getAmountOfMethods(), 0);
				}
				methods.put(pyClass.getAmountOfMethods(), methods.get(pyClass.getAmountOfMethods()) + 1);

				if (!vars.containsKey(pyClass.getAmountOfVariables())) {
					vars.put(pyClass.getAmountOfVariables(), 0);
				}
				vars.put(pyClass.getAmountOfVariables(), vars.get(pyClass.getAmountOfVariables()) + 1);

				antipatterns.put("DATA CLASS", pyClass.isDataClass());
				antipatterns.put("BLOB", pyClass.isBlob());
				antipatterns.put("FUNCTIONAL DECOMPOSITION", pyClass.isFunctionalDecomposition());
				antipatterns.put("SPAGHETTI CODE", pyClass.isSpaghettiCode());
				antipatterns.put("SWISS ARMY KNIFE", pyClass.isSwissArmyKnife());

//				for (Arg a : pyClass.getParents()) {
//					System.out.println(a.getValue().getClass());
//				}

				for (String name : antipatterns.keySet()) {
					if (antipatterns.get(name)) {
						printAntipattern(name, fileName, pyClass.getName());
					}
				}
//
//				if (pyClass.noInheritance()) {
//					System.out.println("noInheritance " + pyClass.getName() + "  " + fileName);
//				}
//				if (pyClass.hasProceduralName()) {
//					System.out.println("hasProceduralName " + pyClass.getName() + "  " + fileName);
//				}
//				if (pyClass.hasLongMethod()) {
//					System.out.println("hasLongMethod " + pyClass.getName() + "  " + fileName);
//				}
//				if (pyClass.hasTooManyMethodsWithNoParams()) {
//					System.out.println("hasTooManyMethodsWithNoParams " + pyClass.getName() + "  " + fileName);
//				}
//				if (pyClass.usesGlobals()) {
//					System.out.println("usesGlobals " + pyClass.getName() + "  " + fileName);
//				}

//				System.out.println("IDENTIFIERS: ");
//				pyClass.getVariables().forEach(i -> System.out.println("\t" + i));
			}
		}
		System.out.println("METHODS:");
		for (Integer i : methods.keySet()) {
			System.out.println(i + "\t=> " + methods.get(i));
		}
		System.out.println("VARIABLES:");
		for (Integer i : vars.keySet()) {
			System.out.println(i + "\t=> " + vars.get(i));
		}
	}

	private static void printAntipattern(String antipatternName, String fileName, String className) {
		System.out.println("FOUND " + antipatternName + ": \n\tfile name: " + fileName + "\n\tclass: " + className);
	}

	public static List<Module> getTrees(List<String> filePaths) {
		List<Module> trees = new ArrayList<>();
		System.out.println("start");
		long startTime = System.nanoTime();
		for (String filePath : filePaths) {
			try {
				System.out.println("------------------------------ Parse ------------------------------");
				System.out.println("FILE: \t" + filePath);
				Module tree = parse(filePath);
				trees.add(tree);
				System.out.println("LOC: \t" + tree.getLocSpan());
				System.out.println("-------------------------------------------------------------------\n");
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
