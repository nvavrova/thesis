package ast.node;

/**
 * Created by Nik on 20-05-2015
 */
public class LocInfo {
	private final Integer startLine;
	private final Integer endLine;

	public LocInfo(Integer startLine, Integer endLine) {
		assert (startLine <= endLine);
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public Integer getStartLine() {
		return this.startLine;
	}

	public Integer getEndLine() {
		return this.endLine;
	}

	public Integer getLocSpan() {
		return this.endLine - this.startLine;
	}
}
