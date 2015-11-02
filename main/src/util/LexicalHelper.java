package util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nik on 01-07-2015
 */
public class LexicalHelper {
	public final static String SELF_KEYWORD = "self";

	private final static String[] CONTROLLER_NAMES = {"Manage", "Process", "Control", "Handle", "System"}; //TODO
	private final static String[] PROCEDURAL_NAMES = {"Make", "Create", "Exec", "Compute"}; //TODO

	public static List<String> getControllerNames() {
		return Arrays.asList(CONTROLLER_NAMES);
	}

	public static List<String> getProceduralNames() {
		return Arrays.asList(PROCEDURAL_NAMES);
	}

	public static Boolean isControllerName(String subjectName) {
		for (String name : getControllerNames()) {
			if (subjectName.contains(name)){
				return true;
			}
		}
		return false;
	}

	public static Boolean isProceduralName(String subjectName) {
		for (String name : getProceduralNames()) {
			if (subjectName.contains(name)){
				return true;
			}
		}
		return false;
	}
}