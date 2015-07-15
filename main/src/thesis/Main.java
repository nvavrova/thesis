package thesis;

import ast.AstBuilder;
import ast.Module;
import gen.Python3Lexer;
import gen.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		File folder = new File(args[0]);
		FileHelper fh = new FileHelper(folder);
		List<String> filePaths = fh.getPythonFilePaths();

		Map<String, Module> trees = getTrees(filePaths);

		ClassCollector collector = new ClassCollector(trees);
		Classes classes = collector.getClasses();
		for (String fileName : classes) {
			List<Class> pyClasses = classes.getClasses(fileName);
			for (Class pyClass : pyClasses) {
				Map<String, Boolean> antipatterns = new HashMap<>();
				antipatterns.put("BLOB", pyClass.isBlob());
				antipatterns.put("FUNCTIONAL DECOMPOSITION", pyClass.isFunctionalDecomposition());
				antipatterns.put("SPAGHETTI CODE", pyClass.isSpaghettiCode());
				antipatterns.put("SWISS ARMY KNIFE", pyClass.isSwissArmyKnife());

//				for (Arg a : pyClass.getOldparents()) {
//					System.out.println(a.getValue().getClass());
//				}

//				for (String name : antipatterns.keySet()) {
//					if (antipatterns.get(name)) {
//						printAntipattern(name, fileName, pyClass.getName());
//					}
//				}
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
	}

	private static void printAntipattern(String antipatternName, String fileName, String className) {
		System.out.println("FOUND " + antipatternName + ": \n\tfile name: " + fileName + "\n\tclass: " + className);
	}

	public static Map<String, Module> getTrees(List<String> filePaths) {
		Map<String, Module> trees = new HashMap<>();
		System.out.println("start");
		long startTime = System.nanoTime();
		for (String filePath : filePaths) {
			try {
				System.out.println("------------------------------ Parse ------------------------------");
				System.out.println("FILE: \t" + filePath);
				Module tree = parse(filePath);
				LocCoverageResolver.resolve(tree);
				trees.put(filePath, tree);
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
		AstBuilder astBuilder = new AstBuilder(context);
		return astBuilder.build();
	}
}
