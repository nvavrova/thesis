package main;

import ast.Module;
import model.Class;
import model.*;
import process.File2Tree;
import process.GitLocationProcessor;
import util.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 24-11-2015
 */
public class StatsCsvCreator extends CsvCreator {
	private final List<File> projectFolders;
	private final GitLocationProcessor gitLocs;

	private static final String CLASS_STREAM_NAME = "class";
	private static final String MODULE_STREAM_NAME = "module";
	private static final String PROJECT_STREAM_NAME = "project";

	public StatsCsvCreator(String folder, List<File> projectFolders, String gitLocationsFile) {
		super(folder);
		this.projectFolders = projectFolders;
		this.gitLocs = new GitLocationProcessor(gitLocationsFile);
		this.gitLocs.readData();
	}

	public void createStatsCsv() throws IOException {
		this.createClassStream();
		this.createModuleStream();
		this.createProjectStream();

		BufferedReader br = new BufferedReader(new FileReader("filtered-projects.txt"));
		List<String> projects = br.lines().collect(Collectors.toList());
		br.close();

		for (File f : this.projectFolders) {
			if (projects.contains(f.getAbsolutePath())) {
				System.out.print(((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024/1024));
				this.createStatsCsv(f);
				System.gc();
				System.out.println(" -> " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024/1024) + "\t\t" + f.getAbsolutePath());
			}
		}
	}

	private void createClassStream() throws IOException {
		this.createStream(CLASS_STREAM_NAME, "project", "module", "class name", "# of private variables",
				"# of protected variables", "# of public variables", "# of accessors", "LCOM", "LOC", "# of parents",
				"# of methods with no params", "# of used globals", "# of defined globals", "# of methods",
				"total method AID", "total method ALD", "average method AID", "average method ALD");
	}

	private void createModuleStream() throws IOException {
		this.createStream(MODULE_STREAM_NAME, "project", "git link", "module", "parses", "LOC", "# of classes");
	}

	private void createProjectStream() throws IOException {
		this.createStream(PROJECT_STREAM_NAME, "project", "git link", "modules", "LOC", "classes", "parse ratio");
	}

	private void createStatsCsv(File projectFolder) {
//			System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
//			System.out.println("Name: " + projectFolder.getAbsolutePath());
		try {
			List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
			Map<String, Module> trees = File2Tree.getAsts(allFiles);
			ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
			Project project = mb.getProject();
			String link = this.gitLocs.getLink(project.getPath());

			Long loc = 0L;
			Integer classCount = 0;
			Integer correctlyParsed = 0;
			for (model.Module module : project.getModules()) {
				this.printModuleLine(module, project.getPath(), link);

				Map<String, model.Class> classes = module.getDefinedClassesInclSubclassesByName();
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
			project.unlink();
		}
		catch (Exception ex) {
			System.err.println("EXCEPTION: " + ex.getMessage());
			ex.printStackTrace();
		}
//			System.out.println("-----------------------------------------------------------------------------------------------------------");
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
		Double parsePercentage = parseRatio * 100;

		List<String> line = new ArrayList<>();
		line.add(projectPath);
		line.add(gitLink);
		line.add(String.valueOf(moduleCount));
		line.add(String.valueOf(loc));
		line.add(String.valueOf(classCount));
		line.add(String.valueOf(parsePercentage.intValue()));
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