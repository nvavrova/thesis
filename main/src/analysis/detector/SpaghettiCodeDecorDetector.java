package analysis.detector;

import model.Class;
import model.Subroutine;
import util.LexicalHelper;

/**
 * Created by Nik on 07-11-2015
 */
public class SpaghettiCodeDecorDetector extends Detector {

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		return this.noInheritance(cls) && this.hasProceduralName(cls.getName()) && this.usesGlobals(cls);
	}

	@Override
	protected Boolean confirmDefect(Class cls) {
		return this.hasLongMethod(cls) && this.hasTooManyMethodsWithNoParams(cls);
	}

	protected Boolean hasProceduralName(String name) {
		return LexicalHelper.isProceduralName(name);
	}

	public boolean usesGlobals(Class cls) {
		return cls.getReferencedGlobalsSet().size() > 1;
	}

	protected boolean noInheritance(Class cls) {
		return cls.getSuperclassNames().size() == 0;
	}

	protected boolean hasLongMethod(Class cls) {
		for (Subroutine m : cls.getDefinedSubroutinesSet()) {
			if (this.isLong(m)) {
				return true;
			}
		}
		return false;
	}

	protected boolean hasTooManyMethodsWithNoParams(Class cls) {
		return cls.subroutinesWithNoParamsCount() > 7;
	}

	public boolean isLong(Subroutine m) {
		return m.getLoc() > 100;
	}

}
