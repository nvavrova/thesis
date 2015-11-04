package main;

import ast.Module;
import model.*;
import model.Class;
import process.File2Tree;
import util.FileHelper;
import util.FileOpener;
import util.StringHelper;

import java.io.*;
import java.util.*;

/**
 * Created by Nik on 08-10-2015
 */
public class StatsCollector {


	private static String CSV_DELIMITER = ";";

	//args[0] = the location of the projects
	//args[1] = the CSV file which contains project disk location, github link
	public static void main(String[] args) throws IOException {

		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		File sourceFolder = new File(args[0]);
		List<File> subfolders = FileHelper.getSubfolders(sourceFolder);
		CsvCreator csvCreator = new CsvCreator(subfolders, args[1]);
		csvCreator.createStatsCsv();
	}



	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}


	private static class CsvCreator {
		private final List<File> projectFolders;
		private final GitLocationProcessor gitLocs;

		private PrintStream classStream;
		private PrintStream moduleStream;

		public CsvCreator(List<File> projectFolders, String gitLocationsFile) {
			this.projectFolders = projectFolders;
			this.gitLocs = new GitLocationProcessor(gitLocationsFile);
		}

		private void createStatsCsv() throws FileNotFoundException {
			this.gitLocs.readData();
			this.classStream = this.createClassStream();
			this.moduleStream = this.createModuleStream();
			this.projectFolders.forEach(project -> this.createStatsCsv(project));
		}

		private PrintStream createClassStream() throws FileNotFoundException {
			return this.createStream("class", "project", "module", "class name", "# of private variables",
					"# of protected variables", "# of public variables", "# of accessors", "LCOM", "LOC", "# of parents",
					"# of methods with no params", "# of used globals", "# of defined globals", "# of methods",
					"total method AID", "total method ALD", "average method AID", "average method ALD");
		}

		private PrintStream createModuleStream() throws FileNotFoundException {
			return this.createStream("module", "project", "git link", "module", "parses", "LOC", "# of classes");
		}

		private PrintStream createStream(String type, String... headers) throws FileNotFoundException {
			PrintStream stream = new PrintStream(new FileOutputStream(FileHelper.getRunFileName(type + "_stats", "csv")));
			List<String> headerParts = new ArrayList<>();
			for (String header : headers) {
				headerParts.add(header);
			}
			stream.println(StringHelper.implode(headerParts, CSV_DELIMITER));
			return stream;
		}

		private void createStatsCsv(File projectFolder) {
			System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
			System.out.println("Name: " + projectFolder.getAbsolutePath());
			try {
				List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
				Map<String, Module> trees = File2Tree.getAsts(allFiles);
				ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
				Project project = mb.getProject();

				for (model.Module module : project.getModules()) {
					String link = this.gitLocs.hasLink(project.getPath()) ? this.gitLocs.getLink(project.getPath()) : "";
					this.moduleStream.println(this.createModuleLine(module, project.getPath(), link));

					Map<String, Class> classes = module.getDefinedClassesInclSubclassesByName();
					for (String clsAlias : classes.keySet()) {
						Class cls = classes.get(clsAlias);
						String classLine = this.createClassLine(clsAlias, cls, project.getPath(), module.getFilePath());
						this.classStream.println(classLine);
					}
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}


		private String createModuleLine(model.Module module, String projectPath, String gitLink) {
			List<String> line = new ArrayList<>();
			line.add(projectPath);
			line.add(gitLink);
			line.add(module.getFilePath());
			line.add(module.hasError() ? "FALSE" : "TRUE");
			line.add(String.valueOf(module.getLoc()));
			line.add(String.valueOf(module.getDefinedClassesInclSubclassesSet().size()));
			return StringHelper.implode(line, CSV_DELIMITER);
		}

		private String createClassLine(String alias, model.Class cls, String projectPath, String modulePath) {
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

			return StringHelper.implode(line, CSV_DELIMITER);
		}
	}



	private static class GitLocationProcessor {

		private final String fileName;
		private final Map<String, String> links;

		public GitLocationProcessor(String fileName) {
			this.fileName = fileName;
			this.links = new HashMap<>();
		}

		public void readData() {
			if (links.keySet().size() == 0) {
				FileOpener fileOpener = new FileOpener(this.fileName);
				List<String> lines = fileOpener.getLines();
				lines.forEach(l -> this.processLine(l));
			}
		}

		public boolean hasLink(String diskLocation) {
			return this.links.containsKey(diskLocation);
		}

		public String getLink(String diskLocation) {
			assert this.links.containsKey(diskLocation);
			return this.links.get(diskLocation);
		}

		private void processLine(String line) {
			List<String> parts = StringHelper.explode(line, ",");
			if (parts.size() == 2) {
				this.addLink(parts.get(0), parts.get(1));
			}
		}

		private void addLink(String diskLocation, String link) {
			if (!this.links.containsKey(diskLocation)) {
				this.links.put(diskLocation, link);
			}
		}
	}
}
