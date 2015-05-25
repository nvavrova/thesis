package ast;

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
}
