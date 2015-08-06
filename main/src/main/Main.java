package main;

import db.DataHandler;
import helpers.FileHelper;
import model.Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		File folder = new File(args[0]);
		List<File> projectFolders = FileHelper.getSubfolders(folder);

		PrintStream out = new PrintStream(new FileOutputStream(getLogName()));
		System.setOut(out);
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

	private static void processProject(File projectFolder) throws Exception {
		DataHandler dataHandler = new DataHandler(projectFolder.getName());
		VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
		Project project = versionSwitcher.getFirstProjectVersion();
		dataHandler.save(project);

		while (!versionSwitcher.isAtLatestVersion()) {
			project = versionSwitcher.getNextProjectVersion();
			dataHandler.save(project);
		}
//		versionSwitcher.cleanUp();
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
}
