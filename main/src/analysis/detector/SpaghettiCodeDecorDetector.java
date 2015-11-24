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
	protected void addRequiredPercentages() {
		this.addRequiredPercentage(Metric.SUBROUTINE_LOC, 85);
		this.addRequiredPercentage(Metric.CLASS_METHODS_NO_PARAMS, 85);
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

	@Override
	protected String getName() {
		return "Spaghetti Code (DECOR)";
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
//			if (this.metrics.isMildOutlier(Metric.SUBROUTINE_LOC, mSize)) {
			if (this.metrics.isInTop(Metric.SUBROUTINE_LOC, 15, mSize)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasTooManyMethodsWithNoParams(String fullClassPath) {
//		return this.metrics.isMildOutlier(Metric.CLASS_METHODS_NO_PARAMS, this.nrMethodsWithNoParams.get(fullClassPath));
		return this.metrics.isInTop(Metric.CLASS_METHODS_NO_PARAMS, 15, this.nrMethodsWithNoParams.get(fullClassPath));
	}

}
