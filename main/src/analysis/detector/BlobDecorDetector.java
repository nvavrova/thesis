package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Subroutine;
import model.Variable;
import util.LexicalHelper;

/**
 * Created by Nik on 05-11-2015
 */
public class BlobDecorDetector extends Detector {

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		return (this.hasControllerName(cls.getName()) || this.hasControllerMethods(cls))
				&& this.relatedDataClassesCount(cls) > 1;
	}

	@Override
	protected Boolean confirmDefect(model.Class cls) {
		return this.isLargeClass(cls) || this.hasLowCohesion(cls);
	}

	private Boolean hasControllerMethods(Class cls) {
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

	private long relatedDataClassesCount(Class cls) {
		return cls.getReferencedClassesSet().stream().filter(c -> this.isDataClass(c)).count();
	}

	private boolean isDataClass(Class cls) {
		Long publicVarCount = cls.getDefinedVariablesSet().stream().filter(Variable::isPrivate).count();
		return publicVarCount > 11;
		//nr of accessors very high
//		return cls.getNumberOfAccessors() > 5;
	}

	private boolean isLargeClass(Class cls) {
		return this.metrics.isInTop(Metric.CLASS_LOC, 10, cls.getLoc());
	}

	private boolean hasLowCohesion(Class cls) {
		return this.metrics.isInTop(Metric.CLASS_LCOM, 10, cls.getLcom());
	}
}
