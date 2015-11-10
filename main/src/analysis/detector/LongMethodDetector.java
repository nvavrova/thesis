package analysis.detector;

import analysis.Metric;
import model.Subroutine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 10-11-2015
 */
public class LongMethodDetector extends Detector {

	private final Map<String,Integer> subroutineLoc;

	public LongMethodDetector() {
		this.subroutineLoc = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		this.subroutineLoc.put(subroutine.getFullPath(), subroutine.getLoc());
		return true;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.SUBROUTINE_LOC, this.subroutineLoc.get(fullPath));
	}

	@Override
	protected String getName() {
		return "Long Method";
	}
}
