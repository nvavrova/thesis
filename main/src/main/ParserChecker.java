package main;

import org.antlr.v4.runtime.ParserRuleContext;
import process.File2Tree;
import util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

		ParserChecker.collectUnparsable(args[0], args.length == 2 ? args[1] : null);
	}

	private static void collectUnparsable(String path, String destination) {
		List<String> allFiles = FileHelper.getPythonFilePaths(new File(path));
		int i = 0;
		for (String fileName : allFiles) {
			System.out.println(fileName);
			try {
				ParserRuleContext context = File2Tree.getParseTree(fileName);
			}
			catch (Exception ex) {
				ParserChecker.handleException(ex);
				if (destination != null) {
					try {
						Files.copy(Paths.get(fileName), Paths.get(destination + "\\file_" + (i++) + ".py"), StandardCopyOption.REPLACE_EXISTING);
					}
					catch (IOException e) {
						ParserChecker.handleException(e);
					}
				}
			}
		}
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}