package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 06-05-2015
 */
public class FileHelper {

	public static final String PYTHON_EXTENSION = ".py";

	public static void createLocation(String path) {
		File file = new File(path);
		if (!file.isDirectory()) {
			createLocation(file.getParent());
		}
		if (file.isDirectory() && !file.exists()) {
			createLocation(file.getParent());
			file.mkdir();
		}
	}

	public static String stampedFileName(String fileName, String extension) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
		String date = dateFormat.format(now);
		return date + "_" + fileName + "." + extension;
	}

	public static String stampedFileName(String directory, String fileName, String extension) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
		String date = dateFormat.format(now);
		return directory + "/" + date + "_" + fileName + "." + extension;
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
