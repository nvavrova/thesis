package analysis;

import model.ContentContainer;
import model.ContentContainerVisitor;
import model.Module;
import model.Subroutine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 04-11-2015
 */
public class Metrics {

	private final Collector collector;
	private boolean finishedCollecting;

	private final IntCounter classLoc;
	private final IntCounter classLcom;
	private final IntCounter classSuperclasses;
	private final IntCounter classMethods;

	private final IntCounter subroutineLoc;
	private final IntCounter subroutineParams;

	public Metrics() {
		this.collector = new Collector();
		this.finishedCollecting = false;

		this.classLoc = new IntCounter();
		this.classLcom = new IntCounter();
		this.classSuperclasses = new IntCounter();
		this.classMethods = new IntCounter();

		this.subroutineLoc = new IntCounter();
		this.subroutineParams = new IntCounter();
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
		IntCounter counter = this.getCounter(metric);
		return counter.isInTop(percentage, value);
	}

	public boolean isInBottom(Metric metric, Integer percentage, Integer value) {
		return this.isInBottom(metric, percentage, value.doubleValue());
	}

	public boolean isInBottom(Metric metric, Integer percentage, Double value) {
		IntCounter counter = this.getCounter(metric);
		return counter.isInBottom(percentage, value);
	}

	private IntCounter getCounter(Metric metric) {
		if (metric == Metric.CLASS_LOC) {
			return this.classLoc;
		}
		if (metric == Metric.CLASS_SUPERCLASSES) {
			return this.classSuperclasses;
		}
		if (metric == Metric.CLASS_METHODS) {
			return this.classMethods;
		}
		if (metric == Metric.CLASS_LCOM) {
			return this.classLcom;
		}
		if (metric == Metric.SUBROUTINE_LOC) {
			return this.subroutineLoc;
		}
		if (metric == Metric.SUBROUTINE_PARAMS) {
			return this.subroutineParams;
		}
		throw new IllegalArgumentException();
	}

	private class IntCounter {
		private Integer amountOfRegisteredValues;
		private final Map<Integer, Integer> values;

		public IntCounter() {
			this.values = new HashMap<>();
		}

		public void add(Integer item) {
			if (!this.values.containsKey(item)) {
				this.values.put(item, 0);
			}
			Integer value = this.values.get(item) + 1;
			this.values.put(item, value);

			this.amountOfRegisteredValues++;
		}

		public boolean isInTop(Integer percentage, Double value) {
			return value >= this.valueAtXPercent(percentage);
		}

		public boolean isInBottom(Integer percentage, Double value) {
			return value <= this.valueAtXPercent(percentage);
		}

		private Integer valueAtXPercent(Integer percentage) {
			Integer counted = 0;
			List<Integer> orderedKeys = this.values.keySet().stream().collect(Collectors.toList());
			Collections.sort(orderedKeys);
			for (Integer key : orderedKeys) {
				counted += this.values.get(key);
				if (this.amountOfRegisteredValues.doubleValue() / 100 * percentage <= counted) {
					return key;
				}
			}
			throw new IllegalArgumentException();
		}
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
