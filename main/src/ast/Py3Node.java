package ast;

import thesis.Py3TreeVisitor;

/**
 * Created by Nik on 19-05-2015
 */
public abstract class Py3Node {

	private final LocInfo locInfo;

	public Py3Node(LocInfo locInfo) {
		this.locInfo = locInfo;
	}

    public Integer getLocSpan() {
        return this.locInfo.getLocSpan();
    }

    public Boolean countsTowardsLoc() {
        return true;
    }

	public abstract <T> T accept(Py3TreeVisitor<T> visitor);
}
