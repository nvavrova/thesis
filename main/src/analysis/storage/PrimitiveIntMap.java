package analysis.storage;

import util.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nik on 26-11-2015
 */
public class PrimitiveIntMap extends Map<Integer> {

	public PrimitiveIntMap(String type) {
		super(type);
	}

	@Override
	public void deserialize(BufferedReader dataReader) throws IOException {
		this.map = new HashMap<>();

		String line = dataReader.readLine();
		while (line != null) {
			List<String> parts = StringHelper.explode(line, DELIMITER);
			this.map.put(parts.get(0), Integer.valueOf(parts.get(1)));
			line = dataReader.readLine();
		}
	}
}
