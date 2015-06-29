package ast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nik on 20-05-2015
 */
public class LocInfo {
	private final Integer startLine;
	private final Integer endLine;
	private final Set<Integer> lines;

	public LocInfo(Integer startLine, Integer endLine) {
		assert (startLine <= endLine);

		this.startLine = startLine;
		this.endLine = endLine;
		this.lines = new HashSet<>();
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public Integer getLocSpan() {
		Integer count = 0;
		for (Integer i = this.startLine; i <= this.endLine; i++) {
			if (this.lines.contains(i)) {
				count++;
			}
		}
		return count;
	}

	public void addLine(Integer line) {
		this.lines.add(line);
	}

	public void addRange(Integer start, Integer end) {
		assert (start <= end);
		assert (start >= this.startLine);
		assert (end <= this.endLine);

		for (Integer i = start; i <= end; i++) {
			this.addLine(i);
		}
	}
}
