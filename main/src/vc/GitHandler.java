package vc;

import helpers.StringHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Nik on 03-08-2015
 */
public class GitHandler {

	private final File baseFolder;
	private final String remoteBranch;
	private final List<String> commits;
	private String startingCommit;
	private Integer currentCommitIndex;

	public GitHandler(File baseFolder) throws IOException, InterruptedException {
		this.baseFolder = baseFolder;
		this.validate();

		this.startingCommit = this.loadHead();
		this.remoteBranch = this.loadRemoteBranch();
		this.commits = this.loadCommits();
		this.currentCommitIndex = this.loadCurrentCommitIndex();
		
		this.exec("stash"); //just in case
	}

	public void finalize() throws IOException, InterruptedException {
		this.goToCommit(this.commits.get(this.currentCommitIndex), this.startingCommit);
		this.exec("stash apply");
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

	/**
	 * goes one revision backwards
	 * @return files that were added or changed in this revision
	 */
	public List<String> goBack() throws IOException, InterruptedException {
		if (!this.isAtFirstCommit()) {
			return this.goToCommit(this.currentCommitIndex - 1);
		}
		return Collections.emptyList();
	}

	/**
	 * goes one revision further
	 * @return files that were added or changed in this revision
	 */
	public List<String> goForth() throws IOException, InterruptedException {
		if (!this.isAtLastCommit()) {
			return this.goToCommit(this.currentCommitIndex + 1);
		}
		return Collections.emptyList();
	}


	/**
	 * goes to specific commit
	 * @return files that were added or changed between this commit and the specified commit
	 */
	private List<String> goToCommit(int commitIndex) throws IOException, InterruptedException {
		int temp = this.currentCommitIndex;
		this.currentCommitIndex = commitIndex;
		return this.goToCommit(this.commits.get(temp), this.commits.get(commitIndex));
	}

	private List<String> goToCommit(String currentSha, String commitSha) throws IOException, InterruptedException {
		this.exec("reset --hard " + commitSha);
		return this.filteredDiff(currentSha, commitSha, false, "D");
	}

	private String getCurrentBranch() throws IOException, InterruptedException {
		return this.execSingleResponse("rev-parse --abbrev-ref HEAD");
	}

	private Map<String, String> diff(String commitOne, String commitTwo) throws IOException, InterruptedException {
		Map<String, String> difRes = new HashMap<>();
		List<String> diff = this.exec("diff --name-status " + commitOne + " " + commitTwo);
		for (String diffLine : diff) {
			List<String> ss = StringHelper.explode(diffLine, "\t");
			String filePath = this.baseFolder.getAbsolutePath() + "\\" + ss.get(1).replace("/", "\\");
			String fileStatus = ss.get(0);
			difRes.put(filePath, fileStatus);
		}
		return difRes;
	}

	private String loadHead() throws IOException, InterruptedException {
		return this.execSingleResponse("rev-parse HEAD");
	}

	private List<String> filteredDiff(String commitOne, String commitTwo, Boolean contains, String filter) throws IOException, InterruptedException {
		List<String> filesChanged = new ArrayList<>();
		Map<String, String> diffRes = this.diff(commitOne, commitTwo);
		for (String filePath : diffRes.keySet()) {
			String fileStatus = diffRes.get(filePath);
			if (!(contains ^ fileStatus.contains(filter))) {
				filesChanged.add(filePath);
			}
		}
		return filesChanged;
	}

	private String loadRemoteBranch() throws IOException, InterruptedException {
		return this.execSingleResponse("rev-parse --abbrev-ref --symbolic-full-name @{u}");
	}

	private List<String> loadCommits() throws IOException, InterruptedException {
		List<String> commitShas = this.exec("log --pretty=format:\"%H\" --reverse " + this.remoteBranch);
		return commitShas;
	}

	private Integer loadCurrentCommitIndex() {
		for (int i = 0; i < this.commits.size(); i++) {
			if (this.commits.get(i).equals(this.startingCommit)) {
				return i;
			}
		}
		return null;
	}

	private String execSingleResponse(String cmd) throws IOException, InterruptedException {
		List<String> s = this.exec(cmd);
		return s.get(0);
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

	private void validate() {
		if (!this.baseFolder.exists()) {
			throw new IllegalArgumentException("The folder does not exist.");
		}
		if (!this.isGitRepoFolder()) {
			for (File f : this.baseFolder.listFiles()) {
				System.out.println(f.getAbsoluteFile());
			}
			throw new IllegalArgumentException("No git repository found.");
		}
	}

	private boolean isGitRepoFolder() {
		String gfName = this.baseFolder.getAbsolutePath() + "\\.git";
		File gitFile = new File(gfName);
		return gitFile.exists();
	}
}
