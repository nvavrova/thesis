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

	private final Map<String, Class> dependentOn;

	private final List<String> parents;
	private final Set<String> references;
	private final Set<String> variables;
	private final List<Method> methods;
	private final Map<String, Integer> methodPosition;
	private Boolean usesGlobals;

	public Class(String name, Module module, Integer loc, List<String> parents) {
		this.name = name;
		this.module = module;
		this.loc = loc;

		this.dependentOn = new HashMap<>();
		this.parents = parents;
		this.references = new HashSet<>();
		this.variables = new HashSet<>();
		this.methods = new ArrayList<>();
		this.methodPosition = new HashMap<>();
		this.usesGlobals = false;
	}

	public void linkVarToClass(String alias, Class c) {
		if (this.isAliasReferenced(alias)) {
			this.dependentOn.put(alias, c);
		}
	}

	private boolean isAliasReferenced(String alias) {
		return this.variables.contains(alias) || this.parents.contains(alias) || this.references.contains(alias);
	}

	public void printDependencies() {
		System.out.println(this.getName() + " DEPENDENCIES");
		for (String a : this.dependentOn.keySet()) {
			System.out.println("\t" + a + "  ->  " + this.dependentOn.get(a).getName());
		}
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

	public List<String> getParents() {
		return this.parents;
	}

	public void addVariable(String varName) {
		this.variables.add(varName);
	}

//	public void addVariable(String varName, String methodName) {
//		Method method = this.getMethod(methodName);
//		method.addVarUse(varName);
//		this.addVariable(varName);
//	}

	public void addReference(String name) {
		this.references.add(name);
	}

	public String getName() {
		return this.name;
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

	public boolean privateFieldsWithOneMethod() {
		return this.privateVariablesCount() > 10 && this.methodCount() == 1;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer getNumberOfAccessors() {
		Long count = this.methods.stream()
				.filter(m -> m.isAccessor())
				.count();
		return count.intValue();
	}

	public Set<String> getVariables() {
		return this.variables;
	}

	public String getFilePath() {
		return this.module.getFilePath();
	}

	public Boolean usesGlobals() {
		//TODO: add the vars that are not declared global by a keyword but still are?
		return this.usesGlobals;
	}

	public Set<Class> getDependencies() {
		return this.dependentOn.values().stream().collect(Collectors.toSet());
	}

	public void registerGlobalUse() {
		this.usesGlobals = true;
	}

	public boolean hasLowCohesion() {
		return this.calculateLcom() > 5;
	}

	public boolean isLargeClass() {
		return this.loc > 800;
	}

	public Integer methodCount() {
		return this.methods.size();
	}

	public Integer variablesCount() {
		return this.variables.size();
	}

	public Integer publicVariablesCount() {
		Long count = this.getPublicVariablesStream().count();
		return count.intValue();
	}

	public Integer privateVariablesCount() {
		Long count = this.getPrivateVariablesStream().count();
		return count.intValue();
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

	public boolean isDataClass() {
		//TODO: figure out how to redefine a DataClass for Python
		return this.publicVariablesCount() > 10;
//		return this.getNumberOfAccessors() > 5;
	}

	public Boolean hasControllerName() {
		return Helper.isControllerName(this.name);
	}

	public Boolean hasProceduralName() {
		return Helper.isProceduralName(this.name);
	}

	public Boolean hasControllerMethods() {
		for (Method m : this.methods) {
			if (m.isController()) {
				return true;
			}
		}
		return false;
	}

	public boolean noInheritance() {
		return this.parents.size() == 0;
	}

	public boolean lowAmountOfMethods() {
		return this.methods.size() <= 4;
	}

	public boolean hasTooManyParents() {
		return this.parents.size() > 2;
	}

	public boolean hasLongMethod() {
		for (Method m : this.methods) {
			if (m.isLongMethod()) {
				return true;
			}
		}
		return false;
	}

	public Integer methodsWithNoParamsCount() {
		Long count = this.methods.stream()
				.filter(m -> m.hasNoParams())
				.count();
		return count.intValue();
	}

	public boolean hasTooManyMethodsWithNoParams() {
		return this.methodsWithNoParamsCount() > 8;
	}

	public Integer relatedPrivateFieldsWithOneMethodCount() {
		Long count = this.dependentOn.values().stream()
				.filter(c -> c.privateFieldsWithOneMethod())
				.count();
		return count.intValue();
	}

	public Integer relatedDataClassesCount() {
		Long count = this.dependentOn.values().stream()
				.filter(c -> c.isDataClass())
				.count();
		return count.intValue();
	}

	private void addMethod(String name, Integer loc, List<String> params, Boolean isAccessor) {
		Method method = new Method(name, loc, params, isAccessor);
		this.methodPosition.put(name, this.methods.size());
		this.methods.add(method);
	}

	private Method getMethod(String name) {
		assert (this.methodPosition.containsKey(name));

		Integer pos = this.methodPosition.get(name);
		return this.methods.get(pos);
	}

	private Integer calculateLcom() {
		int intersect = 0;
		int nonIntersect = 0;
		for (int i = 0; i < this.methods.size(); i++) {
			for (int j = i + 1; j > i && j < this.methods.size(); j++) {
				Method a = this.methods.get(i);
				Method b = this.methods.get(j);
				if (a.hasVariableIntersection(b)) {
					intersect++;
				}
				else {
					nonIntersect++;
				}
			}
		}
		return Math.max(0, nonIntersect - intersect);
	}
}
