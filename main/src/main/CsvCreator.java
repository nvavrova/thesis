package main;

import util.FileHelper;
import util.StringHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Nik on 11-11-2015
 */
public class CsvCreator {

	protected static final String CSV_DELIMITER = ";";
	private final Map<String, PrintStream> streams;

	public CsvCreator() {
		this.streams = new HashMap<>();
	}

	protected void createStream(String streamId, String... headers) throws FileNotFoundException {
		PrintStream stream = new PrintStream(new FileOutputStream(FileHelper.getLogName(streamId + "_stats", "csv")));
		List<String> headerParts = new ArrayList<>();
		for (String header : headers) {
			headerParts.add(header);
		}
		this.streams.put(streamId, stream);
		this.addLine(streamId, headerParts);
	}

	public void addLine(String streamId, String... parts) {
		this.addLine(streamId, Arrays.asList(parts));
	}

	public void addLine(String streamId, List<String> parts) {
		this.streams.get(streamId).println(this.createLine(parts));
	}

	protected String createLine(List<String> parts) {
		return StringHelper.implode(parts, CSV_DELIMITER);
	}

	public void close() {
		streams.values().forEach(v -> v.close());
	}
}
