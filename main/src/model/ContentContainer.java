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
				.filter(v -> v.getVarType() == VarType.GLOBAL)
				.collect(Collectors.toSet());
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
		for (String clsAlias : scope.definedClasses.keySet()) {
			if (this.calledSubroutineNames.contains(clsAlias)) {
				this.referencedClasses.put(clsAlias, scope.definedClasses.get(clsAlias));
			}
		}
		for (String methodAlias : scope.definedSubroutines.keySet()) {
			if (this.calledSubroutineNames.contains(methodAlias)) {
				this.calledSubroutines.put(methodAlias, scope.definedSubroutines.get(methodAlias));
			}
		}
		for (String varAlias : scope.definedVars.keySet()) {
			if (this.referencedVarNames.contains(varAlias)) {
				for (Variable var : scope.definedVars.get(varAlias)) {
					this.addVariableReference(varAlias, var);
				}
			}
		}

		Scope classScope = scope.copy();
		classScope.addToScope(this.name, this, false);
		for (Class cls : this.definedClasses.values()) {
			cls.resolveDependencies(classScope);
		}

		Scope methodScope = scope.copy();
		methodScope.addToScope("", this, true);
		for (Subroutine m : this.definedSubroutines.values()) {
			m.resolveDependencies(methodScope);
		}
	}
}
