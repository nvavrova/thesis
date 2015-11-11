package main;

import util.FileOpener;
import util.StringHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (links.keySet().size() == 0) {
			FileOpener fileOpener = new FileOpener(this.fileName);
			List<String> lines = fileOpener.getLines();
			lines.forEach(l -> this.processLine(l));
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
