package thesis;

import ast.AstBuilder;
import ast.Py3Node;
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

		Map<String, Py3Node> trees = getTrees(filePaths);

		ClassCollector collector = new ClassCollector(trees.values());
		Classes classes = collector.getClasses();
		for (Class pyClass : classes) {
			if (pyClass.isBlob()) {
				System.out.println("FOUND BLOB");
			}
		}
	}

	public static Map<String, Py3Node> getTrees(List<String> filePaths) {
		Map<String, Py3Node> trees = new HashMap<>();
		System.out.println("start");
		long startTime = System.nanoTime();
		for (String filePath : filePaths) {
			try {
                System.out.println("------------------------------ Parse ------------------------------");
				System.out.println("FILE: \t" + filePath);
                Py3Node tree = parse(filePath);
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

	public static Py3Node parse(String fileName) throws Exception {
		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder(context);
		return astBuilder.build();
	}
}
