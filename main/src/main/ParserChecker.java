package main;

import gen.PythonLexer;
import gen.PythonParser;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;
import util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

/**
 * Created by Nik on 08-10-2015
 */
public class ParserChecker {

	//args[0] = the location of the projects
	//args[1] = the destination to which the unparsable files will be copied to
	public static void main(String[] args) throws IOException {

//		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
//		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
//		System.setOut(out);
//		System.setErr(err);

		ParserChecker.collectUnparsables(args[0], args.length == 2 ? args[1] : null);
	}

	private static void collectUnparsables(String path, String destination) {
		List<String> allFiles = FileHelper.getPythonFilePaths(new File(path));
		int i = 0;
		for (String fileName : allFiles) {
			Boolean parsable = collectUnparsable(fileName, destination, i);
			if (parsable) {
				i++;
			}
		}
	}

	private static Boolean collectUnparsable(String filePath, String destination, int counter) {
		try {
			FileHelper fh = new FileHelper(filePath);
			String contents = fh.getTrimmedFileContents();
			org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(contents);
			PythonLexer lexer = new PythonLexer(input);

			org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
			PythonParser parser = new PythonParser(tokens);
			parser.setErrorHandler(new BailErrorStrategy());

			Date start = new Date();
			ParserRuleContext context = parser.file_input();
			Date end = new Date();
			System.out.println("Parsing " + filePath + "\nDuration: " + ((end.getTime() - start.getTime()) / 1000.0)
					+ "seconds\n-------------------------------------------------------------");
			return true;
		}
		catch (Exception ex) {
			ParserChecker.handleException(ex);
			if (destination != null) {
				try {
					Files.copy(Paths.get(filePath), Paths.get(destination + "\\file_" + String.format("%03d", counter) + ".py"), StandardCopyOption.REPLACE_EXISTING);
				}
				catch (IOException e) {
					ParserChecker.handleException(e);
				}
			}
		}
		return false;
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
