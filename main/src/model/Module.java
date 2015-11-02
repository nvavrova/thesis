package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 21-07-2015
 */
public class Module extends ContentContainer {

	private final Map<String, Class> classImports;
	private final Map<String, Module> moduleImports;

	private final String filePath;
	private final String error;

	public Module(String name, String filePath, String error) {
		super(name);

		this.filePath = filePath;
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

	public void resolveInheritance() {
		Scope scope = this.buildScope();
		this.definedClasses.values().forEach(c -> c.resolveInheritance(scope));
	}

	public void resolveInheritedItems() {
		this.definedClasses.values().forEach(Class::copyParentVars);
	}

	public void resolveDependencies() {
		Scope scope = this.buildScope();
		this.getChildren().forEach(c -> c.resolveDependencies(scope));
	}

	private Scope buildScope() {
		Scope scope = new Scope();
		scope.addToScope("", this, true);
		for (String clsAlias : this.classImports.keySet()) {
			scope.addClassToScope("", clsAlias, this.classImports.get(clsAlias));
		}
		for (String moduleAlias : this.moduleImports.keySet()) {
			scope.addToScope(moduleAlias, this.moduleImports.get(moduleAlias), false);
		}
		return scope;
	}

	public boolean containsClass(String name) {
		return this.getDefinedClassesInclSubclassesByName().containsKey(name);
	}

	public Class getClass(String name) {
		assert (this.getDefinedClassesInclSubclassesByName().containsKey(name));

		return this.getDefinedClassesInclSubclassesByName().get(name);
	}

	public String getFilePath() {
		return this.filePath;
	}

	public String getError() {
		return this.error;
	}

	@Override
	public void resolveDependencies(Scope scope) {
	}

	@Override
	public boolean isInParentLine(ContentContainer container) {
		return this.equals(container);
	}
}
