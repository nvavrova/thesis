package main;

import util.FileHelper;
import util.Settings;
import util.StringHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Nik on 11-11-2015
 */
public class CsvCreator {

	protected static final String CSV_DELIMITER = ";";
	private final Map<String, PrintStream> streams;
	private final String folder;

	public CsvCreator(String folder) {
		this.streams = new HashMap<>();
		this.folder = folder;
	}

	public void createStream(String streamId, String... headers) throws IOException {
		Properties config = Settings.getConfig();

		PrintStream stream = new PrintStream(new FileOutputStream(FileHelper.stampedFileName(this.folder, streamId + "_stats", "csv")));
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
