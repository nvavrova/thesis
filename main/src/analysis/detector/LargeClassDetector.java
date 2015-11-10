package analysis.detector;

import analysis.Metric;
import model.Class;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 10-11-2015
 */
public class LargeClassDetector extends Detector {

	private final Map<String, Integer> classLoc;

	public LargeClassDetector() {
		this.classLoc = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(Class cls) {
		this.classLoc.put(cls.getFullPath(), cls.getLoc());
		return true;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.CLASS_LOC, this.classLoc.get(fullPath));
	}

	@Override
	protected String getName() {
		return "Large Class";
	}
}
