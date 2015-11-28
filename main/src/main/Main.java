package main;

import analysis.Register;
import analysis.detector.*;
import ast.Module;
import model.ModelBuilder;
import model.Project;
import process.File2Tree;
import process.GitLocationProcessor;
import util.FileHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class Main {

	private static GitLocationProcessor gitLocs;

	private static final String CSV_NAME = "RESULTS";

	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		Register register = new Register();

		register.add(new LongMethodDetector());
		register.add(new LongParamListDetector());
		register.add(new LargeClassDetector());
		register.add(new DataClassDetector());
		register.add(new BlobDecorDetector());
		register.add(new FunctionalDecompositionDecorDetector());
		register.add(new SpaghettiCodeDecorDetector());
		register.add(new SwissArmyKnifeDecorDetector());
		register.add(new FeatureEnvyLiShatnawiDetector());

		if (args.length > 1) {
			gitLocs = new GitLocationProcessor(args[1]);
			gitLocs.readData();
		}

		File projectsFolder = new File(args[0]);
		for (File file : projectsFolder.listFiles()) {
			if (file.isDirectory()) {
				System.out.print(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024/1024));
				Project project = createProject(file);
				register.check(project);
				System.gc();
				System.out.println(" -> " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024) + "\t\t" + file.getAbsolutePath());
			}
		}

		CsvCreator csvCreator = new CsvCreator();
		csvCreator.createStream(CSV_NAME, "Project", "Url", "Location", "Defect");
		register.finish(gitLocs, csvCreator);
	}

	private static Project createProject(File projectFolder) {
		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
		Map<String, Module> trees = File2Tree.getAsts(allFiles);
		ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
		return mb.getProject();
	}

}
