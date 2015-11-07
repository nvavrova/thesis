package analysis.detector;

import analysis.Metric;
import model.Class;
import util.LexicalHelper;

/**
 * Created by Nik on 07-11-2015
 */
public class FunctionalDecompositionDecorDetector extends Detector {

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		return this.hasOneMethod(cls) && this.hasProceduralName(cls.getName()) && this.noInheritance(cls);
	}

	@Override
	protected Boolean confirmDefect(Class cls) {
		return this.hasLargeAmountOfPrivateFields(cls);
	}

	private boolean hasOneMethod(Class cls) {
		return cls.getDefinedSubroutinesSet().size() == 1;
	}

	private boolean hasLargeAmountOfPrivateFields(Class cls) {
		return this.metrics.isInTop(Metric.CLASS_METHODS, 15, cls.getDefinedSubroutinesSet().size());
	}

	protected boolean hasProceduralName(String name) {
		return LexicalHelper.isProceduralName(name);
	}

	protected boolean noInheritance(Class cls) {
		return cls.getSuperclassNames().size() == 0;
	}
}
