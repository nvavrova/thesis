package main;

import analysis.DesignDefect;
import analysis.Register;
import analysis.detector.BlobDecorDetector;
import analysis.detector.FunctionalDecompositionDecorDetector;
import analysis.detector.SpaghettiCodeDecorDetector;
import analysis.detector.SwissArmyKnifeDecorDetector;
import ast.Module;
import model.ModelBuilder;
import model.Project;
import process.File2Tree;
import util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {

//		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
//		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
//		System.setOut(out);
//		System.setErr(err);

		Register register = new Register();

		//TODO: add Detectors
		register.add(new BlobDecorDetector());
		register.add(new FunctionalDecompositionDecorDetector());
		register.add(new SpaghettiCodeDecorDetector());
		register.add(new SwissArmyKnifeDecorDetector());

		File projectsFolder = new File(args[0]);
		for (File file : projectsFolder.listFiles()) {
			if (file.isDirectory()) {
				Project project = createProject(file);
				register.check(project);
			}
		}

		Map<String, Set<DesignDefect>> designDefects = register.finish();
		//TODO: do something with the results
	}

	private static Project createProject(File projectFolder) {
		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
		Map<String, Module> trees = File2Tree.getAsts(allFiles);
		ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
		return mb.getProject();
	}

}
