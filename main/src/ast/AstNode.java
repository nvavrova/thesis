package ast;

import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by Nik on 19-05-2015
 */
public abstract class AstNode {

    protected final LocInfo locInfo;

    public AstNode(@NotNull LocInfo locInfo) {
        this.locInfo = locInfo;
    }

	public LocInfo getLocInfo() {
		return this.locInfo;
	}

	public abstract <T> T accept(Visitor<T> visitor);

    public Integer getLocSpan() {
        return this.locInfo.getLocSpan();
    }
}
