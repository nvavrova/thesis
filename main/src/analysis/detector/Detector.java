package analysis.detector;

import analysis.Metric;
import analysis.Metrics;
import analysis.storage.ListMap;
import analysis.storage.PrimitiveIntMap;
import analysis.storage.SetIntMap;
import analysis.storage.SetStrMap;
import main.CsvCreator;
import model.Class;
import model.*;
import process.GitLocationProcessor;
import util.FileHelper;
import util.Settings;

import java.io.IOException;
import java.util.*;

/**
 * Created by Nik on 04-11-2015
 */
public abstract class Detector {

	private static final String DATA_STORES_EXTENSION = "data";

	private SetStrMap defects;

	protected Map<String, analysis.storage.Map> dataStores;

	private final Map<Metric, Set<Integer>> requiredPercentages;

	protected Metrics metrics;
	private final PreliminaryVisitor preliminaryVisitor;
	private boolean finished;

	public Detector() throws IOException {
		String fileName = this.getSuspectFilePath();
		this.defects = new SetStrMap(fileName);
		this.dataStores = new HashMap<>();
		this.requiredPercentages = new HashMap<>();
		this.addRequiredPercentages();
		this.preliminaryVisitor = new PreliminaryVisitor();
		this.addDataStores();
	}

	public void addMetrics(Metrics metrics) {
		this.metrics = metrics;
		this.finished = false;
	}

	public void process(String projectPath, ContentContainer contentContainer) {
		if (this.finished) {
			throw new IllegalStateException();
		}
		this.processChecked(projectPath, contentContainer);
	}

	private void processChecked(String projectPath, ContentContainer contentContainer) {
		Boolean defective = this.preliminaryVisitor.checkForDefect(contentContainer);
		if (defective) {
			this.defects.add(projectPath, contentContainer.getFullPath());
		}
	}

	public void finish(GitLocationProcessor gitLocs, CsvCreator csvCreator, String csvName) throws IOException {
		this.defects.deserialize(false);
		String projectPath = this.defects.getNextKey();
		while (projectPath != null) {
			String fullPath = this.defects.getNextVal();
			if (this.confirmDefect(fullPath)) {
				String projectUrl = gitLocs != null ? gitLocs.getLink(projectPath) : "";
				csvCreator.addLine(csvName, projectPath, projectUrl, fullPath, this.getName());
			}
			projectPath = this.defects.getNextKey();
		}
	}

	public Map<Metric, Set<Integer>> getRequiredPercentages() {
		return this.requiredPercentages;
	}

	protected void addRequiredPercentages() {
	}

	protected void addRequiredPercentage(Metric metric, Integer percentage) {
		if (!this.requiredPercentages.containsKey(metric)) {
			this.requiredPercentages.put(metric, new HashSet<>());
		}
		this.requiredPercentages.get(metric).add(percentage);
	}

	public abstract void addDataStores() throws IOException;

	protected void addDataStore(String name, analysis.storage.Map dataStore) {
		this.dataStores.put(name, dataStore);
	}

	protected PrimitiveIntMap getPrimitiveMapStore(String name) {
		return (PrimitiveIntMap) this.dataStores.get(name);
	}

	protected SetIntMap getSetMapStore(String name) {
		return (SetIntMap) this.dataStores.get(name);
	}

	protected ListMap getListMapStore(String name) {
		return (ListMap) this.dataStores.get(name);
	}

	public void removeData() {
		this.defects.clean();
		this.dataStores.values().forEach(analysis.storage.Map::clean);

	}

	public void deserializeData() throws IOException {
		for (analysis.storage.Map m : this.dataStores.values()) {
			m.deserialize();
		}

	}

	protected String getSuspectFilePath() throws IOException {
		Properties suspectConfig = Settings.getSuspectConfig();
		boolean exists = suspectConfig.containsKey(this.getName());
		String folder = Settings.getConfig().getProperty("locations.data.output");
		return exists ? suspectConfig.getProperty(this.getName()) : FileHelper.stampedFileName(folder, this.getName(), DATA_STORES_EXTENSION);
	}

	protected String getStoreFilePath(String type) throws IOException {
		Properties mapsConfig = Settings.getMapsConfig();
		String fullName = this.getName() + "_" + type;
		boolean exists = mapsConfig.containsKey(fullName);
		String folder = Settings.getConfig().getProperty("locations.data.output");
		return exists ? mapsConfig.getProperty(fullName) : FileHelper.stampedFileName(folder, type, DATA_STORES_EXTENSION);
	}

	//override these where necessary
	protected Boolean isPreliminarilyDefective(Module module) {
		return false;
	}

	protected Boolean isPreliminarilyDefective(Class cls) {
		return false;
	}

	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		return false;
	}

	protected abstract Boolean confirmDefect(String fullPath);

	protected abstract String getName();

	private class PreliminaryVisitor implements ContentContainerVisitor<Boolean> {

		public Boolean checkForDefect(ContentContainer contentContainer) {
			return contentContainer.accept(this);
		}

		@Override
		public Boolean visit(Module m) {
			return isPreliminarilyDefective(m);
		}

		@Override
		public Boolean visit(model.Class m) {
			return isPreliminarilyDefective(m);
		}

		@Override
		public Boolean visit(Subroutine m) {
			return isPreliminarilyDefective(m);
		}
	}
}
