package mining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nik on 12-08-2015
 */
public class Bugs implements Iterable<Bug> {

	private final Map<Long, Bug> bugs;

	public Bugs() {
		this.bugs = new HashMap<>();
	}

	public void addBug(Long id) {
		if (!this.bugs.containsKey(id)) {
			this.bugs.put(id, new Bug(id));
		}
	}

	public void addBugFixCommit(Long bugId, String commitSha) {
		assert (this.bugs.containsKey(bugId));

		this.bugs.get(bugId).addFixCommit(commitSha);
	}

	@Override
	public Iterator<Bug> iterator() {
		return this.bugs.values().iterator();
	}
}
