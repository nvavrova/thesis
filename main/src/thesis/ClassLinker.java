package thesis;

import java.io.File;
import java.util.*;

/**
 * Created by Nik on 11-07-2015
 */
//TODO: solve imports from the same folder, when referring to that folder by name
public class ClassLinker {
	
	private final static String MODULE_DELIMITER = ".";
	private final static String FILE_DELIMITER = "\\";
	
	private final Map<String, Map<String, Class>> resolvedImports;

	private final Map<String, Set<Class>> classesByModule;
	private final Map<Class, List<String>> classParents;
	private final ModuleImports moduleImports;
	private final ClassImports classImports;
	private final Map<Class, Set<String>> identifierUse;

	public ClassLinker() {
		this.classesByModule = new HashMap<>();
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
		this.resolveDependencies();
		System.out.println("linked");
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

		String importedModuleFullPath = StringHelper.swapDelimiter(importedModule, MODULE_DELIMITER, FILE_DELIMITER) + FileHelper.PYTHON_EXTENSION;

		String importedFullPath = this.getModuleFolder(importingModule) + FILE_DELIMITER + importedModuleFullPath;

		this.moduleImports.add(importingModule, importedFullPath, alias);
	}

	public void addClass(Class c, List<String> parents) {
		if (!this.classesByModule.containsKey(c.getFilePath())) {
			this.classesByModule.put(c.getFilePath(), new HashSet<>());
		}
		this.classesByModule.get(c.getFilePath()).add(c);

		this.classParents.put(c, parents);
	}

	private void linkModuleImports() {
		for (String importingModule : this.moduleImports.getModules()) {
			Set<String> importedModules = this.moduleImports.getImports(importingModule);
			for (String importedModule : importedModules) {
				String alias = this.moduleImports.getAlias(importingModule, importedModule);
				if (this.classesByModule.containsKey(importedModule)) {
					for (Class c : this.classesByModule.get(importedModule)) {
						this.addResolvedImport(importingModule, alias + MODULE_DELIMITER + c.getName(), c);
					}
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
		if (this.classesByModule.containsKey(importedModule)) {
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
			Map<String, Class> imports = this.resolvedImports.get(c.getFilePath());
			for (String alias : imports.keySet()) {
				if (alias.equals(parent)) {
					c.addParent(imports.get(alias));
					break;
				}
			}
		}
	}

	private void resolveDependencies() {
		for (Class c : this.identifierUse.keySet()) {
			Set<String> ids = this.identifierUse.get(c);
			if (this.resolvedImports.containsKey(c.getFilePath())) { //TODO: remove this check? should it always be resolved?
				Map<String, Class> imports = this.resolvedImports.get(c.getFilePath());
				for (String id : ids) {
					for (String importAlias : imports.keySet()) {
						if (id.startsWith(importAlias)) {
							c.addDependency(imports.get(importAlias));
							break;
						}
					}
				}
			}
		}
	}

	private String getImportedClassModule(String importingModule, String classPath) {
		List<String> s = StringHelper.explode(classPath, MODULE_DELIMITER);
		List<String> pathList = s.subList(0, s.size() - 1);

		String importingModuleFolder = this.getModuleFolder(importingModule);

		return importingModuleFolder + FILE_DELIMITER + StringHelper.implode(pathList, FILE_DELIMITER) + FileHelper.PYTHON_EXTENSION;
	}

	private String getImportedClassName(String classPath) {
		List<String> s = StringHelper.explode(classPath, MODULE_DELIMITER);
		return s.get(s.size() - 1);
	}

	private String getModuleFolder(String module) {
		File f = new File(module);
		return f.getParent();
	}
}