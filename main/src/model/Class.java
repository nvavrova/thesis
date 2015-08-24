package model;

import util.Helper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Nik on 30-06-2015
 */
//TODO: decide on the constants, limits, etc.
public class Class {

	private final String name;
	private final Module module;

	private final Integer loc;

	private final Map<String, Class> dependentOn;

	private final List<String> parents;
	private final Map<String, Method> methods;
	private final Set<String> variables;
	private final Set<String> definedGlobals;
	private final Set<String> usedGlobals;
	private final Set<String> varReferences;
	private final Set<String> methodReferences;

	public Class(String name, Module module, Integer loc, List<String> parents) {
		this.name = name;
		this.module = module;

		this.loc = loc;

		this.dependentOn = new HashMap<>();

		this.parents = parents;
		this.methods = new HashMap<>();
		this.variables = new HashSet<>();
		this.definedGlobals = new HashSet<>();
		this.usedGlobals = new HashSet<>();
		this.varReferences = new HashSet<>();
		this.methodReferences = new HashSet<>();
	}

	public void addGlobal(String name) {
		this.definedGlobals.add(name);
		this.usedGlobals.add(name);
	}

	public void registerGlobals(Set<String> moduleVars) {
		this.registerClassDepGlobals();
		this.registerModuleDepGlobals(moduleVars);
	}

	public Integer getAccessorCount() {
		Long c = this.methods.values().stream()
				.filter(Method::isAccessor)
				.count();
		return c.intValue();
	}

	//TODO: check this again
	private void registerClassDepGlobals() {
//		for (String alias : this.dependentOn.keySet()) {
//			Class c = this.dependentOn.get(alias);
//		    this.addGlobalUsesFromClass(alias, c.getStaticVariables());
//		}
		for (String alias : this.dependentOn.keySet()) {
			Class c = this.dependentOn.get(alias);
			this.addGlobalUsesFromClass(alias, c.getStaticVariables());
			this.addGlobalDefinitions(alias, c);
		}
	}

	private void registerModuleDepGlobals(Set<String> moduleVars) {
		Set<String> globals = moduleVars.stream()
				.filter(this.varReferences::contains)
				.collect(Collectors.toSet());
		this.usedGlobals.addAll(globals);
	}

	private void addGlobalDefinitions(String alias, Class c) {
		this.definedGlobals.addAll(
				this.getInstanceVariables().stream()
						.filter(var -> c.referencesVar(alias + "." + var))
						.map(var -> "self." + var)
						.collect(Collectors.toSet())
		);
		this.definedGlobals.addAll(
				this.getStaticVariables().stream()
						.filter(var -> c.referencesVar(alias + "." + var))
						.map(var -> this.getName() + "." + var)
						.collect(Collectors.toSet())
		);
	}

	private boolean referencesVar(String varName) {
		return this.varReferences.contains(varName);
	}

	private void addGlobalUsesFromClass(String alias, Set<String> vars) {
		List<String> globals = vars.stream()
				.map(var -> alias + "." + var)
				.filter(this.varReferences::contains)
				.collect(Collectors.toList());
		this.usedGlobals.addAll(globals);
	}

	public void linkVarToClass(String alias, Class c) {
		if (this.isAliasReferenced(alias)) {
			this.dependentOn.put(alias, c);
		}
	}

	private boolean isAliasReferenced(String alias) {
		return this.variables.contains(alias) || this.parents.contains(alias)
				|| this.varReferences.contains(alias) || this.methodReferences.contains(alias);
	}

	public void addAccessor(String name, Integer loc, List<String> params) {
		this.addMethod(name, loc, params, true);
	}

	public void addMethod(String name, Integer loc, List<String> params) {
		this.addMethod(name, loc, params, false);
	}


	public boolean hasNoMethods() {
		return this.methods.size() == 0;
	}

	public Method getLastAddedMethod() {
		return this.methods.get(this.methods.size() - 1);
	}

	public void addVariable(String varName) {
		this.variables.add(varName);
	}

	public void addVarReference(String name) {
		this.varReferences.add(name);
	}

