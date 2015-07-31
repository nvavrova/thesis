package mining;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 29-07-2015
 */
public class Main {
	public static void main(String[] args) {
//		1. get issues labeled bug which are closed (merged?)
//		2. get events for each of these issues
//		3. filter out referenced (from commit msg), (and mentioned mb?)
//		4. get msg from body and find "fix"
//		5. get commit, see which files were modified, and which lines???
		String listIssuesJson = fakeListEvents();
		JSONArray arr = new JSONArray(listIssuesJson);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			String eventType = obj.getString("event");
			if (eventType.equals("referenced")) {
				String commitSha = obj.getString("commit_id");
				String body = obj.getString("body");
				if (body.toLowerCase().contains("fix")) {
					String commitJson = fakeGetCommit(commitSha);

				}
			}
		}
	}

	private static String getIssues(String owner, String repo) {
		String resp = GitHub.get("/repos/" + owner + "/" + repo + "/issues");
		JSONArray arr = new JSONArray(resp);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			String commentsUrl = obj.getString("comments_url");
			System.out.println(GitHub.doRequest(commentsUrl));
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

	private static String fakeGetCommit(String id) {
		return "{\n" +
				"\"url\": \"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"sha\": \"6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"html_url\": \"https://github.com/octocat/Hello-World/commit/6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"comments_url\": \"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e/comments\",\n" +
				"\"commit\": {\n" +
				"\"url\": \"https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"author\": {\n" +
				"\"name\": \"Monalisa Octocat\",\n" +
				"\"email\": \"support@github.com\",\n" +
				"\"date\": \"2011-04-14T16:00:49Z\"\n" +
				"},\n" +
				"\"committer\": {\n" +
				"\"name\": \"Monalisa Octocat\",\n" +
				"\"email\": \"support@github.com\",\n" +
				"\"date\": \"2011-04-14T16:00:49Z\"\n" +
				"},\n" +
				"\"message\": \"Fix all the bugs\",\n" +
				"\"tree\": {\n" +
				"\"url\": \"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"sha\": \"6dcb09b5b57875f334f61aebed695e2e4193db5e\"\n" +
				"},\n" +
				"\"comment_count\": 0\n" +
				"},\n" +
				"\"author\": {\n" +
				"\"login\": \"octocat\",\n" +
				"\"id\": 1,\n" +
				"\"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
				"\"gravatar_id\": \"\",\n" +
				"\"url\": \"https://api.github.com/users/octocat\",\n" +
				"\"html_url\": \"https://github.com/octocat\",\n" +
				"\"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
				"\"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
				"\"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
				"\"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
				"\"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
				"\"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
				"\"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
				"\"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
				"\"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
				"\"type\": \"User\",\n" +
				"\"site_admin\": false\n" +
				"},\n" +
				"\"committer\": {\n" +
				"\"login\": \"octocat\",\n" +
				"\"id\": 1,\n" +
				"\"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
				"\"gravatar_id\": \"\",\n" +
				"\"url\": \"https://api.github.com/users/octocat\",\n" +
				"\"html_url\": \"https://github.com/octocat\",\n" +
				"\"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
				"\"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
				"\"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
				"\"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
				"\"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
				"\"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
				"\"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
				"\"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
				"\"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
				"\"type\": \"User\",\n" +
				"\"site_admin\": false\n" +
				"},\n" +
				"\"parents\": [\n" +
				"{\n" +
				"\"url\": \"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e\",\n" +
				"\"sha\": \"6dcb09b5b57875f334f61aebed695e2e4193db5e\"\n" +
				"}\n" +
				"],\n" +
				"\"stats\": {\n" +
				"\"additions\": 104,\n" +
				"\"deletions\": 4,\n" +
				"\"total\": 108\n" +
				"},\n" +
				"\"files\": [\n" +
				"{\n" +
				"\"filename\": \"file1.txt\",\n" +
				"\"additions\": 10,\n" +
				"\"deletions\": 2,\n" +
				"\"changes\": 12,\n" +
				"\"status\": \"modified\",\n" +
				"\"raw_url\": \"https://github.com/octocat/Hello-World/raw/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt\",\n" +
				"\"blob_url\": \"https://github.com/octocat/Hello-World/blob/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt\",\n" +
				"\"patch\": \"@@ -29,7 +29,7 @@\\n.....\"\n" +
				"}\n" +
				"]\n" +
				"}";
	}

	private static String fakeListEvents() {
		return "[" +
				"{" +
				"\"id\": 370029266," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370029266\"," +
				"\"actor\": {" +
				"\"login\": \"nmadhok\"," +
				"\"id\": 3374962," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/3374962?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/nmadhok\"," +
				"\"html_url\": \"https://github.com/nmadhok\"," +
				"\"followers_url\": \"https://api.github.com/users/nmadhok/followers\"," +
				"\"following_url\": \"https://api.github.com/users/nmadhok/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/nmadhok/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/nmadhok/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/nmadhok/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/nmadhok/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/nmadhok/repos\"," +
				"\"events_url\": \"https://api.github.com/users/nmadhok/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/nmadhok/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"subscribed\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:34:11Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25622\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"id\": 96617896," +
				"\"number\": 25622," +
				"\"title\": \"add wait_for_ip_timeout\"," +
				"\"user\": {" +
				"\"login\": \"steverweber\"," +
				"\"id\": 1800619," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/1800619?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/steverweber\"," +
				"\"html_url\": \"https://github.com/steverweber\"," +
				"\"followers_url\": \"https://api.github.com/users/steverweber/followers\"," +
				"\"following_url\": \"https://api.github.com/users/steverweber/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/steverweber/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/steverweber/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/steverweber/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/steverweber/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/steverweber/repos\"," +
				"\"events_url\": \"https://api.github.com/users/steverweber/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/steverweber/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Medium%20Change\"," +
				"\"name\": \"Medium Change\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Pending%20Discussion\"," +
				"\"name\": \"Pending Discussion\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/RIoT\"," +
				"\"name\": \"RIoT\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 11," +
				"\"created_at\": \"2015-07-22T17:05:48Z\"," +
				"\"updated_at\": \"2015-07-30T23:34:11Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25622\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25622.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25622.patch\"" +
				"}," +
				"\"body\": \"add wait_for_ip_timeout like what is available in openstack and cleanup some code to use seconds vs minutes.\r\n\r\nhttps://github.com/saltstack/salt/issues/25614\r\nn\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370029265," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370029265\"," +
				"\"actor\": {" +
				"\"login\": \"nmadhok\"," +
				"\"id\": 3374962," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/3374962?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/nmadhok\"," +
				"\"html_url\": \"https://github.com/nmadhok\"," +
				"\"followers_url\": \"https://api.github.com/users/nmadhok/followers\"," +
				"\"following_url\": \"https://api.github.com/users/nmadhok/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/nmadhok/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/nmadhok/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/nmadhok/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/nmadhok/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/nmadhok/repos\"," +
				"\"events_url\": \"https://api.github.com/users/nmadhok/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/nmadhok/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"mentioned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:34:11Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25622\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"id\": 96617896," +
				"\"number\": 25622," +
				"\"title\": \"add wait_for_ip_timeout\"," +
				"\"user\": {" +
				"\"login\": \"steverweber\"," +
				"\"id\": 1800619," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/1800619?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/steverweber\"," +
				"\"html_url\": \"https://github.com/steverweber\"," +
				"\"followers_url\": \"https://api.github.com/users/steverweber/followers\"," +
				"\"following_url\": \"https://api.github.com/users/steverweber/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/steverweber/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/steverweber/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/steverweber/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/steverweber/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/steverweber/repos\"," +
				"\"events_url\": \"https://api.github.com/users/steverweber/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/steverweber/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Medium%20Change\"," +
				"\"name\": \"Medium Change\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Pending%20Discussion\"," +
				"\"name\": \"Pending Discussion\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/RIoT\"," +
				"\"name\": \"RIoT\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 11," +
				"\"created_at\": \"2015-07-22T17:05:48Z\"," +
				"\"updated_at\": \"2015-07-30T23:34:11Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25622\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25622.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25622.patch\"" +
				"}," +
				"\"body\": \"add wait_for_ip_timeout like what is available in openstack and cleanup some code to use seconds vs minutes.\r\n\r\nhttps://github.com/saltstack/salt/issues/25614\r\n\r\n\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370029073," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370029073\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"unlabeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:33:42Z\"," +
				"\"label\": {" +
				"\"name\": \"Pending Changes\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25622\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25622/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"id\": 96617896," +
				"\"number\": 25622," +
				"\"title\": \"add wait_for_ip_timeout\"," +
				"\"user\": {" +
				"\"login\": \"steverweber\"," +
				"\"id\": 1800619," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/1800619?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/steverweber\"," +
				"\"html_url\": \"https://github.com/steverweber\"," +
				"\"followers_url\": \"https://api.github.com/users/steverweber/followers\"," +
				"\"following_url\": \"https://api.github.com/users/steverweber/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/steverweber/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/steverweber/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/steverweber/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/steverweber/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/steverweber/repos\"," +
				"\"events_url\": \"https://api.github.com/users/steverweber/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/steverweber/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Medium%20Change\"," +
				"\"name\": \"Medium Change\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Pending%20Discussion\"," +
				"\"name\": \"Pending Discussion\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/RIoT\"," +
				"\"name\": \"RIoT\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 11," +
				"\"created_at\": \"2015-07-22T17:05:48Z\"," +
				"\"updated_at\": \"2015-07-30T23:34:11Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25622\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25622\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25622.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25622.patch\"" +
				"}," +
				"\"body\": \"add wait_for_ip_timeout like what is available in openstack and cleanup some code to use seconds vs minutes.\r\n\r\nhttps://github.com/saltstack/salt/issues/25614\r\n\r\n" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370028271," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370028271\"," +
				"\"actor\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:31:52Z\"," +
				"\"label\": {" +
				"\"name\": \"Grains\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25901\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"id\": 98281161," +
				"\"number\": 25901," +
				"\"title\": \"add support for nilrt\"," +
				"\"user\": {" +
				"\"login\": \"adelcast\"," +
				"\"id\": 10310130," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/10310130?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/adelcast\"," +
				"\"html_url\": \"https://github.com/adelcast\"," +
				"\"followers_url\": \"https://api.github.com/users/adelcast/followers\"," +
				"\"following_url\": \"https://api.github.com/users/adelcast/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/adelcast/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/adelcast/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/adelcast/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/adelcast/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/adelcast/repos\"," +
				"\"events_url\": \"https://api.github.com/users/adelcast/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/adelcast/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Grains\"," +
				"\"name\": \"Grains\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T22:50:05Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:52Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25901\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25901.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25901.patch\"" +
				"}," +
				"\"body\": \"Added support for the opkg-based (opkg execution module is on review at https://github.com/saltstack/salt/pull/25888) NI Linux Real-Time distro.\r\n\r\nSigned-off-by: Alejandro del Castillo <alejandro.delcastillo@ni.com>\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370028225," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370028225\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"unlabeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:31:47Z\"," +
				"\"label\": {" +
				"\"name\": \"Bugfix - back-port\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25892\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25892\"," +
				"\"id\": 98245092," +
				"\"number\": 25892," +
				"\"title\": \"Update 7-zip msi un-installer instructions\"," +
				"\"user\": {" +
				"\"login\": \"TheBigBear\"," +
				"\"id\": 471105," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/471105?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/TheBigBear\"," +
				"\"html_url\": \"https://github.com/TheBigBear\"," +
				"\"followers_url\": \"https://api.github.com/users/TheBigBear/followers\"," +
				"\"following_url\": \"https://api.github.com/users/TheBigBear/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/TheBigBear/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/TheBigBear/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/TheBigBear/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/TheBigBear/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/TheBigBear/repos\"," +
				"\"events_url\": \"https://api.github.com/users/TheBigBear/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/TheBigBear/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Bugfix%20-%20[Done]%20back-ported\"," +
				"\"name\": \"Bugfix - [Done] back-ported\"," +
				"\"color\": \"5319e7\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 1," +
				"\"created_at\": \"2015-07-30T19:33:50Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:47Z\"," +
				"\"closed_at\": \"2015-07-30T23:00:35Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25892\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25892\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25892.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25892.patch\"" +
				"}," +
				"\"body\": \"The instructions as-are do not actually work. The uninstaller flag takes the windows GUID of the msi product code. And the various ```install_flags``` and ```uninstall_flags``` do NOT need leading, or trailing spaces.\r\n\r\n@twangboy can confirm this is a better example (also see salt issue  https://github.com/saltstack/salt/issues/25778)\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370028224," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370028224\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:31:47Z\"," +
				"\"label\": {" +
				"\"name\": \"Bugfix - [Done] back-ported\"," +
				"\"color\": \"5319e7\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25892\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25892/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25892\"," +
				"\"id\": 98245092," +
				"\"number\": 25892," +
				"\"title\": \"Update 7-zip msi un-installer instructions\"," +
				"\"user\": {" +
				"\"login\": \"TheBigBear\"," +
				"\"id\": 471105," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/471105?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/TheBigBear\"," +
				"\"html_url\": \"https://github.com/TheBigBear\"," +
				"\"followers_url\": \"https://api.github.com/users/TheBigBear/followers\"," +
				"\"following_url\": \"https://api.github.com/users/TheBigBear/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/TheBigBear/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/TheBigBear/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/TheBigBear/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/TheBigBear/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/TheBigBear/repos\"," +
				"\"events_url\": \"https://api.github.com/users/TheBigBear/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/TheBigBear/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Bugfix%20-%20[Done]%20back-ported\"," +
				"\"name\": \"Bugfix - [Done] back-ported\"," +
				"\"color\": \"5319e7\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 1," +
				"\"created_at\": \"2015-07-30T19:33:50Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:47Z\"," +
				"\"closed_at\": \"2015-07-30T23:00:35Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25892\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25892\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25892.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25892.patch\"" +
				"}," +
				"\"body\": \"The instructions as-are do not actually work. The uninstaller flag takes the windows GUID of the msi product code. And the various ```install_flags``` and ```uninstall_flags``` do NOT need leading, or trailing spaces.\r\n\r\n@twangboy can confirm this is a better example (also see salt issue  https://github.com/saltstack/salt/issues/25778)\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370028223," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370028223\"," +
				"\"actor\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:31:47Z\"," +
				"\"label\": {" +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25901\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"id\": 98281161," +
				"\"number\": 25901," +
				"\"title\": \"add support for nilrt\"," +
				"\"user\": {" +
				"\"login\": \"adelcast\"," +
				"\"id\": 10310130," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/10310130?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/adelcast\"," +
				"\"html_url\": \"https://github.com/adelcast\"," +
				"\"followers_url\": \"https://api.github.com/users/adelcast/followers\"," +
				"\"following_url\": \"https://api.github.com/users/adelcast/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/adelcast/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/adelcast/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/adelcast/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/adelcast/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/adelcast/repos\"," +
				"\"events_url\": \"https://api.github.com/users/adelcast/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/adelcast/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Grains\"," +
				"\"name\": \"Grains\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T22:50:05Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:52Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25901\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25901.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25901.patch\"" +
				"}," +
				"\"body\": \"Added support for the opkg-based (opkg execution module is on review at https://github.com/saltstack/salt/pull/25888) NI Linux Real-Time distro.\r\n\r\nSigned-off-by: Alejandro del Castillo <alejandro.delcastillo@ni.com>\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370028222," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370028222\"," +
				"\"actor\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:31:47Z\"," +
				"\"label\": {" +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25901\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25901/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"id\": 98281161," +
				"\"number\": 25901," +
				"\"title\": \"add support for nilrt\"," +
				"\"user\": {" +
				"\"login\": \"adelcast\"," +
				"\"id\": 10310130," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/10310130?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/adelcast\"," +
				"\"html_url\": \"https://github.com/adelcast\"," +
				"\"followers_url\": \"https://api.github.com/users/adelcast/followers\"," +
				"\"following_url\": \"https://api.github.com/users/adelcast/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/adelcast/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/adelcast/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/adelcast/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/adelcast/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/adelcast/repos\"," +
				"\"events_url\": \"https://api.github.com/users/adelcast/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/adelcast/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Grains\"," +
				"\"name\": \"Grains\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T22:50:05Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:52Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25901\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25901\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25901.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25901.patch\"" +
				"}," +
				"\"body\": \"Added support for the opkg-based (opkg execution module is on review at https://github.com/saltstack/salt/pull/25888) NI Linux Real-Time distro.\r\n\r\nSigned-off-by: Alejandro del Castillo <alejandro.delcastillo@ni.com>\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370027564," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370027564\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"label\": {" +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25907\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"id\": 98286076," +
				"\"number\": 25907," +
				"\"title\": \"Back-port #25892 to 2015.8\"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"updated_at\": \"2015-07-30T23:30:28Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25907\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25907.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25907.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.8\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370027563," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370027563\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"label\": {" +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25907\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"id\": 98286076," +
				"\"number\": 25907," +
				"\"title\": \"Back-port #25892 to 2015.8\"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"updated_at\": \"2015-07-30T23:30:28Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25907\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25907.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25907.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.8\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370027562," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370027562\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"label\": {" +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25907\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25907/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"id\": 98286076," +
				"\"number\": 25907," +
				"\"title\": \"Back-port #25892 to 2015.8\"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:30:28Z\"," +
				"\"updated_at\": \"2015-07-30T23:30:28Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25907\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25907\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25907.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25907.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.8\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024508," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024508\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:25:49Z\"," +
				"\"label\": {" +
				"\"name\": \"Pending Discussion\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25848\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25848/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25848/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25848/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25848\"," +
				"\"id\": 98042978," +
				"\"number\": 25848," +
				"\"title\": \"Added allusers=\\\"1\\\" when installing msi\"," +
				"\"user\": {" +
				"\"login\": \"twangboy\"," +
				"\"id\": 9383935," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/9383935?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/twangboy\"," +
				"\"html_url\": \"https://github.com/twangboy\"," +
				"\"followers_url\": \"https://api.github.com/users/twangboy/followers\"," +
				"\"following_url\": \"https://api.github.com/users/twangboy/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/twangboy/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/twangboy/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/twangboy/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/twangboy/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/twangboy/repos\"," +
				"\"events_url\": \"https://api.github.com/users/twangboy/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/twangboy/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Execution%20Module\"," +
				"\"name\": \"Execution Module\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Master%20Change\"," +
				"\"name\": \"Master Change\"," +
				"\"color\": \"0052cc\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Pending%20Discussion\"," +
				"\"name\": \"Pending Discussion\"," +
				"\"color\": \"c7def8\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 2," +
				"\"created_at\": \"2015-07-29T22:27:23Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:49Z\"," +
				"\"closed_at\": null," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25848\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25848\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25848.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25848.patch\"" +
				"}," +
				"\"body\": \"Adds ``ALLUSERS=\\\"1\\\"`` as an argument when installing msi\r\nAdded/edited a buttload of documentation\r\nAdded weird function \\\"normalize_name\\\" that was being called by pkg state module (this is weird because it always worked before)\r\nAdded a message when it can't cache the source file instead of throwing a stack trace\r\nFixes: #25839 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024161," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024161\"," +
				"\"actor\": {" +
				"\"login\": \"HabibaAmina\"," +
				"\"id\": 12754586," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12754586?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/HabibaAmina\"," +
				"\"html_url\": \"https://github.com/HabibaAmina\"," +
				"\"followers_url\": \"https://api.github.com/users/HabibaAmina/followers\"," +
				"\"following_url\": \"https://api.github.com/users/HabibaAmina/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/HabibaAmina/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/HabibaAmina/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/HabibaAmina/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/HabibaAmina/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/HabibaAmina/repos\"," +
				"\"events_url\": \"https://api.github.com/users/HabibaAmina/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/HabibaAmina/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"subscribed\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:25:10Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25790\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25790\"," +
				"\"id\": 97741842," +
				"\"number\": 25790," +
				"\"title\": \"Error : command cmd.run \"," +
				"\"user\": {" +
				"\"login\": \"HabibaAmina\"," +
				"\"id\": 12754586," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12754586?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/HabibaAmina\"," +
				"\"html_url\": \"https://github.com/HabibaAmina\"," +
				"\"followers_url\": \"https://api.github.com/users/HabibaAmina/followers\"," +
				"\"following_url\": \"https://api.github.com/users/HabibaAmina/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/HabibaAmina/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/HabibaAmina/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/HabibaAmina/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/HabibaAmina/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/HabibaAmina/repos\"," +
				"\"events_url\": \"https://api.github.com/users/HabibaAmina/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/HabibaAmina/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Question\"," +
				"\"name\": \"Question\"," +
				"\"color\": \"942192\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/37\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Blocked\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/37/labels\"," +
				"\"id\": 343741," +
				"\"number\": 37," +
				"\"title\": \"Blocked\"," +
				"\"description\": \"Issues which are blocked in some way, whether they need further discussion, clarification, justification, or are blocked for some other reason.\"," +
				"\"creator\": {" +
				"\"login\": \"basepi\"," +
				"\"id\": 702318," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702318?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/basepi\"," +
				"\"html_url\": \"https://github.com/basepi\"," +
				"\"followers_url\": \"https://api.github.com/users/basepi/followers\"," +
				"\"following_url\": \"https://api.github.com/users/basepi/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/basepi/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/basepi/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/basepi/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/basepi/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/basepi/repos\"," +
				"\"events_url\": \"https://api.github.com/users/basepi/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/basepi/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 270," +
				"\"closed_issues\": 599," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2013-05-28T22:24:12Z\"," +
				"\"updated_at\": \"2015-07-30T22:56:29Z\"," +
				"\"due_on\": null," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 4," +
				"\"created_at\": \"2015-07-28T15:57:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:10Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"Hello,\r\n\r\nI am writing you, because of an error in the order or cmd.run highstate.\r\n\r\nI have a pkg Maldet already installed on my server which is the master, I wish to start this maldet -a / var /www that can launch scans on the SRV.\r\n\r\nWhen I run in terminal, it launches no mistake though.\r\nWhen I run hard with cmd.run or a state.sls gives me that same error:\r\n\r\nSALT-MASTER: / bin / sh: 1: maldet: not found\r\n\r\nYou think of this error? is it normal ?\r\n![image](https://cloud.githubusercontent.com/assets/12754586/8936376/296193be-3552-11e5-9d89-fd7978624642.png)\r\n\r\nThank you for your answers.\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024160," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024160\"," +
				"\"actor\": {" +
				"\"login\": \"HabibaAmina\"," +
				"\"id\": 12754586," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12754586?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/HabibaAmina\"," +
				"\"html_url\": \"https://github.com/HabibaAmina\"," +
				"\"followers_url\": \"https://api.github.com/users/HabibaAmina/followers\"," +
				"\"following_url\": \"https://api.github.com/users/HabibaAmina/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/HabibaAmina/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/HabibaAmina/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/HabibaAmina/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/HabibaAmina/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/HabibaAmina/repos\"," +
				"\"events_url\": \"https://api.github.com/users/HabibaAmina/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/HabibaAmina/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"mentioned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:25:10Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25790\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25790/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25790\"," +
				"\"id\": 97741842," +
				"\"number\": 25790," +
				"\"title\": \"Error : command cmd.run \"," +
				"\"user\": {" +
				"\"login\": \"HabibaAmina\"," +
				"\"id\": 12754586," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12754586?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/HabibaAmina\"," +
				"\"html_url\": \"https://github.com/HabibaAmina\"," +
				"\"followers_url\": \"https://api.github.com/users/HabibaAmina/followers\"," +
				"\"following_url\": \"https://api.github.com/users/HabibaAmina/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/HabibaAmina/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/HabibaAmina/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/HabibaAmina/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/HabibaAmina/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/HabibaAmina/repos\"," +
				"\"events_url\": \"https://api.github.com/users/HabibaAmina/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/HabibaAmina/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Question\"," +
				"\"name\": \"Question\"," +
				"\"color\": \"942192\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/37\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Blocked\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/37/labels\"," +
				"\"id\": 343741," +
				"\"number\": 37," +
				"\"title\": \"Blocked\"," +
				"\"description\": \"Issues which are blocked in some way, whether they need further discussion, clarification, justification, or are blocked for some other reason.\"," +
				"\"creator\": {" +
				"\"login\": \"basepi\"," +
				"\"id\": 702318," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702318?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/basepi\"," +
				"\"html_url\": \"https://github.com/basepi\"," +
				"\"followers_url\": \"https://api.github.com/users/basepi/followers\"," +
				"\"following_url\": \"https://api.github.com/users/basepi/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/basepi/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/basepi/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/basepi/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/basepi/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/basepi/repos\"," +
				"\"events_url\": \"https://api.github.com/users/basepi/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/basepi/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 270," +
				"\"closed_issues\": 599," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2013-05-28T22:24:12Z\"," +
				"\"updated_at\": \"2015-07-30T22:56:29Z\"," +
				"\"due_on\": null," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 4," +
				"\"created_at\": \"2015-07-28T15:57:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:10Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"Hello,\r\n\r\nI am writing you, because of an error in the order or cmd.run highstate.\r\n\r\nI have a pkg Maldet already installed on my server which is the master, I wish to start this maldet -a / var /www that can launch scans on the SRV.\r\n\r\nWhen I run in terminal, it launches no mistake though.\r\nWhen I run hard with cmd.run or a state.sls gives me that same error:\r\n\r\nSALT-MASTER: / bin / sh: 1: maldet: not found\r\n\r\nYou think of this error? is it normal ?\r\n![image](https://cloud.githubusercontent.com/assets/12754586/8936376/296193be-3552-11e5-9d89-fd7978624642.png)\r\n\r\nThank you for your answers.\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024123," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024123\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"closed\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:25:06Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25849\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"id\": 98045968," +
				"\"number\": 25849," +
				"\"title\": \"Repress template error for GPG renderer (can't seek an OrderedDict)\"," +
				"\"user\": {" +
				"\"login\": \"basepi\"," +
				"\"id\": 702318," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702318?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/basepi\"," +
				"\"html_url\": \"https://github.com/basepi\"," +
				"\"followers_url\": \"https://api.github.com/users/basepi/followers\"," +
				"\"following_url\": \"https://api.github.com/users/basepi/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/basepi/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/basepi/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/basepi/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/basepi/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/basepi/repos\"," +
				"\"events_url\": \"https://api.github.com/users/basepi/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/basepi/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 1," +
				"\"created_at\": \"2015-07-29T22:51:34Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:06Z\"," +
				"\"closed_at\": \"2015-07-30T23:25:06Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25849\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25849.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25849.patch\"" +
				"}," +
				"\"body\": \"Fixes #25774\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024122," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024122\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"merged\"," +
				"\"commit_id\": \"cf6b17984d3d3985c739527143eb844d1b4715ed\"," +
				"\"created_at\": \"2015-07-30T23:25:06Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25849\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"id\": 98045968," +
				"\"number\": 25849," +
				"\"title\": \"Repress template error for GPG renderer (can't seek an OrderedDict)\"," +
				"\"user\": {" +
				"\"login\": \"basepi\"," +
				"\"id\": 702318," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702318?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/basepi\"," +
				"\"html_url\": \"https://github.com/basepi\"," +
				"\"followers_url\": \"https://api.github.com/users/basepi/followers\"," +
				"\"following_url\": \"https://api.github.com/users/basepi/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/basepi/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/basepi/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/basepi/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/basepi/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/basepi/repos\"," +
				"\"events_url\": \"https://api.github.com/users/basepi/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/basepi/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 1," +
				"\"created_at\": \"2015-07-29T22:51:34Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:06Z\"," +
				"\"closed_at\": \"2015-07-30T23:25:06Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25849\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25849.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25849.patch\"" +
				"}," +
				"\"body\": \"Fixes #25774\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370024121," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370024121\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"referenced\"," +
				"\"commit_id\": \"cf6b17984d3d3985c739527143eb844d1b4715ed\"," +
				"\"created_at\": \"2015-07-30T23:25:06Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25849\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25849/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"id\": 98045968," +
				"\"number\": 25849," +
				"\"title\": \"Repress template error for GPG renderer (can't seek an OrderedDict)\"," +
				"\"user\": {" +
				"\"login\": \"basepi\"," +
				"\"id\": 702318," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702318?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/basepi\"," +
				"\"html_url\": \"https://github.com/basepi\"," +
				"\"followers_url\": \"https://api.github.com/users/basepi/followers\"," +
				"\"following_url\": \"https://api.github.com/users/basepi/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/basepi/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/basepi/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/basepi/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/basepi/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/basepi/repos\"," +
				"\"events_url\": \"https://api.github.com/users/basepi/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/basepi/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 1," +
				"\"created_at\": \"2015-07-29T22:51:34Z\"," +
				"\"updated_at\": \"2015-07-30T23:25:06Z\"," +
				"\"closed_at\": \"2015-07-30T23:25:06Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25849\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25849\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25849.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25849.patch\"" +
				"}," +
				"\"body\": \"Fixes #25774\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023770," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023770\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"head_ref_deleted\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:24:25Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023722," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023722\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"closed\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:24:19Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023721," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023721\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"merged\"," +
				"\"commit_id\": \"9a569da4ee50e6dc78a93d24773d7fdf3bc8b12b\"," +
				"\"created_at\": \"2015-07-30T23:24:19Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023720," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023720\"," +
				"\"actor\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"referenced\"," +
				"\"commit_id\": \"9a569da4ee50e6dc78a93d24773d7fdf3bc8b12b\"," +
				"\"created_at\": \"2015-07-30T23:24:19Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023696," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023696\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"referenced\"," +
				"\"commit_id\": \"0d33bae1047eceeedf4d4fd4dd82dcc11297d045\"," +
				"\"created_at\": \"2015-07-30T23:24:16Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25778\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25778\"," +
				"\"id\": 97625369," +
				"\"number\": 25778," +
				"\"title\": \"[2015.5.3-2] windows pkg.remove msiexec fails\"," +
				"\"user\": {" +
				"\"login\": \"getabc\"," +
				"\"id\": 6085468," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6085468?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/getabc\"," +
				"\"html_url\": \"https://github.com/getabc\"," +
				"\"followers_url\": \"https://api.github.com/users/getabc/followers\"," +
				"\"following_url\": \"https://api.github.com/users/getabc/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/getabc/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/getabc/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/getabc/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/getabc/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/getabc/repos\"," +
				"\"events_url\": \"https://api.github.com/users/getabc/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/getabc/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Bug\"," +
				"\"name\": \"Bug\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/High%20Severity\"," +
				"\"name\": \"High Severity\"," +
				"\"color\": \"ff9143\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/13\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Approved\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/13/labels\"," +
				"\"id\": 9265," +
				"\"number\": 13," +
				"\"title\": \"Approved\"," +
				"\"description\": \"All issues that are ready to be worked on, both bugs and features.\"," +
				"\"creator\": {" +
				"\"login\": \"thatch45\"," +
				"\"id\": 507599," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/507599?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/thatch45\"," +
				"\"html_url\": \"https://github.com/thatch45\"," +
				"\"followers_url\": \"https://api.github.com/users/thatch45/followers\"," +
				"\"following_url\": \"https://api.github.com/users/thatch45/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/thatch45/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/thatch45/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/thatch45/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/thatch45/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/thatch45/repos\"," +
				"\"events_url\": \"https://api.github.com/users/thatch45/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/thatch45/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 2450," +
				"\"closed_issues\": 2358," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2011-05-14T04:00:56Z\"," +
				"\"updated_at\": \"2015-07-31T02:48:45Z\"," +
				"\"due_on\": null," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 12," +
				"\"created_at\": \"2015-07-28T06:16:36Z\"," +
				"\"updated_at\": \"2015-07-31T01:20:25Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"Hello,\r\n\r\nRunning 2015.5.3-2 on server and minions.\r\n\r\nTried to uninstall a package with the following command,\r\n\r\n```\r\nsalt 'AC1*.ktm.*' pkg.remove 'Node.js' version=0.12.6\r\n```\r\n*Salt init.sls configuration*\r\n```saltstack\r\n    msiexec: True\r\n    uninstaller: 'msiexec.exe'\r\n    uninstall_flags: '/qn /x {0961D375-33E3-45D7-A0EA-F58693D9FBE5}'\r\n```\r\n\r\n*Execution fails*\r\n```\r\n[INFO    ] Executing command ['msiexec', '/x', 'msiexec.exe', '/qn', '/x', '{4B289DDD-4822-4706-902D-EE51DD657040}'] in directory 'C:\\\\Users\\\\andrewc'\r\n```\r\n*Reworked the init.sls configuration*\r\n```saltstack\r\n    msiexec: True\r\n    uninstaller: '{0961D375-33E3-45D7-A0EA-F58693D9FBE5}'\r\n    uninstall_flags: '/qn'\r\n```\r\nDoes this seem correct?\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023074," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023074\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"label\": {" +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023073," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023073\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"label\": {" +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370023072," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370023072\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"labeled\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"label\": {" +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25905\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25905/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"id\": 98284946," +
				"\"number\": 25905," +
				"\"title\": \"Back-port #25982 to 2015.5 \"," +
				"\"user\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Documentation\"," +
				"\"name\": \"Documentation\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Minor%20Change\"," +
				"\"name\": \"Minor Change\"," +
				"\"color\": \"bfe5bf\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"closed\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": null," +
				"\"comments\": 0," +
				"\"created_at\": \"2015-07-30T23:22:55Z\"," +
				"\"updated_at\": \"2015-07-30T23:31:37Z\"," +
				"\"closed_at\": \"2015-07-30T23:24:19Z\"," +
				"\"pull_request\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/pulls/25905\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/pull/25905\"," +
				"\"diff_url\": \"https://github.com/saltstack/salt/pull/25905.diff\"," +
				"\"patch_url\": \"https://github.com/saltstack/salt/pull/25905.patch\"" +
				"}," +
				"\"body\": \"Back-port #25892 to 2015.5 \"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370022505," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370022505\"," +
				"\"actor\": {" +
				"\"login\": \"rallytime\"," +
				"\"id\": 6547906," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6547906?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/rallytime\"," +
				"\"html_url\": \"https://github.com/rallytime\"," +
				"\"followers_url\": \"https://api.github.com/users/rallytime/followers\"," +
				"\"following_url\": \"https://api.github.com/users/rallytime/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/rallytime/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/rallytime/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/rallytime/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/rallytime/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/rallytime/repos\"," +
				"\"events_url\": \"https://api.github.com/users/rallytime/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/rallytime/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"referenced\"," +
				"\"commit_id\": \"333fbdde306debb989177cca7357db17faa7debd\"," +
				"\"created_at\": \"2015-07-30T23:21:59Z\"," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25778\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25778/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25778\"," +
				"\"id\": 97625369," +
				"\"number\": 25778," +
				"\"title\": \"[2015.5.3-2] windows pkg.remove msiexec fails\"," +
				"\"user\": {" +
				"\"login\": \"getabc\"," +
				"\"id\": 6085468," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/6085468?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/getabc\"," +
				"\"html_url\": \"https://github.com/getabc\"," +
				"\"followers_url\": \"https://api.github.com/users/getabc/followers\"," +
				"\"following_url\": \"https://api.github.com/users/getabc/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/getabc/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/getabc/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/getabc/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/getabc/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/getabc/repos\"," +
				"\"events_url\": \"https://api.github.com/users/getabc/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/getabc/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Bug\"," +
				"\"name\": \"Bug\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/High%20Severity\"," +
				"\"name\": \"High Severity\"," +
				"\"color\": \"ff9143\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Platform\"," +
				"\"name\": \"Platform\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Windows\"," +
				"\"name\": \"Windows\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": null," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/13\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Approved\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/13/labels\"," +
				"\"id\": 9265," +
				"\"number\": 13," +
				"\"title\": \"Approved\"," +
				"\"description\": \"All issues that are ready to be worked on, both bugs and features.\"," +
				"\"creator\": {" +
				"\"login\": \"thatch45\"," +
				"\"id\": 507599," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/507599?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/thatch45\"," +
				"\"html_url\": \"https://github.com/thatch45\"," +
				"\"followers_url\": \"https://api.github.com/users/thatch45/followers\"," +
				"\"following_url\": \"https://api.github.com/users/thatch45/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/thatch45/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/thatch45/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/thatch45/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/thatch45/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/thatch45/repos\"," +
				"\"events_url\": \"https://api.github.com/users/thatch45/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/thatch45/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 2450," +
				"\"closed_issues\": 2358," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2011-05-14T04:00:56Z\"," +
				"\"updated_at\": \"2015-07-31T02:48:45Z\"," +
				"\"due_on\": null," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 12," +
				"\"created_at\": \"2015-07-28T06:16:36Z\"," +
				"\"updated_at\": \"2015-07-31T01:20:25Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"Hello,\r\n\r\nRunning 2015.5.3-2 on server and minions.\r\n\r\nTried to uninstall a package with the following command,\r\n\r\n```\r\nsalt 'AC1*.ktm.*' pkg.remove 'Node.js' version=0.12.6\r\n```\r\n*Salt init.sls configuration*\r\n```saltstack\r\n    msiexec: True\r\n    uninstaller: 'msiexec.exe'\r\n    uninstall_flags: '/qn /x {0961D375-33E3-45D7-A0EA-F58693D9FBE5}'\r\n```\r\n\r\n*Execution fails*\r\n```\r\n[INFO    ] Executing command ['msiexec', '/x', 'msiexec.exe', '/qn', '/x', '{4B289DDD-4822-4706-902D-EE51DD657040}'] in directory 'C:\\\\Users\\\\andrewc'\r\n```\r\n*Reworked the init.sls configuration*\r\n```saltstack\r\n    msiexec: True\r\n    uninstaller: '{0961D375-33E3-45D7-A0EA-F58693D9FBE5}'\r\n    uninstall_flags: '/qn'\r\n```\r\nDoes this seem correct?\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370020423," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370020423\"," +
				"\"actor\": {" +
				"\"login\": \"meggiebot\"," +
				"\"id\": 12242451," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12242451?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/meggiebot\"," +
				"\"html_url\": \"https://github.com/meggiebot\"," +
				"\"followers_url\": \"https://api.github.com/users/meggiebot/followers\"," +
				"\"following_url\": \"https://api.github.com/users/meggiebot/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/meggiebot/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/meggiebot/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/meggiebot/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/meggiebot/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/meggiebot/repos\"," +
				"\"events_url\": \"https://api.github.com/users/meggiebot/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/meggiebot/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"demilestoned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:18:44Z\"," +
				"\"milestone\": {" +
				"\"title\": \"Be 0\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25716\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25716\"," +
				"\"id\": 97165963," +
				"\"number\": 25716," +
				"\"title\": \"tcp transport causes exception on master: 'Exception occurred while handling stream'\"," +
				"\"user\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Beryllium\"," +
				"\"name\": \"Beryllium\"," +
				"\"color\": \"fbca04\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Blocker\"," +
				"\"name\": \"Blocker\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Critical\"," +
				"\"name\": \"Critical\"," +
				"\"color\": \"e95800\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Fixed%20Pending%20Verification\"," +
				"\"name\": \"Fixed Pending Verification\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Transport\"," +
				"\"name\": \"Transport\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/61\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Be%201\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/61/labels\"," +
				"\"id\": 1169058," +
				"\"number\": 61," +
				"\"title\": \"Be 1\"," +
				"\"description\": null," +
				"\"creator\": {" +
				"\"login\": \"whiteinge\"," +
				"\"id\": 91293," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/91293?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/whiteinge\"," +
				"\"html_url\": \"https://github.com/whiteinge\"," +
				"\"followers_url\": \"https://api.github.com/users/whiteinge/followers\"," +
				"\"following_url\": \"https://api.github.com/users/whiteinge/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/whiteinge/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/whiteinge/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/whiteinge/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/whiteinge/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/whiteinge/repos\"," +
				"\"events_url\": \"https://api.github.com/users/whiteinge/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/whiteinge/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 37," +
				"\"closed_issues\": 17," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2015-06-17T18:05:37Z\"," +
				"\"updated_at\": \"2015-07-30T23:40:33Z\"," +
				"\"due_on\": \"2015-08-04T05:59:00Z\"," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 3," +
				"\"created_at\": \"2015-07-24T23:09:29Z\"," +
				"\"updated_at\": \"2015-07-30T23:44:18Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"### versions\r\n```yaml\r\n# salt --versions\r\nSalt Version:\r\n           Salt: 2015.8.0rc2-5-gdca09f8\r\n \r\nDependency Versions:\r\n         Jinja2: 2.7.3\r\n       M2Crypto: 0.21.1\r\n           Mako: 1.0.0\r\n         PyYAML: 3.11\r\n          PyZMQ: 14.4.0\r\n         Python: 2.7.9 (default, Mar  1 2015, 12:57:24)\r\n           RAET: Not Installed\r\n        Tornado: 4.1\r\n            ZMQ: 4.0.5\r\n          ioflo: Not Installed\r\n        libnacl: Not Installed\r\n   msgpack-pure: Not Installed\r\n msgpack-python: 0.4.6\r\n       pycrypto: 2.6.1\r\n \r\nSystem Versions:\r\n           dist: debian 8.1 \r\n        machine: x86_64\r\n        release: 3.16.0-4-amd64\r\n         system: debian 8.1 \r\n```\r\n\r\n### commands\r\n```console\r\n# salt '*' test.ping\r\njmoney-main:\r\n    True\r\n```\r\n\r\n### logs\r\n```\r\n# master log\r\n[DEBUG   ] Sending event - data = {'_stamp': '2015-07-24T23:03:10.708995', 'minions': ['jmoney-main']}\r\n[DEBUG   ] Sending event - data = {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', '_stamp': '2015-07-24T23:03:10.709323', 'user': 'root', 'arg': [], 'fun': 'test.ping', 'minions': ['jmoney-main']}\r\n[INFO    ] User root Published command test.ping with jid 20150724170310708744\r\n[DEBUG   ] Published command details {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', 'ret': '', 'user': 'root', 'arg': [], 'fun': 'test.ping'}\r\n[DEBUG   ] Initializing new IPCClient for path: /var/run/salt/master/publish_pull.ipc\r\n[ERROR   ] Exception occurred while handling stream: \r\n[DEBUG   ] TCP PubServer sending payload: 176 ??body????load??{H?6?A6???o??K?}?\\?/??a???Sew?;?????h?w!@???6i\\??H????1?-?????/y^?)?(???4?????w?W5Yn???? #??Ë?b<??B?1*4??~j???[k?^?enc?aes?head?\r\n[DEBUG   ] LazyLoaded local_cache.prep_jid\r\n[INFO    ] Got return from jmoney-main for job 20150724170310708744\r\n[DEBUG   ] Sending event - data = {'fun_args': [], 'jid': '20150724170310708744', 'return': True, 'retcode': 0, 'success': True, 'cmd': '_return', '_stamp': '2015-07-24T23:03:10.782720', 'fun': 'test.ping', 'id': 'jmoney-main'}\r\n```\r\n```\r\n# minion log\r\n[INFO    ] User root Executing command test.ping with jid 20150724170310708744\r\n[DEBUG   ] Command details {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', 'ret': '', 'user': 'root', 'arg': [], 'fun': 'test.ping'}\r\n[INFO    ] Starting a new job with PID 16925\r\n[DEBUG   ] LazyLoaded test.ping\r\n[INFO    ] Returning information for job: 20150724170310708744\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n```\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370020422," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370020422\"," +
				"\"actor\": {" +
				"\"login\": \"meggiebot\"," +
				"\"id\": 12242451," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12242451?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/meggiebot\"," +
				"\"html_url\": \"https://github.com/meggiebot\"," +
				"\"followers_url\": \"https://api.github.com/users/meggiebot/followers\"," +
				"\"following_url\": \"https://api.github.com/users/meggiebot/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/meggiebot/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/meggiebot/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/meggiebot/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/meggiebot/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/meggiebot/repos\"," +
				"\"events_url\": \"https://api.github.com/users/meggiebot/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/meggiebot/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"milestoned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:18:44Z\"," +
				"\"milestone\": {" +
				"\"title\": \"Be 1\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25716\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25716/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25716\"," +
				"\"id\": 97165963," +
				"\"number\": 25716," +
				"\"title\": \"tcp transport causes exception on master: 'Exception occurred while handling stream'\"," +
				"\"user\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Beryllium\"," +
				"\"name\": \"Beryllium\"," +
				"\"color\": \"fbca04\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Blocker\"," +
				"\"name\": \"Blocker\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Critical\"," +
				"\"name\": \"Critical\"," +
				"\"color\": \"e95800\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Fixed%20Pending%20Verification\"," +
				"\"name\": \"Fixed Pending Verification\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Transport\"," +
				"\"name\": \"Transport\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/61\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Be%201\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/61/labels\"," +
				"\"id\": 1169058," +
				"\"number\": 61," +
				"\"title\": \"Be 1\"," +
				"\"description\": null," +
				"\"creator\": {" +
				"\"login\": \"whiteinge\"," +
				"\"id\": 91293," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/91293?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/whiteinge\"," +
				"\"html_url\": \"https://github.com/whiteinge\"," +
				"\"followers_url\": \"https://api.github.com/users/whiteinge/followers\"," +
				"\"following_url\": \"https://api.github.com/users/whiteinge/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/whiteinge/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/whiteinge/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/whiteinge/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/whiteinge/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/whiteinge/repos\"," +
				"\"events_url\": \"https://api.github.com/users/whiteinge/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/whiteinge/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 37," +
				"\"closed_issues\": 17," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2015-06-17T18:05:37Z\"," +
				"\"updated_at\": \"2015-07-30T23:40:33Z\"," +
				"\"due_on\": \"2015-08-04T05:59:00Z\"," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 3," +
				"\"created_at\": \"2015-07-24T23:09:29Z\"," +
				"\"updated_at\": \"2015-07-30T23:44:18Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"### versions\r\n```yaml\r\n# salt --versions\r\nSalt Version:\r\n           Salt: 2015.8.0rc2-5-gdca09f8\r\n \r\nDependency Versions:\r\n         Jinja2: 2.7.3\r\n       M2Crypto: 0.21.1\r\n           Mako: 1.0.0\r\n         PyYAML: 3.11\r\n          PyZMQ: 14.4.0\r\n         Python: 2.7.9 (default, Mar  1 2015, 12:57:24)\r\n           RAET: Not Installed\r\n        Tornado: 4.1\r\n            ZMQ: 4.0.5\r\n          ioflo: Not Installed\r\n        libnacl: Not Installed\r\n   msgpack-pure: Not Installed\r\n msgpack-python: 0.4.6\r\n       pycrypto: 2.6.1\r\n \r\nSystem Versions:\r\n           dist: debian 8.1 \r\n        machine: x86_64\r\n        release: 3.16.0-4-amd64\r\n         system: debian 8.1 \r\n```\r\n\r\n### commands\r\n```console\r\n# salt '*' test.ping\r\njmoney-main:\r\n    True\r\n```\r\n\r\n### logs\r\n```\r\n# master log\r\n[DEBUG   ] Sending event - data = {'_stamp': '2015-07-24T23:03:10.708995', 'minions': ['jmoney-main']}\r\n[DEBUG   ] Sending event - data = {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', '_stamp': '2015-07-24T23:03:10.709323', 'user': 'root', 'arg': [], 'fun': 'test.ping', 'minions': ['jmoney-main']}\r\n[INFO    ] User root Published command test.ping with jid 20150724170310708744\r\n[DEBUG   ] Published command details {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', 'ret': '', 'user': 'root', 'arg': [], 'fun': 'test.ping'}\r\n[DEBUG   ] Initializing new IPCClient for path: /var/run/salt/master/publish_pull.ipc\r\n[ERROR   ] Exception occurred while handling stream: \r\n[DEBUG   ] TCP PubServer sending payload: 176 ??body????load??{H?6?A6???o??K?}?\\?/??a???Sew?;?????h?w!@???6i\\??H????1?-?????/y^?)?(???4?????w?W5Yn???? #??Ë?b<??B?1*4??~j???[k?^?enc?aes?head?\r\n[DEBUG   ] LazyLoaded local_cache.prep_jid\r\n[INFO    ] Got return from jmoney-main for job 20150724170310708744\r\n[DEBUG   ] Sending event - data = {'fun_args': [], 'jid': '20150724170310708744', 'return': True, 'retcode': 0, 'success': True, 'cmd': '_return', '_stamp': '2015-07-24T23:03:10.782720', 'fun': 'test.ping', 'id': 'jmoney-main'}\r\n```\r\n```\r\n# minion log\r\n[INFO    ] User root Executing command test.ping with jid 20150724170310708744\r\n[DEBUG   ] Command details {'tgt_type': 'glob', 'jid': '20150724170310708744', 'tgt': '*', 'ret': '', 'user': 'root', 'arg': [], 'fun': 'test.ping'}\r\n[INFO    ] Starting a new job with PID 16925\r\n[DEBUG   ] LazyLoaded test.ping\r\n[INFO    ] Returning information for job: 20150724170310708744\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n```\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370020376," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370020376\"," +
				"\"actor\": {" +
				"\"login\": \"meggiebot\"," +
				"\"id\": 12242451," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12242451?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/meggiebot\"," +
				"\"html_url\": \"https://github.com/meggiebot\"," +
				"\"followers_url\": \"https://api.github.com/users/meggiebot/followers\"," +
				"\"following_url\": \"https://api.github.com/users/meggiebot/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/meggiebot/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/meggiebot/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/meggiebot/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/meggiebot/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/meggiebot/repos\"," +
				"\"events_url\": \"https://api.github.com/users/meggiebot/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/meggiebot/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"demilestoned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:18:40Z\"," +
				"\"milestone\": {" +
				"\"title\": \"Be 0\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25720\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25720\"," +
				"\"id\": 97186081," +
				"\"number\": 25720," +
				"\"title\": \"tcp transport: minion stack trace: Unhandled exception running mine.update\"," +
				"\"user\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Beryllium\"," +
				"\"name\": \"Beryllium\"," +
				"\"color\": \"fbca04\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Blocker\"," +
				"\"name\": \"Blocker\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Critical\"," +
				"\"name\": \"Critical\"," +
				"\"color\": \"e95800\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Fixed%20Pending%20Verification\"," +
				"\"name\": \"Fixed Pending Verification\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Transport\"," +
				"\"name\": \"Transport\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/61\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Be%201\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/61/labels\"," +
				"\"id\": 1169058," +
				"\"number\": 61," +
				"\"title\": \"Be 1\"," +
				"\"description\": null," +
				"\"creator\": {" +
				"\"login\": \"whiteinge\"," +
				"\"id\": 91293," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/91293?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/whiteinge\"," +
				"\"html_url\": \"https://github.com/whiteinge\"," +
				"\"followers_url\": \"https://api.github.com/users/whiteinge/followers\"," +
				"\"following_url\": \"https://api.github.com/users/whiteinge/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/whiteinge/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/whiteinge/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/whiteinge/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/whiteinge/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/whiteinge/repos\"," +
				"\"events_url\": \"https://api.github.com/users/whiteinge/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/whiteinge/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 37," +
				"\"closed_issues\": 17," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2015-06-17T18:05:37Z\"," +
				"\"updated_at\": \"2015-07-30T23:40:33Z\"," +
				"\"due_on\": \"2015-08-04T05:59:00Z\"," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 4," +
				"\"created_at\": \"2015-07-25T03:25:46Z\"," +
				"\"updated_at\": \"2015-07-30T23:18:40Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"### versions\r\n```yaml\r\n# salt-minion --versions\r\nSalt Version:\r\n           Salt: 2015.8.0rc2\r\n \r\nDependency Versions:\r\n         Jinja2: 2.7.3\r\n       M2Crypto: 0.21.1\r\n           Mako: 1.0.0\r\n         PyYAML: 3.11\r\n          PyZMQ: 14.4.0\r\n         Python: 2.7.9 (default, Mar  1 2015, 12:57:24)\r\n           RAET: Not Installed\r\n        Tornado: 4.1\r\n            ZMQ: 4.0.5\r\n          ioflo: Not Installed\r\n        libnacl: Not Installed\r\n   msgpack-pure: Not Installed\r\n msgpack-python: 0.4.6\r\n       pycrypto: 2.6.1\r\n \r\nSystem Versions:\r\n           dist: debian 8.1 \r\n        machine: x86_64\r\n        release: 3.16.0-4-amd64\r\n         system: debian 8.1 \r\n```\r\n### logs\r\nThis stack trace happened a few hours after starting the minion.\r\n```python\r\n[INFO    ] Running scheduled job: __mine_interval\r\n[DEBUG   ] schedule: This job was scheduled with jid_include, adding to cache (jid_include defaults to True)\r\n[DEBUG   ] schedule: This job was scheduled with a max number of 2\r\n[DEBUG   ] schedule.handle_func: adding this job to the jobcache with data {'fun': 'mine.update', 'jid': '20150724202343072152', 'pid': 3044, 'id': 'jmoney-main', 'schedule': '__mine_interval'}\r\n[DEBUG   ] MinionEvent PUB socket URI: ipc:///var/run/salt/minion/minion_event_3f1c9a06f3_pub.ipc\r\n[DEBUG   ] MinionEvent PULL socket URI: ipc:///var/run/salt/minion/minion_event_3f1c9a06f3_pull.ipc\r\n[DEBUG   ] Sending event - data = {'clear': False, 'cmd': '_mine', 'data': {}, 'id': 'jmoney-main', '_stamp': '2015-07-25T02:23:43.082212'}\r\n[DEBUG   ] Handling event '_minion_mine\\\\x85\\xa5clear\\xc2\\xa3cmd\\xa5_mine\\xa4data\\x80\\xa2id\\xabjmoney-main\\xa6_stamp\\xba2015-07-25T02:23:43.082212'\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n[ERROR   ] Unhandled exception running mine.update\r\nTraceback (most recent call last):\r\n  File \\\"/root/salt/salt/utils/schedule.py\\\", line 706, in handle_func\r\n    channel.send(load)\r\n  File \\\"/root/salt/salt/utils/async.py\\\", line 77, in wrap\r\n    ret = self._block_future(ret)\r\n  File \\\"/root/salt/salt/utils/async.py\\\", line 87, in _block_future\r\n    return future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 181, in send\r\n    ret = yield self._crypted_transfer(load, tries=tries, timeout=timeout)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 161, in _crypted_transfer\r\n    ret = yield _do_transfer()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 148, in _do_transfer\r\n    timeout=timeout,\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"<string>\\\", line 3, in raise_exc_info\r\nSaltReqTimeoutError: Message timed out\r\n[DEBUG   ] schedule.handle_func: Removing /var/cache/salt/minion/proc/20150724202343072152\r\n```\"" +
				"}" +
				"}," +
				"{" +
				"\"id\": 370020375," +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/events/370020375\"," +
				"\"actor\": {" +
				"\"login\": \"meggiebot\"," +
				"\"id\": 12242451," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/12242451?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/meggiebot\"," +
				"\"html_url\": \"https://github.com/meggiebot\"," +
				"\"followers_url\": \"https://api.github.com/users/meggiebot/followers\"," +
				"\"following_url\": \"https://api.github.com/users/meggiebot/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/meggiebot/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/meggiebot/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/meggiebot/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/meggiebot/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/meggiebot/repos\"," +
				"\"events_url\": \"https://api.github.com/users/meggiebot/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/meggiebot/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"event\": \"milestoned\"," +
				"\"commit_id\": null," +
				"\"created_at\": \"2015-07-30T23:18:40Z\"," +
				"\"milestone\": {" +
				"\"title\": \"Be 1\"" +
				"}," +
				"\"issue\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/issues/25720\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/labels{/name}\"," +
				"\"comments_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/comments\"," +
				"\"events_url\": \"https://api.github.com/repos/saltstack/salt/issues/25720/events\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/issues/25720\"," +
				"\"id\": 97186081," +
				"\"number\": 25720," +
				"\"title\": \"tcp transport: minion stack trace: Unhandled exception running mine.update\"," +
				"\"user\": {" +
				"\"login\": \"jfindlay\"," +
				"\"id\": 702905," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/702905?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/jfindlay\"," +
				"\"html_url\": \"https://github.com/jfindlay\"," +
				"\"followers_url\": \"https://api.github.com/users/jfindlay/followers\"," +
				"\"following_url\": \"https://api.github.com/users/jfindlay/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/jfindlay/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/jfindlay/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/jfindlay/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/jfindlay/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/jfindlay/repos\"," +
				"\"events_url\": \"https://api.github.com/users/jfindlay/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/jfindlay/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"labels\": [" +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Beryllium\"," +
				"\"name\": \"Beryllium\"," +
				"\"color\": \"fbca04\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Blocker\"," +
				"\"name\": \"Blocker\"," +
				"\"color\": \"e11d21\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Core\"," +
				"\"name\": \"Core\"," +
				"\"color\": \"fef2c0\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Critical\"," +
				"\"name\": \"Critical\"," +
				"\"color\": \"e95800\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Fixed%20Pending%20Verification\"," +
				"\"name\": \"Fixed Pending Verification\"," +
				"\"color\": \"d4c5f9\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/P3\"," +
				"\"name\": \"P3\"," +
				"\"color\": \"0a3d77\"" +
				"}," +
				"{" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/labels/Transport\"," +
				"\"name\": \"Transport\"," +
				"\"color\": \"006b75\"" +
				"}" +
				"]," +
				"\"state\": \"open\"," +
				"\"locked\": false," +
				"\"assignee\": {" +
				"\"login\": \"cachedout\"," +
				"\"id\": 111616," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/111616?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/cachedout\"," +
				"\"html_url\": \"https://github.com/cachedout\"," +
				"\"followers_url\": \"https://api.github.com/users/cachedout/followers\"," +
				"\"following_url\": \"https://api.github.com/users/cachedout/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/cachedout/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/cachedout/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/cachedout/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/cachedout/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/cachedout/repos\"," +
				"\"events_url\": \"https://api.github.com/users/cachedout/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/cachedout/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"milestone\": {" +
				"\"url\": \"https://api.github.com/repos/saltstack/salt/milestones/61\"," +
				"\"html_url\": \"https://github.com/saltstack/salt/milestones/Be%201\"," +
				"\"labels_url\": \"https://api.github.com/repos/saltstack/salt/milestones/61/labels\"," +
				"\"id\": 1169058," +
				"\"number\": 61," +
				"\"title\": \"Be 1\"," +
				"\"description\": null," +
				"\"creator\": {" +
				"\"login\": \"whiteinge\"," +
				"\"id\": 91293," +
				"\"avatar_url\": \"https://avatars.githubusercontent.com/u/91293?v=3\"," +
				"\"gravatar_id\": \"\"," +
				"\"url\": \"https://api.github.com/users/whiteinge\"," +
				"\"html_url\": \"https://github.com/whiteinge\"," +
				"\"followers_url\": \"https://api.github.com/users/whiteinge/followers\"," +
				"\"following_url\": \"https://api.github.com/users/whiteinge/following{/other_user}\"," +
				"\"gists_url\": \"https://api.github.com/users/whiteinge/gists{/gist_id}\"," +
				"\"starred_url\": \"https://api.github.com/users/whiteinge/starred{/owner}{/repo}\"," +
				"\"subscriptions_url\": \"https://api.github.com/users/whiteinge/subscriptions\"," +
				"\"organizations_url\": \"https://api.github.com/users/whiteinge/orgs\"," +
				"\"repos_url\": \"https://api.github.com/users/whiteinge/repos\"," +
				"\"events_url\": \"https://api.github.com/users/whiteinge/events{/privacy}\"," +
				"\"received_events_url\": \"https://api.github.com/users/whiteinge/received_events\"," +
				"\"type\": \"User\"," +
				"\"site_admin\": false" +
				"}," +
				"\"open_issues\": 37," +
				"\"closed_issues\": 17," +
				"\"state\": \"open\"," +
				"\"created_at\": \"2015-06-17T18:05:37Z\"," +
				"\"updated_at\": \"2015-07-30T23:40:33Z\"," +
				"\"due_on\": \"2015-08-04T05:59:00Z\"," +
				"\"closed_at\": null" +
				"}," +
				"\"comments\": 4," +
				"\"created_at\": \"2015-07-25T03:25:46Z\"," +
				"\"updated_at\": \"2015-07-30T23:18:40Z\"," +
				"\"closed_at\": null," +
				"\"body\": \"### versions\r\n```yaml\r\n# salt-minion --versions\r\nSalt Version:\r\n           Salt: 2015.8.0rc2\r\n \r\nDependency Versions:\r\n         Jinja2: 2.7.3\r\n       M2Crypto: 0.21.1\r\n           Mako: 1.0.0\r\n         PyYAML: 3.11\r\n          PyZMQ: 14.4.0\r\n         Python: 2.7.9 (default, Mar  1 2015, 12:57:24)\r\n           RAET: Not Installed\r\n        Tornado: 4.1\r\n            ZMQ: 4.0.5\r\n          ioflo: Not Installed\r\n        libnacl: Not Installed\r\n   msgpack-pure: Not Installed\r\n msgpack-python: 0.4.6\r\n       pycrypto: 2.6.1\r\n \r\nSystem Versions:\r\n           dist: debian 8.1 \r\n        machine: x86_64\r\n        release: 3.16.0-4-amd64\r\n         system: debian 8.1 \r\n```\r\n### logs\r\nThis stack trace happened a few hours after starting the minion.\r\n```python\r\n[INFO    ] Running scheduled job: __mine_interval\r\n[DEBUG   ] schedule: This job was scheduled with jid_include, adding to cache (jid_include defaults to True)\r\n[DEBUG   ] schedule: This job was scheduled with a max number of 2\r\n[DEBUG   ] schedule.handle_func: adding this job to the jobcache with data {'fun': 'mine.update', 'jid': '20150724202343072152', 'pid': 3044, 'id': 'jmoney-main', 'schedule': '__mine_interval'}\r\n[DEBUG   ] MinionEvent PUB socket URI: ipc:///var/run/salt/minion/minion_event_3f1c9a06f3_pub.ipc\r\n[DEBUG   ] MinionEvent PULL socket URI: ipc:///var/run/salt/minion/minion_event_3f1c9a06f3_pull.ipc\r\n[DEBUG   ] Sending event - data = {'clear': False, 'cmd': '_mine', 'data': {}, 'id': 'jmoney-main', '_stamp': '2015-07-25T02:23:43.082212'}\r\n[DEBUG   ] Handling event '_minion_mine\\\\x85\\xa5clear\\xc2\\xa3cmd\\xa5_mine\\xa4data\\x80\\xa2id\\xabjmoney-main\\xa6_stamp\\xba2015-07-25T02:23:43.082212'\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n[DEBUG   ] Initializing new AsyncTCPReqChannel for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506', 'aes')\r\n[DEBUG   ] Initializing new SAuth for ('/etc/salt/pki/minion', 'jmoney-main', 'tcp://127.0.0.1:4506')\r\n[DEBUG   ] tcp stream to 127.0.0.1:4506 closed, unable to recv\r\n[ERROR   ] Unhandled exception running mine.update\r\nTraceback (most recent call last):\r\n  File \\\"/root/salt/salt/utils/schedule.py\\\", line 706, in handle_func\r\n    channel.send(load)\r\n  File \\\"/root/salt/salt/utils/async.py\\\", line 77, in wrap\r\n    ret = self._block_future(ret)\r\n  File \\\"/root/salt/salt/utils/async.py\\\", line 87, in _block_future\r\n    return future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 181, in send\r\n    ret = yield self._crypted_transfer(load, tries=tries, timeout=timeout)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 161, in _crypted_transfer\r\n    ret = yield _do_transfer()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 810, in run\r\n    yielded = self.gen.throw(*sys.exc_info())\r\n  File \\\"/root/salt/salt/transport/tcp.py\\\", line 148, in _do_transfer\r\n    timeout=timeout,\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/gen.py\\\", line 807, in run\r\n    value = future.result()\r\n  File \\\"/usr/local/lib/python2.7/dist-packages/tornado/concurrent.py\\\", line 209, in result\r\n    raise_exc_info(self._exc_info)\r\n  File \\\"<string>\\\", line 3, in raise_exc_info\r\nSaltReqTimeoutError: Message timed out\r\n[DEBUG   ] schedule.handle_func: Removing /var/cache/salt/minion/proc/20150724202343072152\r\n```\"" +
				"}" +
				"}" +
				"]";
	}
}
