package mining;

import helpers.StringHelper;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 29-07-2015
 */
public class GitHub {

	private final static String GITHUB_API_BASE_URL = "https://api.github.com";
	private final static String TOKEN = "0512dd0f48fd0b93ce1f09372c93cea8bd33167a";

	public static String doRequest(String url) {
		return doRequest(url, RequestMethod.GET, null);
	}

	public static String get(String url) {
		return get(url, new HashMap<>()); //not an empty collection, b/c of adding auth token later!
	}

	public static String get(String url, Map<String, String> params) {
		params.put("access_token", TOKEN);
		return doRequest(GITHUB_API_BASE_URL + url + getParamString(params), RequestMethod.GET, null);
	}

	public static String post(String url) {
		return post(url, "");
	}

	public static String post(String url, String urlParameters) {
		return doRequest(GITHUB_API_BASE_URL + url, RequestMethod.GET, urlParameters);
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

	private static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return s;
		}
	}


	private static String doRequest(String targetUrl, RequestMethod method, String urlParameters) {
		String response = null;
		HttpURLConnection connection = null;
		try {
			System.out.println("URL: " + targetUrl);
			URL url = new URL(targetUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method.name());

			if (method == RequestMethod.POST) {
				connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
				connection.setDoOutput(true);

				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();
			}

			Integer status = connection.getResponseCode();
			String msg = connection.getResponseMessage();
			System.out.println(status + ": " + msg);
			InputStream is = status == 200 ? connection.getInputStream() : connection.getErrorStream();
			if (status == 403) {

			}
			response = processInput(is);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response;
	}

	private static String processInput(InputStream inputStream) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
		}
		rd.close();
		return response.toString();
	}

	private static void checkLimit() throws InterruptedException {
		String limitJson = get("/rate_limit");
		JSONObject limit = new JSONObject(limitJson);
		JSONObject res = limit.getJSONObject("resources");
		JSONObject core = res.getJSONObject("core");
		Integer remaining = core.getInt("remaining");

		if (remaining == 0) {
			Long resetTime = core.getLong("reset");
			Long now = Instant.now().getEpochSecond();
			Long diff = resetTime - now;
			Thread.sleep(0, diff.intValue());
		}
	}


}
