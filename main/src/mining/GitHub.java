package mining;

import org.json.JSONObject;
import util.StringHelper;

import java.io.*;
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

	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String TOKEN_FILENAME = "token.txt";
	private static final String ENCODING = "UTF-8";

	public static String get(String url) {
		return get(url, new HashMap<>()); //not an empty collection, b/c of adding auth token later!
	}

	public static String get(String url, Map<String, String> params) {
		File tokenFile = new File(TOKEN_FILENAME);
		if (tokenFile.exists()) {
			try {
				FileReader fr = new FileReader(tokenFile);
				BufferedReader br = new BufferedReader(fr);
				params.put("access_token", br.readLine());
				br.close();
				fr.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return RequestHandler.doRequest(GITHUB_API_BASE_URL + url + getParamString(params), RequestMethod.GET, null);
	}

	public static String getParamString(Map<String, String> params) {
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
			return URLEncoder.encode(s, ENCODING);
		}
		catch (UnsupportedEncodingException e) {
			return s;
		}
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
