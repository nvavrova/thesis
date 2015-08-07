package model;

import java.util.*;

/**
 * Created by Nik on 21-07-2015
 */
public class Module {

	private final String filePath;
	private final String name;
	private final String error;
	private final Map<String, Class> classes;
	private final Map<String, Class> classImports;
	private final Map<String, Module> moduleImports;

	public Module(String filePath, String name, String error) {
		this.filePath = filePath;
		this.name = name;
		this.error = error;
		this.classes = new LinkedHashMap<>();
		this.classImports = new HashMap<>();
		this.moduleImports = new HashMap<>();
	}

	public void link() {
		for (Class c : this.classes.values()) {
			this.resolveClassImports(c);
			this.resolveModuleImports(c);
			this.resolveIntraModuleDependencies(c);
		}
	}

	private void resolveClassImports(Class c) {
		for (String alias : this.classImports.keySet()) {
			c.linkVarToClass(alias, this.classImports.get(alias));
		}
	}

	private void resolveModuleImports(Class c) {
		for (String alias : this.moduleImports.keySet()) {
			for (Class moduleClass : this.moduleImports.get(alias).getClasses()) {
				String fullAlias = alias + "." + moduleClass.getName();
				c.linkVarToClass(fullAlias, moduleClass);
			}
		}
	}

	private void resolveIntraModuleDependencies(Class c) {
		this.classes.values().stream()
				.filter(dep -> !c.equals(dep))
				.forEach(dep -> c.linkVarToClass(dep.getName(), dep));
	}

	public void addImport(Module m, String name) {
		this.moduleImports.put(name, m);
	}

	public void addImport(Class c, String name) {
		this.classImports.put(name, c);
	}

	public void registerClass(Class c) {
		this.classes.put(c.getName(), c);
	}

	public boolean containsClass(String name) {
		return this.classes.containsKey(name);
	}

	public boolean containsError() {
		return this.error != null;
	}

	public Class getClass(String name) {
		assert (this.classes.containsKey(name));

		return this.classes.get(name);
	}

	public Collection<Class> getClasses() {
		return this.classes.values();
	}

	public String getFilePath() {
		return this.filePath;
	}

	public String getName() {
		return this.name;
	}

	public String getError() {
		return this.error;
	}
}
