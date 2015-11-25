package mining;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nik on 18-08-2015
 */
public class RequestHandler {

	public static String doRequest(String targetUrl, RequestMethod method, String urlParameters) {
		String response = null;
		HttpURLConnection connection = null;
		try {
			connection = getHttpURLConnection(targetUrl, method);

			if (method == RequestMethod.POST) {
				writePostData(connection, urlParameters);
			}
			response = execute(connection);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response;
	}

	private static String execute(HttpURLConnection connection) throws IOException {
		Integer status = connection.getResponseCode();
		String msg = connection.getResponseMessage();
		System.out.println(status + ": " + msg);
		InputStream is = status == 200 ? connection.getInputStream() : connection.getErrorStream();
		return processInput(is);
	}

	private static void writePostData(HttpURLConnection connection, String urlParameters) throws IOException {
		connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
		connection.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
	}

	private static HttpURLConnection getHttpURLConnection(String targetUrl, RequestMethod method) throws IOException {
		System.out.println("URL: " + targetUrl);
		URL url = new URL(targetUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method.name());
		return connection;
	}

	private static String processInput(InputStream inputStream) throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader rd = new BufferedReader(isr);
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
		}
		rd.close();
		isr.close();
		return response.toString();
	}
}
