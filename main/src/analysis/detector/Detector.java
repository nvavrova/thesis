package analysis.detector;

import analysis.DesignDefect;
import analysis.Metric;
import analysis.Metrics;
import analysis.storage.*;
import model.Class;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Map;

/**
 * Created by Nik on 04-11-2015
 */
public abstract class Detector {

	private SetStrMap defects;

	private Map<String, analysis.storage.Map> dataStores;

	private final Map<Metric, Set<Integer>> requiredPercentages;

	protected Metrics metrics;
	private final PreliminaryVisitor preliminaryVisitor;
	private boolean finished;

	public Detector() {
		this.defects = new SetStrMap(this.getName() + "_suspected_defects_per_project");
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

	public Map<String, Set<DesignDefect>> finish() throws IOException {
		this.defects.deserialize();
		Map<String, Set<DesignDefect>> result = new HashMap<>();
		for (String projectPath : this.defects.keySet()) {
			for (String fullPath : this.defects.get(projectPath)) {
				if (this.confirmDefect(fullPath)) {
					if (!result.containsKey(projectPath)) {
						result.put(projectPath, new HashSet<>());
					}
					result.get(projectPath).add(new DesignDefect(fullPath, this.getName()));
				}
			}
		}
		return result;
	}

	public Map<Metric, Set<Integer>> getRequiredPercentages() {
		return this.requiredPercentages;
	}

	protected void addRequiredPercentages() {}

	protected void addRequiredPercentage(Metric metric, Integer percentage) {
		if (!this.requiredPercentages.containsKey(metric)) {
			this.requiredPercentages.put(metric, new HashSet<>());
		}
		this.requiredPercentages.get(metric).add(percentage);
	}

	public abstract void addDataStores();

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

	public void openDataStores() throws FileNotFoundException {
		this.defects.createDataStore();
		for (analysis.storage.Map m : this.dataStores.values()) {
			m.createDataStore();
		}
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
