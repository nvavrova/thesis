package analysis.detector;

/**
 * Created by Nik on 07-11-2015
 */
public class SwissArmyKnifeDecorDetector extends Detector {

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		return cls.superclassCount() > 1;
	}

	@Override
	protected Boolean confirmDefect(model.Class cls) {
		return this.hasTooManyParents(cls);
	}

	private boolean hasTooManyParents(model.Class cls) {
		return cls.superclassCount() > 1;
	}

}
