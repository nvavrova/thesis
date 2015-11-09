package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Variable;
import util.LexicalHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 07-11-2015
 */
public class FunctionalDecompositionDecorDetector extends Detector {

	private final Map<String, Set<Integer>> nrRelatedClassPrivateFields;

	public FunctionalDecompositionDecorDetector() {
		this.nrRelatedClassPrivateFields = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		boolean check = this.hasOneMethod(cls) && this.hasProceduralName(cls.getName()) && this.noInheritance(cls);

		if (check) {
			Set<Integer> relatedPrivateVars = new HashSet<>();
			for (Class rc : cls.getReferencedClassesSet()) {
				if (rc.getDefinedSubroutinesSet().size() == 1) {
					Long privateVars = rc.getDefinedVarsInclParentsVars().getAsSet().stream()
							.filter(Variable::isPrivate)
							.count();
					relatedPrivateVars.add(privateVars.intValue());
				}
			}
			this.nrRelatedClassPrivateFields.put(cls.getFullPath(), relatedPrivateVars);
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.relatedClassesWithOneMethodAndLotOfPrivateFields(fullPath) >= 2;
	}

	private boolean hasOneMethod(Class cls) {
		return cls.getDefinedSubroutinesSet().size() == 1;
	}

	private boolean hasProceduralName(String name) {
		return LexicalHelper.isProceduralName(name);
	}

	private boolean noInheritance(Class cls) {
		return cls.getSuperclassNames().size() == 0;
	}

	private Integer relatedClassesWithOneMethodAndLotOfPrivateFields(String fullClsPath) {
		Long count = this.nrRelatedClassPrivateFields.get(fullClsPath).stream()
				.filter(v -> this.metrics.isInTop(Metric.CLASS_METHODS, 25, v))
				.count();
		return count.intValue();
	}
}
