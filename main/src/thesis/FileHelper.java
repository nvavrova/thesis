package thesis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik on 06-05-2015
 */
public class FileHelper {

	private final File folder;

	public FileHelper(File folder) {
		this.folder = folder;
	}

	public List<String> getPythonFilePaths() {
		return this.getFilePaths()
				.stream()
				.filter(this::isPythonFile)
				.collect(Collectors.toList());
	}

	public List<String> getFilePaths() {
		return this.getFilePaths(this.folder);
	}

	private List<String> getFilePaths(File folder) {
		List<String> fileNames = new ArrayList<>();

		for (File f : folder.listFiles()) {
			if (f.isFile()) {
				fileNames.add(f.getAbsolutePath());
			}
			else if (f.isDirectory()) {
				fileNames.addAll(getFilePaths(f));
			}
		}

		return fileNames;
	}

	private boolean isPythonFile(String filePath) {
		return filePath.endsWith(".py");
	}
}
