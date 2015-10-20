package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 30-06-2015
 */
public class Class extends ContentContainer {

	private final String name;
	private final Module module;
	private final Integer loc;
	private final List<String> parents;
	private final Map<String, Class> dependentOn;

	public Class(String name, Module module, Integer loc, List<String> parents) {
		this.name = name;
		this.module = module;
		this.loc = loc;
		this.parents = parents;
		this.dependentOn = new HashMap<>();
	}

	public void registerGlobals(Set<String> moduleVars) {
		this.registerClassDepGlobals();
		this.registerModuleDepGlobals(moduleVars);
	}

	private void registerClassDepGlobals() {
		for (String alias : this.dependentOn.keySet()) {
			Class c = this.dependentOn.get(alias);
			this.addGlobalUsesFromClass(alias, c.getDefinedGlobals());
//			this.addGlobalUsesFromClass(alias, c.getStaticVariables());
//			this.addGlobalDefinitions(alias, c);
		}
	}

	private void registerModuleDepGlobals(Set<String> moduleVars) {
		Set<String> globals = moduleVars.stream()
				.filter(this.referencedVariables::contains)
				.collect(Collectors.toSet());
		this.referencedGlobals.addAll(globals);
	}

//	private void addGlobalDefinitions(String clsAlias, Class c) {
//		this.definedGlobals.addAll(
//				this.getInstanceVariables().stream()
//						.filter(var -> c.referencesVar(clsAlias + "." + var))
//						.map(var -> "self." + var)
//						.collect(Collectors.toSet())
//		);
//		this.definedGlobals.addAll(
//				this.getStaticVariables().stream()
//						.filter(var -> c.referencesVar(clsAlias + "." + var))
//						.map(var -> this.getName() + "." + var)
//						.collect(Collectors.toSet())
//		);
//	}
//
//	private Boolean referencesVar(String varName) {
//		return this.referencedVariables.contains(varName);
//	}

	private void addGlobalUsesFromClass(String alias, Set<String> vars) {
		List<String> globals = vars.stream()
				.map(var -> alias + "." + var)
				.filter(this.referencedVariables::contains)
				.collect(Collectors.toList());
		this.referencedGlobals.addAll(globals);
	}

	public void linkVarToClass(String alias, Class c) {
		if (this.isAliasReferenced(alias)) {
			this.dependentOn.put(alias, c);
		}
	}

	private Boolean isAliasReferenced(String alias) {
		return this.definedVariables.contains(alias) || this.parents.contains(alias)
				|| this.referencedVariables.contains(alias) || this.calledMethods.contains(alias);
	}

	public String getName() {
		return this.name;
	}

	public Module getModule() {
		return this.module;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer getLcom() {
		return this.calculateLcom();
	}

	public List<String> getParents() {
		return this.parents;
	}

	public Set<String> getInstanceVariables() {
		return this.getVarsStartingWith("self.");
	}

	public Set<String> getStaticVariables() {
		return this.getVarsStartingWith(this.name + ".");
	}

	public Set<Class> getDependencies() {
		return this.dependentOn.values().stream().collect(Collectors.toSet());
	}

	public Map<String, Class> getNamedDependencies() {
		return this.dependentOn;
	}

	public Integer parentsCount() {
		return this.parents.size();
	}

	private Set<String> getVarsStartingWith(String prefix) {
		Set<String> vars = new HashSet<>();
		for (String s : this.definedVariables) {
			if (s.startsWith(prefix)) {
				s = s.replaceFirst(prefix, "");
				vars.add(s);
			}
		}
		return vars;
	}

	private Boolean privateFieldsWithOnePublicMethod() {
		return //this.privateVariablesCount() > 10 &&
				this.publicMethodCount() == 1;
	}

	private Integer calculateLcom() {
		int intersectDifference = 0; // = not intersecting amount - intersecting amount
		List<Method> ms = this.definedMethods.values().stream().collect(Collectors.toList());
		for (int i = 0; i < ms.size(); i++) {
			for (int j = i + 1; j > i && j < ms.size(); j++) {
				intersectDifference += this.lcomIntersectDifference(ms.get(i), ms.get(j));
			}
		}
		return Math.max(0, intersectDifference);
	}

	private Integer lcomIntersectDifference(Method a, Method b) {
		return a.hasVariableIntersection(b) ? -1 : 1;
	}
}
