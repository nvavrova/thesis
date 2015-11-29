package process;

import util.StringHelper;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik on 11-11-2015
 *
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
				BufferedReader br = new BufferedReader(new FileReader(this.fileName));

				List<String> lines = br.lines().collect(Collectors.toList());
				lines.forEach(l -> this.processLine(l));

				br.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getLink(String diskLocation) {
		if (!this.links.containsKey(diskLocation)) {
			return "";
		}
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
