package thesis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Nik on 01-07-2015
 */
public class Helper {
	private final static String[] CONTROLLER_NAMES = {"Manage", "Process", "Control", "Handle"}; //TODO

	public static List<String> getControllerNames() {
		return Arrays.asList(CONTROLLER_NAMES);
	}

	public static <T> Boolean areSetsDisjoint(Set<T> s1, Set<T> s2) {
		for (T item : s1) {
			if (s2.contains(item)) {
				return false;
			}
		}
		return true;
	}
}
