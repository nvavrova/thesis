package analysis.detector;

import analysis.DesignDefect;
import analysis.Metric;
import analysis.Metrics;
import model.Class;
import model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 04-11-2015
 */
public abstract class Detector {

	private Map<String, Set<DesignDefect>> defects;

	private final Map<Metric, Set<Integer>> requiredPercentages;

	protected Metrics metrics;
	private final PreliminaryVisitor preliminaryVisitor;
	private boolean finished;

	public Detector() {
		this.defects = new HashMap<>();
		this.requiredPercentages = new HashMap<>();
		this.addRequiredPercentages();
		this.preliminaryVisitor = new PreliminaryVisitor();
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
			if (!this.defects.containsKey(projectPath)) {
				this.defects.put(projectPath, new HashSet<>());
			}
			this.defects.get(projectPath).add(new DesignDefect(contentContainer.getFullPath(), this.getName()));
		}
	}

	public Map<String, Set<DesignDefect>> finish() {
		Map<String, Set<DesignDefect>> result = new HashMap<>();
		for (String projectPath : this.defects.keySet()) {
			for (DesignDefect designDefect : this.defects.get(projectPath)) {
				if (this.confirmDefect(designDefect.getFullPath())) {
					if (!result.containsKey(projectPath)) {
						result.put(projectPath, new HashSet<>());
					}
					result.get(projectPath).add(designDefect);
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
