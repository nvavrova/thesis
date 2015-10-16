package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 15-10-2015
 */
public class FileOpener {

	private final String filePath;
	private final List<String> lines;

	public FileOpener(String filePath) {
		this.filePath = filePath;
		this.lines = this.read();
	}

	public List<String> getLines() {
		return this.lines;
	}

	public List<String> getLines(Integer start, Integer end) {
		end = Math.min(end, this.lines.size()); //in case last line is empty, when the BufferedReader doesn't collect it
		return this.lines.subList(start, end);
	}

	public String getTrimmedContents() {
		StringBuilder sb = new StringBuilder();
		this.lines.stream()
//				.filter(l -> l.trim().length() > 0)
				.forEach(l -> sb.append(l.trim().length() != 0 ? l : "").append("\n"));
		return sb.toString();
	}

	private List<String> read() {
		try {
			FileInputStream fis = new FileInputStream(this.filePath);
			InputStreamReader inStrReader = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(inStrReader);

			List<String> res = br.lines().collect(Collectors.toList());

			//hack to fix wrong encoding of ISO-8859-1
			if (res.size() > 1 && res.get(0).startsWith("\u00EF\u00BB\u00BF")) {
				res.set(0, res.get(0).substring(3));
			}

			fis.close();
			inStrReader.close();
			br.close();

			return res;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
