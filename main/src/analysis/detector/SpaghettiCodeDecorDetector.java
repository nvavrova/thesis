package analysis.detector;

import analysis.Metric;
import analysis.storage.ListMap;
import analysis.storage.PrimitiveIntMap;
import model.Class;
import model.Subroutine;
import util.LexicalHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 07-11-2015
 */
public class SpaghettiCodeDecorDetector extends Detector {

	private final static String MLOCS = "MLOCS";
	private final static String MNOPARAM = "MNOPARAM";

	public SpaghettiCodeDecorDetector() throws IOException {
		super();
	}

	@Override
	protected void addRequiredPercentages() {
		this.addRequiredPercentage(Metric.SUBROUTINE_LOC, 85);
		this.addRequiredPercentage(Metric.CLASS_METHODS_NO_PARAMS, 85);
	}

	@Override
	public void addDataStores() throws IOException {
		this.addDataStore(MLOCS, new ListMap(this.getStoreFilePath(MLOCS)));
		this.addDataStore(MNOPARAM, new PrimitiveIntMap(this.getStoreFilePath(MNOPARAM)));
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {
		List<Integer> subroutinesLoc = cls.getDefinedSubroutinesSet().stream()
				.map(Subroutine::getLoc)
				.collect(Collectors.toList());

		boolean check = this.noInheritance(cls) && this.hasProceduralName(cls.getName()) && this.usesGlobals(cls) && subroutinesLoc.size() > 0;

		if (check) {
			subroutinesLoc.forEach(v -> this.getListMapStore(MLOCS).add(cls.getFullPath(), v));
			this.getPrimitiveMapStore(MNOPARAM).add(cls.getFullPath(), cls.subroutinesWithNoParamsCount());
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
		for (Integer mSize : this.getListMapStore(MLOCS).get(fullClassPath)) {
//			if (this.metrics.isMildOutlier(Metric.SUBROUTINE_LOC, mSize)) {
			if (this.metrics.isInTop(Metric.SUBROUTINE_LOC, 15, mSize)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasTooManyMethodsWithNoParams(String fullClassPath) {
//		return this.metrics.isMildOutlier(Metric.CLASS_METHODS_NO_PARAMS, this.nrMethodsWithNoParams.get(fullClassPath));
		return this.metrics.isInTop(Metric.CLASS_METHODS_NO_PARAMS, 15, this.getPrimitiveMapStore(MNOPARAM).get(fullClassPath));
	}

}
