package model;

import thesis.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 21-07-2015
 */
public class ImportResolver {

	private final String projectRoot;
	private final Map<String, Module> modules;
	private final List<String> recordedDirs;

	public ImportResolver(String projectRoot, Map<String, Module> modules) {
		this.projectRoot = projectRoot;
		this.modules = modules;
		this.recordedDirs = new ArrayList<>();
	}

	public Module resolveImport(Module source, String target) {
		if (target.equals(this.projectRoot)) {
			return null;
		}

		File sourceFile = new File(source.getFullPath());
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException();
		}

		List<String> targetParts = StringHelper.explode(target, ".");
		String packageName = sourceFile.getParent() + "\\" + StringHelper.swapDelimiter(target, ".", "\\");
		File curPackage = new File(packageName);
		if (this.isRegularPackage(curPackage)) {
			//TODO: ?
			return null;
		}

		String moduleName = packageName + ".py";
		File curFile = new File(moduleName);
		if (curFile.exists()) {
			return this.modules.get(moduleName);
		}

		return this.resolveImport(source, curFile.getParent());
	}

	private Boolean isRegularPackage(File f) {
		if (f.exists() && f.isDirectory()) {
			return this.containsInitFile(f);
		}
		return false;
	}

	private Boolean containsInitFile(File f) {
		File initFile = new File(f.getAbsolutePath() + "\\__init__.py");
		return initFile.exists();
	}
}