	public void addMethodReference(String name) {
		this.methodReferences.add(name);
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

	public Integer parentsCount() {
		return this.parents.size();
	}

	public Integer usedGlobalsCount() {
		return this.usedGlobals.size();
	}

	public Integer definedGlobalsCount() {
		return this.definedGlobals.size();
	}

	public Set<String> getInstanceVariables() {
		return this.getVarsStartingWith("self.");
	}

	public Set<String> getStaticVariables() {
		return this.getVarsStartingWith(this.name + ".");
	}

	private Set<String> getVarsStartingWith(String prefix) {
		Set<String> vars = new HashSet<>();
		for (String s : this.variables) {
			if (s.startsWith(prefix)) {
				s = s.replaceFirst(prefix, "");
				vars.add(s);
			}
		}
		return vars;
	}

	public Set<String> getVariables() {
		return this.variables;
	}

	public Set<String> getUsedGlobals() {
		return this.usedGlobals;
	}

	public Set<Class> getDependencies() {
		return this.dependentOn.values().stream().collect(Collectors.toSet());
	}

	public Integer methodCount() {
		return this.methods.size();
	}

	public Integer variablesCount() {
		return this.variables.size();
	}

	public Set<Method> getMethods() {
		return this.methods.values().stream().collect(Collectors.toSet());
	}

	public Integer methodsWithNoParamsCount() {
		Long count = this.methods.values().stream()
				.filter(m -> m.hasNoParams())
				.count();
		return count.intValue();
	}

	public Integer publicVariablesCount() {
		Long count = this.getPublicVariablesStream().count();
		return count.intValue();
	}

	public Integer privateVariablesCount() {
		Long count = this.getPrivateVariablesStream().count();
		return count.intValue();
	}


	public boolean isBlob() {
		return (this.isLargeClass() || this.hasLowCohesion()) &&
				(this.hasControllerName() || this.hasControllerMethods())
				&& this.relatedDataClassesCount() > 1;
	}

	public boolean isSwissArmyKnife() {
		return this.hasTooManyParents();
	}

	public boolean isFunctionalDecomposition() {
		return (this.relatedPrivateFieldsWithOneMethodCount() > 2) && (this.hasProceduralName() && this.noInheritance()); //TODO
	}

	public boolean isSpaghettiCode() {
		return this.noInheritance() && this.hasProceduralName() && this.hasLongMethod()
				&& this.hasTooManyMethodsWithNoParams() && this.usesGlobals();
	}


	private boolean isDataClass() {
		//TODO: figure out how to redefine a DataClass for Python
		return this.publicVariablesCount() > 10;
//		return this.getNumberOfAccessors() > 5;
	}

	private boolean privateFieldsWithOneMethod() {
		return this.privateVariablesCount() > 10 && this.methodCount() == 1;
	}

	private Integer getNumberOfAccessors() {
		Long count = this.methods.values().stream()
				.filter(m -> m.isAccessor())
				.count();
		return count.intValue();
	}

	private Boolean usesGlobals() {
		return this.usedGlobals.size() > 0;
	}

	private boolean hasLowCohesion() {
		return this.calculateLcom() > 5;
	}

	private boolean isLargeClass() {
		return this.loc > 800;
	}

	private boolean isPrivateVariable(String varName) {
		return varName.startsWith("_");
	}

	private Stream<String> getPrivateVariablesStream() {
		return this.variables.stream()
				.filter(v -> this.isPrivateVariable(v));
	}

	private Stream<String> getPublicVariablesStream() {
		return this.variables.stream()
				.filter(v -> !this.isPrivateVariable(v));
	}

	private Boolean hasControllerName() {
		return Helper.isControllerName(this.name);
	}

	private Boolean hasProceduralName() {
		return Helper.isProceduralName(this.name);
	}

	private Boolean hasControllerMethods() {
		for (Method m : this.methods.values()) {
			if (m.isController()) {
				return true;
			}
		}
		return false;
	}

	private boolean noInheritance() {
		return this.parents.size() == 0;
	}

	private boolean hasTooManyParents() {
		return this.parents.size() > 2;
	}

	private boolean hasLongMethod() {
		for (Method m : this.methods.values()) {
			if (m.isLongMethod()) {
				return true;
			}
		}
		return false;
	}

	private boolean hasTooManyMethodsWithNoParams() {
		return this.methodsWithNoParamsCount() > 8;
	}

	private Integer relatedPrivateFieldsWithOneMethodCount() {
		Long count = this.dependentOn.values().stream()
				.filter(Class::privateFieldsWithOneMethod)
				.count();
		return count.intValue();
	}

	private Integer relatedDataClassesCount() {
		Long count = this.dependentOn.values().stream()
				.filter(c -> c.isDataClass())
				.count();
		return count.intValue();
	}

	private void addMethod(String name, Integer loc, List<String> params, Boolean isAccessor) {
		Method method = new Method(name, this, loc, params, isAccessor);
		this.methods.put(name, method);
	}

	private Integer calculateLcom() {
		int intersectDifference = 0; // = not intersecting amount - intersecting amount
		List<Method> ms = this.methods.values().stream().collect(Collectors.toList());
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
