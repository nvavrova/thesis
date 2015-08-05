package main;

import db.DataHandler;
import helpers.FileHelper;
import model.Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

//	private static Set<String> getLines(String filePath) {
//		File file = new File(filePath);
//		FileHelper f = new FileHelper(file.getAbsolutePath());
//		return f.getFileContents().stream().collect(Collectors.toSet());
//	}
//
//	private static void debug() {
//		Set<String> linesOne = getLines(args[0] + "\\test\\res.txt");
//		Set<String> linesTwo = getLines(args[0] + "\\test\\res2.txt");
//
//		for (String l : linesOne) {
//			if (!linesTwo.contains(l)) {
//				System.out.println(l);
//			}
//		}
//		System.out.println("---------------------------------------");
//		for (String l : linesTwo) {
//			if (!linesOne.contains(l)) {
//				System.out.println(l);
//			}
//		}
//	}

	public static void main(String[] args) throws IOException, InterruptedException {



		File folder = new File(args[0]);
		List<File> projectFolders = FileHelper.getSubfolders(folder);

		PrintStream out = new PrintStream(new FileOutputStream(getLogName()));
//		System.setOut(out);
		System.setErr(out);

		for (File projectFolder : projectFolders) {
			System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
			System.out.println("Name: " + projectFolder.getName());
			try {
				Main.processProject(projectFolder);
			}
			catch (Exception ex) {
				System.err.println("EXCEPTION: " + ex.getMessage());
				ex.printStackTrace(System.err);
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}
	}

	private static void processProject(File projectFolder) throws IOException, InterruptedException {
		DataHandler dataHandler = new DataHandler(projectFolder.getName());
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
		Project project = versionSwitcher.getFirstProjectVersion();
		dataHandler.save(project);

		while (!versionSwitcher.isAtLatestVersion()) {
			project = versionSwitcher.getNextProjectVersion();
			dataHandler.save(project);
		}

		versionSwitcher.cleanUp();
	}

	private static String getLogName() {
		File folder = new File("logs");
		if (!folder.exists()) {
			folder.mkdir();
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
		String date = dateFormat.format(now);
		return "logs/" + date + "_log.txt";
	}

//	private static void processProjectOld(File projectFolder) throws IOException, InterruptedException {
//		GitHandler gitHandler = new GitHandler(projectFolder);
//		gitHandler.goToFirstCommit();
//		DataHandler dataHandler = new DataHandler(projectFolder.getName());
//
//		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
//		Map<String, Module> prevTrees = getTrees(allFiles);
//		ModelBuilder mb = new ModelBuilder(projectFolder, prevTrees.values());
//		Project prevProject = mb.getProject();
//		saveProject(prevProject, gitHandler.getCurrentCommitSha(), dataHandler);
//
//		while (!gitHandler.isAtLastCommit()) {
//			List<String> changedFiles = gitHandler.goForth();
//			cleanUpNonExistingFiles(projectFolder, prevTrees);
//			Map<String, Module> currentTrees = getTrees(changedFiles);
//
//			ModelBuilder builder = new ModelBuilder(prevProject, prevTrees, currentTrees);
//			prevProject = builder.getProject();
//			prevTrees.putAll(currentTrees);
//
//			saveProject(prevProject, gitHandler.getCurrentCommitSha(), dataHandler);
//
//
////			//clean up non-existing files
////			List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
////			Set<String> nonExistingFiles = trees.keySet().stream()
////					.filter(k -> !allFiles.contains(k))
////					.collect(Collectors.toSet());
////			nonExistingFiles.forEach(k -> trees.remove(k));
////
////			//get new/changed files
////			Map<String, Module> currentTrees = getTrees(changedFiles);
////
////
////			ModelBuilder builder = new ModelBuilder(projectFolder, trees.values(), currentTrees.values());
////			Project project = builder.getProject();
////			Set<model.Class> classes = project.getClasses();
////			dataHandler.save(gitHandler.getCurrentCommitSha(), classes);
////			changedFiles = gitHandler.goForth();
////
////			changedFiles = processProjectFiles(project, trees, dataHandler, gitHandler);
//		}
//	}
//
//	private static void cleanUpNonExistingFiles(File projectFolder, Map<String, ast.Module> trees) {
//		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
//		Set<String> nonExistingFiles = trees.keySet().stream()
//				.filter(k -> !allFiles.contains(k))
//				.collect(Collectors.toSet());
//		nonExistingFiles.forEach(k -> trees.remove(k));
//	}
//
//	private static void saveProject(Project project, String currentCommitSha, DataHandler dataHandler) {
//		Set<model.Class> classes = project.getClasses();
//		dataHandler.save(currentCommitSha, classes);
//	}
//
//	private static List<String> processProjectFiles(Project project, Map<String, Module> trees, DataHandler dataHandler,
//	                                                GitHandler gitHandler) throws IOException, InterruptedException {
//		ModelBuilder collector = new ModelBuilder(trees.values(), project);
//		Set<model.Class> classes = project.getClasses();
////			for (Class pyClass : classes) {
////				Map<String, Boolean> antipatterns = new HashMap<>();
////
////				antipatterns.put("BLOB", pyClass.isBlob());
////				antipatterns.put("FUNCTIONAL DECOMPOSITION", pyClass.isFunctionalDecomposition());
////				antipatterns.put("SPAGHETTI CODE", pyClass.isSpaghettiCode());
////				antipatterns.put("SWISS ARMY KNIFE", pyClass.isSwissArmyKnife());
////
////				antipatterns.keySet().stream()
////						.filter(name -> antipatterns.get(name))
////						.forEach(name -> {
////							printAntipattern(name, fileName, pyClass.getName());
////						});
////			}
//		dataHandler.save(gitHandler.getCurrentCommitSha(), classes);
//		return gitHandler.goForth();
//	}
//
////	private static void printAntipattern(String antipatternName, String fileName, String className) {
////		System.out.println("FOUND " + antipatternName + ": \n\tfile name: " + fileName + "\n\tclass: " + className);
////	}
//
//	private static Map<String, Module> getTrees(List<String> filePaths) {
//		Map<String, Module> trees = new HashMap<>();
//		long startTime = System.nanoTime();
//		for (String filePath : filePaths) {
//			try {
//				Module tree = parse(filePath);
//				trees.put(filePath, tree);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime) / 1000000000;
//		System.out.println("Duration: " + duration + "s");
//		return trees;
//	}
//
//	private static Module parse(String fileName) throws Exception {
//		org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
//		Python3Lexer lexer = new Python3Lexer(input);
//
//		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
//		Python3Parser parser = new Python3Parser(tokens);
//
//		ParserRuleContext context = parser.file_input();
//		AstBuilder astBuilder = new AstBuilder(context, fileName);
//		return astBuilder.build();
//	}
}
