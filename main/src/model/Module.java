package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 21-07-2015
 */
public class Module extends ContentContainer {

	private final String filePath;
	private final String name;
	private final String error;

	private final Map<String, Class> classImports;
	private final Map<String, Module> moduleImports;

	public Module(String filePath, String name, String error) {
		this.filePath = filePath;
		this.name = name;
		this.error = error;

		this.classImports = new HashMap<>();
		this.moduleImports = new HashMap<>();
	}

	public void addImport(Module m, String name) {
		this.moduleImports.put(name, m);
	}

	public void addImport(Class c, String name) {
		this.classImports.put(name, c);
	}

	public void resolveImportsAndDependencies() {
		for (Class c : this.getDefinedClasses()) {
			this.resolveClassImports(c);
			this.resolveModuleImports(c);
			this.resolveIntraModuleDependencies(c);
		}
	}

	public void resolveInstanceVarUse() {
		this.definedClasses.values().forEach(c -> c.markVarsAsClassVars(Collections.emptySet()));
	}

	public void resolveGlobalUse() {
		for (String alias : this.classImports.keySet()) {
			Class cls = this.classImports.get(alias);
			this.resolveClassGlobalUse(alias, cls);
		}
		for (String alias : this.getDefinedClassesByName().keySet()) {
			Class cls = this.getDefinedClassesByName().get(alias);
			this.resolveClassGlobalUse(alias, cls);
		}
		for (String alias : this.moduleImports.keySet()) {
			Module module = this.moduleImports.get(alias);
			this.resolveModuleGlobalUse(alias, module);
		}
		this.resolveGlobalReferences(this.definedGlobals);
	}

	private void resolveModuleGlobalUse(String alias, Module module) {
		//module globals = variables declared as global and any variable used outside of its own module
		Set<String> globals = module.getDefinedGlobals().stream()
				.map(v -> alias + "." + v)
				.collect(Collectors.toSet());
		globals.addAll(module.getDefinedVariables().stream()
				.map(v -> alias + "." + v)
				.collect(Collectors.toSet()));
		this.resolveGlobalReferences(globals);
	}

	private void resolveClassGlobalUse(String alias, Class cls) {
		//class globals = variables declared as global
		Set<String> globals = cls.getDefinedGlobals().stream()
				.map(v -> alias + "." + v)
				.collect(Collectors.toSet());
		//TODO: add a static (class) variables used outside of its own class as well?
//		globals.addAll(cls.getStaticVariables().stream()
//				.map(v -> alias + "." + v)
//				.collect(Collectors.toSet()));
		this.resolveGlobalReferences(globals);
	}

	private void resolveClassImports(Class c) {
		for (String alias : this.classImports.keySet()) {
			c.linkVarToClass(alias, this.classImports.get(alias));
		}
	}

	private void resolveModuleImports(Class c) {
		for (String alias : this.moduleImports.keySet()) {
			for (Class moduleClass : this.moduleImports.get(alias).getDefinedClasses()) {
				String fullAlias = alias + "." + moduleClass.getName();
				c.linkVarToClass(fullAlias, moduleClass);
			}
		}
	}

	private void resolveIntraModuleDependencies(Class c) {
		this.getDefinedClasses().stream()
				.filter(dep -> !c.equals(dep))
				.forEach(dep -> c.linkVarToClass(dep.getName(), dep));
	}

	public boolean containsClass(String name) {
		return this.getDefinedClassesByName().containsKey(name);
	}

	public Class getClass(String name) {
		assert (this.getDefinedClassesByName().containsKey(name));

		return this.getDefinedClassesByName().get(name);
	}

	public String getFilePath() {
		return this.filePath;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Module getModule() {
		return this;
	}

	public String getError() {
		return this.error;
	}
}
