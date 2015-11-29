package analysis.storage;

import util.StringHelper;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Nik on 26-11-2015
 */
public abstract class Map<T> {

	protected static final String DELIMITER = ",";

	protected java.util.Map<String, T> map;

	private final Boolean existing;

	private final String filePath;
	private PrintStream stream;
	private BufferedReader reader;

	protected String currentKey;
	protected String currentVal;

	public Map(String filePath) throws IOException {
		this.filePath = filePath;
		File file = new File(this.filePath);
		this.existing = file.exists();
		if (!this.existing) {
			this.stream = new PrintStream(new FileOutputStream(this.filePath));
		}
	}

	public void add(String key, Integer element) {
		this.add(key, element.toString());
	}

	public void add(String key, String element) {
		if (!this.existing) {
			if (this.stream == null) {
				throw new IllegalStateException("The stream has not been initialized");
			}
			String line = key + DELIMITER + element;
			this.stream.println(line);
		}
	}

	public Set<String> keySet() {
		return this.map.keySet();
	}

	public T get(String key) {
		return this.map.get(key);
	}

	public void deserialize() throws IOException {
	this.deserialize(true);
	}

	public void deserialize(Boolean all) throws IOException {
		if (!this.existing) {
			this.stream.close();
		}

		this.reader = new BufferedReader(new FileReader(this.filePath));
		if (all) {
			this.deserialize(reader);
			reader.close();
		}
	}

	public String getNextKey() throws IOException {
		String line = this.reader.readLine();
		if (line == null) {
			this.currentKey = null;
			this.currentVal = null;
		}
		else {
			List<String> parts = StringHelper.explode(line, DELIMITER);
			this.currentKey = parts.get(0);
			this.currentVal = parts.get(1);
		}
		return this.currentKey;
	}

	public String getNextVal() {
		return this.currentVal;
	}

	public void clean() {
		this.map = null;
	}

	protected abstract void deserialize(BufferedReader dataReader) throws IOException;
}
