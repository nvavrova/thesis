package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Subroutine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 09-11-2015
 */
public class FeatureEnvyLiShatnawiDetector extends Detector {
	//AID > 4 and AID in top 10% and ALD < 3 and referenced classes < 3

	private final Map<String, Set<Integer>> functionAids;

	public FeatureEnvyLiShatnawiDetector() {
		this.functionAids = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(Class cls) {
		Set<Integer> functionAids = new HashSet<>();

		for (Subroutine subroutine : cls.getDefinedSubroutinesSet()) {
			if (subroutine.getAccessOfImportData() > 4 && subroutine.getAccessOfLocalData() < 3
					&& this.getReferencedNonSuperclassClasses(cls.getParentsSet(), subroutine).size() < 3) {
				functionAids.add(subroutine.getAccessOfImportData());
			}
		}
		boolean check = functionAids.size() > 0;
		if (check) {
			this.functionAids.put(cls.getFullPath(), functionAids);
		}
		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		for (Integer aid : this.functionAids.get(fullPath)) {
			if (this.metrics.isInTop(Metric.SUBROUTINE_AID, 10, aid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected String getName() {
		return "Feature Envy (Li & Shatnawi)";
	}

	protected Set<Class> getReferencedNonSuperclassClasses(Set<Class> superclasses, Subroutine subroutine) {
		return subroutine.getReferencedClassesSet().stream()
				.filter(c -> !superclasses.contains(c))
				.collect(Collectors.toSet());
	}
}
