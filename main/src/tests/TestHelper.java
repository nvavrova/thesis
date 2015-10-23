package tests;

import model.Class;
import model.ModelBuilder;
import model.Project;
import process.File2Tree;
import util.FileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 08-07-2015
 */
public class TestHelper {

	public static Map<String, Class> getClasses(String fileName) {
		Project project = TestHelper.getProject(fileName);
		return TestHelper.getClasses(project);
	}

	public static Map<String, Class> getClasses(Project project) {
		Map<String, Class> classMap = new HashMap<>();
		project.getModules().forEach(m -> classMap.putAll(m.getDefinedClassesByName()));
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
		Map<String, ast.Module> trees = File2Tree.getAsts(fileNames);
		ModelBuilder modelBuilder = new ModelBuilder(new File(parent), trees.values());
		return modelBuilder.getProject();
	}
}
