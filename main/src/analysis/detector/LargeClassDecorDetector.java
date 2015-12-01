package analysis.detector;

import analysis.Metric;
import analysis.storage.PrimitiveIntMap;

import java.io.IOException;

/**
 * Created by Nik on 01-12-2015
 */
public class LargeClassDecorDetector extends Detector {

	private final static String METVARS = "MetVars";

	public LargeClassDecorDetector() throws IOException {
		super();
	}

	@Override
	public void addDataStores() throws IOException {
		this.addDataStore(METVARS, new PrimitiveIntMap(this.getStoreFilePath(METVARS)));
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		Integer metVars = this.getMetVars(cls);
		Boolean check = metVars > 0;
		if (check) {
			this.getPrimitiveMapStore(METVARS).add(cls.getFullPath(), getMetVars(cls));
		}
		return check;
	}

	private int getMetVars(model.Class cls) {
		return cls.getDefinedSubroutinesSet().size() + cls.getDefinedVarsInclParentsVars().getAsSet().size();
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.CLASS_METHODS_AND_VARS, this.getPrimitiveMapStore(METVARS).get(fullPath));
	}

	@Override
	protected String getName() {
		return "Large Class (DECOR)";
	}
}
