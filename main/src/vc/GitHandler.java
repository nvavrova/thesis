package vc;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nik on 03-08-2015
 */
public class GitHandler {

	private final File baseFolder;
	private final String latestCommit;
	private final List<String> commits;
	private Integer currentCommitIndex;

	public GitHandler(File baseFolder) throws IOException, InterruptedException {
		this.baseFolder = baseFolder;
		if (!this.baseFolder.exists()) {
			throw new IllegalArgumentException("The folder does not exist.");
		}
		if (!this.isGitRepoFolder()) {
			for (File f : this.baseFolder.listFiles()) {
				System.out.println(f.getAbsoluteFile());
			}
			throw new IllegalArgumentException("No git repository found.");
		}
		this.latestCommit = this.loadLatestCommit();
		this.commits = this.loadCommits();
		Runtime.getRuntime().exec("chmod 777 " + baseFolder.getAbsolutePath());
	}

	public String getCurrentCommitSha() {
		return this.commits.get(this.currentCommitIndex);
	}

	public boolean isAtFirstCommit() {
		return this.currentCommitIndex == 0;
	}

	public boolean isAtLastCommit() {
		return this.currentCommitIndex == this.commits.size() - 1;
	}

	public void goToFirstCommit() throws IOException, InterruptedException {
		this.goToCommit(0);
	}

	public void goToLastCommit() throws IOException, InterruptedException {
		this.goToCommit(this.latestCommit);
	}

	public void goBack() throws IOException, InterruptedException {
		if (!this.isAtFirstCommit()) {
			this.goToCommit(--this.currentCommitIndex);
		}
	}

	public void goForth() throws IOException, InterruptedException {
		if (!this.isAtLastCommit()) {
			this.goToCommit(++this.currentCommitIndex);
		}
	}

	private void goToCommit(int commitIndex) throws IOException, InterruptedException {
		this.goToCommit(this.commits.get(commitIndex));
	}

	private void goToCommit(String commitSha) throws IOException, InterruptedException {
//		this.exec("stash");
		this.exec("reset --hard " + commitSha);
	}

	private String loadHead() throws IOException, InterruptedException {
		List<String> s = this.exec("rev-parse HEAD");
		return s.get(0);
	}

	private String loadLatestCommit() throws IOException, InterruptedException {
		List<String> s = this.exec("rev-parse origin");
		return s.get(0);
	}

	private List<String> loadCommits() throws IOException, InterruptedException {
		List<String> commitShas = this.exec("rev-list --full-history " + this.latestCommit);
		Collections.reverse(commitShas);

		String head = this.loadHead();
		for (int i = 0; i < commitShas.size() ; i++) {
			if (commitShas.get(i).equals(head)) {
				this.currentCommitIndex = i;
				break;
			}
		}

		return commitShas;
	}

	private List<String> exec(String cmd) throws IOException, InterruptedException {
		String fullCmd = "git -C " + this.baseFolder.getAbsolutePath() + " " + cmd;

		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(fullCmd);

		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());

		errorGobbler.start();
		outputGobbler.start();

		int exitVal = proc.waitFor();
		errorGobbler.join();
		outputGobbler.join();

		//TODO: handle errors & exit val
		errorGobbler.getStreamValues()
				.forEach(e ->
						System.err.println(e));

		return outputGobbler.getStreamValues();
	}

	private boolean isGitRepoFolder() {
		String gfName = this.baseFolder.getAbsolutePath() + "\\.git";
		File gitFile = new File(gfName);
		return gitFile.exists();
	}
}
