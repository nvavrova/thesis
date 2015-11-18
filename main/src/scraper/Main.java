package scraper;
import util.FileHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {

	//args = "GIT API TOKEN"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=530"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=531"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=532"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=533"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=534"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=535"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=538"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=566"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=587"
	// "https://pypi.python.org/pypi?:action=browse&show=all&c=607"
	public static void main(String[] args) throws IOException {
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("ERROR")));
		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("OUT")));
		PrintStream allLinks = new PrintStream(new FileOutputStream(FileHelper.getLogName("all_links", "txt")));
		PrintStream databaseScript = new PrintStream(new FileOutputStream(FileHelper.getLogName("git_locations", "csv")));
		PrintStream cloneScript = new PrintStream(new FileOutputStream(FileHelper.getLogName("get_data", "sh")));

		System.setOut(out);
		System.setErr(err);

		List<String> versionUrls = Arrays.asList(args);
		versionUrls.remove(0); //remove token

		PyPiScraper scraper = new PyPiScraper(versionUrls);
		List<GitHubRepo> gitLinks = scraper.getAllGitRepoLinks();

		gitLinks.forEach(l -> allLinks.println(l.getUrl()));

		Map<String, GitHubRepo> validGitRepos = getValidGitHubRepos(gitLinks, args[0]);
		validGitRepos.values().stream()
				.filter(GitHubRepo::isPythonRepo)
				.forEach(r -> addToScript(cloneScript, databaseScript, r));
	}

	private static void addToScript(PrintStream cloneScript, PrintStream repoInfo, GitHubRepo repo) {
		String diskLocation = "data/" + repo.getName() + "_" + UUID.randomUUID();
		cloneScript.println("git clone " + repo.getUrl() + " " + diskLocation);
		repoInfo.println(repo.getUrl() + ";" + diskLocation);
	}

	public static Map<String, GitHubRepo> getValidGitHubRepos(List<GitHubRepo> repos, String token) {
		Map<String, GitHubRepo> results = new HashMap<>();
		GitHubApi gitHubApi = new GitHubApi(token);
		for (GitHubRepo repo : repos) {
			try {
				gitHubApi.updateRepoInfo(repo);
				if (results.containsKey(repo.getUrl())) {
					continue;
				}
				Map<String, Long> langUse = gitHubApi.getLanguageUse(repo);
				repo.setLanguageUsage(langUse);
				results.put(repo.getUrl(), repo);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				repo.setLanguageUsage(Collections.emptyMap());
			}
		}
		return results;
	}
}
