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

	@Override
	public void addVariableDefinition(String varName) {
		if (this.wasVarDefinedInsideMethod(varName)) {
			this.definedVariables.add(varName);
		}
		else {
			//variable defined outside a method, meaning both class and instance variables are created
			this.definedVariables.add("self." + varName);
			this.definedVariables.add(this.name + "." + varName);
		}
	}

	private boolean wasVarDefinedInsideMethod(String varName) {
		return varName.startsWith("self.") || varName.startsWith(this.name + ".");
	}

	@Override
	public Set<String> getDefinedVariables() {
		Set<String> vars = super.getDefinedVariables();
		for (Method method : this.getDefinedMethods()) {
			Set<String> methodVars = method.getDefinedVariables();
			for (String methodVar : methodVars) {
				if (methodVar.startsWith("self.") || methodVar.startsWith(name + ".")) {
					vars.add(methodVar);
				}
			}
		}
		return vars;
	}


	private Boolean referencesVar(String varName) {
		return this.referencedVariables.contains(varName);
	}

	public void linkVarToClass(String alias, Class c) {
		if (this.isAliasReferenced(alias)) {
			this.dependentOn.put(alias, c);
		}
	}

	private Boolean isAliasReferenced(String alias) {
		return this.definedVariables.contains(alias) || this.parents.contains(alias)
				|| this.referencedVariables.contains(alias) || this.getCalledMethods().contains(alias);
	}

	public void markVarsAsClassVars(Set<String> upperClassVars) {
		Set<String> vars = new HashSet<>();
		vars.addAll(upperClassVars);
		vars.addAll(this.definedVariables);

		this.getDefinedMethods().forEach(m -> m.markVarsAsClassVars(vars));
		for (Class c : this.definedClasses.values()) {
			c.markVarsAsClassVars(vars);
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
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
