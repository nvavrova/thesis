package analysis.detector;

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

	private Boolean hasControllerName(String name) {
		return LexicalHelper.isControllerName(name);
	}

	private long relatedDataClassesCount(Class cls) {
		return cls.getReferencedClassesSet().stream().filter(c -> this.isDataClass(c)).count();
	}

	private boolean isDataClass(Class cls) {
		Long publicVarCount = cls.getDefinedVariablesSet().stream().filter(Variable::isPrivate).count();
		return publicVarCount > 11;
//		return cls.getNumberOfAccessors() > 5;
	}

	private boolean isLargeClass(Class cls) {
		return false; //TODO
	}

	private boolean hasLowCohesion(Class cls) {
		return false; //TODO
	}
}
