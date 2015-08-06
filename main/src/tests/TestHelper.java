package tests;

import ast.AstBuilder;
import gen.Python3Lexer;
import gen.Python3Parser;
import helpers.FileHelper;
import main.VersionSwitcher;
import model.Class;
import model.ModelBuilder;
import model.Module;
import model.Project;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 08-07-2015
 */
public class TestHelper {

	public static ast.Module parseFile(String fileName) {
		org.antlr.v4.runtime.CharStream input = null;
		try {
			input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Python3Lexer lexer = new Python3Lexer(input);

		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

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
