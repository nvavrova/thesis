package main;

import ast.Module;
import model.*;
import model.Class;
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
			StatsCollector.createStatsCsv(subfolders);
		}
	}

	private static void createStatsCsv(List<File> projectFolders) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(FileHelper.getRunFileName("stats", "csv")));

		List<String> header = new ArrayList<>();
		header.add("project");
		header.add("module");
		header.add("error");
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
		header.add("# of methods");
		header.add("average method AID");
		header.add("average method ALD");
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

			for (model.Module module : project.getModules()) {
				Map<String, Class> classes = module.getDefinedClassesInclSubclassesByName();
				for (String clsAlias : classes.keySet()) {
					Class cls = classes.get(clsAlias);
					csv.println(StringHelper.implode(createClassLine(clsAlias, cls, module, project), CSV_DELIMITER));
				}
			}
		}
		catch (Exception ex) {
			StatsCollector.handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private static List<String> createClassLine(String alias, model.Class cls, model.Module module, Project project) {
		List<String> line = new ArrayList<>();
		line.add(project.getPath());
		line.add(module.getFilePath());
		line.add(module.getError());
		line.add(alias);
		line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isPrivate).count()));
		line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isProtected).count()));
		line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isPublic).count()));
		line.add(String.valueOf(cls.getDefinedSubroutinesSet().stream().filter(Subroutine::isAccessor).count()));
		line.add(String.valueOf(cls.getLcom()));
		line.add(String.valueOf(cls.getLoc()));
		line.add(String.valueOf(cls.parentsCount()));
		line.add(String.valueOf(cls.subroutinesWithNoParamsCount()));
		line.add(String.valueOf(cls.getReferencedGlobalsSet().size()));
		line.add(String.valueOf(cls.getDefinedVariablesOfTypeSet(VarType.GLOBAL).size()));

		int amountMethods = cls.getDefinedSubroutinesSet().size();
		Double aid = 0.0;
		Double ald = 0.0;
		for (Subroutine s : cls.getDefinedSubroutinesSet()) {
			aid += s.getAccessOfImportData();
			ald += s.getAccessOfLocalData();
		}

		line.add(String.valueOf(amountMethods));
		line.add(String.valueOf(aid/amountMethods));
		line.add(String.valueOf(ald/amountMethods));

		return line;
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
