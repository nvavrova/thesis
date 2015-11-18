package scraper;
import com.sun.deploy.util.StringUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 29-09-2015
 */
public class GitHubApi {

	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String ENCODING = "UTF-8";

	private final String token;

	public GitHubApi(String token) {
		this.token = token;
	}

	public void updateRepoInfo(GitHubRepo repo) {
		String resp = get("/repos/" + repo.getOwner() + '/' + repo.getName());
		JSONObject responsePart = new JSONObject(resp);

		if (responsePart.has("parent")) {
			JSONObject parent = responsePart.getJSONObject("parent");
			repo.setName(parent.getString("name"));
			JSONObject owner = parent.getJSONObject("owner");
			repo.setOwner(owner.getString("login"));
			this.updateRepoInfo(repo);
		}
		else {
			repo.setName(responsePart.getString("name"));
			JSONObject owner = responsePart.getJSONObject("owner");
			repo.setOwner(owner.getString("login"));
		}
	}

	public Map<String, Long> getLanguageUse(GitHubRepo repo) {
		String resp = this.get("/repos/" + repo.getOwner() + '/' + repo.getName() + "/languages");
		JSONObject responsePart = new JSONObject(resp);
		Map<String, Long> langUse = new HashMap<>();
		for (String lang : responsePart.keySet()) {
			Long bytes = responsePart.getLong(lang);
			langUse.put(lang, bytes);
		}
		return langUse;
	}

	private String get(String url) {
		return this.get(url, new HashMap<>()); //not an empty collection, b/c of adding auth token later!
	}

	private String get(String url, Map<String, String> params) {
		params.put("access_token", this.token);
		return TrafficHandler.performGet(GITHUB_API_BASE_URL + url + this.getParamString(params));
	}

	private String getParamString(Map<String, String> params) {
		if (params.size() == 0) {
			return "";
		}
		List<String> partialParams = params.keySet().stream()
				.map(key -> encode(key) + "=" + encode(params.get(key)))
				.collect(Collectors.toList());
		return "?" + StringUtils.join(partialParams, "&");
	}

	private String encode(String s) {
		try {
			return URLEncoder.encode(s, ENCODING);
		}
		catch (UnsupportedEncodingException e) {
			return s;
		}
	}
}
