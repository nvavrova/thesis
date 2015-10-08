package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 06-05-2015
 */
public class FileHelper {

	public static final String PYTHON_EXTENSION = ".py";

	private final String filePath;
	private final List<String> lines;

	public FileHelper(String filePath) {
		this.filePath = filePath;
		this.lines = this.readFile();
	}

	public List<String> getFileLines() {
		return this.lines;
	}

	public List<String> getFileLines(Integer start, Integer end) {
		end = Math.min(end, this.lines.size()); //in case last line is empty, when the BufferedReader doesn't collect it
		return this.lines.subList(start, end);
	}

	public String getTrimmedFileContents() {
		StringBuilder sb = new StringBuilder();
		this.lines.stream()
//				.filter(l -> l.trim().length() > 0)
				.forEach(l -> sb.append(l.trim().length() != 0 ? l : "").append("\n"));
		return sb.toString();
	}

	private List<String> readFile() {
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

	public static String getLogName(String type) {
		return getRunFileName(type, "log");
	}

	public static String getRunFileName(String type, String extension) {
		File folder = new File("logs");
		if (!folder.exists()) {
			folder.mkdir();
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
		String date = dateFormat.format(now);
		return "logs/" + date + "_" + type + "." + extension;
	}

	public static List<String> getPythonFilePaths(List<String> paths) {
		return paths.stream()
				.filter(FileHelper::isPythonFile)
				.collect(Collectors.toList());
	}

	public static List<String> getPythonFilePaths(File folder) {
		return FileHelper.getFilePaths(folder)
				.stream()
				.filter(FileHelper::isPythonFile)
				.collect(Collectors.toList());
	}

	public static List<File> getSubfolders(File folder) {
		List<File> subfolders = new ArrayList<>();
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				subfolders.add(f);
			}
		}
		return subfolders;
	}

	private static List<String> getFilePaths(File folder) {
		List<String> fileNames = new ArrayList<>();

		for (File f : folder.listFiles()) {
			if (f.isFile()) {
				fileNames.add(f.getAbsolutePath());
			}
			else if (f.isDirectory()) {
				fileNames.addAll(FileHelper.getFilePaths(f));
			}
		}

		return fileNames;
	}

	private static boolean isPythonFile(String filePath) {
		return filePath.endsWith(PYTHON_EXTENSION);
	}
}
