package scraper;
import java.util.Map;

/**
 * Created by Nik on 29-09-2015
 */
public class GitHubRepo {

	private static final String HOSTNAME = "https://github.com/";

	private final String url;
	private String name;
	private String owner;
	private Map<String, Long> languageUsage;

	public GitHubRepo(String url) {
		this.url = url;
		this.owner = this.getUrlPart(0);
		this.name = this.getUrlPart(1);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Boolean isPythonRepo() {
		// no other language has more bytes in this project
		if (this.languageUsage == null || !this.languageUsage.containsKey("Python")) {
			return false;
		}
		Long pythonBytes = this.languageUsage.get("Python");
		for (String lang : this.languageUsage.keySet()) {
			if (this.languageUsage.get(lang) >= pythonBytes && !lang.equals("Python")) {
				return false;
			}
		}
		return true;
	}

	public void setLanguageUsage(Map<String, Long> languageUsage) {
		this.languageUsage = languageUsage;
	}

	public String getUrl() {
		return HOSTNAME + this.owner + "/" + this.name;
	}

	private String getUrlPart(int index) {
		String[] parts = this.stripPrefix().split("/");
		return parts.length >= (index + 1) ? parts[index] : "";
	}

	private String stripPrefix() {
		return this.url.replace(HOSTNAME, "");
	}

}
