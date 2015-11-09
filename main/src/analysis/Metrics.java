package analysis;

import model.ContentContainer;
import model.ContentContainerVisitor;
import model.Module;
import model.Subroutine;

/**
 * Created by Nik on 04-11-2015
 */
public class Metrics {

	private final Collector collector;
	private boolean finishedCollecting;

	private final IntMetricVals classLoc;
	private final IntMetricVals classLcom;
	private final IntMetricVals classSuperclasses;
	private final IntMetricVals classMethods;
	private final IntMetricVals classAccessors;
	private final IntMetricVals classMethodsNoParams;

	private final IntMetricVals subroutineLoc;
	private final IntMetricVals subroutineParams;

	public Metrics() {
		this.collector = new Collector();
		this.finishedCollecting = false;

		this.classLoc = new IntMetricVals();
		this.classLcom = new IntMetricVals();
		this.classSuperclasses = new IntMetricVals();
		this.classMethods = new IntMetricVals();
		this.classAccessors = new IntMetricVals();
		this.classMethodsNoParams = new IntMetricVals();

		this.subroutineLoc = new IntMetricVals();
		this.subroutineParams = new IntMetricVals();
	}

	public void register(ContentContainer contentContainer) {
		if (this.finishedCollecting) {
			throw new IllegalStateException();
		}
		this.collector.collect(contentContainer);
	}

	public void terminateCollecting() {
		this.finishedCollecting = true;
	}

	public boolean isInTop(Metric metric, Integer percentage, Integer value) {
		return this.isInTop(metric, percentage, value.doubleValue());
	}

	public boolean isInTop(Metric metric, Integer percentage, Double value) {
		IntMetricVals counter = this.getCounter(metric);
		return counter.isInTop(percentage, value);
	}

	public boolean isInBottom(Metric metric, Integer percentage, Integer value) {
		return this.isInBottom(metric, percentage, value.doubleValue());
	}

	public boolean isInBottom(Metric metric, Integer percentage, Double value) {
		IntMetricVals counter = this.getCounter(metric);
		return counter.isInBottom(percentage, value);
	}

	private IntMetricVals getCounter(Metric metric) {
		if (metric == Metric.CLASS_LOC) {
			return this.classLoc;
		}
		if (metric == Metric.CLASS_SUPERCLASSES) {
			return this.classSuperclasses;
		}
		if (metric == Metric.CLASS_METHODS) {
			return this.classMethods;
		}
		if (metric == Metric.CLASS_ACCESSORS) {
			return this.classAccessors;
		}
		if (metric == Metric.CLASS_LCOM) {
			return this.classLcom;
		}
		if (metric == Metric.CLASS_METHODS_NO_PARAMS) {
			return this.classMethodsNoParams;
		}
		if (metric == Metric.SUBROUTINE_LOC) {
			return this.subroutineLoc;
		}
		if (metric == Metric.SUBROUTINE_PARAMS) {
			return this.subroutineParams;
		}
		throw new IllegalArgumentException();
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
			classLoc.add(m.getLoc());
			classLcom.add(m.getLcom());
			classSuperclasses.add(m.superclassCount());
			classMethods.add(m.getDefinedSubroutinesSet().size());
			classAccessors.add(m.accessorCount());
			classMethodsNoParams.add(m.subroutinesWithNoParamsCount());
			return null;
		}

		@Override
		public Void visit(Subroutine m) {
			subroutineLoc.add(m.getLoc());
			subroutineParams.add(m.paramCount());
			return null;
		}
	}
}
