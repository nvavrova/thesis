package thesis;

import java.util.*;

/**
 * Created by Nik on 17-05-2015
 */
public class Classes implements Iterable<String> {

	private final Map<String, List<Class>> classes;

	public Classes() {
		this.classes = new HashMap<>();
	}

	public void add(String fileName, Class pyClass) {
		if (!this.classes.containsKey(fileName)) {
			this.classes.put(fileName, new ArrayList<>());
		}
		this.classes.get(fileName).add(pyClass);
	}

	public List<Class> getClasses(String fileName) {
		return this.classes.get(fileName);
	}

	@Override
	public Iterator<String> iterator() {
		return this.classes.keySet().iterator();
	}
}
