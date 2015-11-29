package analysis.detector;

import analysis.Metric;
import analysis.storage.PrimitiveIntMap;

import java.io.IOException;

/**
 * Created by Nik on 07-11-2015
 */
public class SwissArmyKnifeDecorDetector extends Detector {

	private final static String PARENTS = "PARENTS";

	public SwissArmyKnifeDecorDetector() throws IOException {
		super();
	}

	@Override
	public void addDataStores() throws IOException {
		this.addDataStore(PARENTS, new PrimitiveIntMap(this.getStoreFilePath(PARENTS)));
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		boolean check = cls.superclassCount() > 1;

		if (check) {
			this.getPrimitiveMapStore(PARENTS).add(cls.getFullPath(), cls.superclassCount());
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.hasTooManyParents(fullPath);
	}

	@Override
	protected String getName() {
		return "Swiss Army Knife (DECOR)";
	}

	private boolean hasTooManyParents(String fullClsPath) {
		return this.metrics.isExtremeOutlier(Metric.CLASS_SUPERCLASSES, this.getPrimitiveMapStore(PARENTS).get(fullClsPath));
//		return this.metrics.isInTop(Metric.CLASS_SUPERCLASSES, 10, this.parents.get(fullClsPath));
	}

}
