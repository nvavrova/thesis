package thesis;

import ast.AstBuilder;
import ast.Py3Node;
import gen.Python3Lexer;
import gen.Python3Parser;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.python.antlr.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		File folder = new File(args[0]);
		FileHelper fh = new FileHelper(folder);
		List<String> filePaths = fh.getPythonFilePaths();

		Map<String, PythonTree> trees = getTrees(filePaths);

		PyVisitorExceptionHandler exHandler = new PyVisitorExceptionHandler();

		ClassCollector classCollector = new ClassCollector(trees.values(), exHandler);

		DependencyCollector depCollector = new DependencyCollector(trees.values(), exHandler, classCollector.getClasses());
		DependencyGraph dependencyGraph = depCollector.getDependencyGraph();
		dependencyGraph.printAlphabetically();
		dependencyGraph.findCyclicDependencies();
	}

	public static Map<String, PythonTree> getTrees(List<String> filePaths) {
		Map<String, PythonTree> trees = new HashMap<>();
		System.out.println("start");
		long startTime = System.nanoTime();
		for (String filePath : filePaths) {
			try {
				parse3(filePath);
//				PythonTree tree = parse(filePath);
//				trees.put(filePath, tree);
//				PyTreePrinter p = new PyTreePrinter(tree);
//				p.prettyPrint();
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

	public static void parse3(String fileName) throws Exception {
		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(new org.antlr.v4.runtime.CommonTokenStream(lexer));

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder();
		Py3Node ast = context.accept(astBuilder);
//		Python3Tree py3Tree = (Python3Tree)astBuilder.visit(context);
	}

	public static PythonTree parse(String fileName) throws Exception {
		CharStream input = new ANTLRFileStream(fileName);
		PythonLexer lexer = new PythonLexer(input);

		FailFastHandler eh = new FailFastHandler();
		lexer.setErrorHandler(eh);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PythonTokenSource indentedSource = new PythonTokenSource(tokens, fileName);
		tokens = new CommonTokenStream(indentedSource);
		PythonParser parser = new PythonParser(tokens);

		parser.setErrorHandler(eh);
		parser.setTreeAdaptor(new PythonTreeAdaptor());
		return (PythonTree) parser.file_input().getTree();
	}
}
