package model;

import thesis.FileHelper;
import thesis.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 11-07-2015
 */
public class Linker {

	private static final String MODULE_DELIMITER = ".";
	private static final String FILE_DELIMITER = "\\";

	private final Map<String, Module> modules;
	private final String projectFolder;

	public Linker(String projectFolder, Map<String, Module> modules) {
		this.modules = modules;
		this.projectFolder = projectFolder;

	}

	public void link() {
	}

	public void addImport(String source, String target, String alias) {
		assert (this.modules.containsKey(source));

		Module sourceModule = this.modules.get(source);
		List<String> sourcePaths = this.getSourcePaths(sourceModule.getPath());
		Boolean added = false;
		for (String sourcePath : sourcePaths) {
			String fullPath = sourcePath + "\\" + this.getFilePath(target);
			added = this.addModuleImport(sourceModule, fullPath, alias);
			if (added) {
				break;
			}
		}
		if (!added) {
			System.out.println("UNRESOLVED: \n\tsource: \t" + source + "\n\timport: \t" + target);
		}
	}

	public void addImport(String source, String importPath, String target, String alias) {
		assert (this.modules.containsKey(source));

		if (target.equals("*")) {
			this.addImportAll(source, importPath);
		}
		else {
			this.addImportSpecific(source, importPath, target, alias);
		}
	}

	private void addImportSpecific(String source, String importPath, String target, String alias) {
		Module sourceModule = this.modules.get(source);
		List<String> sourcePaths = this.getSourcePaths(sourceModule.getPath());

		String pathMiddle = StringHelper.swapDelimiter(importPath, MODULE_DELIMITER, FILE_DELIMITER);

		String modulePathEnd = pathMiddle + FILE_DELIMITER + this.getFilePath(target);
		String classPathEnd = pathMiddle + FileHelper.PYTHON_EXTENSION;
		String className = target;

		Boolean added = false;
		for (String path : sourcePaths) {
			String fullModulePath = path + FILE_DELIMITER + modulePathEnd;
			String fullClassPath = path + FILE_DELIMITER + classPathEnd;
			added = this.addModuleImport(sourceModule, fullModulePath, alias);
			if (!added) {
				added = this.addClassImport(sourceModule, fullClassPath, className, alias);
			}
			if (added) {
				break;
			}
		}

		if (!added) {
			System.out.println("UNRESOLVED: \n\tsource: \t" + source + "\n\timport: \t" + importPath + "   " + target);
		}
	}

	private void addImportAll(String source, String importPath) {
		Module sourceModule = this.modules.get(source);
		String path = this.getParentFolder(source);
		path += this.getFilePath(importPath);

		if (this.modules.containsKey(path)) {
			Module importedModule = this.modules.get(path);
			for (model.Class c : importedModule.getClasses()) {
				sourceModule.addImport(c, c.getName());
			}
		}
	}


	private Boolean addModuleImport(Module source, String path, String alias) {
		if (this.modules.containsKey(path)) {
			Module importedModule = this.modules.get(path);
			source.addImport(importedModule, alias);
			return true;
		}
		return false;
	}


	private List<String> getSourcePaths(String source) {
		assert (source.startsWith(this.projectFolder));

		String sourceFolder = this.getParentFolder(source);

		List<String> paths = new ArrayList<>();
		while (!sourceFolder.equals(this.projectFolder)) {
			paths.add(sourceFolder);
			sourceFolder = this.getParentFolder(sourceFolder);
		}
		paths.add(this.projectFolder);

		return paths;
	}


	private Boolean addClassImport(Module source, String importModule, String className, String alias) {
		if (this.modules.containsKey(importModule)) {
			Module m = this.modules.get(importModule);
			if (m.containsClass(className)) {
				source.addImport(m.getClass(className), alias);
				return true;
			}
		}
		return false;
	}

	private String getParentFolder(String source) {
		File f = new File(source);
		return f.getParent();
	}

	private String getFilePath(String module) {
		return StringHelper.swapDelimiter(module, MODULE_DELIMITER, FILE_DELIMITER) + FileHelper.PYTHON_EXTENSION;
	}

}