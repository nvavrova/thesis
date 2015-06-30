package ast;

import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 19-05-2015
 */
public abstract class Py3Node {

    protected final LocInfo locInfo;

    public Py3Node(@NotNull LocInfo locInfo) {
        this.locInfo = locInfo;
    }

	public LocInfo getLocInfo() {
		return this.locInfo;
	}

	public abstract <T> T accept(Py3TreeVisitor<T> visitor);

    public Integer getLocSpan() {
        return this.locInfo.getLocSpan();
    }
}
