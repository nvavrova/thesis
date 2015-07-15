package thesis;

import java.io.File;
import java.util.*;

/**
 * Created by Nik on 11-07-2015
 */
public class ClassLinker {
	private final Map<String, Map<String, Class>> resolvedImports;

	private final Map<String, Set<Class>> classesByModule;
	private final Map<String, Set<Class>> classesByName;
	private final Map<Class, List<String>> classParents;
	private final ModuleImports moduleImports;
	private final ClassImports classImports;
	private final Map<Class, Set<String>> identifierUse;

	public ClassLinker() {
		this.classesByModule = new HashMap<>();
		this.classesByName = new HashMap<>();
		this.classParents = new HashMap<>();
		this.moduleImports = new ModuleImports();
		this.classImports = new ClassImports();
		this.identifierUse = new HashMap<>();

		this.resolvedImports = new HashMap<>();
	}

	public void link() {
		this.linkClassImports();
		this.linkModuleImports();
		this.linkParents();
	}

	public void addIdentifier(Class c, String id) {
		if (!this.identifierUse.containsKey(c)) {
			this.identifierUse.put(c, new HashSet<>());
		}
		this.identifierUse.get(c).add(id);
	}

	public void addClassImport(String importingModule, String importedClass, String alias) {
		if (alias == null) {
			alias = importedClass;
		}

		String importedModule = this.getImportedClassModule(importingModule, importedClass);
		String importedClassName = this.getImportedClassName(importedClass);

		this.classImports.add(importingModule, importedModule, importedClassName, alias);
	}

	public void addModuleImport(String importingModule, String importedModule, String alias) {
		if (alias == null) {
			alias = importedModule;
		}

		String importedModuleFullPath = StringHelper.swapDelimiter(importedModule, ".", "\\") + FileHelper.PYTHON_EXTENSION;

		String importedFullPath = this.getModuleFolder(importingModule) + "\\" + importedModuleFullPath;

		this.moduleImports.add(importingModule, importedFullPath, alias);
	}

	public void addClass(Class c, List<String> parents) {
		if (!this.classesByModule.containsKey(c.getFilePath())) {
			this.classesByModule.put(c.getFilePath(), new HashSet<>());
		}
		this.classesByModule.get(c.getFilePath()).add(c);

		if (!this.classesByName.containsKey(c.getName())) {
			this.classesByName.put(c.getName(), new HashSet<>());
		}
		this.classesByName.get(c.getName()).add(c);

		this.classParents.put(c, parents);
	}

	private void linkModuleImports() {
		for (String importingModule : this.moduleImports.getModules()) {
			Set<String> importedModules = this.moduleImports.getImports(importingModule);
			for (String importedModule : importedModules) {
				String alias = this.moduleImports.getAlias(importingModule, importedModule);
				for (Class c : this.classesByModule.get(importedModule)) {
					this.addResolvedImport(importingModule, alias + "." + c.getName(), c);
				}
			}
		}
	}

	private void linkClassImports() {
		for (String importingModule : this.classImports.getImportingModules()) {
			for (String importedModule : this.classImports.getImportedModules(importingModule)) {
				this.linkClassImports(importingModule, importedModule);
			}
		}
	}

	private void linkClassImports(String importingModule, String importedModule) {
		Set<String> classes = this.classImports.getClasses(importingModule, importedModule);
		Set<Class> importedModuleClasses = this.classesByModule.get(importedModule);
		for (String className : classes) {
			String alias = this.classImports.getAlias(importingModule, importedModule, className);

			for (Class c : importedModuleClasses) {
				if (c.getName().equals(className)) {
					this.addResolvedImport(importingModule, alias, c);
					break;
				}
			}
		}
	}

	private void addResolvedImport(String importingModule, String alias, Class c) {
		if (!this.resolvedImports.containsKey(importingModule)) {
			this.resolvedImports.put(importingModule, new HashMap<>());
		}
		this.resolvedImports.get(importingModule).put(alias, c);
	}

	private void linkParents() {
		for (Class c : this.classParents.keySet()) {
			for (String parent : this.classParents.get(c)) {
				this.linkParent(c, parent);
			}
		}
	}

	private void linkParent(Class c, String parent) {
		if (this.resolvedImports.containsKey(c.getFilePath())) {
			Map<String, Class> imp = this.resolvedImports.get(c.getFilePath());
			for (String alias : imp.keySet()) {
				if (alias.equals(parent)) {
					c.addParent(imp.get(alias));
					break;
				}
			}
		}
	}

	private String getImportedClassModule(String importingModule, String classPath) {
		List<String> s = StringHelper.explode(classPath, ".");
		List<String> pathList = s.subList(0, s.size() - 1);

		String importingModuleFolder = this.getModuleFolder(importingModule);

		return importingModuleFolder + "\\" + StringHelper.implode(pathList, "\\") + FileHelper.PYTHON_EXTENSION;
	}

	private String getImportedClassName(String classPath) {
		List<String> s = StringHelper.explode(classPath, ".");
		return s.get(s.size() - 1);
	}

	private String getModuleFolder(String module) {
		File f = new File(module);
		return f.getParent();
	}


}
