package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 31-10-2015
 */
public abstract class ContentDefinitions {

	//this is a set instead of a single variable
	//because of the special situation of defining a class and instance variable at the same time (with the same name)
	protected final Map<String, Set<Variable>> definedVars;
	protected final Map<String, Subroutine> definedSubroutines;
	protected final Map<String, Class> definedClasses;

	public ContentDefinitions() {
		this.definedSubroutines = new HashMap<>();
		this.definedVars = new HashMap<>();
		this.definedClasses = new HashMap<>();
	}

	public Map<String, Set<Variable>> getDefinedVarsInclParentsVars() {
		return this.definedVars;
	}

	public Set<Variable> getDefinedVariablesSet() {
		Set<Variable> defVars = new HashSet<>();
		this.definedVars.values().forEach(defVars::addAll);
		return defVars;
	}

	public Set<Variable> getDefinedVariablesOfTypeSet(VarType varType) {
		return this.getDefinedVariablesSet().stream()
				.filter(var -> var.getVarType() == varType)
				.collect(Collectors.toSet());
	}

	public Set<Subroutine> getDefinedSubroutinesSet() {
		return this.definedSubroutines.values().stream().collect(Collectors.toSet());
	}

	public Set<Class> getDefinedClassesInclSubclassesSet() {
		return this.getDefinedClassesInclSubclassesByName().values().stream().collect(Collectors.toSet());
	}

	public Map<String, Class> getDefinedClassesInclSubclassesByName() {
		Map<String, Class> classes = new HashMap<>();
		classes.putAll(this.definedClasses);
		for (ContentContainer cc : this.getChildren()) {
			Map<String, Class> childClasses = cc.getDefinedClassesInclSubclassesByName();
			for (String key : childClasses.keySet()) {
				classes.put(cc.getName() + '.' + key, childClasses.get(key));
			}
		}
		return classes;
	}

	public Set<ContentContainer> getChildren() {
		Set<ContentContainer> children = new HashSet<>();
		children.addAll(this.definedClasses.values());
		children.addAll(this.definedSubroutines.values());
		return children;
	}


	public void addVariableDefinition(Variable var) {
		this.addVariableDefinition(var.getName(), var);
	}

	protected void addVariableDefinition(String name, Variable var) {
		if (!this.definedVars.containsKey(name)) {
			this.definedVars.put(name, new HashSet<>());
		}
		if (!this.definedVarOfTypeExists(name, var.getVarType())) {
			this.definedVars.get(name).add(var);
		}
	}

	private boolean definedVarOfTypeExists(String name, VarType varType) {
		Set<Variable> sameNameVars = this.definedVars.get(name);
		for (Variable sameNameVar : sameNameVars) {
			if (sameNameVar.getVarType() == varType) {
				return true;
			}
		}
		return false;
	}
}
