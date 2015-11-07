package analysis;

import analysis.detector.Detector;
import model.ContentContainer;
import model.Project;

import java.util.*;

/**
 * Created by Nik on 04-11-2015
 */
public class Register {
	private final List<Detector> detectors;
	private final Metrics metrics;
	private boolean detectorsAdded;
	private boolean finished;

	public Register() {
		this.detectors = new ArrayList<>();
		this.metrics = new Metrics();
		this.detectorsAdded = false;
	}

	public void add(Detector detector) {
		if (this.detectorsAdded) {
			throw new IllegalStateException();
		}
		detector.addMetrics(this.metrics);
		this.detectors.add(detector);
	}

	public void check(Project project) {
		if (this.finished) {
			throw new IllegalStateException();
		}
		this.detectorsAdded = true; //don't allow any additional detectors
		project.getModules().forEach(m -> this.check(m));
	}

	private void check(ContentContainer contentContainer) {
		this.metrics.register(contentContainer);
		this.detectors.forEach(d -> d.process(contentContainer));
		contentContainer.getChildren().forEach(c -> this.check(c));
	}

	public Map<String, Set<DesignDefect>> finish() {
		this.finished = true; //don't allow any additional checking
		this.metrics.terminateCollecting();

		Map<String, Set<DesignDefect>> results = new HashMap<>();
		for (Detector detector : this.detectors) {
			Map<String, Set<DesignDefect>> detectorResults = detector.finish();
			for (String project : detectorResults.keySet()) {
				if (!results.containsKey(project)) {
					results.put(project, new HashSet<>());
				}
				results.get(project).addAll(detectorResults.get(project));
			}
		}
		return results;
	}
}
