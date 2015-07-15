package thesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 14-07-2015
 */
public class ModuleImports {

	private final Map<String, Map<String, String>> values; //importing filename -> (imported filename -> alias)

	public ModuleImports() {
		this.values = new HashMap<>();
	}

	public void add(String importingModule, String importedModule, String alias) {
		if (!this.values.containsKey(importingModule)) {
			this.values.put(importingModule, new HashMap<>());
		}
		this.values.get(importingModule).put(importedModule, alias);
	}

	public Set<String> getModules() {
		return this.values.keySet();
	}

	public Set<String> getImports(String importingModule) {
		assert (this.values.containsKey(importingModule));
		return this.values.get(importingModule).keySet();
	}

	public String getAlias(String importingModule, String importedModule) {
		assert (this.values.containsKey(importingModule));
		assert (this.values.get(importingModule).containsKey(importedModule));
		return this.values.get(importingModule).get(importedModule);
	}
}
