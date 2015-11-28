package analysis.detector;

import analysis.Metric;
import analysis.storage.PrimitiveIntMap;
import model.Subroutine;
import model.SubroutineType;

/**
 * Created by Nik on 09-11-2015
 */
public class FeatureEnvyLiShatnawiDetector extends Detector {
	//AID > 4 and AID in top 10% and ALD < 3 and referenced classes < 3

	private final static String AIDS = "AIDS";

	@Override
	protected void addRequiredPercentages() {
		this.addRequiredPercentage(Metric.SUBROUTINE_AID, 90);
	}

	@Override
	public void addDataStores() {
		this.addDataStore(AIDS, new PrimitiveIntMap("FeatureEnvyLiShatnawi_AIDS"));
	}

	@Override
	protected Boolean isPreliminarilyDefective(Subroutine subroutine) {
		SubroutineType subType = subroutine.getSubroutineType();
		boolean check = (subType == SubroutineType.INSTANCE_METHOD || subType == SubroutineType.STATIC_METHOD)
				&& subroutine.getAccessOfImportData() > 4 && subroutine.getAccessOfLocalData() < 3
				&& subroutine.getReferencedClassesSet().size() < 3;

		if (check) {
			this.getPrimitiveMapStore(AIDS).add(subroutine.getFullPath(), subroutine.getAccessOfImportData());
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isInTop(Metric.SUBROUTINE_AID, 10, this.getPrimitiveMapStore(AIDS).get(fullPath));
	}

	@Override
	protected String getName() {
		return "Feature Envy (Li & Shatnawi)";
	}
}
