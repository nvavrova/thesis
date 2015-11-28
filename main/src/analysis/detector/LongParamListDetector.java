package analysis.detector;

import analysis.Metric;
import analysis.storage.PrimitiveIntMap;
import model.Subroutine;

/**
 * Created by Nik on 10-11-2015
 */
public class LongParamListDetector extends Detector {

	private final static String PARAMS = "PARAMS";

	@Override
	public void addDataStores() {
		this.addDataStore(PARAMS, new PrimitiveIntMap("LongParamList_PARAMS"));
	}

	@Override
	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		this.getPrimitiveMapStore(PARAMS).add(subroutine.getFullPath(), subroutine.paramCount());
		return true;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.SUBROUTINE_PARAMS, this.getPrimitiveMapStore(PARAMS).get(fullPath));
	}

	@Override
	protected String getName() {
		return "Long Parameter List";
	}
}
