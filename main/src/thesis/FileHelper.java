package thesis;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
		return this.lines.subList(start, end);
	}

	private List<String> readFile() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.filePath));
			return bf.lines().collect(Collectors.toList());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static List<String> getPythonFilePaths(File folder) {
		return FileHelper.getFilePaths(folder)
				.stream()
				.filter(FileHelper::isPythonFile)
				.collect(Collectors.toList());
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
