package model;

import java.util.*;

/**
 * Created by Nik on 11-07-2015
 */
public class Method extends ContentContainer {
	private final String name;
	private final Class cls;
	private final Integer loc;
	private final Boolean isAccessor;
	private final List<String> params;

	private final Set<String> usedInstanceVars;
	private final Set<String> usedNonInstanceVars;
	private final Set<String> potentialUsedNonInstanceVars;

	private final Map<String, Set<Class>> classInstances;

	private final Map<String, Set<String>> assigns;

	public Method(String name, Class cls, Integer loc, List<String> params, Boolean isAccessor) {
		this.name = name;
		this.cls = cls;
		this.loc = loc;
		this.params = params;
		this.isAccessor = isAccessor;

		this.usedInstanceVars = new HashSet<>();
		this.usedNonInstanceVars = new HashSet<>();
		this.potentialUsedNonInstanceVars = new HashSet<>();

		this.classInstances = new HashMap<>();

		this.assigns = new HashMap<>();
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

	public Integer getAid() {
		//Access of Import Data: Number of data members accessed in a method directly or via accessor-methods, from which the definition-class of the method is not derived.
		//TODO: add used accessors
		return this.usedNonInstanceVars.size();
	}

	public Integer getAld() {
		//Access of Local Data: Number of the data members accessed in the given method, which are local to the class where the method is defined.
		return this.getUsedInstanceVars().size();
	}

	public Integer paramCount() {
		return this.params.size();
	}

	public Set<String> getUsedInstanceVars() {
		return this.usedInstanceVars;
	}

	public Set<String> getUsedNonInstanceVars() {
		return this.usedNonInstanceVars;
	}

	public Boolean isAccessor() {
		return this.isAccessor;
	}

	public Boolean hasVariableIntersection(Method m) {
		return !Collections.disjoint(this.usedInstanceVars, m.getUsedInstanceVars());
	}

	public Boolean isPrivate() {
		return this.name.startsWith("__") && !this.name.endsWith("__");
	}

	public void addClassInstance(String var, Class c) {
		if (!this.classInstances.containsKey(var)) {
			this.classInstances.put(var, new HashSet<>());
		}
		this.classInstances.get(var).add(c);
	}

	public void addInstanceVarUse(String var) {
		this.usedInstanceVars.add(var);
	}

	public void addPotentialNonInstanceVarUse(String var) {
		this.potentialUsedNonInstanceVars.add(var);
	}

	public void addAssign(String target, String source) {
		if (!this.assigns.containsKey(target)) {
			this.assigns.put(target, new HashSet<>());
		}
		this.assigns.get(target).add(source);
	}

	public void resolveClassInstances() {

	}

	public void resolveNonClassVarUsage() {
		Map<String, Class> classDependencies = this.cls.getNamedDependencies();
		for (String varName : this.potentialUsedNonInstanceVars) {
			for (String classAlias : classDependencies.keySet()) {
				if (varName.startsWith(classAlias)) {
					this.usedNonInstanceVars.add(varName);
					break;
				}
			}
		}

		//TODO: add indirect references - references to variables of an instance


		this.potentialUsedNonInstanceVars.clear();
	}
}
