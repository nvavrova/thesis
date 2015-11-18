package main;

import ast.Module;
import model.Class;
import model.*;
import process.File2Tree;
import util.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 08-10-2015
 */
public class StatsCollector {

	//args[0] = the location of the projects
	//args[1] = the CSV file which contains project disk location, github link
	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		File sourceFolder = new File(args[0]);
		List<File> subfolders = FileHelper.getSubfolders(sourceFolder);
		StatsCsvCreator statsCsvCreator = new StatsCsvCreator(subfolders, args[1]);
		statsCsvCreator.createStatsCsv();
		statsCsvCreator.close();
	}



	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}


	private static class StatsCsvCreator extends CsvCreator {
		private final List<File> projectFolders;
		private final GitLocationProcessor gitLocs;

		private final static String CLASS_STREAM_NAME = "class";
		private final static String MODULE_STREAM_NAME = "module";
		private final static String PROJECT_STREAM_NAME = "project";

		public StatsCsvCreator(List<File> projectFolders, String gitLocationsFile) {
			this.projectFolders = projectFolders;
			this.gitLocs = new GitLocationProcessor(gitLocationsFile);
		}

		private void createStatsCsv() throws FileNotFoundException {
			this.gitLocs.readData();
			this.createClassStream();
			this.createModuleStream();
			this.createProjectStream();
			this.projectFolders.forEach(project -> this.createStatsCsv(project));
		}

		private void createClassStream() throws FileNotFoundException {
			this.createStream(CLASS_STREAM_NAME, "project", "module", "class name", "# of private variables",
					"# of protected variables", "# of public variables", "# of accessors", "LCOM", "LOC", "# of parents",
					"# of methods with no params", "# of used globals", "# of defined globals", "# of methods",
					"total method AID", "total method ALD", "average method AID", "average method ALD");
		}

		private void createModuleStream() throws FileNotFoundException {
			this.createStream(MODULE_STREAM_NAME, "project", "git link", "module", "parses", "LOC", "# of classes");
		}

		private void createProjectStream() throws FileNotFoundException {
			this.createStream(PROJECT_STREAM_NAME, "project", "git link", "modules", "LOC", "classes", "parse ratio");
		}

		private void createStatsCsv(File projectFolder) {
			System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
			System.out.println("Name: " + projectFolder.getAbsolutePath());
			try {
				List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
				Map<String, Module> trees = File2Tree.getAsts(allFiles);
				ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
				Project project = mb.getProject();
				String link = this.gitLocs.hasLink(project.getPath()) ? this.gitLocs.getLink(project.getPath()) : "";

				Long loc = 0L;
				Integer classCount = 0;
				Integer correctlyParsed = 0;
				for (model.Module module : project.getModules()) {
					this.printModuleLine(module, project.getPath(), link);

					Map<String, Class> classes = module.getDefinedClassesInclSubclassesByName();
					classCount += classes.size();
					for (String clsAlias : classes.keySet()) {
						Class cls = classes.get(clsAlias);
						this.printClassLine(clsAlias, cls, project.getPath(), module.getFilePath());
					}

					if (!module.hasError()) {
						correctlyParsed++;
					}
					loc += module.getLoc();
				}
				int moduleCount = project.getModules().size();
				this.printProjectLine(project.getPath(), link, moduleCount, loc, classCount, correctlyParsed.doubleValue() / moduleCount);
			}
			catch (Exception ex) {
				handleException(ex);
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}

		private void printProjectLine(String projectPath, String gitLink, Integer moduleCount, Long loc, Integer classCount, Double parseRatio) {
			List<String> projectLine = this.createProjectLine(projectPath, gitLink, moduleCount, loc, classCount, parseRatio);
			this.addLine(PROJECT_STREAM_NAME, projectLine);
		}

		private void printModuleLine(model.Module module, String projectPath, String gitLink) {
			List<String> moduleLine = this.createModuleLine(module, projectPath, gitLink);
			this.addLine(MODULE_STREAM_NAME, moduleLine);
		}

		private void printClassLine(String alias, model.Class cls, String projectPath, String modulePath) {
			List<String> classLine = this.createClassLine(alias, cls, projectPath, modulePath);
			this.addLine(CLASS_STREAM_NAME, classLine);
		}


		private List<String> createProjectLine(String projectPath, String gitLink, Integer moduleCount, Long loc, Integer classCount, Double parseRatio) {
			List<String> line = new ArrayList<>();
			line.add(projectPath);
			line.add(gitLink);
			line.add(String.valueOf(moduleCount));
			line.add(String.valueOf(loc));
			line.add(String.valueOf(classCount));
			line.add(String.valueOf(parseRatio));
			return line;
		}


		private List<String> createModuleLine(model.Module module, String projectPath, String gitLink) {
			List<String> line = new ArrayList<>();
			line.add(projectPath);
			line.add(gitLink);
			line.add(module.getFilePath());
			line.add(module.hasError() ? "0" : "1");
			line.add(String.valueOf(module.getLoc()));
			line.add(String.valueOf(module.getDefinedClassesInclSubclassesSet().size()));
			return line;
		}

		private List<String> createClassLine(String alias, model.Class cls, String projectPath, String modulePath) {
			List<String> line = new ArrayList<>();
			line.add(projectPath);
			line.add(modulePath);
			line.add(alias);
			line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isPrivate).count()));
			line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isProtected).count()));
			line.add(String.valueOf(cls.getDefinedVariablesSet().stream().filter(Variable::isPublic).count()));
			line.add(String.valueOf(cls.getDefinedSubroutinesSet().stream().filter(Subroutine::isAccessor).count()));
			line.add(String.valueOf(cls.getLcom()));
			line.add(String.valueOf(cls.getLoc()));
			line.add(String.valueOf(cls.superclassCount()));
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
			line.add(String.valueOf(aid));
			line.add(String.valueOf(ald));
			line.add(String.valueOf(amountMethods != 0 ? aid/amountMethods : 0));
			line.add(String.valueOf(amountMethods != 0 ? ald/amountMethods : 0));

			return line;
		}
	}
}
