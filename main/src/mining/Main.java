package mining;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 29-07-2015
 */
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		String cmd = "ls";
		Process proc = rt.exec(cmd);

		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");

		errorGobbler.start();
		outputGobbler.start();

		int exitVal = proc.waitFor();
		System.out.println("ExitValue: " + exitVal);
	}

	private static void stuff() {
//		1. get issues labeled bug which are closed (merged?)
//		2. get events for each of these issues
//		3. filter out referenced (from commit msg), (and mentioned mb?)
//		4. get msg from body and find "fix"
//		5. get commit, see which files were modified, and which lines???
		String owner = "";
		String repo = "";

		String listIssuesJson = listEvents(owner, repo);
		JSONArray arr = new JSONArray(listIssuesJson);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			String eventType = obj.getString("event");
			if (eventType.equals("referenced")) {
				String commitSha = obj.getString("commit_id");
				String body = obj.getString("body");
				if (body.toLowerCase().contains("fix")) {
					String commitJson = getCommit(owner, repo, commitSha);
					JSONObject commit = new JSONObject(commitJson);
					JSONArray changedFiles = commit.getJSONArray("files");
					for (int j = 0; j < changedFiles.length(); j++) {
						JSONObject changedFile = changedFiles.getJSONObject(j);
						//TODO: if status == modified?
						String fileName = changedFile.getString("filename");
						System.out.println(fileName);
					}
				}
			}
		}
	}

	private static String getCommit(String owner, String repo, String sha) {
		//TODO
		return "";
	}

	private static String listEvents(String owner, String repo) {
		//TODO
		return "";
	}

	private static String getIssues(String owner, String repo) {
		String resp = GitHub.get("/repos/" + owner + "/" + repo + "/issues");
		JSONArray arr = new JSONArray(resp);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			String commentsUrl = obj.getString("comments_url");
//			System.out.println(GitHub.doRequest(commentsUrl));
		}
		return resp;
	}

	private static String getIssuesWithLabel(String owner, String repo, String label) {
		Map<String, String> params = new HashMap<>();
		params.put("labels", label);
		return GitHub.get("/repos/" + owner + "/" + repo + "/issues", params);
	}

	private static String listIssueEventsForRepo(String owner, String repo) {
		StringBuilder sb = new StringBuilder();
		String response;
		Integer pageNr = 1;
		do {
			response = GitHub.get("/repos/" + owner + "/" + repo + "/issues/events?page=" + Integer.toString(pageNr++));
			sb.append(response);
		}
		while (response != null && !response.equals(""));
		return sb.toString();
	}

	private static String getCommentsForIssue(String owner, String repo, Integer number) {
		return GitHub.get("/repos/" + owner + "/" + repo + "/issues/" + Integer.toString(number) + " /comments");
	}
}
