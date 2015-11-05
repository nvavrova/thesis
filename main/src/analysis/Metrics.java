package analysis;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 04-11-2015
 */
public class Metrics {

	private final Map<Metric, List<Double>> values;
	private final Collector collector;
	private boolean finishedCollecting;

	public Metrics() {
		this.values = new HashMap<>();
		this.collector = new Collector();
		this.finishedCollecting = false;
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

	public boolean isInTop(Integer percentage, Metric metric, Integer value) {
		return this.isInTop(percentage, metric, value.doubleValue());
	}

	public boolean isInTop(Integer percentage, Metric metric, Double value) {
		if (!this.values.containsKey(metric)) {
			throw new IllegalArgumentException("This metric is not registered");
		}
		Integer borderIndex = this.values.size() - Math.round(this.values.size() / 100 * percentage);
		Double borderValue = this.values.get(metric).get(borderIndex);
		return value >= borderValue;
	}

	public boolean isInBottom(Integer percentage, Metric metric, Integer value) {
		return this.isInBottom(percentage, metric, value.doubleValue());
	}

	public boolean isInBottom(Integer percentage, Metric metric, Double value) {
		if (!this.values.containsKey(metric)) {
			throw new IllegalArgumentException("This metric is not registered");
		}
		Integer borderIndex = Math.round(this.values.size() / 100 * percentage);
		Double borderValue = this.values.get(metric).get(borderIndex);
		return value <= borderValue;
	}

	private class Collector implements ContentContainerVisitor<Void> {

		public void collect(ContentContainer contentContainer) {
			contentContainer.accept(this);
		}

		@Override
		public Void visit(Module m) {
			//TODO: collect metrics
			return null;
		}

		@Override
		public Void visit(model.Class m) {
			//TODO: collect metrics
			return null;
		}

		@Override
		public Void visit(Subroutine m) {
			//TODO: collect metrics
			return null;
		}
	}
}
