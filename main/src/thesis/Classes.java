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

	public void add(Class pyClass) {
		String filePath = pyClass.getFilePath();
		if (!this.classes.containsKey(filePath)) {
			this.classes.put(filePath, new ArrayList<>());
		}
		this.classes.get(filePath).add(pyClass);
	}

	public List<Class> getClasses(String fileName) {
		return this.classes.get(fileName);
	}

	@Override
	public Iterator<String> iterator() {
		return this.classes.keySet().iterator();
	}

}
