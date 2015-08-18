package main;

import ast.Module;
import util.FileHelper;
import model.ModelBuilder;
import model.Project;
import vc.GitHandler;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 05-08-2015
 */
public class VersionSwitcher {

	private final File projectFolder;
	private final GitHandler gitHandler;
	private Map<String, Module> trees;
	private Project project;

	public VersionSwitcher(File projectFolder) throws Exception {
		this.projectFolder = projectFolder;
		this.gitHandler = new GitHandler(projectFolder);
	}

	public Project getLatestProjectVersion() throws Exception {
		this.gitHandler.goToLastCommit();
		return this.getProject();
	}

	public Project getFirstProjectVersion() throws Exception {
		this.gitHandler.goToFirstCommit();
		return this.getProject();
	}

	private Project getProject() {
		List<String> allFiles = FileHelper.getPythonFilePaths(this.projectFolder);
		this.trees = File2AstConverter.getTrees(allFiles);

		ModelBuilder mb = new ModelBuilder(this.projectFolder, this.trees.values());
		this.project = mb.getProject();
		this.project.setVersion(this.gitHandler.getCurrentCommitSha());

		return this.project;
	}

	public boolean isAtLatestVersion() {
		return this.gitHandler.isAtLastCommit();
	}

	public Project getNextProjectVersion() throws Exception {
		List<String> changedFiles = this.gitHandler.goForth();
		List<String> changedPythonFiles = FileHelper.getPythonFilePaths(changedFiles);

		this.cleanUpNonExistingFiles();
		Map<String, Module> currentTrees = File2AstConverter.getTrees(changedPythonFiles);

		ModelBuilder builder = new ModelBuilder(this.project, this.trees, currentTrees);
		this.project = builder.getProject();
		this.project.setVersion(this.gitHandler.getCurrentCommitSha());
		this.trees.putAll(currentTrees);

		return this.project;
	}
//
//	public void cleanUp() throws Exception {
//		this.gitHandler.finalize();
//	}

	private void cleanUpNonExistingFiles() {
		List<String> allFiles = FileHelper.getPythonFilePaths(this.projectFolder);
		Set<String> nonExistingFiles = this.trees.keySet().stream()
				.filter(k -> !allFiles.contains(k))
				.collect(Collectors.toSet());
		nonExistingFiles.forEach(k -> this.trees.remove(k));
	}
}
