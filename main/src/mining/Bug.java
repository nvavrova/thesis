package mining;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 12-08-2015
 */
public class Bug {

	private final Long id;
	private final List<String> fixCommits;

	public Bug(Long id) {
		this.id = id;
		this.fixCommits = new ArrayList<>();
	}

	public void addFixCommit(String commitSha) {
		this.fixCommits.add(commitSha);
	}

	public Long getId() {
		return this.id;
	}

	public List<String> getFixCommits() {
		return this.fixCommits;
	}
}
