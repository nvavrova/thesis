package thesis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Nik on 17-05-2015
 */
public class Classes implements Iterable<String> {

	private final Set<String> classes;

	public Classes() {
		this.classes = new HashSet<>();
	}

	public void add(String className) {
		this.classes.add(className);
	}

	public boolean classExists(String className) {
		return this.classes.contains(className);
	}

	@Override
	public Iterator iterator() {
		return this.classes.iterator();
	}
}
