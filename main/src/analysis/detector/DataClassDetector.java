package analysis.detector;

import analysis.Metric;
import model.Class;
import model.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 10-11-2015
 */
public class DataClassDetector extends Detector {

	private final Map<String, Integer> classAccessors;
	private final Map<String, Integer> classPublicFields;

	public DataClassDetector() {
		this.classAccessors = new HashMap<>();
		this.classPublicFields = new HashMap<>();
	}

	@Override
	protected Boolean isPreliminarilyDefective(Class cls) {
		Integer accessors = cls.accessorCount();
		Long publicFields = cls.getDefinedVarsInclParentsVars().getAsSet().stream().filter(Variable::isPublic).count();
		boolean check = accessors > 1 || publicFields > 1;

		if (check) {
			this.classAccessors.put(cls.getFullPath(), accessors);
			this.classPublicFields.put(cls.getFullPath(), publicFields.intValue());
		}

		return check;
	}

	@Override
	protected Boolean confirmDefect(String fullPath) {
		return this.metrics.isExtremeOutlier(Metric.CLASS_ACCESSORS, this.classAccessors.get(fullPath))
				|| this.metrics.isExtremeOutlier(Metric.CLASS_PUBLIC_FIELDS, this.classPublicFields.get(fullPath));
	}

	@Override
	protected String getName() {
		return "Data Class";
	}
}
