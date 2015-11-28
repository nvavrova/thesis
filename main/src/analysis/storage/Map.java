package analysis.storage;

import util.FileHelper;

import java.io.*;
import java.util.Set;

/**
 * Created by Nik on 26-11-2015
 */
public abstract class Map<T> {

	protected static final String DELIMITER = ",";

	protected java.util.Map<String, T> map;

	private final String fileName;
	private PrintStream stream;

	public Map(String type) {
		this.fileName = FileHelper.getLogName(type, "data", "test-data\\collected_data");
	}

	public void createDataStore() throws FileNotFoundException {
		this.stream = new PrintStream(new FileOutputStream(this.fileName));
	}

	public void add(String key, Integer element) {
		this.add(key, element.toString());
	}

	public void add(String key, String element) {
		if (this.stream == null) {
			throw new IllegalStateException("The stream has not been initialized");
		}
		String line = key + DELIMITER + element;
		this.stream.println(line);
	}

	public Set<String> keySet() {
		return this.map.keySet();
	}

	public T get(String key) {
		return this.map.get(key);
	}

	public void deserialize() throws IOException {
		this.stream.close();

		FileInputStream fis = new FileInputStream(this.fileName);
		InputStreamReader inStrReader = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(inStrReader);

		this.deserialize(br);

		br.close();
		inStrReader.close();
		fis.close();
	}

	public void clean() {
		this.map = null;
	}

	protected abstract void deserialize(BufferedReader dataReader) throws IOException;
}
