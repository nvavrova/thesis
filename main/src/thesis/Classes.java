package thesis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nik on 17-05-2015
 */
public class Classes implements Iterable<String> {

	//TODO: fix for classes with same name and different module
	private final Map<String, Integer> classes;

	public Classes() {
		this.classes = new HashMap<>();
	}

	public void add(String className, Integer loc) {
		this.classes.put(className, loc);
	}

	public boolean classExists(String className) {
		return this.classes.containsKey(className);
	}

	public Integer getSize(String className) {
		assert (classes.containsKey(className));
		return classes.get(className);
	}

	@Override
	public Iterator iterator() {
		return this.classes.keySet().iterator();
	}
}
