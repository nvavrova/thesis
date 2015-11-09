package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Subroutine;
import util.LexicalHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 07-11-2015
 */
public class SpaghettiCodeDecorDetector extends Detector {

	private final Map<String, List<Integer>> methodSizes;
	private final Map<String, Integer> nrMethodsWithNoParams;

	public SpaghettiCodeDecorDetector() {
		this.methodSizes = new HashMap<>();
		this.nrMethodsWithNoParams = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		boolean check = this.noInheritance(cls) && this.hasProceduralName(cls.getName()) && this.usesGlobals(cls);

		if (check) {
			List<Integer> subroutinesLoc = cls.getDefinedSubroutinesSet().stream()
					.map(Subroutine::getLoc)
					.collect(Collectors.toList());
			this.methodSizes.put(cls.getFullPath(), subroutinesLoc);
			this.nrMethodsWithNoParams.put(cls.getFullPath(), cls.subroutinesWithNoParamsCount());
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.hasLongMethod(fullPath) && this.hasTooManyMethodsWithNoParams(fullPath);
	}

	private boolean hasProceduralName(String name) {
		return LexicalHelper.isProceduralName(name);
	}

	private boolean usesGlobals(Class cls) {
		return cls.getReferencedGlobalsSet().size() > 1;
	}

	private boolean noInheritance(Class cls) {
		return cls.getSuperclassNames().size() == 0;
	}

	private boolean hasLongMethod(String fullClassPath) {
		for (Integer mSize : this.methodSizes.get(fullClassPath)) {
			if (this.metrics.isInTop(Metric.SUBROUTINE_LOC, 12, mSize)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasTooManyMethodsWithNoParams(String fullClassPath) {
		return this.metrics.isInTop(Metric.CLASS_METHODS_NO_PARAMS, 12, this.nrMethodsWithNoParams.get(fullClassPath));
	}

}
