package main;

import ast.Module;
import model.ModelBuilder;
import model.Project;
import model.VarType;
import process.File2Tree;
import util.FileHelper;
import util.StringHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//			List<File> subfolders = new ArrayList<>();
//			subfolders.add(new File("D:\\intellij_projects\\thesis\\data\\asyncio"));
			StatsCollector.createStatsCsv(subfolders);
		}
	}

	private static void createStatsCsv(List<File> projectFolders) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(FileHelper.getRunFileName("stats", "csv")));

		List<String> header = new ArrayList<>();
		header.add("project");
		header.add("module");
		header.add("class name");
		header.add("# of private variables");
		header.add("# of protected variables");
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
			List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
			Map<String, Module> trees = File2Tree.getAsts(allFiles);
			ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
			Project project = mb.getProject();

			//TODO
//			for (model.Class c : project.getClasses()) {
//				csv.println(StringHelper.implode(createClassLine(c, project), CSV_DELIMITER));
//			}
		}
		catch (Exception ex) {
			StatsCollector.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static List<String> createClassLine(model.Class c, Project project) {
		List<String> line = new ArrayList<>();
		line.add(project.getPath());
		//TODO: get the module
//		line.add(c.getModule().getFilePath());
		line.add(c.getName());
		line.add(String.valueOf(c.getDefinedVariablesSet().stream().filter(v -> v.isPrivate()).count()));
		line.add(String.valueOf(c.getDefinedVariablesSet().stream().filter(v -> v.isProtected()).count()));
		line.add(String.valueOf(c.getDefinedVariablesSet().stream().filter(v -> v.isPublic()).count()));
		line.add(String.valueOf(c.getDefinedSubroutinesSet().stream().filter(s -> s.isAccessor()).count()));
		line.add(String.valueOf(c.getLcom()));
		line.add(String.valueOf(c.getLoc()));
		line.add(String.valueOf(c.parentsCount()));
		line.add(String.valueOf(c.subroutinesWithNoParamsCount()));
		line.add(String.valueOf(c.getReferencedGlobalsSet().size()));
		line.add(String.valueOf(c.getDefinedVariablesOfTypeSet(VarType.GLOBAL).size()));
		return line;
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
