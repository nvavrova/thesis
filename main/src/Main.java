import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.python.antlr.*;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {

			File folder = new File(args[0]);
			FileHelper fh = new FileHelper(folder);
			List<String> filePaths = fh.getPythonFilePaths();

			for (String filePath : filePaths) {
				processFile(filePath);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processFile(String filePath) throws Exception {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("FILE: " + filePath + "\n");

		PythonTree tree = parse(filePath);

//		PythonTreePrinter p = new PythonTreePrinter(tree);
//		p.prettyPrint();

		MetaCollector collector = new MetaCollector(tree);

		collector.getClasses().forEach(System.out::println);

		DependencyGraph dg = collector.getDependencyGraph();
		dg.print();
		System.out.println("__________________________________________________________________________");
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
