package thesis;

import ast.LocInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nik on 11-07-2015
 */
public class Method {
	private final String name;
	private final LocInfo loc;
	private final Boolean isAccessor;
	private final Set<String> usedClassVars;

	public Method(String name, LocInfo loc, Boolean isAccessor) {
		this.name = name;
		this.loc = loc;
		this.isAccessor = isAccessor;
		this.usedClassVars = new HashSet<>();
	}

	public String getName() {
		return this.name;
	}

	public Integer getLoc() {
		return this.loc.getLocSpan();
	}

	public Set<String> getUsedClassVars() {
		return this.usedClassVars;
	}

	public void addVarUse(String varName) {
		this.usedClassVars.add(varName);
	}

	public Boolean isAccessor() {
		return this.isAccessor;
	}

	public Boolean isController() {
		for (String name : Helper.getControllerNames()) {
			if (this.name.contains(name)){
				return true;
			}
		}
		return false;
	}

	public Boolean hasVariableIntersection(Method m) {
		return !Helper.areSetsDisjoint(this.usedClassVars, m.getUsedClassVars());
	}
}
