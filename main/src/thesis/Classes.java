package thesis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nik on 17-05-2015
 */
public class Classes implements Iterable<Class> {

	private final List<Class> classes;

	public Classes() {
		this.classes = new ArrayList<>();
	}

	public void add(Class pyClass) {
		this.classes.add(pyClass);
	}

	@Override
	public Iterator<Class> iterator() {
		return this.classes.iterator();
	}
}
