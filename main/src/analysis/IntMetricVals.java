package analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 09-11-2015
 */
public class IntMetricVals {
	private Integer amountOfRegisteredValues;
	private Integer sum;
	private final Map<Integer, Integer> values;

	public IntMetricVals() {
		this.values = new HashMap<>();
		this.amountOfRegisteredValues = 0;
		this.sum = 0;
	}

	public void add(Integer item) {
		if (!this.values.containsKey(item)) {
			this.values.put(item, 0);
		}
		Integer value = this.values.get(item) + 1;
		this.values.put(item, value);

		this.amountOfRegisteredValues++;
		this.sum += item;
	}

	public boolean isInTop(Integer percentage, Double value) {
		return value >= this.valueAtXPercent(percentage);
	}

	public boolean isInBottom(Integer percentage, Double value) {
		return value <= this.valueAtXPercent(percentage);
	}

	public boolean atLeast2StdDevFromAvg(Integer val) {
		return this.nrOfStandardDeviationsFromAvg(val) >= 2;
	}

	private Integer valueAtXPercent(Integer percentage) {
		Integer counted = 0;
		List<Integer> orderedKeys = this.values.keySet().stream().collect(Collectors.toList());
		Collections.sort(orderedKeys);
		for (Integer value : orderedKeys) {
			counted += this.values.get(value);
			if (this.amountOfRegisteredValues.doubleValue() / 100 * percentage <= counted) {
				return value;
			}
		}
		throw new IllegalArgumentException();
	}

	private Double nrOfStandardDeviationsFromAvg(Integer val) {
		return Math.abs((this.average() - val) / this.standardDeviation());
	}

	private Double average() {
		return this.sum.doubleValue() / this.amountOfRegisteredValues;
	}

	private Double standardDeviation() {
		Double avg = this.average();
		Double sumOfIndividualDevSquares = 0.0;
		for (Integer value : this.values.keySet()) {
			sumOfIndividualDevSquares += this.values.get(value) * Math.pow(value - avg, 2);
		}
		return Math.sqrt(sumOfIndividualDevSquares / this.amountOfRegisteredValues);
	}
}
