package process;

import db.DataHandler;
import db.pojo.ProjectEntity;
import model.Project;

import java.io.File;

/**
 * Created by Nik on 15-10-2015
 */
public class VersionedProcessor extends Processor {

	private final Boolean saveInDb;

	public VersionedProcessor(Boolean saveInDb) {
		this.saveInDb = saveInDb;
	}

	@Override
	public void process() {
		DataHandler.loadProjects().forEach(this::handleAllProjectVersions);
	}

	private void handleAllProjectVersions(ProjectEntity projectEntity) {
		System.out.println("----------------------------------------------- NEW PROJECT -----------------------------------------------");
		System.out.println("Name: " + projectEntity.getDiskLocation());
		try {
			this.processProject(projectEntity);
		}
		catch (Exception ex) {
			handleException(ex);
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}

	private void processProject(ProjectEntity projectEntity) throws Exception {
		DataHandler dataHandler = new DataHandler(projectEntity);
		File projectFolder = new File(projectEntity.getDiskLocation());
		try {
			VersionSwitcher versionSwitcher = new VersionSwitcher(projectFolder);
			Project project = versionSwitcher.getFirstProjectVersion();
			this.processResults(project, dataHandler);

			while (!versionSwitcher.isAtLatestVersion()) {
				project = versionSwitcher.getNextProjectVersion();
				this.processResults(project, dataHandler);
			}

		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	private void processResults(Project project, DataHandler dataHandler) {
		if (this.saveInDb) {
			dataHandler.save(project);
		}
	}

	private static void handleException(Exception ex) {
		System.err.println("EXCEPTION: " + ex.getMessage());
		ex.printStackTrace();
	}
}
