package util;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 15-10-2015
 */
public class FileOpener {

	private static final Integer SIZE_LIMIT_BYTES = 5*1024*1024;

	private final String filePath;
	private List<String> lines;

	private boolean read;

	public FileOpener(String filePath) {
		this.filePath = filePath;
		this.lines = Collections.emptyList();
		this.read = false;
	}

	public List<String> getLines() throws FileSizeLimitExceededException {
		this.read();
		return this.lines;
	}

	public List<String> getLines(Integer start, Integer end) throws FileSizeLimitExceededException {
		this.read();
		end = Math.min(end, this.lines.size()); //in case last line is empty, when the BufferedReader doesn't collect it
		return this.lines.subList(start, end);
	}

	public String getTrimmedContents() throws FileSizeLimitExceededException {
		this.read();
		StringBuilder sb = new StringBuilder();
		this.lines.stream()
//				.filter(l -> l.trim().length() > 0)
				.forEach(l -> sb.append(l.trim().length() != 0 ? l : "").append("\n"));
		return sb.toString();
	}

	private void read() throws FileSizeLimitExceededException {
		if (this.read) {
			return;
		}

		this.read = true;
		File f = new File(this.filePath);
		if (f.length() > SIZE_LIMIT_BYTES) {
			throw new FileSizeLimitExceededException("File \"" + this.filePath + "\" too large (" + f.length() + " bytes)");
		}
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

			this.lines = res;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class FileSizeLimitExceededException extends Exception {
		public FileSizeLimitExceededException(String message) {
			super(message);
		}
	}
}
