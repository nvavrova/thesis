package model;

import java.util.Map;
import java.util.Set;

/**
 * Created by Nik on 26-10-2015
 */
public class Scope extends ContentDefinitions {

	public Scope() {}

	public Scope(ContentDefinitions definitions) {
		super();
		this.definedClasses.putAll(definitions.definedClasses);
		this.definedSubroutines.putAll(definitions.definedSubroutines);
		this.definedVars.putAll(definitions.definedVars);
	}

	public void addToScope(String prefix, ContentContainer container, Boolean passInstanceObjects) {
		this.addToScope(prefix, "self", container, passInstanceObjects);
	}

	public void addToScope(String prefix, String instancePrefix, ContentContainer container, Boolean passInstanceObjects) {
		this.addClassesToScope(prefix, container.definedClasses);
		this.addSubroutinesToScope(prefix, instancePrefix, container.definedSubroutines, passInstanceObjects);
		this.addVariablesToScope(prefix, instancePrefix, container.definedVars, passInstanceObjects);
	}

	private void addClassesToScope(String prefix, Map<String, Class> classes) {
		for (String defClsName : classes.keySet()) {
			Class cls = classes.get(defClsName);
			this.addClassToScope(prefix, defClsName, cls);
		}
	}

	public void addClassToScope(String prefix, String defClsName, Class cls) {
		String name = this.addPrefix(defClsName, prefix);
		if (!this.definedClasses.containsKey(name)) {
			this.definedClasses.put(name, cls);
		}
	}

	private void addSubroutinesToScope(String prefix, String instancePrefix, Map<String, Subroutine> subroutines, Boolean passInstanceObjects) {
		for (String defSubName : subroutines.keySet()) {
			Subroutine subroutine = subroutines.get(defSubName);
			String name = this.getAddedSubroutineName(subroutine.getSubroutineType(), defSubName, prefix, instancePrefix);
			if (!this.definedSubroutines.containsKey(name) && (passInstanceObjects || subroutine.getSubroutineType() != SubroutineType.INSTANCE_METHOD)) {
				this.definedSubroutines.put(name, subroutine);
			}
		}
	}

	private String getAddedSubroutineName(SubroutineType subroutineType, String defSubName, String prefix, String instancePrefix) {
		if (subroutineType == SubroutineType.FUNCTION) {
			return defSubName;
		}
		if (subroutineType == SubroutineType.INSTANCE_METHOD) {
			return this.addPrefix(defSubName, instancePrefix); //, true
		}
		if (subroutineType == SubroutineType.STATIC_METHOD) {
			return this.addPrefix(defSubName, prefix);
		}
		throw new IllegalArgumentException("Unknown subroutine type");
	}

	private void addVariablesToScope(String prefix, String instancePrefix, Map<String, Set<Variable>> variables, Boolean passInstanceObjects) {
		for (String defVarName : variables.keySet()) {
			Set<Variable> vars = variables.get(defVarName);
			for (Variable var : vars) {
				String name = this.getAddedVariableName(var.getVarType(), defVarName, prefix, instancePrefix);
				if (passInstanceObjects || var.getVarType() != VarType.INSTANCE) {
					this.addVariableDefinition(name, var);
				}
			}
		}
	}

	private String getAddedVariableName(VarType varType, String defVarName, String prefix, String instancePrefix) {
		if (varType == VarType.INSTANCE) {
			return this.addPrefix(defVarName, instancePrefix); //, true
		}
		if (varType == VarType.CLASS || varType == VarType.LOCAL || varType == VarType.GLOBAL) {
			return this.addPrefix(defVarName, prefix);
		}
		throw new IllegalArgumentException("Unknown variable type");
	}

	private boolean emptyPrefix(String prefix) {
		return prefix.equals("");
	}

	private String addPrefix(String name, String newPrefix) {
		return this.emptyPrefix(newPrefix) ? name : newPrefix + "." + name;
	}

	private String addPrefix(String name, String newPrefix, Boolean replaceEmptyWithSelf) {
		if (replaceEmptyWithSelf && this.emptyPrefix(newPrefix)) {
			return this.addPrefix(name, "self");
		}
		return this.addPrefix(name, newPrefix);
	}

	public Scope copy() {
		Scope scope = new Scope();
		scope.definedClasses.putAll(this.definedClasses);
		scope.definedSubroutines.putAll(this.definedSubroutines);
		scope.definedVars.putAll(this.definedVars);
		return scope;
	}
}
