package analysis.detector;

import analysis.Metric;
import analysis.storage.SetIntMap;
import model.Class;
import model.Variable;
import util.LexicalHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nik on 07-11-2015
 */
public class FunctionalDecompositionDecorDetector extends Detector {

	private final static int RCPF_LIMIT = 2;

	private final static String RCPF = "RCPF";

	public FunctionalDecompositionDecorDetector() throws IOException {
	}

	@Override
	public void addDataStores() throws IOException {
		this.addDataStore(RCPF, new SetIntMap(this.getStoreFilePath(RCPF)));
	}

	@Override
	protected Boolean isPreliminarilyDefective(model.Class cls) {

		Set<Integer> relatedPrivateVars = new HashSet<>();
		cls.getReferencedClassesSet().stream().filter(rc -> this.hasOneMethod(rc)).forEach(rc -> {
			Long privateVars = rc.getDefinedVarsInclParentsVars().getAsSet().stream()
					.filter(Variable::isPrivate)
					.count();
			relatedPrivateVars.add(privateVars.intValue());
		});

		boolean check = this.hasProceduralName(cls.getName()) && this.noInheritance(cls) && relatedPrivateVars.size() >= RCPF_LIMIT;

		if (check) {
			relatedPrivateVars.forEach(v -> this.getSetMapStore(RCPF).add(cls.getFullPath(), v));
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.relatedClassesWithOneMethodAndLotOfPrivateFields(fullPath) >= RCPF_LIMIT;
	}

	@Override
	protected String getName() {
		return "Functional Decomposition (DECOR)";
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
		Long count = this.getSetMapStore(RCPF).get(fullClsPath).stream()
				.filter(v -> this.metrics.isMildOutlier(Metric.CLASS_PRIVATE_FIELDS, v))
				.count();
		return count.intValue();
	}
}
