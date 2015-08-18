package mining;

import db.DataHandler;
import db.pojo.ProjectEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import util.FileHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 29-07-2015
 */
public class Miner {

	private final String owner;
	private final String repo;
	private final String bugLabel;
	private Bugs bugs;

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(FileHelper.getLogName("out")));
		PrintStream err = new PrintStream(new FileOutputStream(FileHelper.getLogName("err")));
		System.setOut(out);
		System.setErr(err);

		for (ProjectEntity pe : DataHandler.loadProjects()){
			Miner miner = new Miner(pe.getOwner(), pe.getRepo(), pe.getBugLabel());
			Bugs bugs = miner.mine();

			DataHandler dataHandler = new DataHandler(pe);
			dataHandler.save(bugs);
		}
	}

	public Miner(String owner, String repo, String bugLabel) {
		this.owner = owner;
		this.repo = repo;
		this.bugLabel = bugLabel.toLowerCase();
	}

	public Bugs mine() {

//		1. get issues labeled bug which are closed (merged?)
//		2. get events for each of these issues
//		3. filter out closed, referenced (from commit msg)... and mb also mentioned?
//		4. get msg from body and if not closed, find "fix"
//		5. get commit info

		this.bugs = new Bugs();

//		String listIssuesJson = getIssuesWithStateAndLabel("closed", "bug");
		for (JSONArray arr : this.listIssueEventsForRepo()) {
			for (int i = 0; i < arr.length(); i++) {
				JSONObject eventJson = arr.getJSONObject(i);
				JSONObject issueJson = eventJson.getJSONObject("issue");
				if (this.isBug(issueJson)) {
					this.processBug(eventJson, issueJson);
				}
			}
		}

		return this.bugs;
	}

	private void processBug(JSONObject eventJson, JSONObject issueJson) {
		long bugId = issueJson.getLong("id");
		this.bugs.addBug(bugId);

		if (eventJson.has("event")){
			String eventType = eventJson.getString("event");
			if (eventType.equals("referenced") || eventType.equals("closed")) {
				this.processCommit(bugId, eventJson, eventType.equals("closed"));
			}
		}
	}

	private void processCommit(long bugId, JSONObject eventJson, boolean isClose) {
		if (eventJson.has("commit_id") && eventJson.has("body")) {
			String commitSha = eventJson.getString("commit_id");
			String body = eventJson.getString("body");
			if (isClose || body.toLowerCase().contains("fix")) {
				this.bugs.addBugFixCommit(bugId, commitSha);
			}
		}
	}

	private boolean isBug(JSONObject issueJson) {
		JSONArray labels = issueJson.getJSONArray("labels");
		for (int i = 0; i < labels.length(); i++) {
			JSONObject label = labels.getJSONObject(i);
			String labelName = label.getString("name").toLowerCase();
			if (labelName.equals(this.bugLabel)) {
				return true;
			}
		}
		return false;
	}

	private String getIssuesWithStateAndLabel(String state, String label) {
		Map<String, String> params = new HashMap<>();
		params.put("state", state);
		params.put("labels", label);
		return GitHub.get("/repos/" + this.owner + "/" + this.repo + "/issues", params);
	}

	private List<JSONArray> listIssueEventsForRepo() {
		return this.listAll("/repos/" + this.owner + "/" + this.repo + "/issues/events");
	}

	private List<JSONArray> listAll(String url) {
		return this.listAll(url, new HashMap<>());
	}

	private List<JSONArray> listAll(String url, Map<String, String> params) {
		List<JSONArray> resp = new ArrayList<>();
		Integer pageNr = 1;
		do {
			params.put("page", Integer.toString(pageNr++));
			String response = GitHub.get(url, params);

			if (response == null || response.trim().equals("")) {
				break;
			}

			JSONArray responsePart = new JSONArray(response);
			if (responsePart.length() == 0) {
				break;
			}

			resp.add(responsePart);
		}
		while (true);
		return resp;
	}
}
