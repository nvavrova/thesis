package mining;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by Nik on 31-07-2015
 */
public class GitQueue {

	private final static String GITHUB_API_BASE_URL = "https://api.github.com";
	private final static String TOKEN = "0512dd0f48fd0b93ce1f09372c93cea8bd33167a";

	private final Queue<String> ids;
	private final Map<String, RequestMethod> methods;
	private final Map<String, String> urls;
	private final Map<String, Map<String, String>> urlParams;

	public GitQueue() {
		this.ids = new PriorityQueue<>();
		this.methods = new HashMap<>();
		this.urls = new HashMap<>();
		this.urlParams = new HashMap<>();
	}

	public void add(RequestMethod method, String url) {
		this.add(method, url, new HashMap<>());
	}

	public void add(RequestMethod method, String url, Map<String, String> urlParams) {
		urlParams.put("access_token", "");
		String id = UUID.randomUUID().toString();

		this.ids.add(id);
		methods.put(id, method);
		urls.put(id, url);
		this.urlParams.put(id, urlParams);
	}

	public void execute() {
		while (!ids.isEmpty()) {
			String id = ids.remove();
			this.doRequest(GITHUB_API_BASE_URL + urls.get(id) + "?access_token=0512dd0f48fd0b93ce1f09372c93cea8bd33167a", methods.get(id), null);
		}
	}

	private String doRequest(String targetUrl, RequestMethod method, String urlParameters) {
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
			response = this.processInput(is);
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

	private String processInput(InputStream inputStream) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
		}
		rd.close();
		return response.toString();
	}
}
