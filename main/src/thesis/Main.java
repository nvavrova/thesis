package thesis;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
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

		DependencyGraphBuilder collector = new DependencyGraphBuilder(trees.values(), exHandler, classCollector.getClasses());
		DependencyGraph dg = collector.getDependencyGraph();
		dg.printAlphabetically();
		dg.findCyclicDependencies();
	}

	public static Map<String, PythonTree> getTrees(List<String> filePaths) {
		Map<String, PythonTree> trees = new HashMap<>();
		for (String filePath : filePaths) {
			try {
				PythonTree tree = parse(filePath);
				trees.put(filePath, tree);
//				PyTreePrinter p = new PyTreePrinter(tree);
//				p.prettyPrint();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return trees;
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
