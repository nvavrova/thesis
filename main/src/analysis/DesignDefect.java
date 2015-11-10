package analysis;

/**
 * Created by Nik on 05-11-2015
 */
public class DesignDefect {

	private final String fullPath;
	private final String defect;

	public DesignDefect(String fullPath, String defect) {
		this.fullPath = fullPath;
		this.defect = defect;
	}

	public String getFullPath() {
		return this.fullPath;
	}

	public String getDefect() {
		return this.defect;
	}
}
