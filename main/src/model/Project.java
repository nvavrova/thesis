package model;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 03-08-2015
 */
public class Project {

	private final File folder;
	private final Map<String, Module> modules;
	private Set<String> modulesInThisVersion;

	public Project(File folder) {
		this.folder = folder;
		this.modules = new HashMap<>();
		this.modulesInThisVersion = new HashSet<>();
	}

	public String getName() {
		return this.folder.getName();
	}

	public String getFolder() {
		return this.folder.getAbsolutePath();
	}

	public void initVersion() {
		this.modulesInThisVersion = new HashSet<>();
	}

	public void cleanupVersion() {
		Set<String> keysToRemove = this.modules.keySet().stream()
				.filter(k -> !modulesInThisVersion.contains(k))
				.collect(Collectors.toSet());
		keysToRemove.forEach(this.modules::remove);
	}

	public void registerModule(Module m) {
		String modulePath = m.getFilePath();
		this.modules.put(modulePath, m);
		this.modulesInThisVersion.add(modulePath);
	}

	public boolean hasModule(String modulePath) {
		return this.modules.containsKey(modulePath);
	}

	public Module getModule(String modulePath) {
		assert (this.modules.containsKey(modulePath));

		return this.modules.get(modulePath);
	}

	public Collection<Module> getModules() {
		return this.modules.values();
	}

	public Set<Class> getClasses() {
		Set<Class> classes = new HashSet<>();
		this.modules.values().forEach(m -> classes.addAll(m.getClasses()));
		return classes;
	}

}
