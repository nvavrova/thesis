package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Subroutine;
import util.LexicalHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 05-11-2015
 */
public class BlobDecorDetector extends Detector {

	private final Map<String, Set<Integer>> relatedAccessorCounts;
	private final Map<String, Integer> loc;
	private final Map<String, Integer> lcom;

	public BlobDecorDetector() {
		this.relatedAccessorCounts = new HashMap<>();
		this.loc = new HashMap<>();
		this.lcom = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {

		boolean seemsDefective = this.hasControllerName(cls.getName()) || this.hasControllerMethods(cls);

		if (seemsDefective) {
			Set<Integer> relatedAccessorCounts = cls.getReferencedClassesSet().stream()
					.map(Class::accessorCount)
					.collect(Collectors.toSet());
			this.relatedAccessorCounts.put(cls.getFullPath(), relatedAccessorCounts);
			this.loc.put(cls.getFullPath(), cls.getLoc());
			this.lcom.put(cls.getFullPath(), cls.getLcom());
		}

		return seemsDefective;
	}

	@Override
	protected Boolean confirmDefect(String clsFullPath) {
		return (this.isLargeClass(clsFullPath) || this.hasLowCohesion(clsFullPath)) && this.relatedDataClassesCount(clsFullPath) > 2;
	}

	@Override
	protected String getName() {
		return "Blob (DECOR)";
	}

	private boolean hasControllerMethods(Class cls) {
		for (Subroutine m : cls.getDefinedSubroutinesSet()) {
			if (this.isController(m)) {
				return true;
			}
		}
		return false;
	}

	private boolean isController(Subroutine m) {
		return this.hasControllerName(m.getName());
	}

	private boolean hasControllerName(String name) {
		return LexicalHelper.isControllerName(name);
	}

	private boolean isLargeClass(String clsFullPath) {
		return this.metrics.isMildOutlier(Metric.CLASS_LOC, this.loc.get(clsFullPath));
//		return this.metrics.isInTop(Metric.CLASS_LOC, 15, this.loc.get(clsFullPath));
	}

	private boolean hasLowCohesion(String clsFullPath) {
		return this.metrics.isMildOutlier(Metric.CLASS_LCOM, this.lcom.get(clsFullPath));
//		return this.metrics.isInTop(Metric.CLASS_LCOM, 15, this.lcom.get(clsFullPath));
	}

	private long relatedDataClassesCount(String clsFullPath) {
		return this.relatedAccessorCounts.get(clsFullPath).stream()
				.filter(v -> this.metrics.isMildOutlier(Metric.CLASS_ACCESSORS, v))
//				.filter(v -> this.metrics.isInTop(Metric.CLASS_ACCESSORS, 15, v))
				.count();
	}
}
;