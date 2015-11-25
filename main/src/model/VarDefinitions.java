package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 02-11-2015
 */
public class VarDefinitions implements Unlinkable {

	//this is a set instead of a single variable
	//because of the special situation of defining a class and instance variable at the same time (with the same name)
	private final Map<String, Set<Variable>> vars;

	public VarDefinitions() {
		this.vars = new HashMap<>();
	}

	public void add(Variable var) {
		this.add(var.getName(), var);
	}

	public void add(String alias, Variable var) {
		this.add(alias, var, false);
	}

	public void addUnrestricted(Variable var) {
		this.addUnrestricted(var.getName(), var);
	}

	public void addUnrestricted(String alias, Variable var) {
		this.add(alias, var, true);
	}

	public void addAllEnforceRestriction(VarDefinitions definitions) {
		this.removeVarsWithSameNameAndType(definitions);
		for (String varName : definitions.vars.keySet()) {
			for (Variable var : definitions.vars.get(varName)) {
				this.addUnrestricted(varName, var);
			}
		}
	}

	private void removeVarsWithSameNameAndType(VarDefinitions definitions) {
		for (String name : this.vars.keySet()) {
			Set<Variable> nameVars = new HashSet<>();
			for (Variable v : this.vars.get(name)) {
				if (!this.containsVarOfType(definitions.vars, name, v.getVarType())) {
					nameVars.add(v);
				}
			}
			this.vars.put(name, nameVars);
		}
	}

	public void addAllUnrestricted(VarDefinitions definitions) {
		for (String name : definitions.vars.keySet()) {
			for (Variable v : definitions.vars.get(name)) {
				this.addUnrestricted(name, v);
			}
		}
	}

	public Set<Variable> getAsSet() {
		Set<Variable> defVars = new HashSet<>();
		this.vars.values().forEach(defVars::addAll);
		return defVars;
	}

	public Set<Variable> getAsSetOfType(VarType varType) {
		return this.getAsSet().stream()
				.filter(var -> var.getVarType().equals(varType))
				.collect(Collectors.toSet());
	}

	public Set<String> getNames() {
		return this.vars.keySet();
	}

	public Set<Variable> getVarsWithName(String name) {
		assert this.vars.containsKey(name);
		return this.vars.get(name);
	}

	@Override
	public void unlink() {
		for (Set<Variable> vars : this.vars.values()) {
			vars.forEach(Variable::unlink);
		}
		this.vars.clear();
	}

	private void add(String alias, Variable var, boolean unrestricted) {
		if (!this.vars.containsKey(alias)) {
			this.vars.put(alias, new HashSet<>());
		}
		if (unrestricted || !this.definedVarOfTypeExists(alias, var.getVarType())) {
			this.vars.get(alias).add(var);
		}
	}

	private boolean definedVarOfTypeExists(String name, VarType varType) {
		return this.containsVarOfType(this.vars, name, varType);
	}

	private boolean containsVarOfType(Map<String, Set<Variable>> data, String name, VarType varType) {
		if (data.containsKey(name)) {
			Set<Variable> sameNameVars = data.get(name);
			for (Variable sameNameVar : sameNameVars) {
				if (sameNameVar.getVarType() == varType) {
					return true;
				}
			}
		}
		return false;
	}
}
