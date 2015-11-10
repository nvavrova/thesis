package main;

import analysis.DesignDefect;
import analysis.Register;
import analysis.detector.*;
import ast.Module;
import model.ModelBuilder;
import model.Project;
import process.File2Tree;
import util.FileHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		PrintStream resultStream = new PrintStream(new FileOutputStream(FileHelper.getRunFileName("RESULTS", "csv")));

		Register register = new Register();

		register.add(new BlobDecorDetector());
		register.add(new FunctionalDecompositionDecorDetector());
		register.add(new SpaghettiCodeDecorDetector());
		register.add(new SwissArmyKnifeDecorDetector());
		register.add(new FeatureEnvyLiShatnawiDetector());

//		Project project = createProject(new File(args[0]));
//		register.check(project);
		File projectsFolder = new File(args[0]);
		for (File file : projectsFolder.listFiles()) {
			if (file.isDirectory()) {
				Project project = createProject(file);
				register.check(project);
			}
		}
		resultStream.println("Project,Location,Defect");
		Map<String, Set<DesignDefect>> designDefects = register.finish();
		for (String projectLocation : designDefects.keySet()) {
			for (DesignDefect dd : designDefects.get(projectLocation)) {
				resultStream.println(projectLocation + "," + dd.getFullPath() + "," + dd.getDefect());
			}
		}
	}

	private static Project createProject(File projectFolder) {
		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
		Map<String, Module> trees = File2Tree.getAsts(allFiles);
		ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
		return mb.getProject();
	}

}
