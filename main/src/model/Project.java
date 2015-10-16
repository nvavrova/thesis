package model;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 03-08-2015
 */
public class Project {

	private final Map<String, Module> modules;
	private final File folder;
	private String version;

	public Project(File folder) {
		this.folder = folder;
		this.modules = new HashMap<>();
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public File getFolder() {
		return this.folder;
	}

	public String getName() {
		return this.folder.getName();
	}

	public String getPath() {
		return this.folder.getAbsolutePath();
	}

	public void registerModule(Module m) {
		String modulePath = m.getFilePath();
		this.modules.put(modulePath, m);
	}

	public boolean hasModule(String modulePath) {
		return this.modules.containsKey(modulePath);
	}

	public Module getModule(String modulePath) {
		assert (this.modules.containsKey(modulePath));

		return this.modules.get(modulePath);
	}

	public Set<Module> getModules() {
		return this.modules.values().stream().collect(Collectors.toSet());
	}

	public Set<Class> getClasses() {
		Set<Class> classes = new HashSet<>();
		this.modules.values().forEach(m -> classes.addAll(m.getClasses()));
		return classes;
	}

	public Set<Method> getMethods() {
		Set<Method> methods = new HashSet<>();
		this.modules.values().forEach(m -> m.getClasses().forEach(c -> methods.addAll(c.getMethods())));
		return methods;
	}

}
