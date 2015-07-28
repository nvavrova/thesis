package helpers;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nik on 14-07-2015
 */
public class StringHelper {
	public static List<String> explode(String str, String delimiter) {
		String escapedDelimiter = delimiter.replaceAll("([\\\\\\.\\[\\{\\(\\*\\+\\?\\^\\$\\|])", "\\\\$1");
		return Arrays.asList(str.split(escapedDelimiter));
	}

	public static String implode(List<String> strings, String delimiter) {
		return StringUtils.join(strings, delimiter);
	}

	public static String swapDelimiter(String str, String oldDelim, String newDelim) {
		return implode(explode(str, oldDelim), newDelim);
	}

	public static List<String> replicate(String s, Integer count) {
		List<String> ss = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			ss.add(s);
		}
		return ss;
	}
}
