package analysis.storage;

import util.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nik on 26-11-2015
 */
public class SetIntMap extends Map<Set<Integer>> {

	public SetIntMap(String type) throws IOException {
		super(type);
	}

	@Override
	public void deserialize(BufferedReader dataReader) throws IOException {
		this.map = new HashMap<>();

		String line = dataReader.readLine();
		while (line != null) {
			List<String> parts = StringHelper.explode(line, DELIMITER);
			String key = parts.get(0);
			if (!this.map.containsKey(key)) {
				this.map.put(key, new HashSet<>());
			}
			this.map.get(key).add(Integer.valueOf(parts.get(1)));
			line = dataReader.readLine();
		}
	}
}
