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

	protected final Map<String, Class> definedClasses;
	protected final Map<String, Subroutine> definedSubroutines;
	protected final VarDefinitions definedVars;

	public ContentDefinitions() {
		this.definedSubroutines = new HashMap<>();
		this.definedVars = new VarDefinitions();
		this.definedClasses = new HashMap<>();
	}

	public VarDefinitions getDefinedVarsInclParentsVars() {
		return this.definedVars;
	}

	public Set<Variable> getDefinedVariablesSet() {
		return this.definedVars.getAsSet();
	}

	public Set<Variable> getDefinedVariablesOfTypeSet(VarType varType) {
		return this.definedVars.getAsSetOfType(varType);
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
		this.definedVars.add(name, var);
	}
}
