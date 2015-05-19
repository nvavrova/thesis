package ast;

/**
 * Created by Nik on 20-05-2015
 */
public class LocInfo {
	private final Integer startLine;
	private final Integer endLine;
	private final Integer locSpan;

	public LocInfo(Integer startLine, Integer endLine) {
		assert (startLine <= endLine);
		this.startLine = startLine;
		this.endLine = endLine;
		this.locSpan = endLine - startLine;
	}

	public LocInfo(Integer startLine, Integer endLine, Integer locSpan) {
		assert (startLine <= endLine);
		this.startLine = startLine;
		this.endLine = endLine;
		this.locSpan = locSpan;
	}

	public Integer getStartLine() {
		return startLine;
	}

	public Integer getEndLine() {
		return endLine;
	}

	public Integer getLocSpan() {
		return locSpan;
	}
}
