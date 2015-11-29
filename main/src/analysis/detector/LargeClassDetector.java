package analysis.detector;

import analysis.Metric;
import analysis.storage.PrimitiveIntMap;
import model.Class;

import java.io.IOException;

/**
 * Created by Nik on 10-11-2015
 */
public class LargeClassDetector extends Detector {

	private final static String LOC = "LOC";

	public LargeClassDetector() throws IOException {
		super();
	}

	@Override
	public void addDataStores() throws IOException {
		this.addDataStore(LOC, new PrimitiveIntMap(this.getStoreFilePath(LOC)));
	}

	@Override
	protected Boolean isPreliminarilyDefective(Class cls) {
		this.getPrimitiveMapStore(LOC).add(cls.getFullPath(), cls.getLoc());
		return true;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.CLASS_LOC, this.getPrimitiveMapStore(LOC).get(fullPath));
	}

	@Override
	protected String getName() {
		return "Large Class";
	}
}
