package vc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 31-07-2015
 */
public class StreamGobbler extends Thread {

	private final InputStream inputStream;
	private final List<String> streamValues;

	StreamGobbler(InputStream inputStream) {
		this.inputStream = inputStream;
		this.streamValues = new ArrayList<>();
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				this.streamValues.add(line);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public List<String> getStreamValues() {
		return this.streamValues;
	}
}
