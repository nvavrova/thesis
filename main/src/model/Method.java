package model;

import java.util.Collections;
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

	public Boolean isAccessor() {
		return this.isAccessor;
	}

	public Boolean hasVariableIntersection(Method m) {
		return !Collections.disjoint(this.usedClassVars, m.getUsedClassVars());
	}

	public Boolean isPrivate() {
		return this.name.startsWith("__") && !this.name.endsWith("__");
	}
}
