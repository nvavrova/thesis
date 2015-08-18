package util;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

	public static String getStackTraceString(Exception ex) {
		String stackTrace = "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		stackTrace = sw.toString();
		try {
			sw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
		return stackTrace;
	}
}
