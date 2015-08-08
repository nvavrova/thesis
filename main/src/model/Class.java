package model;

import helpers.Helper;

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
	private Boolean usesGlobals;

	private final Map<String, Class> dependentOn;

	private final List<String> parents;
	private final Map<String, Method> methods;
	private final Set<String> variables;
	private final Set<String> references;

	public Class(String name, Module module, Integer loc, List<String> parents) {
		this.name = name;
		this.module = module;

		this.loc = loc;
		this.usesGlobals = false;

		this.dependentOn = new HashMap<>();

		this.parents = parents;
		this.methods = new HashMap<>();
		this.variables = new HashSet<>();
		this.references = new HashSet<>();
	}


	public void linkVarToClass(String alias, Class c) {
		if (this.isAliasReferenced(alias)) {
			this.dependentOn.put(alias, c);
		}
	}

	private boolean isAliasReferenced(String alias) {
		return this.variables.contains(alias) || this.parents.contains(alias) || this.references.contains(alias);
	}

	public void registerGlobalUse() {
		this.usesGlobals = true;
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

	public void addReference(String name) {
		this.references.add(name);
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
		return 0; //TODO: implement
	}

	public Set<String> getVariables() {
		return this.variables;
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
		//TODO: add relations to Data Classes
		return (this.isLargeClass() || this.hasLowCohesion()) &&
				(this.hasControllerName() || this.hasControllerMethods());
		// && this.relatedDataClassesCount() > 1;
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
		//TODO: add the vars that are not declared global by a keyword but still are?
		return this.usesGlobals;
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
				.filter(c -> c.privateFieldsWithOneMethod())
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
