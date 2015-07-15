package thesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 14-07-2015
 */
public class ClassImports {

	private Map<String, String> aliases; //importing module -> alias
	private Map<String, String> classes; //alias + importing module -> class
	private Map<String, String> modules; //alias + importing module -> imported module

	private final Map<String, Map<String, Map<String, String>>> values; //importing filename -> (imported filename -> (class name -> alias))

	public ClassImports() {
		this.values = new HashMap<>();
	}

	public void add(String importingModule, String importedClassModule, String importedClassName, String alias) {
		this.getInsertMap(importingModule, importedClassModule).put(importedClassName, alias);
//		this.getInsertMap(importingModule, importedClassName).put(alias, importedClassModule);
	}

	public Set<String> getImportingModules() {
		return this.values.keySet();
	}

	public Set<String> getImportedModules(String importingModule) {
		assert (this.values.containsKey(importingModule));

		return this.values.get(importingModule).keySet();
	}

	public Set<String> getClasses(String importingModule, String importedModule) {
		assert (this.values.containsKey(importingModule));
		assert (this.values.get(importingModule).containsKey(importedModule));

		return this.values.get(importingModule).get(importedModule).keySet();
	}

	public String getAlias(String importingModule, String importedModule, String className) {
		assert (this.values.containsKey(importingModule));
		assert (this.values.get(importingModule).containsKey(importedModule));
		assert (this.values.get(importingModule).get(importedModule).containsKey(className));

		return this.values.get(importingModule).get(importedModule).get(className);
	}

	public Map<String, String> getAliasedClasses(String importingModule, String importedModule) {
		assert (this.values.containsKey(importingModule));
		assert (this.values.get(importingModule).containsKey(importedModule));

		return this.values.get(importingModule).get(importedModule);
	}

	private Map<String, String> getInsertMap(String importingModule, String classModule) {
		Map<String, Map<String, String>> insMap = this.getInsertMap(importingModule);
		if (!insMap.containsKey(classModule)) {
			insMap.put(classModule, new HashMap<>());
		}
		return insMap.get(classModule);
	}

	private Map<String, Map<String, String>> getInsertMap(String importingModule) {
		if (!this.values.containsKey(importingModule)) {
			this.values.put(importingModule, new HashMap<>());
		}
		return this.values.get(importingModule);
	}
}
