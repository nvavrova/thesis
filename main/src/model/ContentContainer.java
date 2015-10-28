package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 20-10-2015
 */
public abstract class ContentContainer {

	protected final String name;

	//variables
	//this is a set instead of a single variable
	//because of the special situation of defining a class and instance variable at the same time (with the same name)
	protected final Map<String, Set<Variable>> definedVars;
	protected final Map<String, Set<Variable>> referencedVars;

	protected final Set<String> referencedVarNames;
	//subroutines
	protected final Map<String, Subroutine> definedSubroutines;
	protected final Map<String, Subroutine> calledSubroutines;

	protected final Set<String> calledSubroutineNames;
	//classes
	protected final Map<String, Class> definedClasses;
	protected final Map<String, Class> referencedClasses;

	public ContentContainer(String name) {
		this.name = name;

		this.definedVars = new HashMap<>();
		this.referencedVars = new HashMap<>();
		this.referencedVarNames = new HashSet<>();

		this.definedSubroutines = new HashMap<>();
		this.calledSubroutines = new HashMap<>();
		this.calledSubroutineNames = new HashSet<>();

		this.definedClasses = new HashMap<>();
		this.referencedClasses = new HashMap<>();
	}


	//-----------------------------------------------------------------------------------------------------\\
	//---------------------------------------------- GETTERS ----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\

	public String getName() {
		return this.name;
	}

	public Set<Variable> getDefinedVariables() {
		Set<Variable> defVars = new HashSet<>();
		this.definedVars.values().forEach(defVars::addAll);
		return defVars;
	}

	public Set<Variable> getDefinedVariablesOfType(VarType varType) {
		return this.getDefinedVariables().stream()
				.filter(var -> var.getVarType() == varType)
				.collect(Collectors.toSet());
	}

	public Set<Subroutine> getDefinedSubroutines() {
		return this.definedSubroutines.values().stream().collect(Collectors.toSet());
	}

	public Set<Class> getDefinedClassesInclSubclasses() {
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

	public Set<Variable> getReferencedVariables() {
		Set<Variable> refVars = new HashSet<>();
		this.referencedVars.values().forEach(refVars::addAll);
		this.getChildren().forEach(c -> refVars.addAll(c.getReferencedVariables()));
		return refVars;
	}

	public Set<Variable> getReferencedGlobals() {
		return this.getReferencedVariables().stream()
				.filter(v -> v.getVarType() == VarType.GLOBAL || test(v))// || (v.getVarType() == VarType.LOCAL && !v.isInAncestorLine(this)))
				.collect(Collectors.toSet());
	}

	private boolean test(Variable v) {
		if (v.getVarType() == VarType.LOCAL) {
			return !v.isInAncestorLine(this);
		}
		return false;
	}

	public Set<Class> getReferencedClasses() {
		Set<Class> classes = this.referencedClasses.values().stream().collect(Collectors.toSet());
		this.getChildren().forEach(c -> classes.addAll(c.getReferencedClasses()));
		return classes;
	}

	public Integer subroutinesWithNoParamsCount() {
		Long count = this.definedSubroutines.values().stream()
				.filter(m -> m.paramCount() == 0)
				.count();
		return count.intValue();
	}

	//-----------------------------------------------------------------------------------------------------\\
	//---------------------------------------------- ADDERS -----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	public void addVariableDefinition(Variable var) {
		this.addVariableDefinition(var.getName(), var);
	}

	public void addVariableReference(String varName) {
		this.referencedVarNames.add(varName);
	}

	public void addSubroutineDefinition(Subroutine subroutine) {
		this.definedSubroutines.put(subroutine.getName(), subroutine);
	}

	public void addSubroutineCall(String subroutineName) {
		this.calledSubroutineNames.add(subroutineName);
	}

	public void addClassDefinition(Class cls) {
		this.definedClasses.put(cls.getName(), cls);
	}

	protected void addVariableReference(String name, Variable var) {
		if (!this.referencedVars.containsKey(name)) {
			this.referencedVars.put(name, new HashSet<>());
		}
		this.referencedVars.get(name).add(var);
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

	//-----------------------------------------------------------------------------------------------------\\
	//----------------------------------------------- OTHER -----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	public void resolveDependencies(Scope scope) {
		this.resolveDependencies("", scope);

		Scope classScope = scope.copy();
		classScope.addToScope(this.name, this, false);
		for (Class cls : this.definedClasses.values()) {
			if (!cls.equals(this)) {
				cls.resolveDependencies(classScope);
			}
		}

		Scope methodScope = scope.copy();
		methodScope.addToScope("", this, true);
		for (Subroutine m : this.definedSubroutines.values()) {
			if (!m.equals(this)) {
				m.resolveDependencies(methodScope);
			}
		}
	}

	private void resolveDependencies(String prefix, ContentContainer scopePart) {
		if (scopePart.equals(this)) {
			return;
		}
		for (String alias : scopePart.definedClasses.keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			if (this.calledSubroutineNames.contains(prefixedAlias)) {
				this.referencedClasses.put(prefixedAlias, scopePart.definedClasses.get(alias));
			}
			this.resolveDependencies(prefixedAlias, scopePart.definedClasses.get(alias));
		}

		for (String alias : scopePart.definedSubroutines.keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			if (this.calledSubroutineNames.contains(prefixedAlias)) {
				this.calledSubroutines.put(prefixedAlias, scopePart.definedSubroutines.get(alias));
			}
			this.resolveDependencies(prefixedAlias, scopePart.definedSubroutines.get(alias));
		}

		for (String alias : scopePart.definedVars.keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			if (this.referencedVarNames.contains(prefixedAlias)) {
				for (Variable var : scopePart.definedVars.get(alias)) {
					this.addVariableReference(prefixedAlias, var);
				}
			}
		}
	}

	private String prefix(String str, String prefix) {
		return prefix.equals("") ? str : prefix + "." + str;
	}

	public abstract boolean isInAncestorLine(ContentContainer container);
}
