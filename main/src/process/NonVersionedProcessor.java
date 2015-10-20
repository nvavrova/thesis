package process;

import ast.Module;
import model.ModelBuilder;
import model.Project;
import util.FileHelper;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 15-10-2015
 */
public class NonVersionedProcessor extends Processor {

	private final String projectsLocation;

	public NonVersionedProcessor(String projectsLocation) {
		this.projectsLocation = projectsLocation;
	}

	@Override
	public void process() {
		File projectsFolder = new File(this.projectsLocation);
		for (File file : projectsFolder.listFiles()) {
			if (file.isDirectory()) {
				Project project = this.createProject(file);
				this.process(project);
			}
		}
	}

	private Project createProject(File projectFolder) {
		List<String> allFiles = FileHelper.getPythonFilePaths(projectFolder);
		Map<String, Module> trees = File2Tree.getAsts(allFiles);
		ModelBuilder mb = new ModelBuilder(projectFolder, trees.values());
		return mb.getProject();
	}

	private void process(Project project) {
		//TODO
	}
}
