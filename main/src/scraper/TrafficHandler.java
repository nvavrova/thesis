package scraper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nik on 12-08-2015
 */
public class TrafficHandler {

	public static String performGet(String targetUrl) {
		String response = null;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(targetUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			Integer status = connection.getResponseCode();
//			String msg = connection.getResponseMessage();
			InputStream is = status == 200 ? connection.getInputStream() : connection.getErrorStream();
			if (status == 200) {
				System.out.println("URL [" + targetUrl + "] PASSED");
			}
			else {
				System.err.println("URL [" + targetUrl + "] FAILED: (" + status + ") ");
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
