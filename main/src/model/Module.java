package model;

import java.util.*;

/**
 * Created by Nik on 21-07-2015
 */
public class Module {

	private final String filePath;
	private final String name;
	private final Map<String, Class> classes;
	private final Map<String, Class> classImports;
	private final Map<String, Module> moduleImports;

	public Module(String filePath, String name) {
		this.filePath = filePath;
		this.name = name;
		this.classes = new LinkedHashMap<>();
		this.classImports = new HashMap<>();
		this.moduleImports = new HashMap<>();
	}

	public void addImport(Module m, String name) {
		this.moduleImports.put(name, m);
	}

	public void addImport(Class c, String name) {
		this.classImports.put(name, c);
	}

	public void addClass(Class c) {
		this.classes.put(c.getName(), c);
	}

	public Boolean containsClass(String name) {
		return this.classes.containsKey(name);
	}

	public Class getClass(String name) {
		assert (this.classes.containsKey(name));

		return this.classes.get(name);
	}

	public Collection<Class> getClasses() {
		return this.classes.values();
	}

	public Class getLastClass() {
		return this.classes.get(this.classes.size() - 1);
	}

	public String getFilePath() {
		return this.filePath;
	}

	public String getName() {
		return this.name;
	}

	public String getFullPath() {
		return this.filePath + "\\" + this.name;
	}
}
