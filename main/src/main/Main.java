package main;

import db.DataHandler;
import db.pojo.ProjectEntity;
import model.Project;
import util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		DataHandler.loadProjects().forEach(Main::handleLatestProjectVersion);
		//DataHandler.loadProjects().forEach(Main::handleAllProjectVersions);
	}

	private static void handleLatestProjectVersion(ProjectEntity projectEntity) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectEntity.getDiskLocation());
		try {
			DataHandler dataHandler = new DataHandler(projectEntity);
			File projectFolder = new File(projectEntity.getDiskLocation());
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getLatestProjectVersion();

			dataHandler.save(project);
		}
		catch (Exception ex) {
			System.err.println("EXCEPTION: " + ex.getMessage());
			ex.printStackTrace(System.err);
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
			System.err.println("EXCEPTION: " + ex.getMessage());
			ex.printStackTrace(System.err);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static void processProject(ProjectEntity projectEntity) throws Exception {
		DataHandler dataHandler = new DataHandler(projectEntity);
		File projectFolder = new File(projectEntity.getDiskLocation());
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
		Project project = versionSwitcher.getFirstProjectVersion();

		dataHandler.save(project);

		while (!versionSwitcher.isAtLatestVersion()) {
			project = versionSwitcher.getNextProjectVersion();
			dataHandler.save(project);
		}
	}
}
