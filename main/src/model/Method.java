package model;

import util.Helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nik on 11-07-2015
 */
public class Method {
	private final String name;
	private final Class cls;
	private final Integer loc;
	private final Boolean isAccessor;
	private final Set<String> usedClassVars;
	private final List<String> params;

	public Method(String name, Class cls, Integer loc, List<String> params, Boolean isAccessor) {
		this.name = name;
		this.cls = cls;
		this.loc = loc;
		this.params = params;
		this.isAccessor = isAccessor;
		this.usedClassVars = new HashSet<>();
	}

	public String getName() {
		return this.name;
	}

	public Class getCls() {
		return this.cls;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer paramCount() {
		return this.params.size();
	}

	public Set<String> getUsedClassVars() {
		return this.usedClassVars;
	}

	public boolean isAccessor() {
		return this.isAccessor;
	}

	public boolean isController() {
		return Helper.isControllerName(this.name);
	}

	public boolean hasVariableIntersection(Method m) {
		return !Helper.areSetsDisjoint(this.usedClassVars, m.getUsedClassVars());
	}

	public boolean isLongMethod() {
		return this.loc > 100;
	}

	public boolean hasNoParams() {
		return this.params.size() == 0;
	}

	public boolean isPrivate() {
		return this.name.startsWith("__") && !this.name.endsWith("__");
	}
}
