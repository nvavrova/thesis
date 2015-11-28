package analysis;

import analysis.detector.Detector;
import main.CsvCreator;
import model.ContentContainer;
import model.Project;
import process.GitLocationProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Nik on 04-11-2015
 */
public class Register {

	private static final String CSV_NAME = "RESULTS";

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

	public void check(Project project) throws FileNotFoundException {
		if (this.finished) {
			throw new IllegalStateException();
		}
		if (!this.detectorsAdded) {
			this.detectorsAdded = true; //don't allow any additional detectors
			for (Detector d : this.detectors) {
				d.openDataStores();
			}
		}
		project.getModules().forEach(m -> this.check(project.getPath(), m));
		project.unlink();
	}

	private void check(String projectPath, ContentContainer contentContainer) {
		this.metrics.register(contentContainer);
		this.detectors.forEach(d -> d.process(projectPath, contentContainer));
		contentContainer.getChildren().forEach(c -> this.check(projectPath, c));
	}

	public void finish(GitLocationProcessor gitLocs, CsvCreator csvCreator) throws IOException {
		this.finished = true; //don't allow any additional checking
		Map<Metric, Set<Integer>> reqMetrics = aggregateRequiredMetrics();
		this.metrics.terminateCollecting(reqMetrics);

		for (Detector detector : this.detectors) {
			detector.deserializeData();
			Map<String, Set<DesignDefect>> detectorResults = detector.finish();
			detector.removeData();

			for (String projectLocation : detectorResults.keySet()) {
				String projectUrl = gitLocs != null ? gitLocs.getLink(projectLocation) : "";
				for (DesignDefect dd : detectorResults.get(projectLocation)) {
					csvCreator.addLine(CSV_NAME, projectLocation, projectUrl, dd.getFullPath(), dd.getDefect());
				}
			}
		}
	}

	private Map<Metric, Set<Integer>> aggregateRequiredMetrics() {
		Map<Metric, Set<Integer>> aggregatedRequiredMetrics = new HashMap<>();
		for (Detector detector : this.detectors) {
			Map<Metric, Set<Integer>> detectorPercentages = detector.getRequiredPercentages();
			for (Metric metric : detectorPercentages.keySet()) {
				if (!aggregatedRequiredMetrics.containsKey(metric)) {
					aggregatedRequiredMetrics.put(metric, new HashSet<>());
				}
				aggregatedRequiredMetrics.get(metric).addAll(detectorPercentages.get(metric));
			}
		}
		return aggregatedRequiredMetrics;
	}
}
