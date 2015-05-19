package thesis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 17-05-2015
 */
public class PyNode {

	protected final String name;
	protected final String location;
	private final List<PyNode> calls;

	public PyNode(String name, String location) {
		this.name = name;
		this.location = location;
		this.calls = new ArrayList<>();
	}

	public String getName() {
		return this.name;
	}

	public String getLocation() {
		return this.location;
	}

	public void addCall(PyNode calledObject) {
		this.calls.add(calledObject);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() * this.location.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PyNode)) {
			return false;
		}
		PyNode f = (PyNode) obj;
		return this.name.equals(f.getName()) && this.location.equals(f.getLocation());
	}
}
