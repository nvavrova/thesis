package analysis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 09-11-2015
 */
public class IntMetricVals {
	private boolean sorted;

	private final String valuesFileName;

	private PrintStream valueStream;
	private List<Integer> values;
	private final Map<Integer, Integer> percentageLimits;

	private Integer sum;
	private Integer amountOfValues;
	private Double average;
	private Double median;
	private Double standardDeviation;
	private Double q1;
	private Double q3;

	public IntMetricVals() {
		this.sorted = false;
		this.values = new ArrayList<>();

		this.valuesFileName = UUID.randomUUID().toString();
		try {
			this.valueStream = new PrintStream(new FileOutputStream(this.valuesFileName));
		}
		catch (FileNotFoundException e) {
			System.err.println("Cannot create metrics file. Switched to using a list for storage.");
			e.printStackTrace();
		}

		this.percentageLimits = new HashMap<>();
	}

	public void add(Integer item) {
		if (this.sorted) {
			throw new IllegalStateException();
		}
		if (this.valueStream != null) {
			this.valueStream.println(item);
		}
		else {
			this.values.add(item);
		}
	}

	public boolean isInTop(Integer percentage, Integer value) {
		if (!this.sorted) {
			throw new IllegalStateException();
		}
		return value >= this.percentageLimits.get(100 - percentage);
	}

	public boolean isInBottom(Integer percentage, Integer value) {
		if (!this.sorted) {
			throw new IllegalStateException();
		}
		return value <= this.percentageLimits.get(percentage);
	}

	public boolean isMildOutlier(Integer val) {
		return this.isOutlier(val, 1.5);
	}

	public boolean isExtremeOutlier(Integer val) {
		return this.isOutlier(val, 3.0);
	}

	private boolean isOutlier(Integer val, Double iqrMultiplicand) {
		if (!this.sorted) {
			throw new IllegalStateException();
		}
		Double iqr = this.q3 - this.q1;
		return val < this.q1 - iqrMultiplicand * iqr || val > this.q3 + iqrMultiplicand * iqr;
	}

	public boolean atLeast2StdDevFromAvg(Integer val) {
		if (!this.sorted) {
			throw new IllegalStateException();
		}
		return this.nrOfStandardDeviationsFromAvg(val) >= 2;
	}

	private Double nrOfStandardDeviationsFromAvg(Integer val) {
		return Math.abs((this.average - val) / this.standardDeviation);
	}

	public void sortAndCalculateStats(Set<Integer> requiredPercentages) throws IOException {
		if (this.sorted) {
			throw new IllegalStateException();
		}
		this.sorted = true;

		if (this.valueStream != null) {
			this.loadValuesAndHandleFile();
		}
		Collections.sort(this.values);

		this.amountOfValues = this.values.size();
		this.sum = 0;
		this.values.forEach(v -> this.sum += v);

		this.average = this.sum.doubleValue() / this.amountOfValues;
		this.median = this.median(this.values);
		this.standardDeviation = this.standardDeviation();

		if (this.amountOfValues == 1) {
			this.q1 = this.values.get(0).doubleValue();
			this.q3 = this.values.get(0).doubleValue();
		}
		else if (this.amountOfValues == 2) {
			this.q1 = this.values.get(0).doubleValue();
			this.q3 = this.values.get(1).doubleValue();
		}
		else {
			boolean even = this.amountOfValues % 2 == 0;
			Integer q1Index = even ? (this.amountOfValues - 2) / 2 : this.amountOfValues / 2;
			Integer q3Index = even ? q1Index + 2 : q1Index + 1;
			q1Index = this.applyBoundaries(q1Index, this.amountOfValues - 1);
			q3Index = this.applyBoundaries(q3Index, this.amountOfValues - 1);

			this.q1 = this.median(this.values.subList(0, q1Index));
			this.q3 = this.median(this.values.subList(q3Index, this.amountOfValues));
		}

		for (Integer percentage : requiredPercentages) {
			this.percentageLimits.put(percentage, this.valueAtXPercent(percentage));
		}
		this.values = Collections.emptyList();
	}

	private void loadValuesAndHandleFile() throws IOException {
		this.valueStream.close();

		FileInputStream fis = new FileInputStream(this.valuesFileName);
		InputStreamReader inStrReader = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(inStrReader);
		this.values = br.lines().map(Integer::parseInt).collect(Collectors.toList());
		br.close();
		inStrReader.close();
		fis.close();

		try {
			Files.delete(Paths.get(this.valuesFileName));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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

	private Double standardDeviation() {
		Double sumOfIndividualDevSquares = 0.0;
		for (Integer value : this.values) {
			sumOfIndividualDevSquares += Math.pow(value - this.average, 2);
		}
		return Math.sqrt(sumOfIndividualDevSquares / this.amountOfValues);
	}

	private Integer valueAtXPercent(Integer percentage) {
		Double d = this.amountOfValues / 100.0 * percentage;
		Long l = Math.round(d);
		return this.values.get(l.intValue());
	}

	private Integer applyBoundaries(Integer index, Integer maxSize) {
		index = Math.min(index, maxSize);
		index = Math.max(index, 0);
		return index;
	}
}
