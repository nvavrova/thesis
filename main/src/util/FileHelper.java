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

	public List<String> getFileContents() {
		return this.lines;
	}

	public List<String> getFileContents(Integer start, Integer end) {
		end = Math.min(end, this.lines.size()); //in case last line is empty, when the BufferedReader doesn't collect it
		return this.lines.subList(start, end);
	}

	private List<String> readFile() {
		try {
			FileReader fr = new FileReader(this.filePath);
			BufferedReader br = new BufferedReader(fr);
			List<String> res = br.lines().collect(Collectors.toList());
			fr.close();
			br.close();

			return res;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static String getLogName(String type) {
		File folder = new File("logs");
		if (!folder.exists()) {
			folder.mkdir();
		}

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
		String date = dateFormat.format(now);
		return "logs/" + date + "_" + type + "_log.txt";
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
