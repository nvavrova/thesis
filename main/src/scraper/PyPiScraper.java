package scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 10-11-2015
 */
public class PyPiScraper {

	private static final String PYPI_PREFIX = "https://pypi.python.org";
	private static final String GIT_HUB_URL = "https://github.com/";

	private final List<String> urlsToCheck;

	public PyPiScraper(List<String> urlsToCheck) {
		this.urlsToCheck = urlsToCheck;
	}

	public List<GitHubRepo> getAllGitRepoLinks() {
		Set<String> allGitRepoLinks = new HashSet<>();
		for (String url : this.urlsToCheck) {
			String strResp = TrafficHandler.performGet(url);
			Document doc = Jsoup.parse(strResp, url);
			List<String> packageUrls = this.processPyPiListHtml(doc.child(0));
			for (String packageUrl : packageUrls) {
				allGitRepoLinks.addAll(
						this.processPyPiPackage(packageUrl).stream()
								.map(this::cleanLink)
								.filter(l -> l != null)
								.collect(Collectors.toSet())
				);
			}
		}
		allGitRepoLinks.forEach(System.out::println);
		return allGitRepoLinks.stream().map(rl -> new GitHubRepo(rl)).collect(Collectors.toList());
	}

	private String cleanLink(String link) {
		String partialLink = link.replaceFirst(GIT_HUB_URL, "");
		List<String> parts = Arrays.asList(partialLink.split("/"));
		if (parts.size() >= 2) {
			return GIT_HUB_URL + parts.get(0) + "/" + parts.get(1);
		}
		return null;
	}

	private Set<String> processPyPiPackage(String piPyPackageUrl) {
		String strResp = TrafficHandler.performGet(piPyPackageUrl);
		Set<String> links = new HashSet<>();
		if (strResp != null) {
			Document doc = Jsoup.parse(strResp, piPyPackageUrl);
			Element body = doc.child(0);
			Elements linkElems = body.getElementsByAttributeValueContaining("href", GIT_HUB_URL);
			links.addAll(linkElems.stream()
					.map(e -> e.attr("href"))
					.collect(Collectors.toSet()));
		}
		else {
			System.err.println("REQUEST TO [" + piPyPackageUrl + "] FAILED");
		}
		return links;
	}

	private List<String> processPyPiListHtml(Node n) {
		Element body = (Element) this.findChildByName(n, "body");
		Element tableParent = body.getElementById("content-body")
				.getElementById("body-main")
				.getElementById("content")
				.child(2);
		return this.processPyPiListTable(tableParent.child(3));
	}

	private List<String> processPyPiListTable(Element n) {
		List<String> urls = new ArrayList<>();
		Element tbody = n.child(0);
		for (Element el : tbody.children()) {
			if (el.hasClass("odd") || el.hasClass("even")) {
				Element link = el.child(0).child(0);
				urls.add(PYPI_PREFIX + link.attr("href"));
			}
		}
		return urls;
	}

	private Node findChildByName(Node parent, String name) {
		for (Node n : parent.childNodes()) {
			if (n.nodeName().equals(name)) {
				return n;
			}
		}
		return null;
	}

}
