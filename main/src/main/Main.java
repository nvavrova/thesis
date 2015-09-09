package main;

import db.DataHandler;
import db.pojo.ProjectEntity;
import gen.PythonLexer;
import gen.PythonParser;
import model.*;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;
import util.FileHelper;
import util.StringHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static String CSV_DELIMITER = ";";

	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);
//
		if (args.length > 0) {
			Main.processSupplied(args);
		}
		else {
			DataHandler.loadProjects().forEach(Main::handleLatestProjectVersion);
		}


//		Main.createStatsCsv(DataHandler.loadProjects());
	}

	private static void processSupplied(String[] args) {
		List<String> allFiles = FileHelper.getPythonFilePaths(new File(args[0]));
		for (String fileName : allFiles) {
			System.out.println(fileName);
			try {
					org.antlr.v4.runtime.CharStream input = new org.antlr.v4.runtime.ANTLRFileStream(fileName);
					PythonLexer lexer = new PythonLexer(input);

					org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
					PythonParser parser = new PythonParser(tokens);
					parser.setErrorHandler(new BailErrorStrategy());

					ParserRuleContext context = parser.file_input();

			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void handleLatestProjectVersion(ProjectEntity projectEntity) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectEntity.getDiskLocation());
		try {
			DataHandler dataHandler = new DataHandler(projectEntity);
			File projectFolder = new File(projectEntity.getDiskLocation());
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getLatestProjectVersion();

//			dataHandler.save(project);
		}
		catch (Exception ex) {

			Main.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static void handleAllProjectVersions(ProjectEntity projectEntity) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectEntity.getDiskLocation());
		try {
			Main.processProject(projectEntity);
		}
		catch (Exception ex) {
			Main.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static void createStatsCsv(List<ProjectEntity> projectEntities) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(FileHelper.getRunFileName("stats", "csv")));

		List<String> header = new ArrayList<>();
		header.add("module");
		header.add("class name");
		header.add("# of private variables");
		header.add("# of public variables");
		header.add("# of accessors");
		header.add("LCOM");
		header.add("LOC");
		header.add("# of parents");
		header.add("# of methods with no params");
		header.add("# of used globals");
		header.add("# of defined globals");
		csv.println(StringHelper.implode(header, CSV_DELIMITER));

		projectEntities.forEach(project -> Main.createStatsCsv(project, csv));
	}

	private static void createStatsCsv(ProjectEntity projectEntity, PrintStream csv) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectEntity.getDiskLocation());
		try {
			File projectFolder = new File(projectEntity.getDiskLocation());
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getLatestProjectVersion();

			for (model.Class c : project.getClasses()) {
				List<String> line = new ArrayList<>();
				line.add(c.getModule().getFilePath());
				line.add(c.getName());
				line.add(String.valueOf(c.privateVariablesCount()));
				line.add(String.valueOf(c.publicVariablesCount()));
				line.add(String.valueOf(c.getAccessorCount()));
				line.add(String.valueOf(c.getLcom()));
				line.add(String.valueOf(c.getLoc()));
				line.add(String.valueOf(c.parentsCount()));
				line.add(String.valueOf(c.methodsWithNoParamsCount()));
				line.add(String.valueOf(c.usedGlobalsCount()));
				line.add(String.valueOf(c.definedGlobalsCount()));

				csv.println(StringHelper.implode(line, CSV_DELIMITER));
			}
		}
		catch (Exception ex) {
			Main.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static void processProject(ProjectEntity projectEntity) throws Exception {
		DataHandler dataHandler = new DataHandler(projectEntity);
		File projectFolder = new File(projectEntity.getDiskLocation());
		try {
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getFirstProjectVersion();

//			dataHandler.save(project);

			while (!versionSwitcher.isAtLatestVersion()) {
				project = versionSwitcher.getNextProjectVersion();
//				dataHandler.save(project);
			}

		}
		catch (Exception ex) {
			Main.handleException(ex);
		}
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
