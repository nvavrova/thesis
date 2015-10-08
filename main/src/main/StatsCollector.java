package main;

import db.DataHandler;
import db.pojo.ProjectEntity;
import model.Project;
import util.FileHelper;
import util.StringHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 08-10-2015
 */
public class StatsCollector {


	private static String CSV_DELIMITER = ";";

	//args[0] = the location of the projects (OPTIONAL)
	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		if (args.length > 0) {
			File sourceFolder = new File(args[0]);
			List<File> subfolders = FileHelper.getSubfolders(sourceFolder);
			StatsCollector.createStatsCsv(subfolders);
		}
		else {
			List<ProjectEntity> projectEntities = DataHandler.loadProjects();
			StatsCollector.createStatsCsvForProjects(projectEntities);
		}
	}


	private static void createStatsCsvForProjects(List<ProjectEntity> projectEntities) throws FileNotFoundException {
		List<File> projectFolders = projectEntities.stream()
				.map(p -> new File(p.getDiskLocation()))
				.collect(Collectors.toList());
		StatsCollector.createStatsCsv(projectFolders);
	}

	private static void createStatsCsv(List<File> projectFolders) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(FileHelper.getRunFileName("stats", "csv")));

		List<String> header = new ArrayList<>();
		header.add("project");
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

		projectFolders.forEach(project -> StatsCollector.createStatsCsv(project, csv));
	}

	private static void createStatsCsv(File projectFolder, PrintStream csv) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectFolder.getAbsolutePath());
		try {
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getLatestProjectVersion();

			for (model.Class c : project.getClasses()) {
				List<String> line = new ArrayList<>();
				line.add(project.getFolderPath());
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
			StatsCollector.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
