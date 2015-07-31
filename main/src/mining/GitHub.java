package mining;

import helpers.StringHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 29-07-2015
 */
public class GitHub {

	public static String doRequest(String url) {
		return doRequest(url, RequestMethod.GET, null);
	}

	public static String get(String url) {
		return get(url, new HashMap<>()); //new hashmap b/c of adding auth token
	}

	public static String get(String url, Map<String, String> params) {
		return doRequest(GITHUB_API_BASE_URL + url + getParamString(params), RequestMethod.GET, null);
	}

	private static String getParamString(Map<String, String> params) {
		if (params.size() == 0) {
			return "";
		}
		List<String> partialParams = params.keySet().stream()
				.map(key -> encode(key) + "=" + encode(params.get(key)))
				.collect(Collectors.toList());
		return "?" + StringHelper.implode(partialParams, "&");
	}

}
