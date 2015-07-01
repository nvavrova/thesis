package thesis;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nik on 01-07-2015
 */
public class Helper {
	private final static String[] CONTROLLER_NAMES = {"Manage", "Process", "Control"};

	public static List<String> getControllerNames() {
		return Arrays.asList(CONTROLLER_NAMES);
	}
}
