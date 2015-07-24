package model;

import thesis.Helper;

import java.util.*;

/**
 * Created by Nik on 30-06-2015
 */
//TODO: decide on the constants, limits, etc.
public class Class {

	private final String name;
	private final Module module;
	private final Integer loc;

	private final Set<Class> dependentOn;

	private final List<String> parents;
	private final Set<String> variables;
	private final List<Method> methods;
	private final Map<String, java.lang.Integer> methodPosition;
	private Boolean usesGlobals;

	public Class(String name, Module module, Integer loc, List<String> parents) {
		this.name = name;
		this.module = module;
		this.loc = loc;

		this.dependentOn = new HashSet<>();
		this.parents = parents;
		this.variables = new HashSet<>();
		this.methods = new ArrayList<>();
		this.methodPosition = new HashMap<>();
		this.usesGlobals = false;
	}

	public void addDependency(Class c) {
		this.dependentOn.add(c);
	}

	public void addAccessor(String name, Integer loc, List<String> params) {
		this.addMethod(name, loc, params, true);
	}

	public void addMethod(String name, Integer loc, List<String> params) {
		this.addMethod(name, loc, params, false);
	}

	public Boolean hasNoMethods() {
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

	public void addVariable(String varName, String methodName) {
		Method method = this.getMethod(methodName);
		method.addVarUse(varName);
		this.addVariable(varName);
	}

	public String getName() {
		return this.name;
	}

	public Boolean isBlob() {
		//TODO: add relations to Data Classes
		return (this.isLargeClass() || this.hasLowCohesion()) &&
				(this.hasControllerName() || this.hasControllerMethods());
		// && this.amountOfRelatedDataClasses() > 1;
	}

	public Boolean isSwissArmyKnife() {
		return this.hasTooManyParents();
	}

	public Boolean isFunctionalDecomposition() {
		return this.hasProceduralName() && this.noInheritance(); //TODO
	}

	public Boolean isSpaghettiCode() {
		return this.noInheritance() && this.hasProceduralName() && this.hasLongMethod()
				&& this.hasTooManyMethodsWithNoParams() && this.usesGlobals();
	}

	public java.lang.Integer getLoc() {
		return this.loc;
	}

	public java.lang.Integer getNumberOfAccessors() {
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

	public void registerGlobalUse() {
		this.usesGlobals = true;
	}

	public Boolean hasLowCohesion() {
		return this.calculateLcom() > 5;
	}

	public Boolean isLargeClass() {
		return this.loc > 800;
	}

	public java.lang.Integer getAmountOfMethods() {
		return this.methods.size();
	}

	public java.lang.Integer getAmountOfVariables() {
		return this.variables.size();
	}

	public java.lang.Integer getAmountOfPublicVariables() {
		Long count = this.variables.stream()
				.filter(v -> !v.startsWith("_"))
				.count();
		return count.intValue();
	}

	public java.lang.Integer getAmountOfPrivateVariables() {
		Long count = this.variables.stream()
				.filter(v -> v.startsWith("_"))
				.count();
		return count.intValue();
	}

	public Boolean isDataClass() {
		//TODO: figure out how to redefine a DataClass for Python
		return this.getAmountOfPublicVariables() > 10;
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

	public Boolean noInheritance() {
		return this.parents.size() == 0;
	}

	public Boolean lowAmountOfMethods() {
		return this.methods.size() <= 4;
	}

	public Boolean hasTooManyParents() {
		return this.parents.size() > 2;
	}

	public Boolean hasLongMethod() {
		for (Method m : this.methods) {
			if (m.isLongMethod()) {
				return true;
			}
		}
		return false;
	}

	public java.lang.Integer getNrOfMethodsWithNoParams() {
		Long count = this.methods.stream()
				.filter(e -> e.hasNoParams())
				.count();
		return count.intValue();
	}

	public Boolean hasTooManyMethodsWithNoParams() {
		return this.getNrOfMethodsWithNoParams() > 8;
	}

	public java.lang.Integer amountOfRelatedDataClasses() {
		Long count = this.dependentOn.stream()
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

		java.lang.Integer pos = this.methodPosition.get(name);
		return this.methods.get(pos);
	}

	private java.lang.Integer calculateLcom() {
		int intersect = 0;
		int nonIntersect = 0;
		for (int i = 0; i < this.methods.size(); i++) {
			for (int j = i + 1; j > i && j < methods.size(); j++) {
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
