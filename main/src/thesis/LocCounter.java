package thesis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nik on 23-07-2015
 */
public class LocCounter {

	private static final Pattern emptyPattern = Pattern.compile("[ \t]*");
	private static final Pattern commentPattern = Pattern.compile("[ \t]*#.*");

	private final FileHelper fileHelper;

	public LocCounter(String module) {
		this.fileHelper = new FileHelper(module);
	}

	public Integer count() {
		List<String> lines = this.fileHelper.getFileContents();
		return this.count(lines);
	}

	public Integer count(Integer startLine, Integer endLine) {
		List<String> lines = this.fileHelper.getFileContents(startLine - 1, endLine);
		return this.count(lines);
	}

	private Integer count(List<String> lines) {
		Long nr = lines.stream().filter(this::countsTowardsLoc).count();
		return nr.intValue();
	}

	public boolean countsTowardsLoc(String line) {
		boolean commentLine = this.isCommentLine(line);
		boolean emptyLine = this.isEmptyLine(line);
		return !(commentLine || emptyLine);
	}

	public boolean isEmptyLine(String line) {
		Matcher matcher = emptyPattern.matcher(line);
		return matcher.matches();
	}

	public boolean isCommentLine(String line) {
		Matcher matcher = commentPattern.matcher(line);
		return matcher.matches();
	}
}
