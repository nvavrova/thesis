package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 20-10-2015
 */
public abstract class ContentContainer {

	protected final String name;

	//variables
	//this is a set because of the special situation of defining a class and instance variable at the same time, with the same name
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

	public String getName() {
		return this.name;
	}

	public Set<Variable> getReferencedGlobals() {
		Set<Variable> r = this.getReferencedVariables().stream()
				.filter(v -> v.getVarType() == VarType.GLOBAL)
				.collect(Collectors.toSet());
		this.getChildren().forEach(c -> r.addAll(c.getReferencedGlobals()));
		return r;
	}

	public Set<Variable> getDefinedVariables() {
		Set<Variable> defVars = new HashSet<>();
		for (Set<Variable> vars : this.definedVars.values()) {
			defVars.addAll(vars);
		}
		return defVars;
	}

	public Set<Variable> getReferencedVariables() {
		Set<Variable> refVars = new HashSet<>();
		for (Set<Variable> vars : this.referencedVars.values()) {
			refVars.addAll(vars);
		}
		return refVars;
	}

	public Set<Subroutine> getDefinedSubroutines() {
		return this.getDefinedSubroutinesByName().values().stream().collect(Collectors.toSet());
	}

	public Map<String, Subroutine> getDefinedSubroutinesByName() {
		Map<String, Subroutine> subroutines = new HashMap<>();
		subroutines.putAll(this.definedSubroutines);
		for (ContentContainer cc : this.getChildren()) {
			Map<String, Subroutine> childClasses = cc.getDefinedSubroutinesByName();
			for (String key : childClasses.keySet()) {
				subroutines.put(cc.getName() + '.' + key, childClasses.get(key));
			}
		}
		return subroutines;
	}

	public Set<String> getCalledSubroutineNames() {
		Set<String> r = new HashSet<>();
		r.addAll(this.calledSubroutineNames);
		this.getChildren().forEach(c -> r.addAll(c.getCalledSubroutineNames()));
		return r;
	}

	public Set<Class> getDefinedClasses() {
		return this.getDefinedClassesByName().values().stream().collect(Collectors.toSet());
	}

	public Map<String, Class> getDefinedClassesByName() {
		Map<String, Class> classes = new HashMap<>();
		classes.putAll(this.definedClasses);
		for (ContentContainer cc : this.getChildren()) {
			Map<String, Class> childClasses = cc.getDefinedClassesByName();
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

	public void resolveGlobalReferences(Set<Variable> globals) {
		//TODO: fix
	}

	public Integer publicVariablesCount() {
		Long count = this.getDefinedVariables().stream()
				.filter(v -> v.isPublic()).count();
		return count.intValue();
	}

	public Integer privateVariablesCount() {
		Long count = this.getDefinedVariables().stream()
				.filter(v -> v.isPrivate()).count();
		return count.intValue();
	}

	public Integer subroutineCount() {
		return this.definedSubroutines.size();
	}

	public Integer publicSubroutineCount() {
		Long l = this.definedSubroutines.values().stream()
				.filter(Subroutine::isPrivate)
				.count();
		return l.intValue();
	}

	public Integer accessorsCount() {
		Long c = this.definedSubroutines.values().stream()
				.filter(Subroutine::isAccessor)
				.count();
		return c.intValue();
	}

	public Integer definedGlobalsCount() {
		Long l = this.getDefinedVariables().stream()
				.filter(var -> var.getVarType() == VarType.GLOBAL)
				.count();
		return l.intValue();
	}

	public Boolean usesGlobals() {
		return this.referencedGlobalsCount() > 0;
	}

	public Integer referencedGlobalsCount() {
		Long c = this.getReferencedVariables().stream()
				.filter(v -> v.getVarType() == VarType.GLOBAL)
				.count();
		return c.intValue();
	}

	public Integer subroutinesWithNoParamsCount() {
		Long count = this.definedSubroutines.values().stream()
				.filter(m -> m.paramCount() == 0)
				.count();
		return count.intValue();
	}

	public Set<Class> getReferencedClasses() {
		return this.referencedClasses.values().stream().collect(Collectors.toSet());
	}

	public void resolveDependencies(Scope scope) {
		for (String clsAlias : scope.definedClasses.keySet()) {
			if (this.getCalledSubroutineNames().contains(clsAlias)) {
				this.referencedClasses.put(clsAlias, scope.definedClasses.get(clsAlias));
			}
		}
		for (String methodAlias : scope.definedSubroutines.keySet()) {
			if (this.getCalledSubroutineNames().contains(methodAlias)) {
				this.calledSubroutines.put(methodAlias, scope.definedSubroutines.get(methodAlias));
			}
		}
		for (String varAlias : scope.definedVars.keySet()) {
			if (this.referencedVarNames.contains(varAlias)) {
				if (!this.referencedVars.containsKey(varAlias)) {
					this.referencedVars.put(varAlias, new HashSet<>());
				}
				for (Variable var : scope.definedVars.get(varAlias)) {
					this.addVariableDefinition(varAlias, var);
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
