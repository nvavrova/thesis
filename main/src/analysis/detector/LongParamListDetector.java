package analysis.detector;

import analysis.Metric;
import model.Subroutine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 10-11-2015
 */
public class LongParamListDetector extends Detector {

	private final Map<String, Integer> subroutineParamCount;

	public LongParamListDetector() {
		this.subroutineParamCount = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		this.subroutineParamCount.put(subroutine.getFullPath(), subroutine.paramCount());
		return true;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.SUBROUTINE_PARAMS, this.subroutineParamCount.get(fullPath));
	}

	@Override
	protected String getName() {
		return "Long Parameter List";
	}
}
