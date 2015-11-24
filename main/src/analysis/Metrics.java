package analysis;

import model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 04-11-2015
 */
public class Metrics {

	private final Collector collector;
	private boolean finishedCollecting;

	private final Map<Metric, IntMetricVals> intMetrics;

	public Metrics() {
		this.collector = new Collector();
		this.finishedCollecting = false;

		this.intMetrics = new HashMap<>();
		this.intMetrics.put(Metric.CLASS_LOC, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_SUPERCLASSES, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_METHODS, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_ACCESSORS, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_LCOM, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_METHODS_NO_PARAMS, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_PUBLIC_FIELDS, new IntMetricVals());
		this.intMetrics.put(Metric.CLASS_PRIVATE_FIELDS, new IntMetricVals());
		this.intMetrics.put(Metric.SUBROUTINE_LOC, new IntMetricVals());
		this.intMetrics.put(Metric.SUBROUTINE_PARAMS, new IntMetricVals());
		this.intMetrics.put(Metric.SUBROUTINE_AID, new IntMetricVals());
	}

	public void register(ContentContainer contentContainer) {
		if (this.finishedCollecting) {
			throw new IllegalStateException();
		}
		this.collector.collect(contentContainer);
	}

	public void terminateCollecting(Map<Metric, Set<Integer>> requiredMetricPercentages) throws IOException {
		this.finishedCollecting = true;
		for (Metric metric : this.intMetrics.keySet()) {
			IntMetricVals counter = this.intMetrics.get(metric);
			counter.sortAndCalculateStats(requiredMetricPercentages.get(metric));
		}
	}

	public boolean isExtremeOutlier(Metric metric, Integer value) {
		return this.getCounter(metric).isExtremeOutlier(value);
	}

	public boolean isMildOutlier(Metric metric, Integer value) {
		return this.getCounter(metric).isMildOutlier(value);
	}

	public boolean isInTop(Metric metric, Integer percentage, Integer value) {
		IntMetricVals counter = this.getCounter(metric);
		return counter.isInTop(percentage, value);
	}
	public boolean isInBottom(Metric metric, Integer percentage, Integer value) {
		IntMetricVals counter = this.getCounter(metric);
		return counter.isInBottom(percentage, value);
	}

	private IntMetricVals getCounter(Metric metric) {
		return this.intMetrics.get(metric);
	}

	private class Collector implements ContentContainerVisitor<Void> {

		public void collect(ContentContainer contentContainer) {
			contentContainer.accept(this);
		}

		@Override
		public Void visit(Module m) {
			return null;
		}

		@Override
		public Void visit(model.Class m) {
			getCounter(Metric.CLASS_LOC).add(m.getLoc());
			getCounter(Metric.CLASS_SUPERCLASSES).add(m.superclassCount());
			getCounter(Metric.CLASS_METHODS).add(m.getDefinedSubroutinesSet().size());
			getCounter(Metric.CLASS_ACCESSORS).add(m.accessorCount());
			getCounter(Metric.CLASS_LCOM).add(m.getLcom());
			getCounter(Metric.CLASS_METHODS_NO_PARAMS).add(m.subroutinesWithNoParamsCount());
			Long publicFields = m.getDefinedVarsInclParentsVars().getAsSet().stream().filter(Variable::isPublic).count();
			getCounter(Metric.CLASS_PUBLIC_FIELDS).add(publicFields.intValue());
			Long privateFields = m.getDefinedVarsInclParentsVars().getAsSet().stream().filter(Variable::isPrivate).count();
			getCounter(Metric.CLASS_PRIVATE_FIELDS).add(privateFields.intValue());
			return null;
		}

		@Override
		public Void visit(Subroutine m) {
			getCounter(Metric.SUBROUTINE_LOC).add(m.getLoc());
			getCounter(Metric.SUBROUTINE_PARAMS).add(m.paramCount());
			getCounter(Metric.SUBROUTINE_AID).add(m.getAccessOfImportData());
			return null;
		}
	}
}
