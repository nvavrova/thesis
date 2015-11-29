package main;

import analysis.Register;
import analysis.detector.*;
import ast.Module;
import model.ModelBuilder;
import model.Project;
import process.File2Tree;
import process.GitLocationProcessor;
import util.FileHelper;
import util.Settings;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Main {

	private static GitLocationProcessor gitLocs;

	private static final String CSV_NAME = "RESULTS";

	public static void main(String[] args) throws IOException {

		Properties config = Settings.getConfig();

		createLocations(config);

		boolean filtered = config.containsKey("locations.data.input.filter");

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.stampedFileName(config.getProperty("locations.log.out"), "out", "log")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.stampedFileName(config.getProperty("locations.log.error"), "err", "log")));
		System.setOut(out);
		System.setErr(err);

		Register register = new Register();
		registerDetectors(register);

		gitLocs = new GitLocationProcessor(config.getProperty("locations.data.input.disklocations"));
		gitLocs.readData();

		List<String> projects = Collections.emptyList();
		if (filtered) {
			BufferedReader br = new BufferedReader(new FileReader(config.getProperty("locations.data.input.filter")));
			projects = br.lines().collect(Collectors.toList());
			br.close();
		}

		File projectsFolder = new File(config.getProperty("locations.data.input"));
		for (File file : projectsFolder.listFiles()) {
			if (file.isDirectory() && (!filtered || projects.contains(file.getAbsolutePath()))) {
				processProject(register, file);
			}
		}

		CsvCreator csvCreator = new CsvCreator(config.getProperty("locations.data.results"));
		csvCreator.createStream(CSV_NAME, "Project", "Url", "Location", "Defect");
		register.finish(gitLocs, csvCreator);
	}

	private static void processProject(Register register, File file) throws FileNotFoundException {
		System.out.print(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024));
		Project project = createProject(file);
		register.check(project);
		System.gc();
		System.out.println(" -> " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024) + "\t\t" + file.getAbsolutePath());
	}

	private static void registerDetectors(Register register) throws IOException {
		register.add(new LongMethodDetector());
		register.add(new LongParamListDetector());
		register.add(new LargeClassDetector());
		register.add(new DataClassDetector());
		register.add(new BlobDecorDetector());
		register.add(new FunctionalDecompositionDecorDetector());
		register.add(new SpaghettiCodeDecorDetector());
		register.add(new SwissArmyKnifeDecorDetector());
		register.add(new FeatureEnvyLiShatnawiDetector());
	}

	private static void createLocations(Properties config) {
		FileHelper.createLocation(config.getProperty("locations.log.out"));
		FileHelper.createLocation(config.getProperty("locations.log.error"));
		FileHelper.createLocation(config.getProperty("locations.data.input"));
		FileHelper.createLocation(config.getProperty("locations.data.output"));
		FileHelper.createLocation(config.getProperty("locations.data.results"));
	}

	private static Project createProject(File projectFolder) {
//		System.out.println("Project: " + projectFolder.getAbsolutePath());
		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
		Map<String, Module> trees = File2Tree.getAsts(allFiles);
		ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
		return mb.getProject();
	}

}
