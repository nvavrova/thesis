package main;

import util.StringHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 11-11-2015
 * <p>
 * Takes path to the CSV file in format "project_disk_location,project_git_link"
 */
public class GitLocationProcessor {
	private final String fileName;
	private final Map<String, String> links;

	public GitLocationProcessor(String fileName) {
		this.fileName = fileName;
		this.links = new HashMap<>();
	}

	public void readData() {
		if (this.links.keySet().size() == 0) {
			try {
				FileInputStream fis = new FileInputStream(this.fileName);
				InputStreamReader inStrReader = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(inStrReader);

				List<String> lines = br.lines().collect(Collectors.toList());
				lines.forEach(l -> this.processLine(l));
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean hasLink(String diskLocation) {
		return this.links.containsKey(diskLocation);
	}

	public String getLink(String diskLocation) {
		assert this.links.containsKey(diskLocation);
		return this.links.get(diskLocation);
	}

	private void processLine(String line) {
		List<String> parts = StringHelper.explode(line, ",");
		if (parts.size() == 2) {
			this.addLink(parts.get(0), parts.get(1));
		}
	}

	private void addLink(String diskLocation, String link) {
		if (!this.links.containsKey(diskLocation)) {
			this.links.put(diskLocation, link);
		}
	}
}
