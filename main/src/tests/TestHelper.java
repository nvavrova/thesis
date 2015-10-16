package tests;

import ast.AstBuilder;
import gen.PythonLexer;
import gen.PythonParser;
import process.VersionSwitcher;
import model.Class;
import model.ModelBuilder;
import model.Module;
import model.Project;
import org.antlr.v4.runtime.ParserRuleContext;
import util.FileHelper;
import util.FileOpener;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 08-07-2015
 */
public class TestHelper {

	public static ast.Module parseFile(String fileName) {
		FileOpener fo = new FileOpener(fileName);
		String contents = fo.getTrimmedContents();
		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRInputStream(contents);
		PythonLexer lexer = new PythonLexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		PythonParser parser = new PythonParser(tokens);

		ParserRuleContext context = parser.file_input();
		AstBuilder astBuilder = new AstBuilder(context, fileName);
		return astBuilder.build();
	}

	public static Project getFirstVersionProject(String fileName) throws Exception {
		File projectFolder = new File("version_switch");
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
		return versionSwitcher.getFirstProjectVersion();
	}

	public static Map<String, Module> getModules(Project project) {
		Set<Module> modules = project.getModules();
		return modules.stream().collect(Collectors.toMap(k -> k.getFilePath(), v -> v));
	}

	public static Map<String, Class> getClasses(String fileName) {
		Project project = TestHelper.getProject(fileName);
		return TestHelper.getClasses(project);
	}

	public static Map<String, Class> getClasses(Project project) {
		Map<String, Class> classMap = new HashMap<>();
		project.getClasses().forEach(c -> classMap.put(c.getName(), c));
		return classMap;
	}

	public static Project getProject(String fileName) {
		File file = new File(fileName);
		String projectFolder = file.isDirectory() ? file.getAbsolutePath() : file.getParent();

		List<String> filePaths = new ArrayList<>();
		if (file.isDirectory()) {
			filePaths.addAll(FileHelper.getPythonFilePaths(file));
		}
		else {
			filePaths.add(fileName);
		}

		return TestHelper.getProject(projectFolder, filePaths);
	}


	private static Project getProject(String parent, List<String> fileNames) {
		List<ast.Module> trees = new ArrayList<>();
		fileNames.stream().forEach(f -> trees.add(parseFile(f)));
		ModelBuilder modelBuilder = new ModelBuilder(new File(parent), trees);
		return modelBuilder.getProject();
	}
}
