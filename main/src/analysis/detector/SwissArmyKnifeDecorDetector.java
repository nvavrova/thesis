package analysis.detector;

import analysis.Metric;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 07-11-2015
 */
public class SwissArmyKnifeDecorDetector extends Detector {

	private final Map<String, Integer> parents;

	public SwissArmyKnifeDecorDetector() {
		this.parents = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		boolean check = cls.superclassCount() > 1;

		if (check) {
			this.parents.put(cls.getFullPath(), cls.superclassCount());
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
		return this.metrics.isExtremeOutlier(Metric.CLASS_SUPERCLASSES, this.parents.get(fullClsPath));
//		return this.metrics.isInTop(Metric.CLASS_SUPERCLASSES, 10, this.parents.get(fullClsPath));
	}

}
