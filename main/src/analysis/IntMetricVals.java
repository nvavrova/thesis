package analysis;

import java.util.*;

/**
 * Created by Nik on 09-11-2015
 */
public class IntMetricVals {
	private Integer sum;
	private final List<Integer> values;
	private boolean sorted;

	private Double average;
	private Double median;
	private Double standardDeviation;
	private Double q1;
	private Double q3;

	public IntMetricVals() {
		this.values = new ArrayList<>();
		this.sorted = false;
		this.sum = 0;
	}

	public void add(Integer item) {
		this.values.add(item);
		this.sum += item;
	}

	public boolean isInTop(Integer percentage, Double value) {
		return value >= this.valueAtXPercent(percentage);
	}

	public boolean isInBottom(Integer percentage, Double value) {
		return value <= this.valueAtXPercent(percentage);
	}

	public boolean isMildOutlier(Integer val) {
		return this.isOutlier(val, 1.5);
	}

	public boolean isExtremeOutlier(Integer val) {
		return this.isOutlier(val, 3.0);
	}

	private boolean isOutlier(Integer val, Double iqrMultiplicand) {
		this.sortAndCalculateStats();
		Double iqr = this.q3 - this.q1;
		return val < this.q1 - iqrMultiplicand * iqr || val > this.q3 + iqrMultiplicand * iqr;
	}

	public boolean atLeast2StdDevFromAvg(Integer val) {
		this.sortAndCalculateStats();
		return this.nrOfStandardDeviationsFromAvg(val) >= 2;
	}

	private Double median(List<Integer> sortedVals) {
		int valSize = sortedVals.size();
		if (valSize % 2 == 0) {
			int i = valSize / 2;
			i = this.applyBoundaries(i, valSize - 2);
			return (sortedVals.get(i).doubleValue() + sortedVals.get(i + 1)) / 2.0;
		}
		int i = (valSize / 2) + 1;
		i = this.applyBoundaries(i, valSize - 1);
		return sortedVals.get(i).doubleValue();
	}

	private Integer valueAtXPercent(Integer percentage) {
		this.sortAndCalculateStats();
		Double d = this.values.size() / 100.0 * percentage;
		Long l = Math.round(d);
		return this.values.get(l.intValue());
	}

	private Double nrOfStandardDeviationsFromAvg(Integer val) {
		return Math.abs((this.average() - val) / this.standardDeviation);
	}

	private Double average() {
		return this.sum.doubleValue() / this.values.size();
	}

	private Double standardDeviation() {
		Double sumOfIndividualDevSquares = 0.0;
		for (Integer value : this.values) {
			sumOfIndividualDevSquares += Math.pow(value - this.average, 2);
		}
		return Math.sqrt(sumOfIndividualDevSquares / this.values.size());
	}

	private void sortAndCalculateStats() {
		if (!this.sorted) {
			Collections.sort(this.values);
		}

		this.average = this.average();
		this.median = this.median(this.values);
		this.standardDeviation = this.standardDeviation();

		int valSize = this.values.size();

		if (valSize == 1) {
			this.q1 = this.values.get(0).doubleValue();
			this.q3 = this.values.get(0).doubleValue();
		}
		else if (valSize == 2) {
			this.q1 = this.values.get(0).doubleValue();
			this.q3 = this.values.get(1).doubleValue();
		}
		else {
			boolean even = valSize % 2 == 0;
			Integer q1Index = even ? (valSize - 2) / 2 : valSize / 2;
			Integer q3Index = even ? q1Index + 2 : q1Index + 1;
			q1Index = this.applyBoundaries(q1Index, valSize - 1);
			q3Index = this.applyBoundaries(q3Index, valSize - 1);

			this.q1 = this.median(this.values.subList(0, q1Index));
			this.q3 = this.median(this.values.subList(q3Index, valSize));
		}
	}

	private Integer applyBoundaries(Integer index, Integer maxSize) {
		index = Math.min(index, maxSize);
		index = Math.max(index, 0);
		return index;
	}
}
